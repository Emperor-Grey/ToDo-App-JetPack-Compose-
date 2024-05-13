package com.example.to_doapp.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.to_doapp.utils.Action

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit) {
    Scaffold(topBar = {
        TaskAppBar(navigateToListScreen = navigateToListScreen)
    }, content = {
        val padding = it
    })
}
