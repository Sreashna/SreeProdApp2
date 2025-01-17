package sree.ddukk.sreeprodapp.Database

import androidx.room.*
import sree.ddukk.sreeprodapp.DataModel.FavoriteProductEntity
@Dao
interface FavoriteProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(product: FavoriteProductEntity)

    @Delete
    fun removeFavorite(product: FavoriteProductEntity)

    @Query("SELECT * FROM favorite_products")
    fun getAllFavorites(): List<FavoriteProductEntity>
}

