package com.example.to_doapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.to_doapp.navigation.destinations.listComposable
import com.example.to_doapp.navigation.destinations.taskComposable
import com.example.to_doapp.utils.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(navigateToTaskScreen = screen.task)
        taskComposable(navigateToHomeScreen = screen.list)
    }

}
