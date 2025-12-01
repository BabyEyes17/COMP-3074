package ca.gbc.treasurely.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.gbc.treasurely.data.dao.PoiDao
import ca.gbc.treasurely.data.dao.UserDao
import ca.gbc.treasurely.data.model.PointOfInterest
import ca.gbc.treasurely.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [PointOfInterest::class, User::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun poiDao(): PoiDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "treasurely.db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                // Safe because INSTANCE is set below
                                INSTANCE?.poiDao()?.createPoi(
                                    PointOfInterest(
                                        name = "Demo Treasure",
                                        address = "123 Treasure Lane",
                                        task = "Find the hidden clue",
                                        tags = listOf("demo", "sample"),
                                        rating = 5,
                                        latitude = 43.6532,
                                        longitude = -79.3832,
                                        qrCodeValue = "demo-qr",
                                        isFound = false
                                    )
                                )
                            }
                        }
                    })
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
