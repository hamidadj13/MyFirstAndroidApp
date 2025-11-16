package com.example.myfirstandroidapp.data

import android.app.Application
import com.example.myfirstandroidapp.MainDispatcherRule
import com.example.myfirstandroidapp.data.local.UserEntity
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: UserLocalRepository

    private val mockApp = mockk<Application>(relaxed = true)

    @Before
    fun setup() {
        repository = UserLocalRepository(mockApp)
    }

    @Test
    fun addUser_shouldEmitUser() = runTest {
        val user = UserEntity(firstName = "Alice", lastName = "Smith", email = "alice@example.com")
        repository.addUser(user)

        val users = repository.observedUsers().first()
        assert(users.any { it.firstName == "Alice" })
    }

    @Test
    fun clear_shouldEmitEmptyList() = runTest {
        repository.clear()
        val users = repository.observedUsers().first()
        Assert.assertEquals(0, users.size)
    }
}