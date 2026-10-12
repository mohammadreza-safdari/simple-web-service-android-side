package com.example.domain.model

data class Sport(
    val id: Int,
    val name: String,
    val description: String,
    val users: List<User>
)
