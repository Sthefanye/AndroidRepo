package com.example.androidrepo.domain.repository

import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.domain.model.Repositories
import retrofit2.Response

interface IGithubRepositories {
    suspend fun getRepositories() : Response<RepositoriesResponse>

    suspend fun getPullRequests(owner: String, repository: String) : Response<List<PullRequestsResponse>>
}