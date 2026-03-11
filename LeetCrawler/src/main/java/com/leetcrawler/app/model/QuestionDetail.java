package com.leetcrawler.app.model;

import com.leetcrawler.app.model.enums.AccountType;
import com.leetcrawler.app.model.enums.Difficulty;

import java.util.List;

public record QuestionDetail(
        String frontendId,
        String title,
        String titleSlug,
        Difficulty difficulty,
        AccountType accountType,
        String htmlContent,
        List<String> topicTags
) {}
