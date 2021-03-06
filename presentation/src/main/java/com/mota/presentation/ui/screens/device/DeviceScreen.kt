package com.mota.presentation.ui.screens.device

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.domain.entities.PbDevice
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.maps.model.LatLng
import com.mota.presentation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun DeviceScreen(onDataChange: (PbDevice?) -> Unit) {
    var tabSelected by remember { mutableStateOf("Mine") }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (row, deviceTab, divider, deviceList) = createRefs()
        Row(
            modifier = Modifier
                .size(50.dp, 4.dp)
                .background(Color.Gray)
                .constrainAs(row) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 12.dp)
                },
            horizontalArrangement = Arrangement.Center
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(deviceTab) {
                    start.linkTo(parent.start)
                    top.linkTo(row.bottom, margin = 14.dp)
                }
        ) {
            TabPage(onTabSelectChanged = { tabSelected = it })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
                .constrainAs(divider) {
                    top.linkTo(deviceTab.bottom)
                    start.linkTo(deviceTab.start)
                }
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(deviceList) {
                    top.linkTo(divider.bottom)
                    start.linkTo(divider.start)
                }
        ) {
            if (tabSelected == "Mine") MinePage(onDataChange) else SharedPage()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun TabPage(onTabSelectChanged: (String) -> Unit) {
    val tabs = listOf(
        TabItem.Mine,
        TabItem.Shared
    )
    Tabs(tabs, onTabSelectChanged)
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Tabs(tabs: List<TabItem>, onTabSelectChanged: (String) -> Unit) {
    var tabIndex by remember { mutableStateOf(0) }
    var colorIndicatorTab by remember { mutableStateOf(Color.Yellow) }
    TabRow(
        selectedTabIndex = tabIndex,
        contentColor = Color.Black,
        backgroundColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(tabPositions[tabIndex]),
                height = 3.dp,
                color = colorIndicatorTab
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = tabIndex == index,
                onClick = {
                    tabIndex = index
                    colorIndicatorTab = Color(tab.color)
                    onTabSelectChanged(tab.title)
                },
                text = { Text(text = tab.title, fontSize = 18.sp, fontFamily = FontFamily.Default) }
            )
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 60.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}

@Composable
fun MinePage(onDataChange: (PbDevice?) -> Unit) {
    val models = listOf(
        PbDevice("1ac","124342", "Finder's Huu", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("2ac","151512", "Finder's Dan", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("3ac","121525", "Finder's Tony", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("4ac","136323", "Found 's Huu", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("5ac","135312", "BlackCard 's Huu", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("6ac","175453", "Honey's Huu", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("7ac","135773", "Finder's Vet", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("8ac","186945", "Finder's Ka", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("9ac","114515", "BlackCard's Ka", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("7ad","174788", "Finder's Loi", LatLng(0.1121, 10.004), 19085038503, ""),
        PbDevice("8ad","124267", "Finder's Hong", LatLng(0.1121, 10.004), 19085038503, ""),
    )
    LazyColumn(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 170.dp)
    ) {
        items(models) {
            Surface(
                color = Color.White,
                modifier = Modifier.clickable {
                    onDataChange(it)
                }) {
                DeviceItem(pbDevice = it)
            }
        }
    }
}

@Composable
fun SharedPage() {
    Text(text = "Shared", color = Color.Black)
}

@Composable
fun DeviceItem(pbDevice: PbDevice) {
    ConstraintLayout {
        val (deviceIcon, name, location, lastSeen, divider) = createRefs()
        Box(
            modifier = Modifier
                .size(70.dp, 70.dp)
                .clip(RoundedCornerShape(45))
                .background(Color.LightGray)
                .constrainAs(deviceIcon) {
                    top.linkTo(parent.top, margin = 5.dp)
                    start.linkTo(parent.start, margin = 12.dp)
                }
        )
        Text(
            text = pbDevice.deviceName,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(deviceIcon.end, margin = 10.dp)
                }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .constrainAs(location) {
                    top.linkTo(name.bottom, margin = 4.dp)
                    start.linkTo(name.start)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                modifier = Modifier.size(15.dp, 15.dp)
            )
            Text(
                text = "Th??nh ph??? H??? Ch?? Minh",
                color = Color.LightGray,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painterResource(id = R.drawable.ic_time),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp, 15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "1 hour ago",
                color = Color.LightGray,
                fontSize = 14.sp,
                lineHeight = 14.sp,
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .background(Color.LightGray)
                .constrainAs(lastSeen) {
                    top.linkTo(name.bottom, margin = 4.dp)
                    start.linkTo(location.end, margin = 8.dp)
                }
        ) {

        }
        Divider(
            modifier = Modifier
                .background(Color.LightGray)
                .constrainAs(divider) {
                    top.linkTo(deviceIcon.bottom, margin = 5.dp)
                    start.linkTo(deviceIcon.end)
                },
            thickness = 0.5.dp
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun DetailDeviceBottomSheet(deviceData: PbDevice?, onDataChange: (PbDevice?) -> Unit) {
    var currentDevice: PbDevice? = null
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = state,
        sheetBackgroundColor = Color.White,
        sheetContent = { DeviceDetailView(deviceData, onDataChange, scope, state) },
    ) {
        if (currentDevice != deviceData) {
            scope.launch {
                state.show()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DeviceDetailView(
    deviceData: PbDevice?,
    onDataChange: (PbDevice?) -> Unit,
    scope: CoroutineScope,
    state: ModalBottomSheetState
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (btnPull, btnBack, deviceName, divider, info) = createRefs()
        Row(
            modifier = Modifier
                .size(50.dp, 4.dp)
                .background(Color.Gray)
                .constrainAs(btnPull) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 12.dp)
                },
            horizontalArrangement = Arrangement.Center
        ) {}
        Image(
            painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    scope.launch {
                        state.hide()
                        onDataChange(null)// reset data when closing the detail sheet
                    }
                }
                .constrainAs(btnBack) {
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(parent.top, margin = 24.dp)
                }
        )
        Text(
            text = deviceData?.deviceName ?: "No data",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(deviceName) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(btnBack.end, margin = 20.dp)
                }
        )
        Divider(
            modifier = Modifier
                .height(0.5.dp)
                .background(Color.LightGray)
                .constrainAs(divider) {
                    top.linkTo(deviceName.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "macAddress: ${deviceData?.macAddress}\n" +
                    "guid: ${deviceData?.guid}\n" +
                    "latLng: ${deviceData?.latlng}\n" +
                    "timeStamp: ${deviceData?.timeStamp}\n" +
                    "imageUrl: ${deviceData?.imageUrl}",
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(info) {
                top.linkTo(divider.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeviceScreen(onDataChange = { it })
}