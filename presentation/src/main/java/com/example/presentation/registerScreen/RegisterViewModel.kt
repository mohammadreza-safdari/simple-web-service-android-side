package com.example.presentation.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SportRequest
import com.example.domain.model.api_model.RegisterRequest
import com.example.domain.useCases.UseCasesReachPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCases: UseCasesReachPoint
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()
    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.NameChanged -> {
                _state.update {
                    it.copy(name = event.value)
                }
            }
            is RegisterEvent.EmailChanged -> {
                _state.update {
                    it.copy(email = event.value)
                }
            }
            is RegisterEvent.PasswordChanged -> {
                _state.update {
                    it.copy(password = event.value)
                }
            }
            is RegisterEvent.PasswordConfirmationChanged -> {
                _state.update {
                    it.copy(passwordConfirmation = event.value)
                }
            }
            RegisterEvent.RegisterClicked -> {
                register()
            }
        }
    }

    private fun register() {
        val current = _state.value
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val currentState = state.value
            val request = RegisterRequest(
               name = currentState.name,
               email = currentState.email,
               password = currentState.password,
               password_confirmation = currentState.passwordConfirmation
            )
            useCases.register(request).fold(
                onSuccess = { auth ->
                    _state.update { it.copy(isLoading = false) }
                    _state.update { it.copy(error = null) }
                    useCases.saveToken(auth.token)
                    _effect.send(RegisterEffect.NavigateToSports)
                },
                onFailure = {
                    _state.update { it.copy(isLoading = false) }
                    _state.update { it.copy(error = "operation failed!") }
                }
            )
        }
    }
}