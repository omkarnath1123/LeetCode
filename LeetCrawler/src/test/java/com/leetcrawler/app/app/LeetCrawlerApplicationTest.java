package com.leetcrawler.app.app;

import com.leetcrawler.app.client.LeetCodeClient;
import com.leetcrawler.app.config.AppConfig;
import com.leetcrawler.app.model.ProgressQuestion;
import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.SubmissionSummary;
import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeetCrawlerApplicationTest {

    @Test
    void run_shouldThrowWhenSessionMissing(@TempDir Path tempDir) {
        AppConfig cfg = new AppConfig("", tempDir, tempDir.resolve("Problems"), 10);
        assertThrows(IllegalArgumentException.class, () -> LeetCrawlerApplication.run(cfg, new FakeClient()));
    }

    @Test
    void run_shouldGenerateWithFakeClient(@TempDir Path tempDir) {
        AppConfig cfg = new AppConfig("token", tempDir, tempDir.resolve("Problems"), 10);
        var out = LeetCrawlerApplication.run(cfg, new FakeClient());
        assertEquals(1, out.size());
    }

    private static final class FakeClient implements LeetCodeClient {
        @Override
        public List<ProgressQuestion> fetchSolvedQuestions(int pageSize) {
            return List.of(new ProgressQuestion("1", "A", "a", Difficulty.EASY));
        }

        @Override
        public QuestionDetail fetchQuestionDetail(String titleSlug) {
            return new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>Hi</p>", List.of("Array"));
        }

        @Override
        public List<SubmissionSummary> fetchAcceptedSubmissions(String titleSlug, int limit) {
            return List.of(new SubmissionSummary(1, "java", "Accepted", 100));
        }

        @Override
        public SubmissionDetail fetchSubmissionDetail(int submissionId) {
            return new SubmissionDetail(1, "class Solution {}", "1 ms", "40 MB");
        }
    }
}
