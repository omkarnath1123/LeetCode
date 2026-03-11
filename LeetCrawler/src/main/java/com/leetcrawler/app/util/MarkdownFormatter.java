package com.leetcrawler.app.util;

public final class MarkdownFormatter {
    private MarkdownFormatter() {}

    public static String normalize(String input) {
        if (input == null) return "";
        String text = input
                .replace("\u00A0", " ")
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .replaceAll("```\\n{2,}", "```\n")
                .replaceAll("\\n{2,}```", "\n```")
                .replaceAll("\n{3,}", "\n\n");

        return text.strip() + "\n";
    }
}
