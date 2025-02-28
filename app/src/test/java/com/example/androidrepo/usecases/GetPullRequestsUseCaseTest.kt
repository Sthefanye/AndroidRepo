package com.example.androidrepo.usecases

import com.example.androidrepo.data.api.GithubApiService
import com.example.androidrepo.data.mappers.toDomain
import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import com.example.androidrepo.data.usecase.GetPullRequestsUseCase
import com.example.androidrepo.data.usecase.GetRepositoriesUseCase
import com.example.androidrepo.utils.common.NetworkResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import retrofit2.Response

@RunWith(JUnit4::class)
class GetPullRequestsUseCaseTest {
    private val serviceMock = mockk<GithubApiService>()

    @Mock
    private lateinit var githubRepositoriesMock: GithubRepositoriesImpl

    @Mock
    private lateinit var useCase: GetPullRequestsUseCase


    @Before
    fun setup() {
        githubRepositoriesMock = GithubRepositoriesImpl(serviceMock)
        useCase = GetPullRequestsUseCase(githubRepositoriesMock)
    }

    @Test
    fun `successfully invoke and fetch data` () = runBlocking {
        val mockPullRequests = mockk<Response<List<PullRequestsResponse>>>()
        val mockPullRequestsResponse : List<PullRequestsResponse> = listOf()

        val owner = "Jetbrains"
        val repo = "kotlin"
        every { mockPullRequests.isSuccessful } returns true
        every { mockPullRequests.body() } returns mockPullRequestsResponse
        coEvery { githubRepositoriesMock.getPullRequests(owner, repo) } returns mockPullRequests

        assertEquals(
            NetworkResult.Success(data = mockPullRequestsResponse.map { it.toDomain() }),
            useCase.invoke(owner, repo).toList()[1]
        )
    }

    @Test
    fun `invoke and fetch failed data`() = runBlocking {
        val mockPullRequestsResponse = mockk<Response<List<PullRequestsResponse>>>()
        val mockPullRequests : List<PullRequestsResponse> = listOf()

        val owner = "Jetbrains"
        val repo = "kotlin"
        every { mockPullRequestsResponse.isSuccessful } returns false
        every { mockPullRequestsResponse.body() } returns mockPullRequests
        every { mockPullRequestsResponse.message() } returns "failure"
        coEvery { githubRepositoriesMock.getPullRequests(owner, repo) } returns mockPullRequestsResponse

        assertEquals(
            NetworkResult.Failure<String>(message = "failure"),
            useCase.invoke(owner, repo).toList()[1]
        )
    }
}