package com.example.data.remote

import com.example.domain.model.SportRequest
import com.example.data.remote.dto.SportResponse
import com.example.data.remote.dto.UserResponse
import com.example.data.remote.dto.UsersResponse
import com.example.data.remote.dto.api_authentication.AuthResponse
import com.example.domain.model.api_model.LoginRequest
import com.example.domain.model.api_model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("api/sports")
    suspend fun index(): List<SportResponse>

    @GET("api/users")
    suspend fun users(): List<UsersResponse>

    @POST("api/sports")
    suspend fun store(
        @Body request: SportRequest
    ): List<SportResponse>

    @DELETE("api/sports/{sportId}")
    suspend fun destroy(
        @Path("sportId") sportId: Int
    )

    @POST("api/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("api/logout")
    suspend fun logout(
        token: String
    )

    @GET("api/user")
    suspend fun user(
        token: String
    ): UserResponse
}