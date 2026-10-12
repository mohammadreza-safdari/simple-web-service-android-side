package com.example.domain.model.api_model

import com.example.domain.model.User

data class Auth(
    val token: String,
    val user: User
)