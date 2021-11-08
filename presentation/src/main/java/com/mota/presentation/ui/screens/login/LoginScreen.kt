package com.mota.presentation.ui.screens.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mota.presentation.R
import com.mota.presentation.common.Constants
import com.mota.presentation.ui.screens.home.HomeScreenViewModel
import com.mota.presentation.ui.theme.PebblebeeTheme

const val TAG = "LoginScreen"

@Composable
fun LoginScreen(navController: NavController?) {
    Image(
        painterResource(id = R.drawable.cropped_map_background),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        alpha = 0.1F
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Row(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painterResource(id = R.drawable.pebblebee_logo),
                "Logo",
                modifier = Modifier.scale(0.8f)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.weight(1.5f),
            horizontalArrangement = Arrangement.Center
        ) {
            VerifyScreen(navController = navController)
        }
    }
}

@Composable
fun VerifyScreen(navController: NavController?) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(7))
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column {
            var typeView by rememberSaveable { mutableStateOf(Constants.EMAIL_SCREEN) }
            Spacer(Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val textTitle =
                    if (typeView == Constants.EMAIL_SCREEN) "One Time Login" else "Enter Activation Code"
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = textTitle,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.h3
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .size(50.dp, 2.dp)
                    .background(color = Color.Yellow)
                    .align(Alignment.CenterHorizontally),
            ) { }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                val text = if (typeView == Constants.EMAIL_SCREEN) {
                    "No confusing passwords or accounts.\nYour email is only used to connect you to your\n Pebblebee account."
                } else {
                    "Your activation code has been send to"
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.body2,
                    lineHeight = 19.sp
                )
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                var textHint by remember { mutableStateOf("Email Address") }
                var count by remember { mutableStateOf(1) }
                val keyboardType: KeyboardType
                if (typeView == Constants.EMAIL_SCREEN) {
                    textHint = "Email Address"
                    keyboardType = KeyboardType.Text
                } else {
                    textHint = "Activation Code"
                    keyboardType = KeyboardType.Number
                    count = 1
                }
                ProvideTextStyle(TextStyle(color = Color.Gray)) {
                    TextField(
                        modifier = Modifier
                            .background(color = Color.White)
                            .onFocusEvent {
                                if (it.isFocused && count == 1) {
                                    count++
                                    textHint = ""
                                }
                                Log.d(TAG, "onFocusEvent: $it")
                            },
                        value = textHint,
                        onValueChange = {
                            textHint = it
                        },
                        maxLines = 1,
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType)
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 0.dp),
                color = Color.Gray,
                thickness = 0.5.dp
            )
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                var count by remember { mutableStateOf(1) }
                Button(
                    onClick = {
                        if (count == 1) {
                            typeView = Constants.PIN_SCREEN
                        } else {
                            navController?.let { HomeScreenViewModel(it) }
                        }
                        count++
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    shape = RoundedCornerShape(100),
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Text(
                        modifier = Modifier.padding(60.dp, 4.dp),
                        text = "Submit",
                        style = MaterialTheme.typography.body1,
                        color = Color.White
                    )
                }
            }
            Spacer(Modifier.height(35.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "By signing up, you agree to our\n Terms of Use and Privacy Policy",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.body2,
                    lineHeight = 19.sp
                )
            }
            Spacer(Modifier.height(30.dp))
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PebblebeeTheme {
        LoginScreen(null)
    }
}