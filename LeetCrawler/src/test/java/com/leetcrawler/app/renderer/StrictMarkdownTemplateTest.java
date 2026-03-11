package com.leetcrawler.app.renderer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StrictMarkdownTemplateTest {

    @Test
    void template_shouldContainAllPlaceholders() {
        String t = StrictMarkdownTemplate.TEMPLATE;
        assertTrue(t.contains("{{FRONTEND_ID}}"));
        assertTrue(t.contains("{{TITLE}}"));
        assertTrue(t.contains("{{TITLE_SLUG}}"));
        assertTrue(t.contains("{{DIFFICULTY}}"));
        assertTrue(t.contains("{{LOCK}}"));
        assertTrue(t.contains("{{PROBLEM_STATEMENT}}"));
        assertTrue(t.contains("{{PATTERN}}"));
        assertTrue(t.contains("{{ASSET_PATH}}"));
        assertTrue(t.contains("{{INTUITION}}"));
        assertTrue(t.contains("{{TIME_COMPLEXITY}}"));
        assertTrue(t.contains("{{SPACE_COMPLEXITY}}"));
        assertTrue(t.contains("{{JAVA_CODE}}"));
    }
}
