package sree.ddukk.sreeprodapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import sree.ddukk.sreeprodapp.DataModel.FavoriteProductEntity

@Database(entities = [FavoriteProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteProductDao(): FavoriteProductDao
}

