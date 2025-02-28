package com.example.androidrepo.data.model

import com.google.gson.annotations.SerializedName

data class PullRequestsResponse (
    @SerializedName("url")
    val url: String,
    @SerializedName("id")
    val id: Float,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("diff_url")
    val diffUrl: String,
    @SerializedName("patch_url")
    val patchUrl: String,
    @SerializedName("issue_url")
    val issueUrl: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("locked")
    val locked: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("body")
    val body: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("closed_at")
    val closedAt: String?,
    @SerializedName("merged_at")
    val mergedAt: String?,
    @SerializedName("merge_commit_sha")
    val mergeCommitSha: String,
    @SerializedName("assignee")
    val assignee: AssigneeResponse?,
    @SerializedName("assignees")
    val assignees: List<UserResponse>?,
    @SerializedName("requested_reviewers")
    val requestedReviewers: List<UserResponse>?,
    @SerializedName("requested_teams")
    val requestedTeams:  List<UserResponse>?,
    @SerializedName("labels")
    val labels:  List<LabelsResponse>?,
    @SerializedName("milestone")
    val milestone: String?,
    @SerializedName("draft")
    val draft: Boolean,
    @SerializedName("commits_url")
    val commitsUrl: String,
    @SerializedName("review_comments_url")
    val reviewCommentsUrl: String,
    @SerializedName("review_comment_url")
    val reviewCommentUrl: String,
    @SerializedName("comments_url")
    val commentsUrl: String,
    @SerializedName("statuses_url")
    val statusesUrl: String,
    @SerializedName("head")
    val head: HeadResponse?,
    @SerializedName("links")
    val links: LinksResponse,
    @SerializedName("author_association")
    val authorAssociation: String,
    @SerializedName("auto_merge")
    val autoMerge: String?,
    @SerializedName("active_lock_reason")
    val activeLockReason: String?,
) {
    data class AssigneeResponse(
        val login: String,
        @SerializedName("id")
        val id: Float,
        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("gravatar_id")
        val gravatarId: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("html_url")
        val htmlUrl: String,
        @SerializedName("followers_url")
        val followersUrl: String,
        @SerializedName("following_url")
        val followingUrl: String,
        @SerializedName("gists_url")
        val gistsUrl: String,
        @SerializedName("starred_url")
        val starredUrl: String,
        @SerializedName("subscriptions_url")
        val subscriptionsUrl: String,
        @SerializedName("organizations_url")
        val organizationsUrl: String,
        @SerializedName("repos_url")
        val reposUrl: String,
        @SerializedName("events_url")
        val eventsUrl: String,
        @SerializedName("received_events_url")
        val receivedEventsUrl: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("user_view_type")
        val userViewType: String,
        @SerializedName("site_admin")
        val siteAdmin: Boolean,
    )

    data class LabelsResponse(
        @SerializedName("id")
        val id: Float,
        @SerializedName("node_id")
        val nodeId: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("color")
        val color: String,
        @SerializedName("default")
        val default: Boolean,
        @SerializedName("description")
        val description: String,
    )

    data class LinksResponse(
        @SerializedName("self")
        val self: LinkResponse,
        @SerializedName("html")
        val html: LinkResponse,
        @SerializedName("issue")
        val issue: LinkResponse,
        @SerializedName("comments")
        val comments: LinkResponse,
        @SerializedName("review_comments")
        val reviewComments: LinkResponse,
        @SerializedName("review_comment")
        val reviewComment: LinkResponse,
        @SerializedName("commits")
        val commits: LinkResponse,
        @SerializedName("statuses")
        val statuses: LinkResponse
    )

    data class LinkResponse(
        @SerializedName("href")
        val href: String
    )

}
