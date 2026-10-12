package com.example.domain.useCases.auth

import com.example.domain.model.User
import com.example.domain.repository.Repository

class User constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Result<User> {
        return repository.user()
    }
}