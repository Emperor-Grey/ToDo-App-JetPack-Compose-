package com.example.to_doapp.ui.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.models.Priority
import com.example.to_doapp.data.models.ToDoTask
import com.example.to_doapp.data.repositories.ToDoRepository
import com.example.to_doapp.utils.RequestState
import com.example.to_doapp.utils.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val id: MutableState<Int> = mutableIntStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }

    private val _selectedTasks: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTasks: StateFlow<ToDoTask?> = _selectedTasks

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect { task ->
                _selectedTasks.value = task
            }
        }
    }

    fun updateTaskFields(selectedTasks: ToDoTask?) {
        if (selectedTasks != null) {
            id.value = selectedTasks.id
            title.value = selectedTasks.title
            description.value = selectedTasks.description
            priority.value = selectedTasks.priority
        } else {
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }
}
