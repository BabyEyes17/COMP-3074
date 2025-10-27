package com.example.treasurely.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.treasurely.data.db.dao.TreasureHuntDao
import com.example.treasurely.data.db.dao.UserClueProgressDao
import com.example.treasurely.data.db.dao.UserDao
// import com.example.treasurely.data.db.dao.UserTreasureHuntDao
import com.example.treasurely.data.db.entities.Clue
import com.example.treasurely.data.db.entities.TreasureHunt
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.db.relations.UserClueProgressCrossRef
// import com.example.treasurely.data.db.relations.UserTreasureHuntCrossRef
import com.example.treasurely.data.db.utilities.DateTimeConverter

@Database(
    entities = [
        User::class,
        TreasureHunt::class,
        Clue::class,
        // UserTreasureHuntCrossRef::class,
        UserClueProgressCrossRef::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateTimeConverter::class)
abstract class TreasurelyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun treasureHuntDao(): TreasureHuntDao
    // abstract fun userTreasureHuntDao(): UserTreasureHuntDao
    abstract fun userClueProgressDao(): UserClueProgressDao

    companion object {
        @Volatile
        private var INSTANCE: TreasurelyDatabase? = null

        fun getInstance(context: Context): TreasurelyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TreasurelyDatabase::class.java,
                    "treasurely_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
