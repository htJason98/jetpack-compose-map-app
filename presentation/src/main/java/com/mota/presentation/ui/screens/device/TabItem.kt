package com.mota.presentation.ui.screens.device

import android.graphics.Color
import androidx.compose.runtime.Composable

sealed class TabItem(var color: Int, var title: String) {
    object Mine : TabItem(Color.YELLOW, "Mine")
    object Shared : TabItem(Color.BLUE, "Shared")
}
