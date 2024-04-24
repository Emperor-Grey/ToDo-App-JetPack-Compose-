package com.example.to_doapp.ui.screens.list

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.example.to_doapp.R
import com.example.to_doapp.components.PriorityItem
import com.example.to_doapp.data.models.Priority

@Composable
fun ListAppBar() {
    DefaultListAppbar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppbar() {
    TopAppBar(title = {
        Text(text = "Tasks")
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.surface,
        scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
        actionIconContentColor = MaterialTheme.colorScheme.surface,
        navigationIconContentColor = MaterialTheme.colorScheme.surface
    ), actions = {
        ListAppbarActions(onSearchClicked = {}, onSortClicked = {})
    })
}

@Composable
fun ListAppbarActions(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { isExpanded = true }) {
        Icon(imageVector = Icons.Default.FilterList, contentDescription = "Choose Priority")
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }, content = {
            DropdownMenuItem(text = {
                PriorityItem(priority = Priority.LOW)
            }, onClick = {
                isExpanded = false
                onSortClicked(
                    Priority.LOW
                )
            })
            DropdownMenuItem(text = {
                PriorityItem(priority = Priority.MEDIUM)
            }, onClick = {
                isExpanded = false
                onSortClicked(Priority.MEDIUM)
            })
            DropdownMenuItem(text = {
                PriorityItem(priority = Priority.HIGH)
            }, onClick = {
                isExpanded = false
                onSortClicked(Priority.HIGH)
            })
        })
    }
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = onSearchClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_tasks),
        )
    }
}

@Preview(wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultListAppBarPrev() {
    DefaultListAppbar()
}
