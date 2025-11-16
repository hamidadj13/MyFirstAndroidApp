package com.example.myfirstandroidapp.data

import com.example.myfirstandroidapp.data.model.CategoryDto
import com.example.myfirstandroidapp.data.model.ProductDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

// Fake ProductRepository pour le test
class FakeProductRepository {
    private val products = listOf(
        ProductDto(
            id = 1,
            title = "Produit 1",
            slug = "produit-1",
            price = 9.99,
            description = "Description produit 1",
            category = CategoryDto(
                id = 1,
                name = "Catégorie 1",
                slug = "slug-cat1",
                image = "url",
                creationAt = "2025-01-01",
                updatedAt = "2025-01-01"
            ),
            images = emptyList(),
            creationAt = "2025-01-01",
            updatedAt = "2025-01-01"
        )
    )

    suspend fun getProducts(): List<ProductDto> = products
}

@OptIn(ExperimentalCoroutinesApi::class)
class ProductsRepositoryTest {

    private lateinit var repository: FakeProductRepository

    @Before
    fun setup() {
        repository = FakeProductRepository()
    }

    @Test
    fun `getProducts returns list of products`() = runTest {
        // Act
        val result = repository.getProducts()

        // Assert
        assertEquals(1, result.size)
        assertEquals("Produit 1", result[0].title)
        assertEquals(9.99, result[0].price, 0.0)
        assertEquals("Catégorie 1", result[0].category.name)
        assertEquals("2025-01-01", result[0].category.creationAt)
    }
}
