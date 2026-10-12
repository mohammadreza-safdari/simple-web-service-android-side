package com.example.data.remote.dto

import com.example.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val pivot: PivotResponse
)
fun UserResponse.toUser() : User {
    return User(
        id = id,
        name = name,
        email = email
    )
}
