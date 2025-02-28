package com.example.androidrepo.data.model

import com.example.androidrepo.domain.model.Owner
import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)
