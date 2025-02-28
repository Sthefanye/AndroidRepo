package com.example.androidrepo.data.repository

import com.example.androidrepo.data.api.GithubApi
import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.domain.repository.IGithubRepositories
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoriesImpl @Inject constructor(private val service: GithubApi) :
    IGithubRepositories {
    override suspend fun getRepositories(): Response<RepositoriesResponse> {
        return service.getRepositories()
    }

    override suspend fun getPullRequests(
        owner: String,
        repository: String
    ): Response<List<PullRequestsResponse>> {
        return service.getPullRequests(owner = owner, repository = repository)
    }
}