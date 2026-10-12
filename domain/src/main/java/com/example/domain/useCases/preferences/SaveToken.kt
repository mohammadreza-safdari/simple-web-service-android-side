package com.example.domain.useCases.preferences

import com.example.domain.repository.Repository

class SaveToken constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(token: String) {
        repository.saveToken(token)
    }
}