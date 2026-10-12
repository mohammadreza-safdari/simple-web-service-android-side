package com.example.data.remote.dto

import com.example.domain.model.Sport
import com.example.domain.model.SportsInUser
import com.example.domain.model.Users
import com.google.gson.annotations.SerializedName

data class UsersResponse(
    val id: Int,
    val name: String,
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val sports: List<SportsInUserResponse>
)
fun UsersResponse.toUsers(): Users {
    return Users(
        id = id,
        name = name,
        email = email,
        sports = sports.map {
            SportsInUser(it.id, it.name, it.description)
        }
    )
}