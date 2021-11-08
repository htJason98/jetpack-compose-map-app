package com.mota.pebblebee.ui.screens.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mota.pebblebee.ui.screens.home.HomeScreen
import com.mota.pebblebee.ui.screens.login.LoginScreen

class NavigationHomeScreen {
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @Composable
    fun initNavigationController() {
        val navController: NavHostController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavigationDestinations.loginScreen
        ) {
            composable(route = NavigationDestinations.loginScreen) {
                LoginScreen(navController)
            }
            composable(route = NavigationDestinations.homeScreen) {
                HomeScreen()
            }
        }
    }
}

object NavigationDestinations {
    const val loginScreen = "LoginScreen"
    const val homeScreen = "HomeScreen"
    const val historyScreen = "HistoryScreen"
}