package com.example.androidrepo.usecases

import com.example.androidrepo.data.api.GithubApiService
import com.example.androidrepo.data.mappers.toDomain
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
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
class GetRepositoriesUseCaseTest {

    private val serviceMock = mockk<GithubApiService>()

    @Mock
    private lateinit var githubRepositoriesMock: GithubRepositoriesImpl

    @Mock
    private lateinit var useCase: GetRepositoriesUseCase


    @Before
    fun setup() {
        githubRepositoriesMock = GithubRepositoriesImpl(serviceMock)
        useCase = GetRepositoriesUseCase(githubRepositoriesMock)
    }

    @Test
    fun `successfully invoke and fetch data` () = runBlocking {
        val mockResponse = mockk<Response<RepositoriesResponse>>()
        val mockRepositoriesResponse = RepositoriesResponse(
            totalCounts = 0,
            incompleteResults = false, items = listOf()
        )

        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns mockRepositoriesResponse
        coEvery { githubRepositoriesMock.getRepositories() } returns mockResponse

        assertEquals(
            NetworkResult.Success(data = mockRepositoriesResponse.toDomain().items),
            useCase.invoke().toList()[1]
        )
    }

    @Test
    fun `invoke and fetch failed data`() = runBlocking {
        val mockResponse = mockk<Response<RepositoriesResponse>>()
        val mockRepositoriesResponse = RepositoriesResponse(
            totalCounts = 0,
            incompleteResults = false, items = listOf()
        )
        every { mockResponse.isSuccessful } returns false
        every { mockResponse.body() } returns mockRepositoriesResponse
        every { mockResponse.message() } returns "failure"
        coEvery { githubRepositoriesMock.getRepositories() } returns mockResponse

        assertEquals(
            NetworkResult.Failure<String>(message = "failure"),
            useCase.invoke().toList()[1]
        )
    }
}
