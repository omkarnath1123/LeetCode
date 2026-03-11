package com.leetcrawler.app.model;

public record SubmissionDetail(
        int submissionId,
        String code,
        String runtimeDisplay,
        String memoryDisplay
) {}
