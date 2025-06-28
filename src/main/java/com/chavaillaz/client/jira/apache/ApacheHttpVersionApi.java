package com.chavaillaz.client.jira.apache;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.hc.client5.http.async.methods.SimpleRequestBuilder.delete;
import static org.apache.hc.client5.http.async.methods.SimpleRequestBuilder.get;
import static org.apache.hc.client5.http.async.methods.SimpleRequestBuilder.post;
import static org.apache.hc.client5.http.async.methods.SimpleRequestBuilder.put;
import static org.apache.hc.core5.http.ContentType.APPLICATION_JSON;

import java.util.concurrent.CompletableFuture;

import com.chavaillaz.client.common.security.Authentication;
import com.chavaillaz.client.jira.api.VersionApi;
import com.chavaillaz.client.jira.domain.Version;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;

public class ApacheHttpVersionApi extends AbstractApacheHttpClient implements VersionApi {

    /**
     * Creates a new {@link VersionApi} using Apache HTTP client.
     *
     * @param client         The Apache HTTP client to use
     * @param baseUrl        The URL of Jira
     * @param authentication The authentication information
     */
    public ApacheHttpVersionApi(CloseableHttpAsyncClient client, String baseUrl, Authentication authentication) {
        super(client, baseUrl, authentication);
    }

    @Override
    public CompletableFuture<Version> addVersion(Version version) {
        return sendAsync(requestBuilder(post(), URL_VERSION, EMPTY)
                .setBody(serialize(version), APPLICATION_JSON), Version.class);
    }

    @Override
    public CompletableFuture<Version> getVersion(String versionId) {
        return sendAsync(requestBuilder(get(), URL_VERSION, versionId), Version.class);
    }

    @Override
    public CompletableFuture<Version> updateVersion(Version version) {
        return sendAsync(requestBuilder(put(), URL_VERSION, version.getId())
                .setBody(serialize(version), APPLICATION_JSON), Version.class);
    }

    @Override
    public CompletableFuture<Void> deleteVersion(String versionId) {
        return sendAsync(requestBuilder(delete(), URL_VERSION, versionId), Void.class);
    }

}
