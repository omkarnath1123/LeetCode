package com.leetcrawler.app.renderer;

import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkdownDocumentRendererTest {

    @Test
    void render_shouldIncludeExpectedSections() {
        MarkdownDocumentRenderer renderer = new MarkdownDocumentRenderer();
        QuestionDetail question = new QuestionDetail(
                "9999",
                "Sample Problem",
                "sample-problem",
                Difficulty.MEDIUM,
                AccountType.NORMAL,
                "<p>Hello <strong>world</strong></p>",
                List.of("Array")
        );
        SubmissionDetail submission = new SubmissionDetail(1, "class Solution {}", "1 ms", "40 MB");

        String markdown = renderer.render(question, submission, "Array");

        assertTrue(markdown.contains("Link: [9999. Sample Problem]"));
        assertTrue(markdown.contains("Tag : **Medium**"));
        assertTrue(markdown.contains("Lock: **Normal**"));
        assertTrue(markdown.contains("```java"));
        assertTrue(markdown.contains("../Assets/PATTERN_array.svg"));
    }

    @Test
    void render_shouldFallbackWhenSubmissionMissing() {
        MarkdownDocumentRenderer renderer = new MarkdownDocumentRenderer();
        QuestionDetail question = new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>x</p>", List.of("Graph"));

        String markdown = renderer.render(question, null, "Graph Traversal");

        assertTrue(markdown.contains("Accepted Java solution unavailable"));
        assertTrue(markdown.contains("../Assets/PATTERN_graph.svg"));

        String blankCode = renderer.render(question, new SubmissionDetail(1, "   ", "1", "1"), "Graph Traversal");
        assertTrue(blankCode.contains("Accepted Java solution unavailable"));
    }

    @Test
    void render_shouldSelectAllAssetsByBodyOrPatternKeywords() {
        MarkdownDocumentRenderer renderer = new MarkdownDocumentRenderer();
        QuestionDetail q = new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>tree and stack and binary search and dynamic programming</p>", List.of("Tree"));
        SubmissionDetail s = new SubmissionDetail(1, "code", "1", "1");

        assertTrue(renderer.render(q, s, "Dynamic Programming").contains("DP_fibonacci.svg"));
        assertTrue(renderer.render(new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>graph</p>", List.of()), s, "Array").contains("PATTERN_graph.svg"));
        assertTrue(renderer.render(new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>tree</p>", List.of()), s, "Array").contains("PATTERN_tree.svg"));
        assertTrue(renderer.render(new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>binary search</p>", List.of()), s, "Array").contains("PATTERN_binary_search.svg"));
        assertTrue(renderer.render(new QuestionDetail("1", "A", "a", Difficulty.EASY, AccountType.NORMAL, "<p>stack</p>", List.of()), s, "Array").contains("PATTERN_stack.svg"));
    }

    @Test
    void inferPattern_shouldCoverAllMappingsAndFallbacks() {
        MarkdownDocumentRenderer renderer = new MarkdownDocumentRenderer();
        assertTrue(renderer.inferPattern(List.of("depth-first search")).contains("Depth First Search"));
        assertTrue(renderer.inferPattern(List.of("breadth-first search")).contains("Breath First Search"));
        assertTrue(renderer.inferPattern(List.of("dynamic programming")).contains("Dynamic Programming"));
        assertTrue(renderer.inferPattern(List.of("binary search")).contains("Binary Search"));
        assertTrue(renderer.inferPattern(List.of("union find")).contains("Union-Find"));
        assertTrue(renderer.inferPattern(List.of("hash table")).contains("Hash Table"));
        assertTrue(renderer.inferPattern(List.of("linked list")).contains("Linked-List"));
        assertTrue(renderer.inferPattern(List.of("custom" )).contains("custom"));
        assertTrue(renderer.inferPattern(List.of()).contains("Array"));
        assertTrue(renderer.inferPattern(null).contains("Array"));
    }
}
