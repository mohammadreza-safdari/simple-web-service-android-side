package com.example.domain.useCases.auth

import com.example.domain.model.api_model.Auth
import com.example.domain.model.api_model.RegisterRequest
import com.example.domain.repository.Repository

class Register constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(request: RegisterRequest): Result<Auth> {
        return repository.register(request)
    }
}