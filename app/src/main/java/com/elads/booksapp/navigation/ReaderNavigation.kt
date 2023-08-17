package com.elads.booksapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elads.booksapp.screens.details.BookDetailsScreen
import com.elads.booksapp.screens.home.ReaderHomeScreen
import com.elads.booksapp.screens.login.ReaderLoginScreen
import com.elads.booksapp.screens.search.ReaderBookSearchScreen
import com.elads.booksapp.screens.splash.ReaderSplashScreen
import com.elads.booksapp.screens.stats.ReaderStatsScreen
import com.elads.booksapp.screens.update.ReaderBookUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {
        composable(route = ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }
        composable(route = ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }
        composable(route = ReaderScreens.HomeScreen.name) {
            ReaderHomeScreen(navController = navController)
        }
        composable(route = ReaderScreens.SearchScreen.name) {
            ReaderBookSearchScreen(navController = navController)
        }
        composable(route = ReaderScreens.DetailsScreen.name) {
            BookDetailsScreen(navController = navController)
        }
        composable(route = ReaderScreens.UpdateScreen.name) {
            ReaderBookUpdateScreen(navController = navController)
        }
        composable(route = ReaderScreens.StatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }
    }
}