/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.util.basicDp
import com.example.androiddevchallenge.util.firstBaselineToTop
import com.example.androiddevchallenge.util.sideDp

@Composable
fun LoginScreen(darkMode: Boolean, navController: NavController) {
    val emailStateStr = remember { mutableStateOf("") }
    val passwordStateStr = remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        val textColor = if (darkMode) white else gray
        val buttonColor = if (darkMode) gray else white
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.height(184.dp)) {
                Text(
                    text = "Log in with email",
                    style = MaterialTheme.typography.h1,
                    color = textColor,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .firstBaselineToTop(184.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = sideDp, end = sideDp),
                value = emailStateStr.value,
                onValueChange = { emailStateStr.value = it },
                label = {
                    Text(
                        text = "Email address",
                        color = textColor,
                        style = MaterialTheme.typography.body1,
                    )
                }
            )
            Spacer(modifier = Modifier.height(basicDp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = sideDp, end = sideDp),
                visualTransformation = PasswordVisualTransformation(),
                value = passwordStateStr.value,
                onValueChange = { passwordStateStr.value = it },
                label = {
                    Text(
                        text = "Password (8+ characters)",
                        color = textColor,
                        style = MaterialTheme.typography.body1,
                    )
                }
            )
            Box(modifier = Modifier.height(24.dp)) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = sideDp, end = sideDp)
                ) {
                    Text(
                        text = "By clicking below, you agree to our ",
                        style = MaterialTheme.typography.body2,
                        color = textColor,
                        modifier = Modifier
                            .firstBaselineToTop(24.dp)
                    )
                    Text(
                        text = "Terms of Use",
                        style = MaterialTheme.typography.body2.copy(
                            textDecoration = TextDecoration.Underline
                        ),
                        color = textColor,
                        modifier = Modifier
                            .firstBaselineToTop(24.dp)
                    )
                    Text(
                        text = " and consent",
                        style = MaterialTheme.typography.body2,
                        color = textColor,
                        modifier = Modifier
                            .firstBaselineToTop(24.dp)
                    )
                }
            }
            Box(modifier = Modifier.height(16.dp)) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(start = sideDp, end = sideDp)
                ) {
                    Text(
                        text = "to our ",
                        style = MaterialTheme.typography.body2,
                        color = textColor,
                        modifier = Modifier
                            .firstBaselineToTop(16.dp)
                    )
                    Text(
                        text = "Privacy Policy",
                        style = MaterialTheme.typography.body2.copy(
                            textDecoration = TextDecoration.Underline
                        ),
                        color = textColor,
                        modifier = Modifier
                            .firstBaselineToTop(16.dp)
                    )
                    Text(
                        text = ".",
                        style = MaterialTheme.typography.body2,
                        color = textColor,
                        modifier = Modifier
                            .firstBaselineToTop(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(sideDp))
            Button(
                onClick = {
                    navController.navigate(route = "homeScreen")
                },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = "Log in",
                    color = buttonColor,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

// @Preview("Light Theme3", widthDp = 360, heightDp = 640)
// @Composable
// fun LightPreview3() {
//    MyTheme {
//        LoginScreen(darkMode = false)
//    }
// }
//
// @Preview("Dark Theme2", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview3() {
//    MyTheme(darkTheme = true) {
//        LoginScreen(true)
//    }
// }
