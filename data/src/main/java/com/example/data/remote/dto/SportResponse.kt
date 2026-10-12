package com.example.data.remote.dto

import com.example.domain.model.Sport
import com.example.domain.model.User
import com.google.gson.annotations.SerializedName

data class SportResponse(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val users: List<UserResponse>
)
fun SportResponse.toSport(): Sport {
    return Sport(
        id = id,
        name = name,
        description = description,
        users = users.map {
            User(it.id, it.name, it.email)
        }
    )
}