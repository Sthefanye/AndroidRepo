package com.example.androidrepo.data.model

import com.google.gson.annotations.SerializedName

data class PullRequestsResponse(
    @SerializedName("url")
    val url: String,
    @SerializedName("id")
    val id: Long?,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("body")
    val body: String?
)
