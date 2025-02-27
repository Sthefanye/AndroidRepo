package com.example.androidrepo.data.api

import com.example.androidrepo.domain.model.pulls.PullRequests
import com.example.androidrepo.domain.model.repositories.Repositories
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String = "language:Kotlin",
        @Query("sort") sort: String = "stars",
        @Query("per_page") perPage: Int = 100
    ): Response<Repositories>

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repository: String,
        @Query("per_page") perPage: Int = 100
    ): Response<List<PullRequests>>
}