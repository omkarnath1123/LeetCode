package com.leetcrawler.app.renderer;

/**
 * Single source of truth for markdown output format.
 *
 * Edit TEMPLATE freely.
 * Keep placeholders unchanged; renderer will replace them.
 */
public final class StrictMarkdownTemplate {

    private StrictMarkdownTemplate() {}

    public static final String TEMPLATE = """
            Link: [{{FRONTEND_ID}}. {{TITLE}}](https://leetcode.com/problems/{{TITLE_SLUG}}/description/) <br>
            Tag : **{{DIFFICULTY}}**<br>
            Lock: **{{LOCK}}**

            {{PROBLEM_STATEMENT}}

            **Solution:**

            - [x] [[{{PATTERN}}]]

            ## Visual Reference

            ![Pattern Visual]({{ASSET_PATH}})

            ## Detailed Intuition

            {{INTUITION}}

            **Time Complexity** : {{TIME_COMPLEXITY}}<br>
            **Space Complexity** : {{SPACE_COMPLEXITY}}

            ```java
            {{JAVA_CODE}}
            ```
            """;
}
