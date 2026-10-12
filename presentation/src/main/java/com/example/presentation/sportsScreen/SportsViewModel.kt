package com.example.presentation.sportsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.generalUtil.Resource
import com.example.domain.model.Sport
import com.example.domain.model.SportRequest
import com.example.domain.useCases.UseCasesReachPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val useCases: UseCasesReachPoint
) : ViewModel() {
    private val _state = MutableStateFlow(SportsState())
    val state = _state.asStateFlow()

    init {
        onEvent(SportsEvent.LoadSports)
    }

    fun onEvent(event: SportsEvent) {
        when (event) {
            is SportsEvent.NameChanged ->
                _state.update { it.copy(sportName = event.value) }
            is SportsEvent.DescriptionChanged ->
                _state.update { it.copy(sportDescription = event.value) }
            SportsEvent.CreateSport -> createSport() //data object doesn't want is in when
            is SportsEvent.DeleteSport -> deleteSport(event.id)
            SportsEvent.LoadSports -> loadSports()
        }
    }

    private fun loadSports() {
        useCases.index().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(sports = result.data?: emptyList())
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
    private fun createSport() {
        viewModelScope.launch {
            val currentState = state.value
            val request = SportRequest(
                name = currentState.sportName,
                description = currentState.sportDescription
            )
            useCases.store(request).fold(
                onSuccess = { result ->
                    _state.value = _state.value.copy(
                        sports = _state.value.sports + result
                    )
                },
                onFailure = { error ->
                    _state.value = _state.value.copy(
                        error = error.message
                    )
                }
            )
        }
    }
    private fun deleteSport(id: Int) {
        viewModelScope.launch {
            useCases.destroy(id)
            _state.value = _state.value.copy(
                sports = _state.value.sports.filter { it.id != id }
            )
        }
    }
}