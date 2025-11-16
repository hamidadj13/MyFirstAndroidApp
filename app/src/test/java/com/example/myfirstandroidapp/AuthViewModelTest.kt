package com.example.myfirstandroidapp

import com.example.myfirstandroidapp.ui.auth.AuthViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val authViewModel = AuthViewModel()

    @Test
    fun initialState_shouldNotBeNull() = runTest {
        val state = authViewModel.isAuthenticated.value
        assertNotNull(state)
    }

    @Test
    fun logout_shouldSetIsAuthenticatedFalse() = runTest {
        authViewModel.logout()
        val state = authViewModel.isAuthenticated.value
        assertFalse(state)
    }
}
