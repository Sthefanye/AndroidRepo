package com.example.androidrepo.data.api

import com.example.androidrepo.domain.model.pulls.PullRequests
import com.example.androidrepo.domain.model.repositories.Repositories
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") query: String = "language:Kotlin",
        @Query("sort") sort: String = "stars",
        @Query("per_page") perPage: Int = 100
    ): Call<Repositories>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repository: String,
        @Query("per_page") perPage: Int = 100
    ): Call<List<PullRequests>>
}