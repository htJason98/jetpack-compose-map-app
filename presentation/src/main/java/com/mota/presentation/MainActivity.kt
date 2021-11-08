package com.mota.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mota.presentation.ui.theme.PebblebeeTheme
import com.mota.presentation.ui.screens.navigation.NavigationHomeScreen

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            PebblebeeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationHomeScreen().initNavigationController()
                }
            }
        }
    }
}

