package com.example.to_doapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.screens.task.TaskScreen
import com.example.to_doapp.utils.Action
import com.example.to_doapp.utils.Constants
import com.example.to_doapp.utils.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(navigateToListScreen: (Action) -> Unit) {

    composable(
        route = Constants.TASK_SCREEN, arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStack ->
        val taskid = navBackStack.arguments!!.getInt(TASK_ARGUMENT_KEY)

        TaskScreen(navigateToListScreen = navigateToListScreen)
    }
}
