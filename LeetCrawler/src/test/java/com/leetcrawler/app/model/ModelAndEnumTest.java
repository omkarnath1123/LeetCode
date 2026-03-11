package com.leetcrawler.app.model;

import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelAndEnumTest {

    @Test
    void enums_shouldReturnDisplayNames() {
        assertEquals("Easy", Difficulty.EASY.display());
        assertEquals("Premium", AccountType.PREMIUM.display());
    }

    @Test
    void records_shouldExposeValues() {
        GeneratedDocument doc = new GeneratedDocument("1", "Two Sum", Path.of("x"));
        ProgressQuestion pq = new ProgressQuestion("1", "Two Sum", "two-sum", Difficulty.EASY);
        QuestionDetail qd = new QuestionDetail("1", "Two Sum", "two-sum", Difficulty.EASY, AccountType.NORMAL, "<p>x</p>", List.of("Array"));
        SubmissionDetail sd = new SubmissionDetail(1, "class Solution{}", "1 ms", "40 MB");
        SubmissionSummary ss = new SubmissionSummary(1, "java", "Accepted", 100L);

        assertEquals("Two Sum", doc.title());
        assertEquals("two-sum", pq.titleSlug());
        assertEquals("<p>x</p>", qd.htmlContent());
        assertEquals("40 MB", sd.memoryDisplay());
        assertEquals(100L, ss.timestamp());
    }
}
