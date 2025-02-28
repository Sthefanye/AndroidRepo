package com.example.androidrepo.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.androidrepo.data.api.GithubApiService
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import com.example.androidrepo.data.usecase.GetRepositoriesUseCase
import com.example.androidrepo.domain.model.Items
import com.example.androidrepo.presentation.screens.repositories.viewmodel.RepositoriesListViewModel
import com.example.androidrepo.utils.common.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.InjectMocks
import org.mockito.Mock

@ExperimentalCoroutinesApi
class RepositoriesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @InjectMocks
    private lateinit var viewModel: RepositoriesListViewModel

    private val serviceMock = mockk<GithubApiService>()

    @Mock
    private lateinit var githubRepositoriesMock: GithubRepositoriesImpl

    private val useCaseMock = mockk<GetRepositoriesUseCase>(relaxed = true)

    @ExperimentalCoroutinesApi

    private val observer: Observer<NetworkResult<List<Items>>> = mockk(relaxed = true)

    @Before
    fun setup() {
        githubRepositoriesMock = GithubRepositoriesImpl(serviceMock)
        viewModel = RepositoriesListViewModel(useCaseMock)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `get repositories and success`() = runTest {
        val expectedResponse = NetworkResult.Success<List<Items>>(listOf())

        coEvery { (useCaseMock).invoke() } returns flowOf(expectedResponse)

        viewModel.getRepositories().join()
        viewModel.itemList.observeForever(observer)

        assertEquals(expectedResponse, viewModel.itemList.value)
    }

    @Test
    fun `get repositories and fail`() = runTest {
        val expectedResponse = NetworkResult.Failure<List<Items>>(message = "failure")

        coEvery { (useCaseMock).invoke() } returns flowOf(expectedResponse)

        viewModel.getRepositories().join()
        viewModel.itemList.observeForever(observer)

        assertEquals(expectedResponse, viewModel.itemList.value)
    }

    @Test
    fun `get repositories and loading `() = runTest {
        val expectedResponse = NetworkResult.Loading<List<Items>>(true)

        coEvery { (useCaseMock).invoke() } returns flowOf(expectedResponse)

        viewModel.getRepositories().join()
        viewModel.itemList.observeForever(observer)

        assertEquals(expectedResponse, viewModel.itemList.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}