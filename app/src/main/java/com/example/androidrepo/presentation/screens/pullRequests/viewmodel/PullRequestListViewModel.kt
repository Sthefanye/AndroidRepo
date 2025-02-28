package com.example.androidrepo.presentation.screens.pullRequests.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidrepo.utils.common.NetworkResult
import com.example.androidrepo.data.usecase.GetPullRequestsUseCase
import com.example.androidrepo.domain.model.PullRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestListViewModel @Inject constructor(private val useCase: GetPullRequestsUseCase) :
    ViewModel() {
    private val  _pullRequestsList = MutableLiveData<NetworkResult<List<PullRequests>>>()
    val pullRequestsList: LiveData<NetworkResult<List<PullRequests>>>
        get() = _pullRequestsList

    fun getPullRequests(owner: String, repository: String) = viewModelScope.launch {
        useCase.invoke(owner, repository).collect {
            _pullRequestsList.value = it
        }
    }
}