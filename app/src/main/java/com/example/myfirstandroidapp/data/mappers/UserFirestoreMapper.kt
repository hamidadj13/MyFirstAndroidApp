package com.example.myfirstandroidapp.data.mappers

import com.example.myfirstandroidapp.data.local.UserEntity
import kotlin.String

data class FirestoreUser (
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val ownerUid: String = "",
    val localId: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val source: String = "local"
    )

fun UserEntity.toFirestoreUser(ownerUid: String): FirestoreUser =
    FirestoreUser(
         firstName = firstName,
         lastName = lastName,
         email = email,
         ownerUid = ownerUid,
         localId = localId
    )
