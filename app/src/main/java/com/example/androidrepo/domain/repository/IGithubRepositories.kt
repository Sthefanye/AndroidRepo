package com.example.androidrepo.domain.repository

import com.example.androidrepo.domain.model.pulls.PullRequests
import com.example.androidrepo.domain.model.repositories.Repositories
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface IGithubRepositories {
    fun getRepositories() : Call<Repositories>

    fun getPullRequests(owner: String, repository: String) : Call<List<PullRequests>>
}