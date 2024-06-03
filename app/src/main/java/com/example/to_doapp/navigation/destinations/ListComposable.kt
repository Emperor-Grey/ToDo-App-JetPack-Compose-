package com.example.to_doapp.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_doapp.ui.screens.list.ListScreen
import com.example.to_doapp.ui.viewmodel.SharedViewModel
import com.example.to_doapp.utils.Constants.LIST_ARGUMENT_KEY
import com.example.to_doapp.utils.Constants.LIST_SCREEN
import com.example.to_doapp.utils.toAction


fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit, sharedViewModel: SharedViewModel
) {
    composable(route = LIST_SCREEN, arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
        type = NavType.StringType
    })) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel = sharedViewModel)
    }
}
