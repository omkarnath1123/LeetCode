package com.leetcrawler.app.util;

import java.util.regex.Pattern;

public final class HtmlToMarkdownConverter {
    private static final Pattern TAG_PATTERN = Pattern.compile("<[^>]+>");

    private HtmlToMarkdownConverter() {}

    public static String convert(String html) {
        if (html == null || html.isBlank()) {
            return "";
        }
        String text = html
                .replace("\u00A0", " ")
                .replace("<br>", "\n")
                .replace("<br/>", "\n")
                .replace("<br />", "\n")
                .replaceAll("(?is)<pre>(.*?)</pre>", "```\n$1\n```")
                .replaceAll("(?is)<li[^>]*>", "- ")
                .replaceAll("(?is)</li>", "\n")
                .replaceAll("(?is)</?(ul|ol|p|div|section)[^>]*>", "\n")
                .replaceAll("(?is)<strong[^>]*>(.*?)</strong>", "**$1**")
                .replaceAll("(?is)<b[^>]*>(.*?)</b>", "**$1**")
                .replaceAll("(?is)<em[^>]*>(.*?)</em>", "*$1*")
                .replaceAll("(?is)<i[^>]*>(.*?)</i>", "*$1*")
                .replaceAll("(?is)<code[^>]*>(.*?)</code>", "`$1`");

        text = TAG_PATTERN.matcher(text).replaceAll("");
        text = text.replaceAll("\n{3,}", "\n\n").trim();
        return text;
    }
}
