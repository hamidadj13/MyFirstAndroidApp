package com.example.myfirstandroidapp.data.remote

import com.example.myfirstandroidapp.data.model.ProductDto
import com.example.myfirstandroidapp.data.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}