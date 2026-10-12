package com.example.presentation.dashboardScreen

import com.example.domain.model.User
import com.example.domain.model.Users

data class DashboardState(
    val users: List<Users> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)