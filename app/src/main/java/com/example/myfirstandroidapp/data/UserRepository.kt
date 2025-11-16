package com.example.myfirstandroidapp.data

import com.example.myfirstandroidapp.data.model.UserDto
import com.example.myfirstandroidapp.data.remote.ApiClient

class UserRepository {
    suspend fun fetchUsers(): List<UserDto> = ApiClient.api.getUsers()
}