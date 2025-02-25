package com.example.androidrepo.domain.model.pulls

import com.google.gson.annotations.SerializedName

data class Labels(
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