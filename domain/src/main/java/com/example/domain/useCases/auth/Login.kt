package com.example.domain.useCases.auth

import com.example.domain.model.api_model.Auth
import com.example.domain.model.api_model.LoginRequest
import com.example.domain.repository.Repository

class Login constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(request: LoginRequest): Result<Auth> {
        return repository.login(request)
    }
}