package com.example.to_doapp.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.ViewModels.SharedViewModel
import com.example.to_doapp.utils.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority


    Scaffold(topBar = {
        TaskAppBar(navigateToListScreen = navigateToListScreen, selectedTask = selectedTask)
    }, content = { padValues ->
        TaskContent(
            modifier = Modifier.padding(padValues),
            title = title,
            onTitleChange = {
                sharedViewModel.title.value = it
            },
            description = description,
            onDescriptionChange = {
                sharedViewModel.description.value = it
            },
            priority = priority,
            onPrioritySelected = {
                sharedViewModel.priority.value = it

            },
        )
    })
}
