package com.example.androidrepo.data.model

import com.google.gson.annotations.SerializedName

data class HeadResponse(
    @SerializedName("label")
    val label: String,
    @SerializedName("ref")
    val ref: String,
    @SerializedName("sha")
    val sha: String,
    @SerializedName("user")
    val user: UserResponse?,
    @SerializedName("repo")
    val repo: RepoResponse?,
)
