package com.example.myfirstandroidapp.data.model

data class UserDto (
    val id: Int,
    val email: String,
    val name: String,
    val role: String,
    val password: String? = null,
    val avatar: String,
    val creationAt: String,
    val updatedAt: String
)
