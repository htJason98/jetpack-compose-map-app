package com.mota.pebblebee.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.mota.pebblebee.ui.screens.navigation.NavigationDestinations

class HomeScreenViewModel(
    navController: NavController
) : ViewModel() {

    init {
        navController.navigate(NavigationDestinations.homeScreen)
    }
}