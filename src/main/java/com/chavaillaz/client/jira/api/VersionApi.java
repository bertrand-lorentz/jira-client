package com.chavaillaz.client.jira.api;

import java.util.concurrent.CompletableFuture;

import com.chavaillaz.client.jira.domain.Version;

public interface VersionApi extends AutoCloseable {

    String URL_VERSION = "version/{0}";

    /**
     * Gets a specific version.
     *
     * @param versionId The version identifier
     * @return A {@link CompletableFuture} with the version
     */
    CompletableFuture<Version> getVersion(String versionId);

    /**
     * Updates a version.
     *
     * @param versionId The version identifier
     * @param version   The version to update
     * @return A {@link CompletableFuture} with the updated version
     */
    CompletableFuture<Version> updateVersion(Version version);
}
