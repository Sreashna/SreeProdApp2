package sree.ddukk.sreeprodapp.ui.Sections

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun Category(navController: NavController) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            TextButton(onClick = { navController.navigate("see_all_categories") }) {
                Text(text = "View all", color = Color(0xFFFF9800))
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            val categories = listOf(
                Category("Mobile Phones", R.drawable.ic_launcher_foreground),
                Category("AirPods", R.drawable.ic_launcher_foreground),
                Category("Speaker", R.drawable.ic_launcher_foreground),
                Category("Controller", R.drawable.ic_launcher_foreground)
            )
            items(categories) { category ->
                CategoryCard(category)
            }
        }
    }
}






@Composable
fun CategoryCard(category: Category) {
    Column(
        modifier = Modifier.padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(color = Color(0xFF94B0E1), shape = RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = category.name,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardPreview() {
    CategoryCard(Category("lzp", R.drawable.mac))
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    val navController = rememberNavController()
    Category(navController = navController)
}
