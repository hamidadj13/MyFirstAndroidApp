package com.example.myfirstandroidapp.ui.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.myfirstandroidapp.data.model.CategoryDto
import com.example.myfirstandroidapp.data.model.ProductDto
import com.example.myfirstandroidapp.ui.theme.MyFirstAndroidAppTheme

@Composable
fun ProductCard(
    product: ProductDto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.images.getOrNull(0) ?: "",
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true, name = "Product Card Preview")
private fun ProductCardPreview() {
    MyFirstAndroidAppTheme {
        ProductCard(
            product = ProductDto(
                id = 1,
                title = "Modern Elegance Teal Armchair",
                price = 24.0,
                description = "Elevate your living space with this beautifully crafted armchair, featuring a sleek wooden frame that complements its vibrant teal upholstery. Ideal for adding a pop of color and contemporary style to any room, this chair provides both superb comfort and sophisticated design. Perfect for reading, relaxing, or creating a cozy conversation nook.",
                category = CategoryDto(
                    id = 1,
                    name = "Chairs",
                    slug = "chairs",
                    image = "https://i.imgur.com/Qphac99.jpeg",
                    creationAt = "2025-11-03T17:19:40.000Z",
                    updatedAt = "2025-11-03T17:19:40.000Z"
                ),
                slug = "modern-elegance-teal-armchair",
                creationAt = "2025-11-03T17:19:40.000Z",
                updatedAt = "2025-11-03T17:19:40.000Z",
                images = listOf("https://i.imgur.com/Qphac99.jpeg")
            )
        )
    }
}
