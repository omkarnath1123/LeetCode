package com.leetcrawler.app.service;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExistingProblemIndexTest {

    @Test
    void loadExistingIds_shouldReadNumberedMarkdown(@org.junit.jupiter.api.io.TempDir Path tempDir) throws Exception {
        Files.writeString(tempDir.resolve("1. A.md"), "x");
        Files.writeString(tempDir.resolve("README.md"), "x");
        Path newDir = tempDir.resolve("New");
        Files.createDirectories(newDir);
        Files.writeString(newDir.resolve("2. B.md"), "x");

        ExistingProblemIndex index = new ExistingProblemIndex();
        Set<String> ids = index.loadExistingIds(tempDir);

        assertTrue(ids.contains("1"));
        assertFalse(ids.contains("2"));
        assertEquals(1, ids.size());
    }

    @Test
    void extractId_shouldHandleLeadingZeroes() {
        ExistingProblemIndex index = new ExistingProblemIndex();
        assertEquals("12", index.extractId("0012. X.md").orElseThrow());
        assertEquals("0", index.extractId("0000. X.md").orElseThrow());
        assertTrue(index.extractId("X.md").isEmpty());
    }
}
