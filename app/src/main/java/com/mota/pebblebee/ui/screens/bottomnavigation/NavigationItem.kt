package com.mota.pebblebee.ui.screens.bottomnavigation

import com.mota.pebblebee.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Account : NavigationItem("account", R.drawable.ic_account, "Account")
    object Devices : NavigationItem("devices", R.drawable.ic_devices, "Devices")
    object Settings : NavigationItem("settings", R.drawable.ic_settings, "Settings")
}