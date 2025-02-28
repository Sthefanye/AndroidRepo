package com.example.androidrepo.data.api

import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.domain.model.Repositories
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
    ): Response<RepositoriesResponse>

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repository: String,
        @Query("per_page") perPage: Int = 100
    ): Response<List<PullRequestsResponse>>
}