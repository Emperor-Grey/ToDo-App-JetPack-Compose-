package com.example.to_doapp.ui.screens.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.example.to_doapp.R

@Composable
fun ListScreen(
    navigateToTaskScreens: (Int) -> Unit
) {
    Scaffold(modifier = Modifier, topBar = {
        ListAppBar()
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

@Preview(showBackground = true, wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE)
@Composable
private fun ListScreenPrev() {
    ListScreen(navigateToTaskScreens = {})
}
