package com.example.domain.useCases

import com.example.domain.model.Sport
import com.example.domain.repository.Repository

class Destroy constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(sportId: Int) {
        repository.destroy(sportId)
    }
}