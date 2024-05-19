package com.example.to_doapp.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.utils.Action

@Composable
fun TaskScreen(navigateToListScreen: (Action) -> Unit, selectedTask: ToDoTask?) {
    Scaffold(topBar = {
        TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
    }, content = { padValues ->
        TaskContent(
            modifier = Modifier.padding(padValues),
            title = "",
            onTitleChange = {},
            description = "",
            onDescriptionChange = {},
            priority = Priority.LOW,
            onPrioritySelected = {},
        )
    })
}
