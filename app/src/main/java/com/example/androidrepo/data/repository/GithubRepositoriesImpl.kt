package com.example.androidrepo.data.repository

import com.example.androidrepo.data.api.GithubApiService
import com.example.androidrepo.data.mappers.toDomain
import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.domain.model.Repositories
import com.example.androidrepo.domain.repository.IGithubRepositories
import retrofit2.Response
import javax.inject.Inject

class GithubRepositoriesImpl @Inject constructor(private val service: GithubApiService) :
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