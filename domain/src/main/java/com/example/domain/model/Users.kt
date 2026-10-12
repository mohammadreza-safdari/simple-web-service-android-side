package com.example.domain.model

data class Users(
    val id: Int,
    val name: String,
    val email: String,
    val sports: List<SportsInUser>
)