package com.houlis.haris.picfind.feature.list.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.houlis.haris.picfind.feature.list.ui.PicturesRoute

const val PICTURES_ROUTE = "pictures_route"

fun NavController.navigateToPictures(navOptions: NavOptions) {
    navigate(PICTURES_ROUTE, navOptions)
}

fun NavGraphBuilder.picturesScreen(onNavigateToDetails: (picId: String) -> Unit) {
    composable(route = PICTURES_ROUTE) {
        PicturesRoute { picId ->
            onNavigateToDetails(picId)
        }
    }
}
