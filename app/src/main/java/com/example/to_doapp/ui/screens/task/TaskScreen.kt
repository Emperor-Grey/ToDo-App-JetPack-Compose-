package com.example.to_doapp.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.viewmodel.SharedViewModel
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


    val context = LocalContext.current


    Scaffold(topBar = {
        TaskAppBar(navigateToListScreen = { action ->
            if (action == Action.NO_ACTION) {
                navigateToListScreen(action)
            } else {
                if (sharedViewModel.validateFields()) {
                    navigateToListScreen(action)
                } else {
                    displayToast(context = context)
                }
            }

        }, selectedTask = selectedTask)
    }, content = { padValues ->
        TaskContent(
            modifier = Modifier.padding(padValues),
            title = title,
            onTitleChange = {
                sharedViewModel.updateTitle(it)

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

fun displayToast(context: Context) {
    Toast.makeText(context, "Empty Fields", Toast.LENGTH_SHORT).show()
}
