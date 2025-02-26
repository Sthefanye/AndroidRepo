package com.example.androidrepo.data.model

import com.example.androidrepo.domain.model.repositories.Items
import com.google.gson.annotations.SerializedName

data class RepositoriesResponse (
    @SerializedName("total_count")
    val totalCounts: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Items>
)