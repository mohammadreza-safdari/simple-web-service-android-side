package com.example.domain.useCases.preferences

import com.example.domain.repository.Repository

class GetToken constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): String? {
        return repository.getToken()
    }
}