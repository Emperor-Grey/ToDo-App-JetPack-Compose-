package com.example.to_doapp.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.ViewModels.SharedViewModel
import com.example.to_doapp.ui.screens.task.TaskScreen
import com.example.to_doapp.utils.Action
import com.example.to_doapp.utils.Constants
import com.example.to_doapp.utils.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit, sharedViewModel: SharedViewModel
) {

    composable(
        route = Constants.TASK_SCREEN, arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStack ->
        val taskId = navBackStack.arguments!!.getInt(TASK_ARGUMENT_KEY)

        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTasks.collectAsState()

        LaunchedEffect(key1 = taskId) {
            sharedViewModel.updateTaskFields(selectedTasks = selectedTask)
        }

        TaskScreen(
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel
        )
    }
}
