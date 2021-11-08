package com.mota.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mota.presentation.R
import com.mota.presentation.common.Constants
import com.mota.presentation.ui.screens.account.AccountScreen
import com.mota.presentation.ui.screens.bottomnavigation.NavigationItem
import com.mota.presentation.ui.screens.device.DeviceScreen
import com.mota.presentation.ui.theme.PebblebeeTheme

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    var namePage by remember { mutableStateOf("Account") }
    var sheetPeekHeight by remember { mutableStateOf(120) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
        )
        BottomSheetScaffold(
            modifier = Modifier
                .padding(0.dp, 20.dp, 0.dp, 0.dp),
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = sheetPeekHeight.dp,
            sheetBackgroundColor = Color.White,
            backgroundColor = Color.White,
            sheetContent = { SheetContent(namePage = namePage) },
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = namePage,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            BottomNavigationBar(onNamePageChange = { namePage = it })
        }
    }
}

@ExperimentalMaterialApi
fun caculateFraction(scaffoldState: BottomSheetScaffoldState): Float {
    val fraction = scaffoldState.bottomSheetState.progress.fraction
    val targetValue = scaffoldState.bottomSheetState.targetValue
    val currentValue = scaffoldState.bottomSheetState.currentValue
    return when {
        currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 0f
        currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 1f
        currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> fraction
        else -> 1f - fraction
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SheetContent(namePage: String) {
    when(namePage) {
        Constants.ACCOUNT_PAGE -> {
            AccountScreen()
        }
        Constants.DEVICE_PAGE -> {
            DeviceScreen()
        }
        Constants.SETTING_PAGE -> {
        }
    }
}

@Composable
fun BottomNavigationBar(onNamePageChange: (String) -> Unit) {
    var isSelected by remember { mutableStateOf("account") }
    val items = listOf(
        NavigationItem.Account,
        NavigationItem.Devices,
        NavigationItem.Settings,
    )
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier
            .height(80.dp),
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = isSelected == item.route,
                onClick = {
                    isSelected = when (item) {
                        NavigationItem.Account -> {
                            onNamePageChange(item.title)
                            item.route
                        }
                        NavigationItem.Devices -> {
                            onNamePageChange(item.title)
                            item.route
                        }
                        else -> {
                            onNamePageChange(item.title)
                            item.route
                        }
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PebblebeeTheme {
        HomeScreen()
    }
}