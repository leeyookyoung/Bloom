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
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.gray
import com.example.androiddevchallenge.ui.theme.grayUnSelected
import com.example.androiddevchallenge.ui.theme.white
import com.example.androiddevchallenge.util.BrowseTheme
import com.example.androiddevchallenge.util.DesignYourHomeGarden
import com.example.androiddevchallenge.util.basicDp
import com.example.androiddevchallenge.util.browseThemeList
import com.example.androiddevchallenge.util.designYourHomeGardenList
import com.example.androiddevchallenge.util.firstBaselineToTop
import com.example.androiddevchallenge.util.sideDp
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun HomeScreen(darkMode: Boolean) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = { HomeScreenBottomLayout(darkMode) }
    ) {
        ContentScreen(darkMode)
    }
}

@Composable
fun ContentScreen(darkMode: Boolean) {
    val textColor = if (darkMode) white else gray

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(bottom = 56.dp)
            .navigationBarsPadding(),
    ) {
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(start = sideDp, end = sideDp),
                value = "",
                onValueChange = { },
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                        Spacer(modifier = Modifier.width(basicDp))
                        Text(
                            text = "Search",
                            color = textColor,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                }
            )
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .padding(start = sideDp)
            ) {
                Text(
                    text = "Browse Themes",
                    color = textColor,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .firstBaselineToTop(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(sideDp))
            Row(
                Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(start = sideDp, end = sideDp)
            ) {
                for (browseTheme in browseThemeList) {
                    BrowseThemesContent(browseTheme, darkMode)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .padding(start = sideDp, end = sideDp)
            ) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val (text, filterList) = createRefs()
                    Text(
                        text = "Design your home garden",
                        color = textColor,
                        style = MaterialTheme.typography.h1,
                        modifier = Modifier
                            .firstBaselineToTop(32.dp)
                            .constrainAs(text) {
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "filter list icon",
                        modifier = Modifier
                            .size(24.dp)
                            .constrainAs(filterList) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(sideDp))
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = sideDp, end = sideDp)
            ) {
                for (gardenYhg in designYourHomeGardenList) {
                    DesignYourHomeGardenContent(
                        designYourHomeGarden = gardenYhg,
                        darkMode = darkMode
                    )
                }
            }
        }
    }
}

@Composable
fun DesignYourHomeGardenContent(designYourHomeGarden: DesignYourHomeGarden, darkMode: Boolean) {
    val textColor = if (darkMode) white else gray
    Surface(color = MaterialTheme.colors.background) {
        Row(
            modifier = Modifier
                .padding(bottom = basicDp)
        ) {
            Image(
                painter = painterResource(id = designYourHomeGarden.resId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(
                        RoundedCornerShape(2.dp)
                    ),
                contentScale = ContentScale.Crop
            )
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = sideDp, end = sideDp)
            ) {
                val (text, description, check, space1, divider, space2) = createRefs()
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .padding(start = basicDp)
                        .constrainAs(text) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                ) {
                    Text(
                        text = designYourHomeGarden.name,
                        modifier = Modifier.firstBaselineToTop(24.dp),
                        style = MaterialTheme.typography.h2,
                        color = textColor
                    )
                }
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .padding(start = basicDp)
                        .constrainAs(description) {
                            top.linkTo(text.bottom)
                            start.linkTo(text.start)
                        }
                ) {
                    Text(
                        text = designYourHomeGarden.description,
                        modifier = Modifier.firstBaselineToTop(16.dp),
                        style = MaterialTheme.typography.body1,
                        color = textColor
                    )
                }

                Checkbox(
                    checked = designYourHomeGarden.checked,
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = MaterialTheme.colors.background
                    ),
                    onCheckedChange = {
                        designYourHomeGarden.checked = it
                    },
                    modifier = Modifier
                        .constrainAs(check) {
                            end.linkTo(parent.end)
                            bottom.linkTo(description.bottom)
                        }
                )
                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                        .constrainAs(space1) {
                            top.linkTo(description.bottom)
                            start.linkTo(parent.start)
                        }
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(divider) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        },

                )
            }
        }
    }
}

@Composable
fun BrowseThemesContent(browseTheme: BrowseTheme, darkMode: Boolean) {
    val textColor = if (darkMode) white else gray

    Column() {
        Card(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.padding(end = basicDp),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 4.dp
        ) {
            Column {
                Image(
                    painter = painterResource(id = browseTheme.resId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 125.dp, height = 96.dp)
                        .clip(
                            RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                        ),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .padding(start = sideDp)
                ) {
                    Text(
                        text = browseTheme.name,
                        color = textColor,
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier
                            .firstBaselineToTop(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(sideDp))
            }
        }
        Spacer(modifier = Modifier.height(basicDp))
    }
}

@Composable
fun HomeScreenBottomLayout(darkMode: Boolean) {
    val textColor = if (darkMode) white else gray
    Surface(elevation = sideDp, color = MaterialTheme.colors.primary) {
        ConstraintLayout(
            modifier = Modifier
                .navigationBarsPadding()
                .height(height = 56.dp)
                .fillMaxWidth()
        ) {
            val (home, favorites, profile, cart) = createRefs()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(home) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(favorites.start)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Bottom Navigation Home",
                    modifier = Modifier.size(24.dp),
                    tint = textColor
                )
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.caption,
                    color = textColor
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(favorites) {
                    top.linkTo(parent.top)
                    start.linkTo(home.end)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(profile.start)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Bottom Navigation Favorites",
                    modifier = Modifier.size(24.dp),
                    tint = grayUnSelected
                )
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.caption,
                    color = grayUnSelected
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(profile) {
                    top.linkTo(parent.top)
                    start.linkTo(favorites.end)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(cart.start)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Bottom Navigation Profile",
                    modifier = Modifier.size(24.dp),
                    tint = grayUnSelected
                )
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.caption,
                    color = grayUnSelected
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.constrainAs(cart) {
                    top.linkTo(parent.top)
                    start.linkTo(profile.end)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Bottom Navigation Cart",
                    modifier = Modifier.size(24.dp),
                    tint = grayUnSelected
                )
                Text(
                    text = "Cart",
                    style = MaterialTheme.typography.caption,
                    color = grayUnSelected
                )
            }
        }
    }
}

@Preview("Light Theme3", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview3() {
    MyTheme {
        HomeScreen(darkMode = false)
    }
}

@Preview("Dark Theme3", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview3() {
    MyTheme(darkTheme = true) {
        HomeScreen(darkMode = true)
    }
}
