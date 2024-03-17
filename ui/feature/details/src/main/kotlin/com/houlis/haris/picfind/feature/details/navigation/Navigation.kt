package com.houlis.haris.picfind.feature.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.houlis.haris.picfind.feature.details.ui.DetailsRoute

private const val detailsRoute = "details_route/"
private const val picIdArg = "pic_id"

fun NavController.navigateToDetails(picId: String) {
    navigate(detailsRoute + picId)
}

fun NavGraphBuilder.detailsScreen(onBackClicked: () -> Unit) {
    composable(
        route = "$detailsRoute{$picIdArg}",
        arguments = listOf(navArgument(picIdArg) { type = NavType.StringType })
    ) {
        DetailsRoute(
            picId = requireNotNull(it.arguments?.getString(picIdArg)),
            onBackClicked = onBackClicked
        )
    }
}
