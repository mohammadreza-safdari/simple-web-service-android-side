package com.example.domain.useCases.auth

import com.example.domain.repository.Repository

class Logout constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.logout()
    }
}