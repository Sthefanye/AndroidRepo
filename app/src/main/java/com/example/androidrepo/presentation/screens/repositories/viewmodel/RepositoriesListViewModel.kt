package com.example.androidrepo.presentation.screens.repositories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidrepo.core.common.NetworkResult
import com.example.androidrepo.data.usecase.GetRepositoriesUseCase
import com.example.androidrepo.domain.model.repositories.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(private val useCase: GetRepositoriesUseCase) :
    ViewModel() {
    private val  _itemList= MutableLiveData<NetworkResult<List<Items>>>()
    val itemList: LiveData<NetworkResult<List<Items>>>
        get() = _itemList

    init {
        getRepositories()
    }

    private fun getRepositories() = viewModelScope.launch {
        useCase.invoke().collect {
           _itemList.value = it
        }
    }
}