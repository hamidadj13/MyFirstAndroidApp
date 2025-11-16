package com.example.myfirstandroidapp

import com.example.myfirstandroidapp.data.FirestoreRepository
import com.example.myfirstandroidapp.data.UserLocalRepository
import com.example.myfirstandroidapp.data.local.UserEntity
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myfirstandroidapp.ui.users.UserDbViewModel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.junit.After


@OptIn(ExperimentalCoroutinesApi::class)
class UserDbViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var localRepo: UserLocalRepository
    private lateinit var remoteRepo: FirestoreRepository
    private lateinit var viewModel: UserDbViewModel

    @Before
    fun setup() {
        localRepo = mockk(relaxed = true)
        remoteRepo = mockk(relaxed = true)

        // On injecte nos repos simulés via un constructeur secondaire
        viewModel = spyk(UserDbViewModel(mockk())) {
            every { this@spyk.repo } returns localRepo
            every { this@spyk.remoteRepo } returns remoteRepo
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `addUser calls local and remote repositories`() = testScope.runTest {
        val user = UserEntity(
            localId = 0,
            firstName = "John",
            lastName = "Doe",
            email = "john@example.com"
        )

        viewModel.addUser(user.firstName, user.lastName, user.email)

        coVerify { localRepo.addUser(any()) }
        coVerify { remoteRepo.addUser(any()) }
    }

    @Test
    fun `clearAll calls local repository clear`() = testScope.runTest {
        viewModel.clearAll()
        coVerify { localRepo.clear() }
    }
}
