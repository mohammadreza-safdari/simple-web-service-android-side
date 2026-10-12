package com.example.local_api.navigation

import SportsScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.generalUtil.Routes
import com.example.presentation.dashboardScreen.DashboardScreen
import com.example.presentation.dashboardScreen.DashboardViewModel
import com.example.presentation.loginScreen.LoginScreen
import com.example.presentation.loginScreen.LoginViewModel
import com.example.presentation.registerScreen.RegisterScreen
import com.example.presentation.registerScreen.RegisterViewModel
import com.example.presentation.sportsScreen.SportsViewModel

@Composable
fun NavigationGraph(navController: NavHostController, startDestination: String, bottomPadding: Dp) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(bottom = bottomPadding)
    ) {
        composable(route = Routes.SportsScreen.route) {
            val viewModel: SportsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            SportsScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = Routes.DashboardScreen.route) {
            val viewModel: DashboardViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            DashboardScreen(
                state = state
            )
        }
        composable(route = Routes.RegisterScreen.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            RegisterScreen(
                state = state,
                effect = viewModel.effect,
                onEvent = viewModel::onEvent,
                navigateToSportsScreen = {
                    navController.navigate(Routes.SportsScreen.route)
                },
                navigateToLoginScreen = {
                    navController.navigate(Routes.LoginScreen.route)
                }
            )
        }
        composable(route = Routes.LoginScreen.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            LoginScreen(
                state = state,
                effect = viewModel.effect,
                onEvent = viewModel::onEvent,
                navigateToSportsScreen = {
                    navController.navigate(Routes.SportsScreen.route)
                },
                navigateToRegisterScreen = {
                    navController.navigate(Routes.RegisterScreen.route)
                }
            )
        }
    }
}