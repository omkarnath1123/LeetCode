package com.leetcrawler.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExistingProblemIndex {
    private static final Pattern PREFIX_ID = Pattern.compile("^(\\d+)\\.");

    public Set<String> loadExistingIds(Path repoRoot) {
        try (Stream<Path> stream = Files.walk(repoRoot)) {
            return stream
                    .filter(path -> path.toString().endsWith(".md"))
                    .filter(path -> !path.toString().contains("/New/"))
                    .map(path -> path.getFileName().toString())
                    .map(this::extractId)
                    .flatMap(java.util.Optional::stream)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to scan markdown files", e);
        }
    }

    java.util.Optional<String> extractId(String filename) {
        Matcher matcher = PREFIX_ID.matcher(filename);
        if (!matcher.find()) {
            return java.util.Optional.empty();
        }
        String id = matcher.group(1).replaceFirst("^0+", "");
        return java.util.Optional.of(id.isEmpty() ? "0" : id);
    }
}
