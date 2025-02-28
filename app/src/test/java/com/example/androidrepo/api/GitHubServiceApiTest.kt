package com.example.androidrepo.api

import com.example.androidrepo.data.api.GithubApiService
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import com.example.androidrepo.util.JsonProvider
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject


@RunWith(JUnit4::class)
class GitHubServiceApiTest @Inject constructor() {

    private val server = MockWebServer()

    @Mock
    private lateinit var repository: GithubRepositoriesImpl

    private lateinit var mockedResponse: String


    @Before
    fun init() {
        server.start(8000)
        val baseUrl = server.url("https://api.github.com/").toString()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build().create(GithubApiService::class.java)
        repository = GithubRepositoriesImpl(service)
    }

    @Test
    fun `get repositories and the success response`() {
        mockedResponse = JsonProvider("mocks/repositories.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.getRepositories() }

        Assert.assertNotNull(response)
    }

    @Test
    fun  `get pull requests and the success response`() {
        mockedResponse = JsonProvider("mocks/pull-requests.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.getPullRequests("jetbrains", "kotlin") }

        Assert.assertNotNull(response)
    }

    @Test
    fun `get pull requests and the response is empty`() {
        mockedResponse = JsonProvider("mocks/pull-requests-response-empty.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { repository.getPullRequests("gkd-kit", "gkd") }

        Assert.assertEquals(mockedResponse, response.body().toString())
    }

    @Test
    fun `get pull requests and the response is not found`() {
        mockedResponse = JsonProvider("mocks/response-error-pull-requests.json").content

        val response = runBlocking { repository.getPullRequests("a", "a") }

        Assert.assertEquals(mockedResponse, response.errorBody()?.string())
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}