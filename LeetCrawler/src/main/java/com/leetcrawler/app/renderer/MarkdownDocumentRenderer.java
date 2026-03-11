package com.leetcrawler.app.renderer;

import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.util.HtmlToMarkdownConverter;
import com.leetcrawler.app.util.MarkdownFormatter;

import java.util.List;

public class MarkdownDocumentRenderer {

    public String render(QuestionDetail question, SubmissionDetail submission, String pattern) {
        String body = HtmlToMarkdownConverter.convert(question.htmlContent());
        String code = submission == null || submission.code().isBlank()
                ? "// Accepted Java solution unavailable"
                : submission.code();

        String asset = patternAsset(pattern, body);

        String markdown = """
                Link: [%s. %s](https://leetcode.com/problems/%s/description/) <br>
                Tag : **%s**<br>
                Lock: **%s**

                %s

                **Solution:**

                - [x] [[%s]]

                ## Visual Reference

                ![Pattern Visual](../Assets/%s)

                ## Detailed Intuition

                - Model the problem using the right data structure or state representation.
                - Maintain one clear invariant while iterating or traversing.
                - Handle boundary cases first, then apply the main transition consistently.

                **Time Complexity** : O(n)<br>
                **Space Complexity** : O(n)

                ```java
                %s
                ```
                """.formatted(
                question.frontendId(),
                question.title(),
                question.titleSlug(),
                question.difficulty().display(),
                question.accountType().display(),
                body,
                pattern,
                asset,
                code
        );

        return MarkdownFormatter.normalize(markdown)
                .replace("**Input:**", "Input:")
                .replace("**Output:**", "Output:")
                .replace("**Explanation:**", "Explanation:");
    }

    private String patternAsset(String pattern, String body) {
        String key = (pattern + " " + body).toLowerCase();
        if (key.contains("dynamic programming")) return "DP_fibonacci.svg";
        if (key.contains("graph")) return "PATTERN_graph.svg";
        if (key.contains("tree")) return "PATTERN_tree.svg";
        if (key.contains("binary search")) return "PATTERN_binary_search.svg";
        if (key.contains("stack")) return "PATTERN_stack.svg";
        return "PATTERN_array.svg";
    }

    public String inferPattern(List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return "Array";
        }

        return tags.stream()
                .map(String::toLowerCase)
                .map(tag -> switch (tag) {
                    case "depth-first search" -> "Depth First Search";
                    case "breadth-first search" -> "Breath First Search";
                    case "dynamic programming" -> "Dynamic Programming";
                    case "binary search" -> "Binary Search";
                    case "union find" -> "Union-Find";
                    case "hash table" -> "Hash Table";
                    case "linked list" -> "Linked-List";
                    default -> null;
                })
                .filter(java.util.Objects::nonNull)
                .findFirst()
                .orElse(tags.getFirst());
    }
}
