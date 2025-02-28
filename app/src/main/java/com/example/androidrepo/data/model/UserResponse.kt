package com.example.androidrepo.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)