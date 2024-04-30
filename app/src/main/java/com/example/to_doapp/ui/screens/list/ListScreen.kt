package com.example.to_doapp.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.to_doapp.R
import com.example.to_doapp.ui.theme.ViewModels.SharedViewModel
import com.example.to_doapp.utils.SearchAppBarState

@Composable
fun ListScreen(
    navigateToTaskScreens: (Int) -> Unit, sharedViewModel: SharedViewModel
) {
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextSate: String by sharedViewModel.searchTextState

    Scaffold(modifier = Modifier, topBar = {
        ListAppBar(
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextSate = searchTextSate
        )
    }, floatingActionButton = {
        ListFab(onFabClicked = navigateToTaskScreens)
    }) { paddingValues ->
        val padding = paddingValues
    }
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
