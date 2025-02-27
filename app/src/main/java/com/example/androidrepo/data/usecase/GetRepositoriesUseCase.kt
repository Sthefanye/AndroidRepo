package com.example.androidrepo.data.usecase

import com.example.androidrepo.core.common.NetworkResult
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repository: GithubRepositoriesImpl) {
    operator fun invoke() = flow {
        emit(NetworkResult.Loading(true))

        val result = repository.getRepositories()
        if (!result.isSuccessful) {
            emit(NetworkResult.Failure(message = result.message()))
            return@flow
        }
        result.body()?.let {
            emit(NetworkResult.Success(data = it.items))
        }
    }.catch {
        emit(NetworkResult.Failure(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}