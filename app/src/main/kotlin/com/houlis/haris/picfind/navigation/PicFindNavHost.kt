package com.houlis.haris.picfind.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.houlis.haris.picfind.feature.details.navigation.navigateToDetails
import com.houlis.haris.picfind.feature.details.navigation.picDetailsScreen
import com.houlis.haris.picfind.feature.list.ui.navigation.PICTURES_ROUTE
import com.houlis.haris.picfind.feature.list.ui.navigation.picturesScreen
import com.houlis.haris.picfind.ui.PicFindAppState

/**
 * Top-level navigation graph.
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun PicFindNavHost(
    appState: PicFindAppState,
    modifier: Modifier = Modifier,
    startDestination: String = PICTURES_ROUTE,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        picturesScreen(navController::navigateToDetails)
        picDetailsScreen(navController::popBackStack)
    }
}
