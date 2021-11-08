package com.mota.pebblebee.ui.screens.account

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mota.pebblebee.R

@Composable
fun AccountScreen() {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (text,
            row,
            imageView,
            cameraView,
            tvFirstName,
            tvLastName,
            toggleSetDefault,
            togglePinCode,
            btnLogout,
            dividerFirstTop,
            dividerFirstBottom,
            dividerLastTop) = createRefs()
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
        Text(
            text = "huutq@devblock.net",
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 18.dp)
                start.linkTo(parent.start, margin = 12.dp)
            },
            fontWeight = FontWeight.Bold,
        )
        Box(
            modifier = Modifier
                .size(110.dp, 110.dp)
                .clip(RoundedCornerShape(44))
                .background(Color.Gray)
                .constrainAs(imageView) {
                    start.linkTo(parent.start, margin = 12.dp)
                    top.linkTo(text.bottom, margin = 14.dp)
                }
        )
        Image(
            painterResource(id = R.drawable.icon_change_photo),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(cameraView) {
                    end.linkTo(imageView.end)
                    top.linkTo(imageView.top)
                }
                .clickable {
                    requestOpenGallery(context = context)
                }
        )
        ProvideTextStyle(TextStyle(color = Color.Black)) {
            TextField(
                value = "Huu",
                onValueChange = {
                },
                modifier = Modifier
                    .constrainAs(tvFirstName) {
                        start.linkTo(imageView.end, margin = 12.dp)
                        top.linkTo(text.bottom, margin = 14.dp)
                    }
                    .background(Color.White),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )
        }
        ProvideTextStyle(TextStyle(color = Color.Black)) {
            TextField(
                value = "Tran",
                onValueChange = {
                },
                modifier = Modifier
                    .constrainAs(tvLastName) {
                        start.linkTo(imageView.end, margin = 12.dp)
                        top.linkTo(tvFirstName.bottom)
                    }
                    .background(Color.White),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White)
            )
        }
        Divider(
            modifier = Modifier
                .background(Color.Gray)
                .constrainAs(dividerFirstTop) {
                    top.linkTo(tvFirstName.top)
                    start.linkTo(tvFirstName.start)
                },
            thickness = 0.3.dp
        )
        Divider(
            modifier = Modifier
                .background(Color.Gray)
                .constrainAs(dividerFirstBottom) {
                    top.linkTo(tvFirstName.bottom)
                    start.linkTo(tvFirstName.start)
                },
            thickness = 0.3.dp
        )
        Divider(
            modifier = Modifier
                .background(Color.Gray)
                .constrainAs(dividerLastTop) {
                    top.linkTo(tvLastName.bottom)
                    start.linkTo(tvLastName.start)
                },
            thickness = 0.3.dp
        )
        Row(
            modifier = Modifier.constrainAs(toggleSetDefault) {
                top.linkTo(imageView.bottom, margin = 18.dp)
                start.linkTo(imageView.start)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.account_default_device_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 5.dp, 0.dp)
                    .size(30.dp, 30.dp)
            )
            Text(
                text = "Set Default Device",
                color = Color.Black,
                fontSize = 16.sp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp),
                horizontalArrangement = Arrangement.End
            ) {
                var isChecked by remember { mutableStateOf(true) }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40))
                        .background(Color.LightGray)
                ) {
                    Switch(
                        checked = isChecked,
                        onCheckedChange = {
                            isChecked = !isChecked
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White
                        ),
                    )
                }
            }
        }
        Row(
            modifier = Modifier.constrainAs(togglePinCode) {
                top.linkTo(toggleSetDefault.bottom, margin = 18.dp)
                start.linkTo(toggleSetDefault.start)
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = R.drawable.account_pin_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 5.dp, 0.dp)
                    .size(30.dp, 30.dp)
            )
            Text(
                text = "PIN Code",
                color = Color.Black,
                fontSize = 16.sp,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp),
                horizontalArrangement = Arrangement.End
            ) {
                var isChecked by remember { mutableStateOf(true) }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40))
                        .background(Color.LightGray)
                ) {
                    Switch(
                        checked = isChecked,
                        onCheckedChange = {
                            isChecked = !isChecked
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White
                        )
                    )
                }
            }
        }
        Row(
            modifier = Modifier.constrainAs(btnLogout) {
                top.linkTo(togglePinCode.bottom, margin = 18.dp)
                start.linkTo(togglePinCode.start)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.account_logout_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 5.dp, 0.dp)
                    .size(30.dp, 30.dp)
            )
            Text(
                text = "Log Out",
                color = Color.Red,
                fontSize = 16.sp,
            )
        }
    }
}

fun requestOpenGallery(context: Context) {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AccountScreen()
}