package com.chavaillaz.client.jira.okhttp;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.concurrent.CompletableFuture;

import com.chavaillaz.client.common.security.Authentication;
import com.chavaillaz.client.jira.api.VersionApi;
import com.chavaillaz.client.jira.domain.Version;
import okhttp3.OkHttpClient;

public class OkHttpVersionApi extends AbstractOkHttpClient implements VersionApi {

    /**
     * Creates a new {@link VersionApi} using OkHttp client.
     *
     * @param client         The OkHttp client to use
     * @param baseUrl        The URL of Jira
     * @param authentication The authentication information
     */
    public OkHttpVersionApi(OkHttpClient client, String baseUrl, Authentication authentication) {
        super(client, baseUrl, authentication);
    }

    @Override
    public CompletableFuture<Version> addVersion(Version version) {
        return sendAsync(requestBuilder(URL_VERSION, EMPTY).post(body(version)), Version.class);
    }

    @Override
    public CompletableFuture<Version> getVersion(String versionId) {
        return sendAsync(requestBuilder(URL_VERSION, versionId).get(), Version.class);
    }

    @Override
    public CompletableFuture<Version> updateVersion(Version version) {
        return sendAsync(requestBuilder(URL_VERSION, version.getId()).put(body(version)), Version.class);
    }

    @Override
    public CompletableFuture<Void> deleteVersion(String versionId) {
        return sendAsync(requestBuilder(URL_VERSION, versionId).delete(), Void.class);
    }

}
