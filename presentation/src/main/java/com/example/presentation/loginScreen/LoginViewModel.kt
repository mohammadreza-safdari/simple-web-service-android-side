package com.example.presentation.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.api_model.LoginRequest
import com.example.domain.useCases.UseCasesReachPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: UseCasesReachPoint
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()
    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.update {
                    it.copy(email = event.value)
                }
            }
            is LoginEvent.PasswordChanged -> {
                _state.update {
                    it.copy(password = event.value)
                }
            }
            LoginEvent.LoginClicked -> {
                login()
            }
        }
    }

    private fun login() {
        val current = _state.value
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val request = LoginRequest(
                email = current.email,
                password = current.password
            )
            useCases.login(request).fold(
                onSuccess = { auth ->
                    _state.update { it.copy(isLoading = false) }
                    _state.update { it.copy(error = null) }
                    useCases.saveToken(auth.token)
                    _effect.send(LoginEffect.NavigateToSports)
                },
                onFailure = { e ->
                    _state.update { it.copy(isLoading = false) }
                    _state.update { it.copy(error = "operation failed: $e") }
                }
            )
        }
    }
}