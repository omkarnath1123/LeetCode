package com.leetcrawler.app.model;

import java.nio.file.Path;

public record GeneratedDocument(
        String frontendId,
        String title,
        Path outputPath
) {}
