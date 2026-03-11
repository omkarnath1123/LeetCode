package com.leetcrawler.app.model;

public record SubmissionSummary(
        int id,
        String language,
        String statusDisplay,
        long timestamp
) {}
