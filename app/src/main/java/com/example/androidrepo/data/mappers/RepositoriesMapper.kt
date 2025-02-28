package com.example.androidrepo.data.mappers

import com.example.androidrepo.data.model.ItemsResponse
import com.example.androidrepo.data.model.OwnerResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.domain.model.Items
import com.example.androidrepo.domain.model.Owner
import com.example.androidrepo.domain.model.Repositories

fun RepositoriesResponse.toDomain(): Repositories {
    return Repositories(
        items = items.map { it.toItems() })
}

private fun ItemsResponse.toItems(): Items {
    return Items(
        name = name,
        owner = owner.toOwner(),
        description = description,
        stargazersCount = stargazersCount,
        forksCount = forksCount
    )
}

fun OwnerResponse.toOwner(): Owner {
    return Owner(
        login = login,
        avatarUrl = avatarUrl
    )
}
