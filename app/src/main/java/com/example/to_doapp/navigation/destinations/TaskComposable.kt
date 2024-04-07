package com.example.to_doapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.utils.Action
import com.example.to_doapp.utils.Constants

fun NavGraphBuilder.taskComposable(navigateToHomeScreen: (Action) -> Unit) {

    composable(
        route = Constants.TASK_SCREEN, arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) {

    }
}
