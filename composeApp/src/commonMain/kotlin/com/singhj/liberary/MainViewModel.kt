package com.singhj.liberary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FeesState {
    object Loading : FeesState()
    data class Success(val response: FeesStatusResponse) : FeesState()
    data class Error(val message: String) : FeesState()
}

class MainViewModel(
    private val feesRepository: FeesRepository
) : ViewModel() {

    private val _feesState = MutableStateFlow<FeesState>(FeesState.Loading)
    val feesState = _feesState.asStateFlow()

    init {
        fetchFeesStatus()
    }

    fun fetchFeesStatus() {
        viewModelScope.launch {
            _feesState.value = FeesState.Loading
            try {
                val response = feesRepository.getFeesStatus()
                _feesState.value = FeesState.Success(response)
            } catch (e: Exception) {
                _feesState.value = FeesState.Error(e.message ?: "Failed to fetch fees status")
            }
        }
    }
}
