package com.example.androidrepo.domain.model.pulls

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    val self: Link,
    @SerializedName("html")
    val html: Link,
    @SerializedName("issue")
    val issue: Link,
    @SerializedName("comments")
    val comments: Link,
    @SerializedName("review_comments")
    val reviewComments: Link,
    @SerializedName("review_comment")
    val reviewComment: Link,
    @SerializedName("commits")
    val commits: Link,
    @SerializedName("statuses")
    val statuses: Link
)

data class Link(
    @SerializedName("href")
    val href: String
)