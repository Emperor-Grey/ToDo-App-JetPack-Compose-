package com.example.to_doapp.navigation

import androidx.navigation.NavController
import com.example.to_doapp.utils.Action
import com.example.to_doapp.utils.Constants.LIST_SCREEN

class Screens(navController: NavController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/${taskId}")
    }

}
