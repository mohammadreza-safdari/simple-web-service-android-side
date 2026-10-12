package com.example.core.generalUtil

sealed class Routes(val route: String) {
    object DashboardScreen: Routes("DashboardScreen")
    object SportsScreen: Routes("SportsScreen")
    object RegisterScreen: Routes("RegisterScreen")
    object LoginScreen: Routes("LoginScreen")
}