package com.example.androidrepo.data.usecase

import com.example.androidrepo.utils.common.NetworkResult
import com.example.androidrepo.data.mappers.toDomain
import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.Locale
import javax.inject.Inject

class GetPullRequestsUseCase @Inject constructor(private val repository: GithubRepositoriesImpl) {
    operator fun invoke(owner: String, repo: String) = flow {
        emit(NetworkResult.Loading(true))

        val result = repository.getPullRequests(
            owner.lowercase(Locale.getDefault()),
            repo.lowercase(Locale.getDefault())
        )
        if (!result.isSuccessful) {
            emit(NetworkResult.Failure(message = result.message()))
        }
        result.body()?.let { body ->
            val response = body.map { it.toDomain() }
            emit(NetworkResult.Success(data = response))
        }
    }.catch {
        emit(NetworkResult.Failure(message = it.message.toString()))
        println(it.message.toString())
    }.flowOn(Dispatchers.IO)
}