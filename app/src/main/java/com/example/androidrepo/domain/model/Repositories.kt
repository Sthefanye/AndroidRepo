package com.example.androidrepo.domain.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    @SerializedName("total_count")
    val totalCounts: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Items>
)