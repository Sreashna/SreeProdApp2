package sree.ddukk.sreeprodapp

import FavoritesViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sree.ddukk.sreeprodapp.ui.pages.FavoritesScreen
import sree.ddukk.sreeprodapp.ui.Sections.Banner
import sree.ddukk.sreeprodapp.ui.Sections.BottomNavigationBar
import sree.ddukk.sreeprodapp.ui.Sections.Category
import sree.ddukk.sreeprodapp.ui.Sections.ProductGrid
import sree.ddukk.sreeprodapp.ui.pages.SeeAllCategoriesScreen
import sree.ddukk.sreeprodapp.ui.Sections.Top
import sree.ddukk.sreeprodapp.ui.pages.ProductDetailScreen
import sree.ddukk.sreeprodapp.ui.pages.ProfileScreen
import sree.ddukk.sreeprodapp.ui.pages.Search

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductApp()
        }
    }
}

@Composable
fun ProductApp() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen(
                    navigateToProductDetail = { productName ->
                        navController.navigate("product_detail/$productName")
                    },
                    navController = navController,
                    favoritesViewModel = favoritesViewModel,
                    paddingValues = paddingValues
                )
            }
            composable("see_all_categories") {
                SeeAllCategoriesScreen(navController = navController)
            }
            composable("categories") {
                Search()
            }
            composable("favorites") {
                FavoritesScreen(navController = navController, favoritesViewModel = favoritesViewModel)
            }
            composable("profile") {
                ProfileScreen()
            }
            composable("product_detail/{productName}") { backStackEntry ->
                val productName = backStackEntry.arguments?.getString("productName") ?: "Unknown"
                ProductDetailScreen(
                    productName = productName,
                    navController = navController,
                    favoritesViewModel = favoritesViewModel
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductAppPreview() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    ProductApp()
}
@Composable
fun HomeScreen(
    navigateToProductDetail: (String) -> Unit,
    navController: NavHostController,
    favoritesViewModel: FavoritesViewModel,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Top()
//        Spacer(modifier = Modifier.height(5.dp))
        Banner()
        Spacer(modifier = Modifier.height(10.dp))
        Category(navController = navController)
        Spacer(modifier = Modifier.height(5.dp))
        ProductGrid( navController = navController, favoritesViewModel = favoritesViewModel )
    }
}
