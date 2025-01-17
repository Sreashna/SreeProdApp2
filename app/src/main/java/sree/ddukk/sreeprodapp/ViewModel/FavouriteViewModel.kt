import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sree.ddukk.sreeprodapp.DataModel.FavoriteProductEntity
import sree.ddukk.sreeprodapp.Database.AppDatabase

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "sreeprodapp.db"
    ).build()

    private val favoriteProductDao = db.favoriteProductDao()
    private val _favoriteProducts = mutableStateListOf<Product>()
    val favoriteProducts: List<Product> get() = _favoriteProducts

    init {
        // Load favorites in the background
        viewModelScope.launch(Dispatchers.IO) {
            val favorites = favoriteProductDao.getAllFavorites()
            withContext(Dispatchers.Main) {
                _favoriteProducts.addAll(favorites.map { entityToProduct(it) })
            }
        }
    }

    // Check if product is in favorites
    fun isFavorite(product: Product): Boolean {
        return _favoriteProducts.contains(product)
    }

    // Toggle favorite status
    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            if (_favoriteProducts.contains(product)) {
                _favoriteProducts.remove(product)
                withContext(Dispatchers.IO) {
                    favoriteProductDao.removeFavorite(productToEntity(product))
                }
            } else {
                _favoriteProducts.add(product)
                withContext(Dispatchers.IO) {
                    favoriteProductDao.addFavorite(productToEntity(product))
                }
            }
        }
    }

    // Convert Product to FavoriteProductEntity
    private fun productToEntity(product: Product): FavoriteProductEntity {
        return FavoriteProductEntity(
            id = product.id,
            name = product.name,
            imageRes = product.imageRes,
            discountedPrice = product.discountedPrice
        )
    }

    // Convert FavoriteProductEntity to Product
    private fun entityToProduct(entity: FavoriteProductEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            imageRes = entity.imageRes,
            originalPrice = 100.0, // Example static value
            discountedPrice = entity.discountedPrice,
            discountPercentage = 10.0,
            details = "Default details",
            avgReview = 4.5f,
            numReviews = 50,
            deliveryDate = "2023-12-31"
        )
    }
}
