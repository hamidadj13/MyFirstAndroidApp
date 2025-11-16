package com.example.myfirstandroidapp.ui.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstandroidapp.data.FirestoreRepository
import com.example.myfirstandroidapp.data.UserLocalRepository
import com.example.myfirstandroidapp.data.local.UserEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserDbViewModel(app: Application): AndroidViewModel(app) {
    private val repo = UserLocalRepository(app)

    private val remoteRepo = FirestoreRepository()

    val users  = repo.observedUsers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun addUser(firstName: String, lastName:String, email: String){
        viewModelScope.launch {
            val entity = UserEntity(
                firstName = firstName.trim(),
                lastName = lastName.trim(),
                email = email.trim()
            )

            repo.addUser(entity)

            //Add to firestore
            runCatching { remoteRepo.addUser(entity) }
                .onFailure { it.printStackTrace() }
        }
    }

    fun clearAll() {
        viewModelScope.launch { repo.clear() }
    }
}