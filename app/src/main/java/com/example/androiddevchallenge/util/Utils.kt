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
package com.example.androiddevchallenge.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

// TODO not perfectly have to modify
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = Modifier.layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.toPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height.toInt()) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY.toInt())
    }
}

var basicDp = 8.dp
var sideDp = 16.dp

data class BrowseTheme(
    val id: Int,
    val name: String,
    val resId: Int
)
val browseThemeList: List<BrowseTheme> = listOf(
    BrowseTheme(1, "Desert chic", R.drawable.desert_chic),
    BrowseTheme(2, "Tiny terrariums", R.drawable.tiny_terraiums),
    BrowseTheme(3, "Jungle vibes", R.drawable.jungle_vibes),
    BrowseTheme(4, "Easy care", R.drawable.easy_care),
    BrowseTheme(5, "Statements", R.drawable.statements),
)

data class DesignYourHomeGarden(
    val id: Int,
    val name: String,
    val resId: Int,
    val description: String,
    var checked: Boolean
)
val designYourHomeGardenList: List<DesignYourHomeGarden> = listOf(
    DesignYourHomeGarden(1, "Monstera", R.drawable.monstera, "This is description", true),
    DesignYourHomeGarden(2, "Aglaonema", R.drawable.alagonema, "This is description", false),
    DesignYourHomeGarden(3, "Peace lily", R.drawable.peace_lily, "This is description", false),
    DesignYourHomeGarden(4, "Fiddle leaf tree", R.drawable.fiddle_leaf_tree, "This is description", false),
    DesignYourHomeGarden(5, "Snake plant", R.drawable.snake_plant, "This is description", false),
    DesignYourHomeGarden(6, "Pothos", R.drawable.pothos, "This is description", false),
)
