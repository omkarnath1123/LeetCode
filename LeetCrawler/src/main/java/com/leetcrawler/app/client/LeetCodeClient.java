package com.leetcrawler.app.client;

import com.leetcrawler.app.model.ProgressQuestion;
import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.SubmissionSummary;

import java.util.List;

public interface LeetCodeClient {
    List<ProgressQuestion> fetchSolvedQuestions(int pageSize);
    QuestionDetail fetchQuestionDetail(String titleSlug);
    List<SubmissionSummary> fetchAcceptedSubmissions(String titleSlug, int limit);
    SubmissionDetail fetchSubmissionDetail(int submissionId);
}
