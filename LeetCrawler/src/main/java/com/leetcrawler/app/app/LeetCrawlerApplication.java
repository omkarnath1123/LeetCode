package com.leetcrawler.app.app;

import com.leetcrawler.app.client.GraphQlLeetCodeClient;
import com.leetcrawler.app.client.LeetCodeClient;
import com.leetcrawler.app.config.AppConfig;
import com.leetcrawler.app.model.GeneratedDocument;
import com.leetcrawler.app.renderer.MarkdownDocumentRenderer;
import com.leetcrawler.app.service.ExistingProblemIndex;
import com.leetcrawler.app.service.LeetCrawlerService;

import java.util.List;

public class LeetCrawlerApplication {
    public static void main(String[] args) {
        AppConfig config = AppConfig.fromEnv();
        LeetCodeClient client = new GraphQlLeetCodeClient(config.leetcodeSession());
        run(config, client);
    }

    static List<GeneratedDocument> run(AppConfig config, LeetCodeClient client) {
        if (config.leetcodeSession().isBlank()) {
            throw new IllegalArgumentException("LEETCODE_SESSION is required");
        }

        LeetCrawlerService service = new LeetCrawlerService(
                client,
                new ExistingProblemIndex(),
                new MarkdownDocumentRenderer()
        );

        List<GeneratedDocument> generated = service.crawlAndGenerate(
                config.repoRoot(),
                config.problemsDir(),
                config.pageSize()
        );

        System.out.println("Generated files: " + generated.size());
        generated.forEach(doc -> System.out.println("- " + doc.outputPath()));
        System.out.println("Suggested JVM args: " + String.join(" ", config.toJvmArgs()));
        System.out.println("Suggested env vars: " + config.toEnvironmentVariables());
        return generated;
    }
}
