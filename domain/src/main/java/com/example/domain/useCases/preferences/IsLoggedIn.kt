package com.example.domain.useCases.preferences

import com.example.domain.repository.Repository

class IsLoggedIn constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Boolean {
        return repository.isLoggedIn()
    }
}