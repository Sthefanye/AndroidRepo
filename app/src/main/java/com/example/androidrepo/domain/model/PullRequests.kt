package com.example.androidrepo.domain.model

import com.example.androidrepo.data.model.UserResponse
import com.google.gson.annotations.SerializedName

data class PullRequests (
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("body")
    val body: String?,
)
