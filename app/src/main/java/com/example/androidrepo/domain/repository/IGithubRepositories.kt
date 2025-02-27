package com.example.androidrepo.domain.repository

import com.example.androidrepo.domain.model.pulls.PullRequests
import com.example.androidrepo.domain.model.repositories.Repositories
import retrofit2.Response

interface IGithubRepositories {
    suspend fun getRepositories() : Response<Repositories>

    suspend fun getPullRequests(owner: String, repository: String) : Response<List<PullRequests>>
}