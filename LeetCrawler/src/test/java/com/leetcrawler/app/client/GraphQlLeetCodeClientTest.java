package com.leetcrawler.app.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.SubmissionSummary;
import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GraphQlLeetCodeClientTest {

    @Test
    void fetchSolvedQuestions_shouldPaginateAndStop() {
        AtomicInteger calls = new AtomicInteger();
        GraphQlLeetCodeClient client = new FakeClient(calls,
                "{\"data\":{\"userProgressQuestionList\":{\"questions\":[{\"frontendId\":\"1\",\"title\":\"A\",\"titleSlug\":\"a\",\"difficulty\":\"EASY\"}]}}}",
                "{\"data\":{\"userProgressQuestionList\":{\"questions\":[]}}}");

        var out = client.fetchSolvedQuestions(1);

        assertEquals(1, out.size());
        assertEquals("A", out.getFirst().title());
        assertTrue(calls.get() >= 2);
    }

    @Test
    void fetchSolvedQuestions_shouldBreakWhenPageSmallerThanLimit() {
        AtomicInteger calls = new AtomicInteger();
        GraphQlLeetCodeClient client = new FakeClient(calls,
                "{\"data\":{\"userProgressQuestionList\":{\"questions\":[{\"frontendId\":\"1\",\"title\":\"A\",\"titleSlug\":\"a\",\"difficulty\":\"EASY\"}]}}}");
        var out = client.fetchSolvedQuestions(2);
        assertEquals(1, out.size());
        assertEquals(1, calls.get());
    }

    @Test
    void fetchSolvedQuestions_shouldHandleNonArrayQuestionsNode() {
        GraphQlLeetCodeClient client = new FakeClient(new AtomicInteger(),
                "{\"data\":{\"userProgressQuestionList\":{\"questions\":{\"oops\":1}}}}"
        );
        var out = client.fetchSolvedQuestions(10);
        assertTrue(out.isEmpty());
    }

    @Test
    void fetchQuestionDetail_shouldParseFields() {
        GraphQlLeetCodeClient client = new FakeClient(new AtomicInteger(),
                "{\"data\":{\"question\":{\"questionFrontendId\":\"10\",\"title\":\"X\",\"titleSlug\":\"x\",\"difficulty\":\"MEDIUM\",\"isPaidOnly\":true,\"content\":\"<p>ok</p>\",\"topicTags\":[{\"name\":\"Graph\"}]}}}");

        QuestionDetail detail = client.fetchQuestionDetail("x");

        assertEquals("10", detail.frontendId());
        assertEquals(Difficulty.MEDIUM, detail.difficulty());
        assertEquals(AccountType.PREMIUM, detail.accountType());
        assertEquals(List.of("Graph"), detail.topicTags());
    }

    @Test
    void fetchQuestionDetail_shouldMapNormalAccountWhenNotPaid() {
        GraphQlLeetCodeClient client = new FakeClient(new AtomicInteger(),
                "{\"data\":{\"question\":{\"questionFrontendId\":\"11\",\"title\":\"Y\",\"titleSlug\":\"y\",\"difficulty\":\"EASY\",\"isPaidOnly\":false,\"content\":\"<p>ok</p>\",\"topicTags\":[]}}}");
        QuestionDetail detail = client.fetchQuestionDetail("y");
        assertEquals(AccountType.NORMAL, detail.accountType());
    }

    @Test
    void fetchAcceptedSubmissions_shouldSortDescByTimestamp() {
        GraphQlLeetCodeClient client = new FakeClient(new AtomicInteger(),
                "{\"data\":{\"questionSubmissionList\":{\"submissions\":[{\"id\":2,\"lang\":\"java\",\"statusDisplay\":\"Accepted\",\"timestamp\":100},{\"id\":1,\"lang\":\"python\",\"statusDisplay\":\"Accepted\",\"timestamp\":200}]}}}");

        List<SubmissionSummary> list = client.fetchAcceptedSubmissions("x", 10);

        assertEquals(2, list.size());
        assertEquals(1, list.getFirst().id());
    }

    @Test
    void fetchSubmissionDetail_shouldParse() {
        GraphQlLeetCodeClient client = new FakeClient(new AtomicInteger(),
                "{\"data\":{\"submissionDetails\":{\"id\":7,\"code\":\"class Solution{}\",\"runtimeDisplay\":\"1 ms\",\"memoryDisplay\":\"40 MB\"}}}");

        SubmissionDetail d = client.fetchSubmissionDetail(7);

        assertEquals(7, d.submissionId());
        assertEquals("1 ms", d.runtimeDisplay());
    }

    private static final class FakeClient extends GraphQlLeetCodeClient {
        private static final ObjectMapper MAPPER = new ObjectMapper();
        private final AtomicInteger callCounter;
        private final String[] responses;

        private FakeClient(AtomicInteger callCounter, String... responses) {
            super(null, new ObjectMapper(), "dummy");
            this.callCounter = callCounter;
            this.responses = responses;
        }

        @Override
        protected JsonNode execute(String jsonBody) {
            int i = callCounter.getAndIncrement();
            String payload = responses[Math.min(i, responses.length - 1)];
            try {
                return MAPPER.readTree(payload);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
