package com.example.presentation.dashboardScreen

sealed interface DashboardEvent {
    data object LoadUsers : DashboardEvent
    data object Refresh : DashboardEvent
}