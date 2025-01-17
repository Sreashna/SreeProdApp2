package sree.ddukk.sreeprodapp.ui.Sections

import FavoritesViewModel
import Product
import android.app.Application
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import sree.ddukk.sreeprodapp.R
import sree.ddukk.sreeprodapp.ui.pages.ProductDetailScreen


val products = listOf(
    Product(
        id = "1",
        name = "AirPods",
        imageRes = R.drawable.airpods,
        originalPrice = 13200.0,
        discountedPrice = 11900.0,
        discountPercentage = 10.0,
        details = "The new AirPods provide high-quality sound with seamless integration to Apple devices.",
        avgReview = 4.9f,
        numReviews = 320,
        deliveryDate = "20 January"
    ),
    Product(
        id = "2",
        name = "MacBook Air 13",
        imageRes = R.drawable.mac,
        originalPrice = 110000.0,
        discountedPrice = 105000.0,
        discountPercentage = 5.0,
        details = "Apple's MacBook Air 13 offers a slim profile and high performance for all your needs.",
        avgReview = 5.0f,
        numReviews = 250,
        deliveryDate = "22 January"
    ),
    Product(
        id = "3",
        name = "Xbox Series X",
        imageRes = R.drawable.xbox,
        originalPrice = 50000.0,
        discountedPrice = 47000.0,
        discountPercentage = 6.0,
        details = "Enjoy 4K gaming and seamless performance with the Xbox Series X console.",
        avgReview = 4.8f,
        numReviews = 185,
        deliveryDate = "23 January"
    ),
    Product(
        id = "4",
        name = "iPhone 17 Pro",
        imageRes = R.drawable.iphone,
        originalPrice = 120000.0,
        discountedPrice = 115000.0,
        discountPercentage = 4.0,
        details = "The iPhone 17 Pro offers cutting-edge performance, stunning design, and advanced camera technology.",
        avgReview = 5.0f,
        numReviews = 450,
        deliveryDate = "25 January"
    )
)


@Composable
fun ProductSection(navController: NavController, favoritesViewModel: FavoritesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ProductGrid(navController, favoritesViewModel)
    }
}
@Composable
fun ProductGrid(navController: NavController, favoritesViewModel: FavoritesViewModel) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        val rowItems = products.chunked(2)
        rowItems.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                row.forEach { product ->
                    ProductCard(
                        product = product,
                        navController = navController,
                        favoritesViewModel = favoritesViewModel,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    navController: NavController,
    favoritesViewModel: FavoritesViewModel,
    modifier: Modifier = Modifier
) {
    val isFavorite = favoritesViewModel.isFavorite(product)

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
        color = Color.White,
        shape = RoundedCornerShape(16.dp)
    ).background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFA0CBDE),
                        Color(0xFFE5ECEE)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ).clickable { navController.navigate("product_detail/${product.name}") }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFEAEAEA))
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF333333)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                text = "${product.avgReview} ⭐",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Text(
            text = "₹${product.discountedPrice}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF4CAF50)
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "₹${product.originalPrice}",
            fontSize = 12.sp,
            color = Color.Gray,
            style = TextStyle(textDecoration = TextDecoration.LineThrough)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${product.discountPercentage}% OFF",
            color = Color.Green,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        IconButton(onClick = { favoritesViewModel.toggleFavorite(product) }) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (isFavorite) Color.Red else Color.Gray
            )
        }
    }
}




