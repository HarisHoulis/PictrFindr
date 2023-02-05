package com.houlis.haris.pictrfindr.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.houlis.haris.feature.details.navigation.detailsScreen
import com.houlis.haris.feature.details.navigation.navigateToDetails
import com.houlis.haris.feature.list.ui.navigation.picturesRoute
import com.houlis.haris.feature.list.ui.navigation.picturesScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = picturesRoute) {
        picturesScreen { picId ->
            navController.navigateToDetails(picId)
        }
        detailsScreen {
            navController.popBackStack()
        }
    }
}
