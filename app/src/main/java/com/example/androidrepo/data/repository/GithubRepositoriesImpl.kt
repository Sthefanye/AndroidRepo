package com.example.androidrepo.data.repository

import com.example.androidrepo.data.api.GithubApi
import com.example.androidrepo.domain.model.pulls.PullRequests
import com.example.androidrepo.domain.model.repositories.Repositories
import com.example.androidrepo.domain.repository.IGithubRepositories
import retrofit2.Call
import javax.inject.Inject

class GithubRepositoriesImpl @Inject constructor(private val service: GithubApi) : IGithubRepositories{
    override fun getRepositories(): Call<Repositories> {
        return service.getRepositories()
    }

    override fun getPullRequests(owner: String, repository: String): Call<List<PullRequests>> {
        return service.getPullRequests(owner = owner, repository = repository)
    }
}