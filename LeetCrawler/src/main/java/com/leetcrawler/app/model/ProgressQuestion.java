package com.leetcrawler.app.model;

import com.leetcrawler.app.model.enums.Difficulty;

public record ProgressQuestion(
        String frontendId,
        String title,
        String titleSlug,
        Difficulty difficulty
) {}
