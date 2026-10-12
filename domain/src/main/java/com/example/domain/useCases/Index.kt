package com.example.domain.useCases

import com.example.core.generalUtil.Resource
import com.example.domain.model.Sport
import com.example.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class Index constructor(
    private val repository: Repository
) {
    operator fun invoke() : Flow<Resource<List<Sport>>> {
        return repository.index()
    }
}