package com.example.myfirstandroidapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val firstName: String,
    val lastName: String,
    val email: String,

)