package com.example.androidrepo.data.usecase

import com.example.androidrepo.utils.common.NetworkResult
import com.example.androidrepo.data.mappers.toDomain
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetPullRequestsUseCase @Inject constructor(private val repository: GithubRepositoriesImpl) {
    operator fun invoke(owner: String, repo: String) = flow {
        emit(NetworkResult.Loading(true))

        val result = repository.getPullRequests(owner, repo)
        if (!result.isSuccessful) {
            emit(NetworkResult.Failure(message = result.message()))
            return@flow
        }
        result.body()?.let { body ->
            emit(NetworkResult.Success(data = body.map { it.toDomain() }))
        }
    }.catch {
        emit(NetworkResult.Failure(message = it.message.toString()))
    }.flowOn(Dispatchers.IO)
}