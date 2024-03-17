package com.houlis.haris.picfind.feature.list.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.houlis.haris.picfind.feature.list.ui.PicturesRoute

const val picturesRoute = "pictures_route"

fun NavGraphBuilder.picturesScreen(navigateToDetails: (picId: String) -> Unit) {
    composable(route = picturesRoute) {
        PicturesRoute { picId ->
            navigateToDetails(picId)
        }
    }
}
