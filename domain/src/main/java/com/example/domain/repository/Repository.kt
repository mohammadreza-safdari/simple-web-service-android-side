package com.example.domain.repository

import com.example.core.generalUtil.Resource
import com.example.domain.model.Sport
import com.example.domain.model.SportRequest
import com.example.domain.model.User
import com.example.domain.model.Users
import com.example.domain.model.api_model.Auth
import com.example.domain.model.api_model.LoginRequest
import com.example.domain.model.api_model.RegisterRequest
import kotlinx.coroutines.flow.Flow

interface Repository {
    //API:
    fun index() : Flow<Resource<List<Sport>>>
    fun users() : Flow<Resource<List<Users>>>
    suspend fun store(request: SportRequest) : Result<List<Sport>>
    suspend fun destroy(sportId: Int)
    suspend fun register(request: RegisterRequest): Result<Auth>
    suspend fun login(request: LoginRequest): Result<Auth>
    suspend fun logout(): Result<Unit>
    suspend fun user(): Result<User>
    //PREFERENCES:
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clearToken()
    suspend fun isLoggedIn(): Boolean
    //DATABASE:
}