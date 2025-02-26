package com.example.androidrepo.presentation.screens.repositories.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidrepo.domain.repository.IGithubRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(private val repository: IGithubRepositories) : ViewModel() {

    fun getRepositories() = repository.getRepositories()
}