package com.example.domain.useCases

import com.example.domain.model.Sport
import com.example.domain.model.SportRequest
import com.example.domain.repository.Repository

class Store constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(request: SportRequest): Result<List<Sport>> {
        return repository.store(request)
    }
}