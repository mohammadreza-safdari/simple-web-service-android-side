package com.example.data.remote.dto

import com.example.domain.model.SportsInUser
import com.google.gson.annotations.SerializedName

data class SportsInUserResponse(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val pivot: PivotResponse
)
fun SportsInUserResponse.toSportInUser(): SportsInUser {
    return SportsInUser(
        id = id,
        name = name,
        description = description
    )
}