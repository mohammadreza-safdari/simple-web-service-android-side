package com.example.domain.useCases.preferences

import com.example.domain.repository.Repository

class ClearToken constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.clearToken()
    }
}