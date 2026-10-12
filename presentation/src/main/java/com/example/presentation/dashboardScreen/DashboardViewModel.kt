package com.example.presentation.dashboardScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.generalUtil.Resource
import com.example.domain.useCases.UseCasesReachPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCases: UseCasesReachPoint
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    init {
        onEvent(DashboardEvent.LoadUsers)
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            DashboardEvent.LoadUsers -> loadUsers()
            DashboardEvent.Refresh -> loadUsers()
        }
    }
    private fun loadUsers() {
        useCases.users().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(users = result.data?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = result.isLoading)
                }
            }
        }.launchIn(viewModelScope)
    }
}