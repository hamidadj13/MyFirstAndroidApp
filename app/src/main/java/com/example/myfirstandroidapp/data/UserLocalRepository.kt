package com.example.myfirstandroidapp.data

import android.content.Context
import com.example.myfirstandroidapp.data.local.AppDatabase
import com.example.myfirstandroidapp.data.local.UserEntity

class UserLocalRepository(context: Context) {
    private val dao = AppDatabase.get(context).userDao()

    fun observedUsers() = dao.observedUsers()

    suspend fun addUser(user: UserEntity) = dao.insert(user)

    suspend fun clear() = dao.clear()


}