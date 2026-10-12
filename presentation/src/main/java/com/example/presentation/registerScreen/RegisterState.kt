package com.example.presentation.registerScreen

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)