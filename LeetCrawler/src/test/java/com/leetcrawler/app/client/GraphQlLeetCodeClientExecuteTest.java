package com.leetcrawler.app.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphQlLeetCodeClientExecuteTest {

    @Test
    void execute_shouldParseSuccessResponse() {
        HttpClient stub = new StubHttpClient("{\"data\":{\"x\":1}}", null, false);
        GraphQlLeetCodeClient client = new GraphQlLeetCodeClient(stub, new ObjectMapper(), "token");

        var node = client.execute("{}");

        assertEquals(1, node.path("data").path("x").asInt());
    }

    @Test
    void execute_shouldWrapIoException() {
        HttpClient stub = new StubHttpClient(null, new IOException("boom"), false);
        GraphQlLeetCodeClient client = new GraphQlLeetCodeClient(stub, new ObjectMapper(), "token");
        assertThrows(IllegalStateException.class, () -> client.execute("{}"));
    }

    @Test
    void execute_shouldWrapInterruptedAndSetFlag() {
        HttpClient stub = new StubHttpClient(null, null, true);
        GraphQlLeetCodeClient client = new GraphQlLeetCodeClient(stub, new ObjectMapper(), "token");
        assertThrows(IllegalStateException.class, () -> client.execute("{}"));
        Thread.interrupted();
    }

    private static final class StubHttpClient extends HttpClient {
        private final String body;
        private final IOException ioException;
        private final boolean interrupted;

        private StubHttpClient(String body, IOException ioException, boolean interrupted) {
            this.body = body;
            this.ioException = ioException;
            this.interrupted = interrupted;
        }

        @Override
        public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) throws IOException, InterruptedException {
            if (ioException != null) throw ioException;
            if (interrupted) throw new InterruptedException("interrupted");
            @SuppressWarnings("unchecked")
            T castBody = (T) body;
            return new HttpResponse<T>() {
                @Override public int statusCode() { return 200; }
                @Override public HttpRequest request() { return request; }
                @Override public Optional<HttpResponse<T>> previousResponse() { return Optional.empty(); }
                @Override public HttpHeaders headers() { return HttpHeaders.of(java.util.Map.of(), (a, b) -> true); }
                @Override public T body() { return castBody; }
                @Override public Optional<javax.net.ssl.SSLSession> sslSession() { return Optional.empty(); }
                @Override public URI uri() { return URI.create("https://leetcode.com/graphql/"); }
                @Override public HttpClient.Version version() { return HttpClient.Version.HTTP_1_1; }
            };
        }

        @Override public Optional<java.net.CookieHandler> cookieHandler() { return Optional.empty(); }
        @Override public Optional<java.time.Duration> connectTimeout() { return Optional.empty(); }
        @Override public Redirect followRedirects() { return Redirect.NEVER; }
        @Override public Optional<java.net.ProxySelector> proxy() { return Optional.empty(); }
        @Override public javax.net.ssl.SSLContext sslContext() { return null; }
        @Override public javax.net.ssl.SSLParameters sslParameters() { return null; }
        @Override public Optional<java.net.Authenticator> authenticator() { return Optional.empty(); }
        @Override public Version version() { return Version.HTTP_1_1; }
        @Override public Optional<java.util.concurrent.Executor> executor() { return Optional.empty(); }
        @Override public <T> java.util.concurrent.CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) { throw new UnsupportedOperationException(); }
        @Override public <T> java.util.concurrent.CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler, HttpResponse.PushPromiseHandler<T> pushPromiseHandler) { throw new UnsupportedOperationException(); }
    }
}
