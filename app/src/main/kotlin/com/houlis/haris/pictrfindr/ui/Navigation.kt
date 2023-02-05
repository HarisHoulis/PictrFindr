package com.houlis.haris.pictrfindr.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.houlis.haris.feature.list.ui.navigation.picturesGraph
import com.houlis.haris.feature.list.ui.navigation.picturesRoute

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = picturesRoute) {
        picturesGraph()
    }
}
