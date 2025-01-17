package sree.ddukk.sreeprodapp.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import sree.ddukk.sreeprodapp.DataModel.Category
import sree.ddukk.sreeprodapp.R


@Composable
fun SeeAllCategoriesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier.size(24.dp),
                tint = Color.Blue
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f),
                maxLines = 1
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Menu Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Blue
            )
        }
        val categories = listOf(
            Category("SmartPhones", R.drawable.ic_launcher_foreground),
            Category("Laptops", R.drawable.ic_launcher_foreground),
            Category("Speakers", R.drawable.ic_launcher_foreground),
            Category("Controller", R.drawable.ic_launcher_foreground),
            Category("Airpods", R.drawable.ic_launcher_foreground),
            Category("Camera", R.drawable.ic_launcher_foreground),
            Category("Watches", R.drawable.ic_launcher_foreground),
            Category("Xbox", R.drawable.ic_launcher_foreground),
            Category("Tablet", R.drawable.ic_launcher_foreground)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 26.dp, vertical = 8.dp)
                .weight(1f)
        ) {
            val rows = categories.chunked(3)
            rows.forEach { rowCategories ->
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowCategories.forEach { category ->
                            CategoryCard(category)
                        }
                    }
                }
            }
        }
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0B3081))
        ) {
            Text(text = "Go Back", color = Color.White, fontSize = 16.sp)
        }
    }
}



@Composable
fun CategoryCard(category: Category) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height =180.dp)
            .background(Color(0xFF6692F1), RoundedCornerShape(12.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.name,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun SeeAllCategoriesPreview() {
    val navController = rememberNavController()
    SeeAllCategoriesScreen(navController = navController)
}

