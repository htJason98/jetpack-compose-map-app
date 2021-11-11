package com.mota.presentation.ui.screens.setting

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SettingsScreen() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (btnPull, tabName, divider, listSettings) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(btnPull) {
                    top.linkTo(parent.top, margin = 14.dp)
                },
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(
                modifier = Modifier
                    .size(60.dp, 5.dp)
                    .background(Color.LightGray)
            )
        }
        Text(
            text = "Settings",
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(tabName) {
                top.linkTo(btnPull.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 14.dp)
            }
        )
        Divider(
            modifier = Modifier
                .height(0.5.dp)
                .background(Color.LightGray)
                .constrainAs(divider) {
                    top.linkTo(tabName.bottom, margin = 12.dp)
                }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(listSettings) {
                    top.linkTo(divider.bottom)
                }
        ) {
            GroupList()
        }
    }
}

@Composable
fun GroupList() {
    val groups = listOf(
        SettingItem.Groups,
        SettingItem.Subscription,
        SettingItem.VoiceAssistant,
        SettingItem.Help,
        SettingItem.Shop,
        SettingItem.SendLog,
        SettingItem.PhoneSetting
    )
    val localContext = LocalContext.current
    LazyColumn {
        items(groups) {
            Surface(
                color = Color.White,
                modifier = Modifier
                    .clickable {
                        Toast.makeText(localContext, "${it.itemName}", Toast.LENGTH_SHORT).show()
                    }
            ) {
                GroupItem(it)
            }
        }
    }
}

@Composable
fun GroupItem(settingItem: SettingItem) {
    ConstraintLayout {
        val (icon, divider) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(icon) {
                    top.linkTo(parent.top, margin = 12.dp)
                    start.linkTo(parent.start)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = settingItem.icon),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = settingItem.itemName,
                fontSize = 16.sp,
                color = Color.Black,
            )
        }
        Divider(
            modifier = Modifier
                .height(0.5.dp)
                .background(Color.LightGray)
                .constrainAs(divider) {
                    top.linkTo(icon.bottom, margin = 12.dp)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SettingsScreen()
}