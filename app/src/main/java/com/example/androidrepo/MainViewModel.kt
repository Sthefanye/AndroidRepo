package com.example.androidrepo

import androidx.lifecycle.ViewModel
import com.example.androidrepo.domain.repository.IGithubRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IGithubRepositories
) : ViewModel() {

    fun getRepositories() = repository.getRepositories()

    fun getPullRequests(owner: String, repo: String) = repository.getPullRequests(owner, repo)

}