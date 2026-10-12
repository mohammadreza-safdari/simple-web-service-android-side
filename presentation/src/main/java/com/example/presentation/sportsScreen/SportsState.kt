package com.example.presentation.sportsScreen

import com.example.domain.model.Sport

data class SportsState(
    val sports: List<Sport> = emptyList(),
    val sportName: String = "",
    val sportDescription: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)