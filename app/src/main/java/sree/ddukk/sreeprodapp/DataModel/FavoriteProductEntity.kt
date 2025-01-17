package sree.ddukk.sreeprodapp.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val imageRes: Int,
    val discountedPrice: Double
)




