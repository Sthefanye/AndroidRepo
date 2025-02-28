package com.example.androidrepo.domain.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    @SerializedName("items")
    val items: List<Items>
)