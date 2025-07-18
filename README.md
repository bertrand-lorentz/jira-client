# Jira Client

[Jira]: https://docs.atlassian.com/software/jira/docs/api/REST/9.12.7/
[CompletableFuture]: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/CompletableFuture.html
[Jackson]: https://github.com/FasterXML/jackson
[JavaHttp]: https://openjdk.org/groups/net/httpclient/intro.html
[ApacheHttp]: https://hc.apache.org/httpcomponents-client-5.4.x/
[OkHttp]: https://square.github.io/okhttp/
[Vertx]: https://vertx.io/docs/vertx-web-client/java/

![Dependency Check](https://github.com/chavaillaz/jira-client/actions/workflows/snyk.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.chavaillaz/jira-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.chavaillaz/jira-client)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This library allows you to interact with a Jira instance and choose which HTTP client to use. It is also easily
extendable, allowing you to have custom clients and issues classes. All requests are performed asynchronously and 
return a [CompletableFuture][CompletableFuture]. The serialization and deserialization of domain objects are performed 
using [Jackson][Jackson].

Presently, it supports the following HTTP clients:

- [Java HTTP client][JavaHttp] (included since Java 11)
- [Apache HTTP client][ApacheHttp] 5.5
- [OkHttp client][OkHttp] 4.12
- [Vert.x client][Vertx] 4.5

Note that this library requires at least **Java 17** and has been tested with a [Jira instance version 9.12][Jira].

## Installation

The dependency is available in maven central (see badge for version):

```xml
<dependency>
    <groupId>com.chavaillaz</groupId>
    <artifactId>jira-client</artifactId>
</dependency>
```

Don't forget to also declare the HTTP client you want to use as a dependency (see below), as it is only indicated as 
optional in the project, to avoid gathering them all together despite the fact that only one is needed.

### Java HTTP client

It does not require any dependency (already in Java).

### Apache HTTP client

It requires the following dependency:

```xml
<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.5.x</version>
</dependency>
```

### OkHttp client

It requires the following dependency:

```xml
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>4.12.x</version>
</dependency>
```

### Vert.x client

It requires the following dependency:

```xml
<dependency>
    <groupId>io.vertx</groupId>
    <artifactId>vertx-web-client</artifactId>
    <version>4.5.x</version>
</dependency>
```

## Usage

### Features

- **[IssueApi](src/main/java/com/chavaillaz/client/jira/api/IssueApi.java) -
  Everything for issues, including comments, links, transitions, attachments and work logs**
  - Issues
    - `addIssue(Issue issue)`
    - `getIssue(String issueKey)`
    - `getIssue(String issueKey, Set<IssueExpand> flags)`
    - `getIssue(String issueKey, Set<IssueExpand> flags, Set<String> fields)`
    - `getIssueOptional(String issueKey)`
    - `getIssueOptional(String issueKey, Set<IssueExpand> flags)`
    - `getIssueOptional(String issueKey, Set<IssueExpand> flags, Set<String> fields)`
    - `updateIssue(Issue issue)`
    - `deleteIssue(String issueKey)`
    - `assignIssue(String issueKey, User user)`
    - `unassignIssue(String issueKey)`
    - `getTransitions(String issueKey)`
    - `doTransition(String issueKey, IssueTransition transition)`
  - Comments
    - `getComments(String issueKey)`
    - `getComments(String issueKey, Integer startAt, Integer maxResults)`
    - `getComments(String issueKey, Integer startAt, Integer maxResults, Set<CommentExpand> flags)`
    - `getComment(String issueKey, String id)`
    - `getComment(String issueKey, String id, Set<CommentExpand> flags)`
    - `getCommentOptional(String issueKey, String id)`
    - `getCommentOptional(String issueKey, String id, Set<CommentExpand> flags)`
    - `addComment(String issueKey, Comment comment)`
    - `updateComment(String issueKey, Comment comment)`
    - `deleteComment(String issueKey, String id)`
  - Votes
    - `addVote(String issueKey)`
    - `getVotes(String issueKey)`
  - Watchers
    - `getWatchers(String issueKey)`
    - `addWatcher(String issueKey, String username)`
    - `deleteWatcher(String issueKey, String username)`
  - Work Logs
    - `addWorkLog(String issueKey, WorkLog workLog)`
    - `getWorkLogs(String issueKey)`
    - `getWorkLog(String issueKey, String id)`
    - `getWorkLogOptional(String issueKey, String id)`
    - `updateWorkLog(String issueKey, WorkLog workLog)`
    - `deleteWorkLog(String issueKey, String id)`
  - Attachments
    - `getAttachment(String id)`
    - `getAttachmentOptional(String id)`
    - `getAttachmentContent(String url)`
    - `addAttachment(String issueKey, File... files)`
    - `deleteAttachment(String id)`
  - Remote Links
    - `getRemoteLinks(String issueKey)`
    - `getRemoteLink(String issueKey, String id)`
    - `getRemoteLinkOptional(String issueKey, String id)`
    - `addRemoteLink(String issueKey, RemoteLink remoteLink)`
    - `updateRemoteLink(String issueKey, RemoteLink remoteLink)`
    - `deleteRemoteLink(String issueKey, String id)`
  - Issue Links
    - `getIssueLink(String id)`
    - `getIssueLinkOptional(String id)`
    - `addIssueLink(Link link)`
    - `deleteIssueLink(String id)`
- **[SearchApi](src/main/java/com/chavaillaz/client/jira/api/SearchApi.java) -
  Everything for searches, including filters**
  - Searches
    - `getIssues(Set<String> issuesKey)`
    - `getIssues(Set<String> issuesKey, Set<String> fields)`
    - `searchIssues(String jql)`
    - `searchIssues(String jql, Integer startAt, Integer maxResults)`
    - `searchIssues(String jql, Integer startAt, Integer maxResults, Set<IssueExpand> expand)`
    - `searchIssues(String jql, Integer startAt, Integer maxResults, Set<IssueExpand> expand, Set<String> fields)`
  - Filters
    - `addFilter(Filter filter)`
    - `getFilter(String id)`
    - `getFilterOptional(String id)`
    - `getFavoriteFilters()`
    - `updateFilter(String id, Filter filter)`
    - `deleteFilter(String id)`
- **[ProjectApi](src/main/java/com/chavaillaz/client/jira/api/ProjectApi.java) -
  Everything for projects, including components, versions, statuses and roles**
  - `addProject(ProjectChange project)`
  - `getProjects()`
  - `getProjects(boolean includeArchived, String expand)`
  - `getProject(String projectKey)`
  - `getProjectOptional(String projectKey)`
  - `getProjectVersions(String projectKey)`
  - `getProjectComponents(String projectKey)`
  - `getProjectStatuses(String projectKey)`
  - `getProjectRoles(String projectKey)`
  - `getProjectRole(String projectKey, String roleId)`
  - `updateProject(String projectKey, ProjectChange project)`
  - `deleteProject(String projectKey)`
- **[UserApi](src/main/java/com/chavaillaz/client/jira/api/UserApi.java) -
  Everything for users**
  - `getUsers(String search)`
  - `getUsers(String search, Integer startAt, Integer maxResults, Boolean includeInactive)`
  - `getAssignableUsers(String search, String key)`
  - `getAssignableUsers(String search, String key, Integer startAt, Integer maxResults, Boolean includeInactive)`
  - `getUser(String username)`
  - `getUserOptional(String username)`
  - `getCurrentUser()`
- **[VersionApi](src/main/java/com/chavaillaz/client/jira/api/VersionApi.java) -
  Everything for versions**
  - `addVersion(Version version)`
  - `getVersion(String versionId)`
  - `updateVersion(Version version)`
  - `deleteVersion(String versionId)`

### Client instantiation

Instantiate the Jira client of your choice by giving your Jira instance URL. Depending on your needs, you may also want 
to add authentication with a personal access token (PAT) using `withTokenAuthentication` or with username and password 
using `withUserAuthentication` method. If you need to connect via a proxy, you can specify it with `withProxy`. Below 
an example for each HTTP client:

- [JavaHttpJiraClient](src/main/java/com/chavaillaz/client/jira/java/JavaHttpJiraClient.java)

```java
JiraClient<Issue> client = JiraClient.javaClient("https://jira.mycompany.com")
    .withUserAuthentication("myUsername","myPassword")
    .withProxy("http://proxy.mycompany.com:1234");
```

- [ApacheHttpJiraClient](src/main/java/com/chavaillaz/client/jira/apache/ApacheHttpJiraClient.java)

```java
JiraClient<Issue> client = JiraClient.apacheClient("https://jira.mycompany.com")
    .withUserAuthentication("myUsername","myPassword")
    .withProxy("http://proxy.mycompany.com:1234");
```

- [OkHttpJiraClient](src/main/java/com/chavaillaz/client/jira/okhttp/OkHttpJiraClient.java)

```java
JiraClient<Issue> client = JiraClient.okHttpClient("https://jira.mycompany.com")
    .withUserAuthentication("myUsername","myPassword")
    .withProxy("http://proxy.mycompany.com:1234");
```

- [VertxHttpJiraClient](src/main/java/com/chavaillaz/client/jira/vertx/VertxHttpJiraClient.java)

```java
JiraClient<Issue> client = JiraClient.vertxClient("https://jira.mycompany.com")
    .withUserAuthentication("myUsername","myPassword")
    .withProxy("http://proxy.mycompany.com:1234");
```

From this `JiraClient` you will then be able to get the desired APIs described in the [feature chapter](#features).

### Iterable results

When requesting a list of elements, the client returns an object encapsulating this list, sometimes with more pieces of
information depending on the Jira endpoint called. From this object, you can iterate directly on its child elements as
in the example below:

```java
Consumer<Filter> deleteFilter = filter -> client.getSearchClient().deleteFilter(filter.getId());
client.getSearchClient().getFavoriteFilters()
    .thenAccept(filters -> filters.forEach(deleteFilter));
```

### Issue custom fields

If your project has custom fields for issues, you have two choices in order to get and set them.

#### Use attribute containing all custom fields

First possibility, simply use `issue.getFields().getCustomFields().get("customfield_12345")` to get the field and
`issue.getFields().getCustomFields().put("customfield_12345", value)` to set a value for this field.

#### Reimplement fields and issue class

Second possibility, create a new class extending `Fields` to hide the custom fields identifiers and interact with Jira
in a more understandable way:

```java
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompanyFields extends Fields {

    @JsonProperty("customfield_12345")
    private User businessEngineer;
    
    ...

}
```

A new `Issue` class will also be needed to override the `fields` attribute:

```java
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompanyIssue extends Issue {

    private CompanyFields fields;

}
```

Finally, specify it when instantiating the `JiraClient`, for example with Apache HTTP client:

```java
JiraClient<CompanyIssue> client = new ApacheHttpJiraClient<>("https://jira.mycompany.com", CompanyIssue.class);
```

Please be aware that both solutions cannot live together as when you have attributes in the class for your fields, it 
will then not be in the `customFields` map (containing all the unmapped fields).

## Contributing

If you have a feature request or found a bug, you can:

- Write an issue
- Create a pull request

### Code style

The code style is based on the default one from IntelliJ, except for the following cases:

#### Imports

Imports are ordered as follows:

- All static imports in a block
- Java non-static imports in a block
- All non-static imports in a block

A single blank line separates every block. Within each block the imported names appear in alphabetical sort order.
Wildcard imports, static or otherwise, are not used.

#### Arrangements

The attributes of **domain classes** are ordered alphabetically. 

## License

This project is under Apache 2.0 License.