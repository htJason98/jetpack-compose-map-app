package com.mota.pebblebee.ui.screens.devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mota.pebblebee.R
import com.mota.pebblebee.ui.screens.bottomnavigation.NavigationItem
import com.mota.pebblebee.ui.theme.PebblebeeTheme

@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    var namePage by remember { mutableStateOf("Account") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        val coroutineScope = rememberCoroutineScope()
        BottomSheetScaffold(
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp),
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 120.dp,
            sheetBackgroundColor = Color.Yellow,
            backgroundColor = Color.White,
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .size(50.dp, 4.dp)
                                .background(color = Color.Gray)
                                .alpha(0.1f)
                        ) { }
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = namePage,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }
                }
            },
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

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PebblebeeTheme {
        HomeScreen()
    }
}