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
package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.theme.gray
import com.example.androiddevchallenge.ui.theme.green900
import com.example.androiddevchallenge.ui.theme.pink100
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.white
import com.example.androiddevchallenge.util.firstBaselineToTop

@Composable
fun WelcomeScreen(darkMode: Boolean, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        val welcomeBackgroundRes =
            if (darkMode) R.drawable.ic_dark_welcome_bg else R.drawable.ic_light_welcome_bg
        val welcomeIllosRes =
            if (darkMode) R.drawable.ic_dark_welcome_illos else R.drawable.ic_light_welcome_illos
        val logo = if (darkMode) R.drawable.ic_dark_logo else R.drawable.ic_light_logo
        val subtitle1Color = if (darkMode) white else gray
        val buttonColor = if (darkMode) gray else white
        val loginColor = if (darkMode) white else pink900
        val tintColor = if (darkMode) pink100 else green900

        ConstraintLayout {
            val (background, column) = createRefs()

            Surface(
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxSize()
                    .constrainAs(background) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = welcomeBackgroundRes),
                    contentDescription = "welcome background",
                    contentScale = ContentScale.Fit,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Spacer(modifier = Modifier.height(72.dp))
                Row {
                    Spacer(modifier = Modifier.width(88.dp))
                    Image(
                        imageVector = ImageVector.vectorResource(id = welcomeIllosRes),
                        colorFilter = ColorFilter.tint(tintColor),
                        contentDescription = "Welcome Illos Icon",
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = logo),
                    contentDescription = "logo",
                    tint = tintColor
                )

                Box(modifier = Modifier.height(32.dp)) {
                    Text(
                        text = "Beautiful home garden solutions",
                        style = MaterialTheme.typography.subtitle1,
                        color = subtitle1Color,
                        // TODO modify baseline
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .firstBaselineToTop(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = "Create acocunt",
                        color = buttonColor,
                        style = MaterialTheme.typography.button,

                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Log in",
                    color = loginColor,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.clickable(
                        onClick = {
                            navController.navigate(route = "loginScreen")
                        }
                    )
                )
            }
        }
    }
}

// @Preview("Light Theme2", widthDp = 360, heightDp = 640)
// @Composable
// fun LightPreview2() {
//    MyTheme {
//        WelcomeScreen(darkMode = false, )
//    }
// }
//
// @Preview("Dark Theme2", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview2() {
//    MyTheme(darkTheme = true) {
//        WelcomeScreen(darkMode = true)
//    }
// }
