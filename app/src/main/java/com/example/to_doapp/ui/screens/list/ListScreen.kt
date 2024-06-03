package com.example.to_doapp.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.to_doapp.R
import com.example.to_doapp.ui.viewmodel.SharedViewModel
import com.example.to_doapp.utils.SearchAppBarState

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel
) {

    val action = sharedViewModel.action.value

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextSate: String by sharedViewModel.searchTextState

    sharedViewModel.handleDatabaseActions(action = action)

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    Scaffold(modifier = Modifier, topBar = {
        ListAppBar(
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextSate = searchTextSate
        )
    }, floatingActionButton = {
        ListFab(onFabClicked = navigateToTaskScreen)
    }, content = { paddingValues ->
        ListContent(
            tasks = allTasks,
            navigateToTaskScreen = navigateToTaskScreen,
            modifier = Modifier.padding(paddingValues)
        )
    })
}

@Composable
fun ListFab(onFabClicked: (Int) -> Unit) {
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    }) {
        Icon(
            imageVector = Icons.Filled.Add, contentDescription = stringResource(R.string.add_button)
        )
    }
}
