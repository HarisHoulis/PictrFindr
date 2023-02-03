package com.houlis.haris.list.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.houlis.haris.list.ui.PicturesRoute

const val picturesRoute = "pictures_route"

fun NavGraphBuilder.picturesGraph() {
    composable(route = picturesRoute) {
        PicturesRoute()
    }
}
