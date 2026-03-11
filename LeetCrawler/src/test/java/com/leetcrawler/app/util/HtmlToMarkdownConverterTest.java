package com.leetcrawler.app.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlToMarkdownConverterTest {

    @Test
    void convert_shouldHandleNullAndBlank() {
        assertEquals("", HtmlToMarkdownConverter.convert(null));
        assertEquals("", HtmlToMarkdownConverter.convert("   "));
    }

    @Test
    void convert_shouldTransformBasicHtml() {
        String html = "<p>Hello <strong>X</strong></p><ul><li>One</li></ul><pre><code>int a=1;</code></pre>";
        String out = HtmlToMarkdownConverter.convert(html);
        assertTrue(out.contains("Hello **X**"));
        assertTrue(out.contains("- One"));
        assertTrue(out.contains("```"));
        assertTrue(out.contains("int a=1;"));
    }
}
