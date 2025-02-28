package com.example.androidrepo.repository

import com.example.androidrepo.data.api.GithubApiService
import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import com.example.androidrepo.util.JsonProvider
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import retrofit2.Response

@RunWith(JUnit4::class)
class GitHubRepositoryTest {

    @Mock
    private lateinit var githubRepositories: GithubRepositoriesImpl

    private val serviceMock = mockk<GithubApiService>()

    @Before
    fun setUp() {
        githubRepositories = GithubRepositoriesImpl(serviceMock)
    }

    @Test
    fun `getRepositories and returns a valid response`() = runBlocking {
        val mockResponse = mockk<RepositoriesResponse>()
        val response = Response.success(mockResponse)

        coEvery { serviceMock.getRepositories() } returns response

        val result = githubRepositories.getRepositories()

        assertNotNull(result)
        Assert.assertTrue(response == result)
    }

    @Test
    fun `getPullRequests and returns a valid response`() = runBlocking {
        val mockPullRequestsResponse = listOf(mockk<PullRequestsResponse>())
        val response = Response.success(mockPullRequestsResponse)

        val owner = "jetbrains"
        val repository = "kotlin"

        coEvery { serviceMock.getPullRequests(owner, repository) } returns response

        val result = githubRepositories.getPullRequests(owner, repository)

        assertNotNull(result)
        Assert.assertTrue(response == result)
    }

}