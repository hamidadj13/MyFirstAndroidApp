package com.example.myfirstandroidapp.ui.users

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstandroidapp.data.UserRepository
import com.example.myfirstandroidapp.data.model.UserDto
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

data class UsersUiState(
    val all: List<UserDto> = emptyList(),
    val visibleCount: Int = 0,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)

class UsersViewModel (
    private val repo: UserRepository = UserRepository()
): ViewModel() {
    companion object { private const val CHUNK = 5 }

    var state = mutableStateOf(UsersUiState())
        private set // On peut lire le state depuis l'extérieur mais pas le modifier

    private var loadJob: Job? = null

    init { load() }

    fun load() {
        loadJob?.cancel()
        state.value = state.value.copy(isLoading = true, error = null)
        loadJob = viewModelScope.launch {
            try {
                val users = repo.fetchUsers()
                state.value = UsersUiState(
                    all = users,
                    visibleCount = minOf(CHUNK, users.size),
                    isLoading = false
                )
            } catch (e: Exception){
                state.value = state.value.copy(isLoading = false, error = e.message ?: "Error")
            }
        }
    }

    fun refresh() {
        state.value = state.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val users = repo.fetchUsers()
                state.value = UsersUiState(
                    all = users,
                    visibleCount = minOf(CHUNK, users.size),
                    isRefreshing = false
                )
            }catch (e: Exception){
                state.value = state.value.copy(isRefreshing = false, error = e.message ?: "Error")
            }
        }
    }

    fun loadMore() {
        val s = state.value
        if(s.isLoading || s.isRefreshing) return
        if(s.visibleCount >= s.all.size) return
        state.value = s.copy(visibleCount = minOf(s.visibleCount + CHUNK, s.all.size))
    }
}