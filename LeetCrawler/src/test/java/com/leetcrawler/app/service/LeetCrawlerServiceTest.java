package com.leetcrawler.app.service;

import com.leetcrawler.app.client.LeetCodeClient;
import com.leetcrawler.app.model.GeneratedDocument;
import com.leetcrawler.app.model.ProgressQuestion;
import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.SubmissionSummary;
import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;
import com.leetcrawler.app.renderer.MarkdownDocumentRenderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeetCrawlerServiceTest {

    @Test
    void crawlAndGenerate_shouldWriteOnlyMissingFiles(@TempDir Path tempDir) throws Exception {
        Path repoRoot = tempDir;
        Path problems = repoRoot.resolve("Problems");
        Files.createDirectories(problems);
        Files.writeString(problems.resolve("1. Existing.md"), "existing");

        LeetCodeClient client = new FakeClient();
        LeetCrawlerService service = new LeetCrawlerService(
                client,
                new ExistingProblemIndex(),
                new MarkdownDocumentRenderer()
        );

        List<GeneratedDocument> generated = service.crawlAndGenerate(repoRoot, problems, 50);

        assertEquals(1, generated.size());
        assertTrue(Files.exists(problems.resolve("2. Missing.md")));
    }

    @Test
    void normalizedId_shouldHandleLeadingZeroes() {
        LeetCrawlerService service = new LeetCrawlerService(new FakeClient(), new ExistingProblemIndex(), new MarkdownDocumentRenderer());
        assertEquals("1", service.normalizedId("001"));
        assertEquals("0", service.normalizedId("000"));
    }

    @Test
    void crawlAndGenerate_shouldHandleNoSubmissions(@TempDir Path tempDir) {
        LeetCodeClient client = new LeetCodeClient() {
            @Override public List<ProgressQuestion> fetchSolvedQuestions(int pageSize) { return List.of(new ProgressQuestion("3", "NoSub", "nosub", Difficulty.EASY)); }
            @Override public QuestionDetail fetchQuestionDetail(String titleSlug) { return new QuestionDetail("3", "NoSub", "nosub", Difficulty.EASY, AccountType.NORMAL, "<p>x</p>", List.of("Array")); }
            @Override public List<SubmissionSummary> fetchAcceptedSubmissions(String titleSlug, int limit) { return List.of(); }
            @Override public SubmissionDetail fetchSubmissionDetail(int submissionId) { throw new AssertionError("should not be called"); }
        };
        LeetCrawlerService service = new LeetCrawlerService(client, new ExistingProblemIndex(), new MarkdownDocumentRenderer());
        var generated = service.crawlAndGenerate(tempDir, tempDir.resolve("Problems"), 10);
        assertEquals(1, generated.size());
    }

    @Test
    void crawlAndGenerate_shouldFallbackToFirstSubmissionWhenNoJava(@TempDir Path tempDir) {
        LeetCodeClient client = new LeetCodeClient() {
            @Override public List<ProgressQuestion> fetchSolvedQuestions(int pageSize) { return List.of(new ProgressQuestion("4", "PyOnly", "py-only", Difficulty.EASY)); }
            @Override public QuestionDetail fetchQuestionDetail(String titleSlug) { return new QuestionDetail("4", "PyOnly", "py-only", Difficulty.EASY, AccountType.NORMAL, "<p>x</p>", List.of("Array")); }
            @Override public List<SubmissionSummary> fetchAcceptedSubmissions(String titleSlug, int limit) { return List.of(new SubmissionSummary(77, "python", "Accepted", 100)); }
            @Override public SubmissionDetail fetchSubmissionDetail(int submissionId) { return new SubmissionDetail(submissionId, "print(1)", "1", "1"); }
        };
        LeetCrawlerService service = new LeetCrawlerService(client, new ExistingProblemIndex(), new MarkdownDocumentRenderer());
        var generated = service.crawlAndGenerate(tempDir, tempDir.resolve("Problems"), 10);
        assertEquals(1, generated.size());
    }

    @Test
    void crawlAndGenerate_shouldThrowOnWriteFailure(@TempDir Path tempDir) throws Exception {
        Path problemsAsFile = tempDir.resolve("Problems");
        Files.writeString(problemsAsFile, "not a directory");
        LeetCrawlerService service = new LeetCrawlerService(new FakeClient(), new ExistingProblemIndex(), new MarkdownDocumentRenderer());
        assertThrows(IllegalStateException.class, () -> service.crawlAndGenerate(tempDir, problemsAsFile, 10));
    }

    private static class FakeClient implements LeetCodeClient {
        @Override
        public List<ProgressQuestion> fetchSolvedQuestions(int pageSize) {
            return List.of(
                    new ProgressQuestion("1", "Existing", "existing", Difficulty.EASY),
                    new ProgressQuestion("2", "Missing", "missing", Difficulty.MEDIUM)
            );
        }

        @Override
        public QuestionDetail fetchQuestionDetail(String titleSlug) {
            return new QuestionDetail(
                    "2",
                    "Missing",
                    "missing",
                    Difficulty.MEDIUM,
                    AccountType.NORMAL,
                    "<p>Question</p>",
                    List.of("Array")
            );
        }

        @Override
        public List<SubmissionSummary> fetchAcceptedSubmissions(String titleSlug, int limit) {
            return List.of(new SubmissionSummary(10, "java", "Accepted", 1L));
        }

        @Override
        public SubmissionDetail fetchSubmissionDetail(int submissionId) {
            return new SubmissionDetail(10, "class Solution {}", "1 ms", "40 MB");
        }
    }
}
