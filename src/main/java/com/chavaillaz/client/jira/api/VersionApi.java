package com.chavaillaz.client.jira.api;

import java.util.concurrent.CompletableFuture;

import com.chavaillaz.client.jira.domain.Version;

public interface VersionApi extends AutoCloseable {

    String URL_VERSION = "version/{0}";

    /**
     * Adds a version to a project.
     *
     * @param version The version to add
     * @return A {@link CompletableFuture} with the version
     */
    CompletableFuture<Version> addVersion(Version version);

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
     * @param version The version to update
     * @return A {@link CompletableFuture} with the updated version
     */
    CompletableFuture<Version> updateVersion(Version version);

    /**
     * Deletes a version.
     *
     * @param versionId The version identifier
     * @return A {@link CompletableFuture} without content
     */
    CompletableFuture<Void> deleteVersion(String versionId);

}
