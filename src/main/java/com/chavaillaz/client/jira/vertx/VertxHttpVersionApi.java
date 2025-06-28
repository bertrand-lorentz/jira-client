package com.chavaillaz.client.jira.vertx;

import static io.vertx.core.http.HttpMethod.DELETE;
import static io.vertx.core.http.HttpMethod.GET;
import static io.vertx.core.http.HttpMethod.POST;
import static io.vertx.core.http.HttpMethod.PUT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.concurrent.CompletableFuture;

import com.chavaillaz.client.common.security.Authentication;
import com.chavaillaz.client.jira.api.VersionApi;
import com.chavaillaz.client.jira.domain.Version;
import io.vertx.ext.web.client.WebClient;

public class VertxHttpVersionApi extends AbstractVertxHttpClient implements VersionApi {

    /**
     * Creates a new {@link VersionApi} using Vert.x client.
     *
     * @param client         The Vert.x client to use
     * @param baseUrl        The URL of Jira
     * @param authentication The authentication information
     */
    public VertxHttpVersionApi(WebClient client, String baseUrl, Authentication authentication) {
        super(client, baseUrl, authentication);
    }

    @Override
    public CompletableFuture<Version> addVersion(Version version) {
        return handleAsync(requestBuilder(POST, URL_VERSION, EMPTY).sendBuffer(body(version)), Version.class);
    }

    @Override
    public CompletableFuture<Version> getVersion(String versionId) {
        return handleAsync(requestBuilder(GET, URL_VERSION, versionId).send(), Version.class);
    }

    @Override
    public CompletableFuture<Version> updateVersion(Version version) {
        return handleAsync(requestBuilder(PUT, URL_VERSION, version.getId()).sendBuffer(body(version)), Version.class);
    }

    @Override
    public CompletableFuture<Void> deleteVersion(String versionId) {
        return handleAsync(requestBuilder(DELETE, URL_VERSION, versionId).send(), Void.class);
    }

}
