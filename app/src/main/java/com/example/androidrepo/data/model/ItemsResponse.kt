package com.example.androidrepo.data.model


import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: OwnerResponse,
    @SerializedName("description")
    val description: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
)
