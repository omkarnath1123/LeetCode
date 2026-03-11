package com.leetcrawler.app.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record AppConfig(
        String leetcodeSession,
        Path repoRoot,
        Path problemsDir,
        int pageSize
) {
    public static final String DOT_ENV_FILE = ".env";
    public static final String ENV_LEETCODE_SESSION = "LEETCODE_SESSION";
    public static final String ENV_LEET_REPO_ROOT = "LEET_REPO_ROOT";
    public static final String PROBLEMS_DIR_NAME = "Problems";
    public static final String DEFAULT_REPO_ROOT = "/path/to/LeetCode";
    public static final int DEFAULT_PAGE_SIZE = 50;
    public static final String JVM_PROP_LEETCODE_SESSION = "leetcode.session";
    public static final String JVM_PROP_LEET_REPO_ROOT = "leet.repo.root";
    public static final String JVM_PROP_PAGE_SIZE = "leet.page.size";

    public static AppConfig fromEnv() {
        Map<String, String> envFile = loadDotEnv(Path.of(DOT_ENV_FILE));

        String session = firstNonBlank(
                System.getenv(ENV_LEETCODE_SESSION),
                envFile.get(ENV_LEETCODE_SESSION),
                ""
        ).trim();

        Path root = Path.of(firstNonBlank(
                System.getenv(ENV_LEET_REPO_ROOT),
                envFile.get(ENV_LEET_REPO_ROOT),
                DEFAULT_REPO_ROOT
        ));

        return new AppConfig(session, root, root.resolve(PROBLEMS_DIR_NAME), DEFAULT_PAGE_SIZE);
    }

    public Map<String, String> toEnvironmentVariables() {
        return Map.of(
                ENV_LEETCODE_SESSION, leetcodeSession,
                ENV_LEET_REPO_ROOT, repoRoot.toString()
        );
    }

    public List<String> toJvmArgs() {
        return List.of(
                "-D" + JVM_PROP_LEETCODE_SESSION + "=" + leetcodeSession,
                "-D" + JVM_PROP_LEET_REPO_ROOT + "=" + repoRoot,
                "-D" + JVM_PROP_PAGE_SIZE + "=" + pageSize
        );
    }

    private static Map<String, String> loadDotEnv(Path envPath) {
        if (!Files.exists(envPath)) {
            return Map.of();
        }
        try {
            return Files.readAllLines(envPath).stream()
                    .map(String::trim)
                    .filter(line -> !line.isBlank())
                    .filter(line -> !line.startsWith("#"))
                    .filter(line -> line.contains("="))
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(parts -> parts[0].trim(), parts -> parts[1].trim(), (a, b) -> b));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read .env", e);
        }
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return "";
    }
}
