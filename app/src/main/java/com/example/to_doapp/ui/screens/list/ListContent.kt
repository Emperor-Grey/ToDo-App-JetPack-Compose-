package com.example.to_doapp.ui.screens.list

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.ui.theme.LARGE_PADDING
import com.example.to_doapp.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.to_doapp.ui.theme.TASK_ITEM_ELEVATION
import com.example.to_doapp.utils.RequestState

@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    modifier: Modifier
) {
    if (tasks is RequestState.Success) {
        if (tasks.data.isEmpty()) {
            EmptyContent()
        } else {
            DisplayTasks(
                tasks = tasks.data, navigateToTaskScreen = navigateToTaskScreen, modifier = modifier
            )
        }
    }
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>, navigateToTaskScreen: (taskId: Int) -> Unit, modifier: Modifier
) {
    LazyColumn(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        items(items = tasks, key = { task -> task.id }) { taskItem ->
            TaskItem(taskItem = taskItem, navigateTaskScreens = navigateToTaskScreen)
        }
    }
}

@Composable
fun TaskItem(taskItem: ToDoTask, navigateTaskScreens: (taskId: Int) -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent,
        shape = RectangleShape,
        tonalElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateTaskScreens(taskItem.id)
        }) {
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = taskItem.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(color = taskItem.priority.color)
                    }
                }
            }
            Text(
                text = taskItem.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = TASK_ITEM_ELEVATION),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TaskItemPrev() {
    TaskItem(taskItem = ToDoTask(
        id = 1,
        title = "Compose Test",
        description = "Some Random Text",
        priority = Priority.MEDIUM
    ), navigateTaskScreens = {})
}
