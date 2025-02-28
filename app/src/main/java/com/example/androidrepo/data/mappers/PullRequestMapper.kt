package com.example.androidrepo.data.mappers

import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.UserResponse
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.domain.model.User

fun PullRequestsResponse.toDomain(): PullRequests {
    return PullRequests(
        htmlUrl = htmlUrl,
        number = number,
        title = title,
        user = user.toUser(),
        body = body,
    )
}


private fun UserResponse.toUser(): User {
    return User(
        login = login,
        avatarUrl = avatarUrl
    )
}

