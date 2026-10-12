package com.example.presentation.loginScreen

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    data object LoginClicked : LoginEvent()
}