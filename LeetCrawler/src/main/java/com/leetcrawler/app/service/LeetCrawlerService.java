package com.leetcrawler.app.service;

import com.leetcrawler.app.client.LeetCodeClient;
import com.leetcrawler.app.model.GeneratedDocument;
import com.leetcrawler.app.model.ProgressQuestion;
import com.leetcrawler.app.model.QuestionDetail;
import com.leetcrawler.app.model.SubmissionDetail;
import com.leetcrawler.app.model.SubmissionSummary;
import com.leetcrawler.app.renderer.MarkdownDocumentRenderer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class LeetCrawlerService {
    private final LeetCodeClient client;
    private final ExistingProblemIndex existingProblemIndex;
    private final MarkdownDocumentRenderer renderer;

    public LeetCrawlerService(LeetCodeClient client,
                              ExistingProblemIndex existingProblemIndex,
                              MarkdownDocumentRenderer renderer) {
        this.client = client;
        this.existingProblemIndex = existingProblemIndex;
        this.renderer = renderer;
    }

    public List<GeneratedDocument> crawlAndGenerate(Path repoRoot, Path problemsDir, int pageSize) {
        Set<String> existingIds = existingProblemIndex.loadExistingIds(repoRoot);

        List<ProgressQuestion> missing = client.fetchSolvedQuestions(pageSize).stream()
                .filter(q -> !existingIds.contains(normalizedId(q.frontendId())))
                .sorted(Comparator.comparingInt(q -> Integer.parseInt(normalizedId(q.frontendId()))))
                .toList();

        return missing.stream()
                .map(this::hydrate)
                .map(bundle -> write(problemsDir, bundle.question, bundle.submission, bundle.pattern))
                .toList();
    }

    String normalizedId(String id) {
        String normalized = id.replaceFirst("^0+", "");
        return normalized.isEmpty() ? "0" : normalized;
    }

    private Bundle hydrate(ProgressQuestion pq) {
        QuestionDetail question = client.fetchQuestionDetail(pq.titleSlug());
        List<SubmissionSummary> submissions = client.fetchAcceptedSubmissions(pq.titleSlug(), 30);

        SubmissionSummary preferred = submissions.stream()
                .filter(sub -> "java".equalsIgnoreCase(sub.language()))
                .findFirst()
                .orElse(submissions.stream().findFirst().orElse(null));

        SubmissionDetail detail = preferred == null ? null : client.fetchSubmissionDetail(preferred.id());
        String pattern = renderer.inferPattern(question.topicTags());
        return new Bundle(question, detail, pattern);
    }

    private GeneratedDocument write(Path problemsDir,
                                    QuestionDetail question,
                                    SubmissionDetail submission,
                                    String pattern) {
        String fileName = "%s. %s.md".formatted(question.frontendId(), question.title());
        Path outputPath = problemsDir.resolve(fileName);
        String markdown = renderer.render(question, submission, pattern);

        try {
            Files.createDirectories(problemsDir);
            Files.writeString(outputPath, markdown);
            return new GeneratedDocument(question.frontendId(), question.title(), outputPath);
        } catch (IOException e) {
            throw new IllegalStateException("Failed writing markdown: " + outputPath, e);
        }
    }

    private record Bundle(QuestionDetail question, SubmissionDetail submission, String pattern) {}
}
