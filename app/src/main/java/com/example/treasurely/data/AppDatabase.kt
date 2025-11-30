package com.example.treasurely.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.treasurely.data.dao.PoiDao
import com.example.treasurely.data.dao.UserDao
import com.example.treasurely.data.model.PointOfInterest
import com.example.treasurely.data.model.User
import kotlin.concurrent.Volatile

@Database(
    entities = [PointOfInterest::class, User::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun poiDao(): PoiDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                Room.databaseBuilder(

                    context.applicationContext,
                    AppDatabase::class.java,
                    "treasurely_db"
                ).build().also { INSTANCE = it}

            }
        }
    }
}