package com.houlis.haris.picfind.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.houlis.haris.picfind.feature.list.ui.navigation.PICTURES_ROUTE
import com.houlis.haris.picfind.feature.list.ui.navigation.navigateToPictures
import com.houlis.haris.picfind.navigation.TopLevelDestination
import com.houlis.haris.picfind.navigation.TopLevelDestination.PICTURES

@Composable
fun rememberPicFindAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    PicFindAppState(navController)
}

@Stable
class PicFindAppState(val navController: NavHostController) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            PICTURES_ROUTE -> PICTURES
            else -> null
        }

    // TODO can be deleted probably
    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            PICTURES -> navController.navigateToPictures(topLevelNavOptions)
        }
    }
}
