package com.leetcrawler.app.config;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppConfigTest {

    @Test
    @SuppressWarnings("unchecked")
    void loadDotEnv_shouldParseKeyValuePairs(@org.junit.jupiter.api.io.TempDir Path tempDir) throws Exception {
        Path env = tempDir.resolve(".env");
        Files.writeString(env, "#c\nA=1\nB=2\nB=3\nINVALID\n");

        Method method = AppConfig.class.getDeclaredMethod("loadDotEnv", Path.class);
        method.setAccessible(true);
        Map<String, String> map = (Map<String, String>) method.invoke(null, env);

        assertEquals("1", map.get("A"));
        assertEquals("3", map.get("B"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void loadDotEnv_shouldReturnEmptyWhenMissing(@org.junit.jupiter.api.io.TempDir Path tempDir) throws Exception {
        Method method = AppConfig.class.getDeclaredMethod("loadDotEnv", Path.class);
        method.setAccessible(true);
        Map<String, String> map = (Map<String, String>) method.invoke(null, tempDir.resolve("missing.env"));
        assertTrue(map.isEmpty());
    }

    @Test
    void fromEnv_shouldCreateUsableConfig() {
        AppConfig cfg = AppConfig.fromEnv();
        assertEquals(AppConfig.DEFAULT_PAGE_SIZE, cfg.pageSize());
        assertEquals(cfg.repoRoot().resolve(AppConfig.PROBLEMS_DIR_NAME), cfg.problemsDir());
    }

    @Test
    void shouldGenerateEnvironmentVariablesAndJvmArgs() {
        AppConfig cfg = new AppConfig("token", Path.of("/tmp/repo"), Path.of("/tmp/repo/Problems"), 25);

        Map<String, String> env = cfg.toEnvironmentVariables();
        assertEquals("token", env.get(AppConfig.ENV_LEETCODE_SESSION));
        assertEquals("/tmp/repo", env.get(AppConfig.ENV_LEET_REPO_ROOT));

        var jvmArgs = cfg.toJvmArgs();
        assertTrue(jvmArgs.contains("-D" + AppConfig.JVM_PROP_LEETCODE_SESSION + "=token"));
        assertTrue(jvmArgs.contains("-D" + AppConfig.JVM_PROP_LEET_REPO_ROOT + "=/tmp/repo"));
        assertTrue(jvmArgs.contains("-D" + AppConfig.JVM_PROP_PAGE_SIZE + "=25"));
    }

    @Test
    void firstNonBlank_shouldReturnFallback() throws Exception {
        Method m = AppConfig.class.getDeclaredMethod("firstNonBlank", String[].class);
        m.setAccessible(true);
        String out = (String) m.invoke(null, (Object) new String[]{" ", null, "x"});
        assertEquals("x", out);
        String empty = (String) m.invoke(null, (Object) new String[]{" ", null, ""});
        assertEquals("", empty);
    }

    @Test
    void loadDotEnv_shouldThrowForUnreadablePath(@org.junit.jupiter.api.io.TempDir Path tempDir) throws Exception {
        Path dir = tempDir.resolve(".env");
        Files.createDirectories(dir);
        Method method = AppConfig.class.getDeclaredMethod("loadDotEnv", Path.class);
        method.setAccessible(true);
        assertThrows(Exception.class, () -> method.invoke(null, dir));
    }
}
