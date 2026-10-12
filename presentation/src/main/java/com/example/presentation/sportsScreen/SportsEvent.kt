package com.example.presentation.sportsScreen

sealed interface SportsEvent {
    data class NameChanged(val value: String) : SportsEvent
    data class DescriptionChanged(val value: String) : SportsEvent
    data object CreateSport : SportsEvent
    data class DeleteSport(val id: Int) : SportsEvent
    data object LoadSports : SportsEvent
}
