package com.leetcrawler.app.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leetcrawler.app.model.ProgressQuestion;
import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.SubmissionSummary;
import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

public class GraphQlLeetCodeClient implements LeetCodeClient {
    private static final URI GRAPHQL_URI = URI.create("https://leetcode.com/graphql/");
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_COOKIE = "Cookie";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String COOKIE_SESSION_PREFIX = "LEETCODE_SESSION=";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(60);
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String sessionCookie;

    public GraphQlLeetCodeClient(String sessionCookie) {
        this(HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build(), new ObjectMapper(), sessionCookie);
    }

    protected GraphQlLeetCodeClient(HttpClient httpClient, ObjectMapper objectMapper, String sessionCookie) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.sessionCookie = sessionCookie;
    }

    @Override
    public List<ProgressQuestion> fetchSolvedQuestions(int pageSize) {
        List<ProgressQuestion> output = new ArrayList<>();
        int skip = 0;
        while (true) {
            String body = """
                {"operationName":"userProgressQuestionList","query":"query userProgressQuestionList($filters: UserProgressQuestionListInput) { userProgressQuestionList(filters: $filters) { totalNum questions { frontendId title titleSlug difficulty } } }","variables":{"filters":{"questionStatus":"SOLVED","sortField":"NUM_SUBMITTED","sortOrder":"ASCENDING","skip":%d,"limit":%d}}}
                """.formatted(skip, pageSize).replace("\n", "");

            JsonNode data = execute(body).path("data").path("userProgressQuestionList");
            JsonNode questions = data.path("questions");
            if (!questions.isArray() || questions.isEmpty()) {
                break;
            }

            questions.forEach(node -> output.add(new ProgressQuestion(
                    node.path("frontendId").asText(),
                    node.path("title").asText(),
                    node.path("titleSlug").asText(),
                    Difficulty.valueOf(node.path("difficulty").asText())
            )));

            if (questions.size() < pageSize) {
                break;
            }
            skip += pageSize;
        }
        return output;
    }

    @Override
    public QuestionDetail fetchQuestionDetail(String titleSlug) {
        String body = """
            {"operationName":"questionData","query":"query questionData($titleSlug: String!) { question(titleSlug: $titleSlug) { questionFrontendId title titleSlug difficulty isPaidOnly content topicTags { name } } }","variables":{"titleSlug":"%s"}}
            """.formatted(titleSlug).replace("\n", "");

        JsonNode q = execute(body).path("data").path("question");
        List<String> tags = StreamSupport.stream(q.path("topicTags").spliterator(), false)
                .map(tag -> tag.path("name").asText())
                .toList();

        return new QuestionDetail(
                q.path("questionFrontendId").asText(),
                q.path("title").asText(),
                q.path("titleSlug").asText(),
                Difficulty.valueOf(q.path("difficulty").asText()),
                q.path("isPaidOnly").asBoolean() ? AccountType.PREMIUM : AccountType.NORMAL,
                q.path("content").asText(),
                tags
        );
    }

    @Override
    public List<SubmissionSummary> fetchAcceptedSubmissions(String titleSlug, int limit) {
        String body = """
            {"operationName":"submissionList","query":"query submissionList($offset: Int!, $limit: Int!, $lastKey: String, $questionSlug: String!, $lang: Int, $status: Int) { questionSubmissionList(offset: $offset, limit: $limit, lastKey: $lastKey, questionSlug: $questionSlug, lang: $lang, status: $status) { submissions { id lang statusDisplay timestamp } } }","variables":{"offset":0,"limit":%d,"lastKey":null,"questionSlug":"%s","lang":null,"status":10}}
            """.formatted(limit, titleSlug).replace("\n", "");

        JsonNode submissions = execute(body)
                .path("data")
                .path("questionSubmissionList")
                .path("submissions");

        return StreamSupport.stream(submissions.spliterator(), false)
                .map(node -> new SubmissionSummary(
                        node.path("id").asInt(),
                        node.path("lang").asText(),
                        node.path("statusDisplay").asText(),
                        node.path("timestamp").asLong(0)
                ))
                .sorted(Comparator.comparingLong(SubmissionSummary::timestamp).reversed())
                .toList();
    }

    @Override
    public SubmissionDetail fetchSubmissionDetail(int submissionId) {
        String body = """
            {"operationName":"submissionDetails","query":"query submissionDetails($submissionId: Int!) { submissionDetails(submissionId: $submissionId) { id code runtimeDisplay memoryDisplay } }","variables":{"submissionId":%d}}
            """.formatted(submissionId).replace("\n", "");

        JsonNode node = execute(body).path("data").path("submissionDetails");
        return new SubmissionDetail(
                node.path("id").asInt(),
                node.path("code").asText(),
                node.path("runtimeDisplay").asText(),
                node.path("memoryDisplay").asText()
        );
    }

    protected JsonNode execute(String jsonBody) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(GRAPHQL_URI)
                .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)
                .header(HEADER_COOKIE, COOKIE_SESSION_PREFIX + sessionCookie)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .timeout(REQUEST_TIMEOUT)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readTree(response.body());
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Failed GraphQL call", e);
        }
    }
}
