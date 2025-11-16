package com.example.myfirstandroidapp.data

import com.example.myfirstandroidapp.data.model.ProductDto
import com.example.myfirstandroidapp.data.remote.ApiClient

class ProductRepository {
    suspend fun fetchProducts(): List<ProductDto> = ApiClient.api.getProducts()
}