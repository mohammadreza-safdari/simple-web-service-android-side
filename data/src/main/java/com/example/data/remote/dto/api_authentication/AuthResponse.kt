package com.example.data.remote.dto.api_authentication

import com.example.data.remote.dto.UserResponse
import com.example.domain.model.User
import com.example.domain.model.api_model.Auth

data class AuthResponse(
    val token: String,
    val user: UserResponse
)
fun AuthResponse.toAuth() : Auth {
    return Auth(
        token = token,
        user = User(
            user.id, user.name, user.email
        )
    )
}