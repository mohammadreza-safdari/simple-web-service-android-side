package com.example.presentation.registerScreen

sealed class RegisterEvent {
    data class NameChanged(val value: String) : RegisterEvent()
    data class EmailChanged(val value: String) : RegisterEvent()
    data class PasswordChanged(val value: String) : RegisterEvent()
    data class PasswordConfirmationChanged(val value: String) : RegisterEvent()
    data object RegisterClicked : RegisterEvent()
}