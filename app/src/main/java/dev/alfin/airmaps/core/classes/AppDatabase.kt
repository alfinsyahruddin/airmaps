package dev.alfin.airmaps.core.classes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.alfin.airmaps.modules.data.datasources.local.LocationDao
import dev.alfin.airmaps.modules.data.entities.database.LocationEntity


@Database(entities = [LocationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLocationDao(): LocationDao

    companion object {
        var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "db_airmaps")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}


