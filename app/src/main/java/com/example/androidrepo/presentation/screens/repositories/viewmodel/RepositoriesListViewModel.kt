package com.example.androidrepo.presentation.screens.repositories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidrepo.utils.common.NetworkResult
import com.example.androidrepo.data.usecase.GetRepositoriesUseCase
import com.example.androidrepo.domain.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(private val useCase: GetRepositoriesUseCase) :
    ViewModel() {
    private val _itemList = MutableLiveData<NetworkResult<List<Items>>>()
    val itemList: LiveData<NetworkResult<List<Items>>>
        get() = _itemList

    fun getRepositories() = viewModelScope.launch {
        useCase.invoke().collect {
            _itemList.value = it
        }
    }

    fun getIsReady(): Boolean {
        val result = viewModelScope.runCatching {
            runBlocking {
                delay(2000)
            }
            true
        }
        return result.isSuccess
    }
}
