package com.example.local_api

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.core.generalUtil.Routes
import com.example.local_api.navigation.BottomNavigationItem
import com.example.local_api.navigation.NavigationGraph
import com.example.local_api.navigation.NewsBottomNavigation
import com.example.local_api.ui.theme.Local_apiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Local_apiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "sports"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "users")
        )
    }
    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    selectedItem = remember(backstackState?.destination?.route) {
        when (backstackState?.destination?.route) {
            Routes.SportsScreen.route -> 0
            Routes.DashboardScreen.route -> 1
            else -> 0
        }
    }
    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Routes.SportsScreen.route ||
                backstackState?.destination?.route == Routes.DashboardScreen.route
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTap(
                                navController = navController,
                                route = Routes.SportsScreen.route
                            )

                            1 -> navigateToTap(
                                navController = navController,
                                route = Routes.DashboardScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavigationGraph(
            navController = navController,
            startDestination = Routes.RegisterScreen.route,
            bottomPadding = bottomPadding
        )
    }
}
private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { sportScreen ->
            popUpTo(sportScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}
