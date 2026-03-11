package com.leetcrawler.app.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownFormatterTest {

    @Test
    void normalize_shouldHandleNull() {
        assertEquals("", MarkdownFormatter.normalize(null));
    }

    @Test
    void normalize_shouldCompressSpacingAndNormalizeLineEndings() {
        String input = "a\r\n\r\n\r\n```\n\ncode\n\n```\r\n";
        String out = MarkdownFormatter.normalize(input);
        assertEquals("a\n```\ncode\n```\n", out);
    }
}
