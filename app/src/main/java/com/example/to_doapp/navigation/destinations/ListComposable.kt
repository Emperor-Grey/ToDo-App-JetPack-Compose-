package com.example.to_doapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.screens.list.ListScreen
import com.example.to_doapp.ui.theme.ViewModels.SharedViewModel
import com.example.to_doapp.utils.Constants.LIST_ARGUMENT_KEY
import com.example.to_doapp.utils.Constants.LIST_SCREEN


fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
    ,sharedViewModel: SharedViewModel
) {
    composable(route = LIST_SCREEN, arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
        type = NavType.StringType
    })) {
        ListScreen(navigateToTaskScreens = navigateToTaskScreen,sharedViewModel = sharedViewModel)
    }
}
