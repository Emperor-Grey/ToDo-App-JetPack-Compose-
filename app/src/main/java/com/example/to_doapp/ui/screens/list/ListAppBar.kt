package com.example.to_doapp.ui.screens.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.example.to_doapp.R
import com.example.to_doapp.components.PriorityItem
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.ui.theme.LARGE_PADDING
import com.example.to_doapp.ui.theme.ViewModels.SharedViewModel
import com.example.to_doapp.utils.SearchAppBarState
import com.example.to_doapp.utils.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel, searchAppBarState: SearchAppBarState, searchTextSate: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppbar(onSearchClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                sharedViewModel.searchTextState.value = ""
            }, onSortClicked = {})

        }

        else -> {
            SearchAppBar(text = searchTextSate, onSearchClicked = {}, onTextChanged = { newText ->
                sharedViewModel.searchTextState.value = newText

            }, onCloseClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                sharedViewModel.searchTextState.value = ""
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppbar(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    TopAppBar(title = {
        Text(text = "Tasks")
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.surface,
        scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
        actionIconContentColor = MaterialTheme.colorScheme.surface,
        navigationIconContentColor = MaterialTheme.colorScheme.surface
    ), actions = {
        ListAppbarActions(onSearchClicked = onSearchClicked, onSortClicked = onSortClicked)
    })
}

@Composable
fun ListAppbarActions(onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = {})
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

@Composable
fun DeleteAllAction(onDeleteClicked: () -> Unit) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { isExpanded = true }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Choose Priority")
        DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }, content = {
            DropdownMenuItem(text = {
                Text(
                    text = "Delete All", modifier = Modifier.padding(
                        LARGE_PADDING
                    ), style = MaterialTheme.typography.titleSmall
                )
            }, onClick = {
                isExpanded = false
                onDeleteClicked()
            })
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }) {
        SearchBar(modifier = Modifier
            .align(Alignment.TopCenter)
            .focusRequester(focusRequester)
            .semantics { traversalIndex = -1f },
            query = text,
            onQueryChange = onTextChanged,
            onSearch = {
                onSearchClicked(it)
            },
            placeholder = { Text(text = "Search for a task...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "Search"
                )
            },
            trailingIcon = {
                Icon(modifier = Modifier.clickable {
                    when (trailingIconState) {
                        TrailingIconState.READY_TO_DELETE -> {
                            onTextChanged("")
                            trailingIconState = TrailingIconState.READY_TO_CLOSE
                        }

                        TrailingIconState.READY_TO_CLOSE -> {
                            if (text.isNotEmpty()) {
                                onTextChanged("")
                            } else {
                                onCloseClicked()
                                trailingIconState = TrailingIconState.READY_TO_DELETE
                            }
                        }
                    }
                }, imageVector = Icons.Default.Close, contentDescription = "Close")

            },
            active = true,
            onActiveChange = {}) {}
    }
}

@Preview(wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DefaultListAppBarPrev() {
    DefaultListAppbar(onSearchClicked = {}, onSortClicked = {})
}
