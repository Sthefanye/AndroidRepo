package com.example.androidrepo.presentation.screens.pullRequests.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidrepo.domain.repository.IGithubRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PullRequestListViewModel @Inject constructor(private val repository: IGithubRepositories) : ViewModel() {

    fun getPullRequests(owner: String, repo: String) = repository.getPullRequests(owner, repo)
}