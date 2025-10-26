package com.example.treasurely.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import com.example.treasurely.data.db.entities.TreasureHunt
import kotlinx.coroutines.flow.Flow

@Dao
interface TreasureHuntDao {

    @Insert
    suspend fun insert(treasureHunt: TreasureHunt)

    @Delete
    suspend fun delete(treasureHunt: TreasureHunt)

    @Query("SELECT * FROM treasureHunts")
    fun getAllTreasureHunts(): Flow<List<TreasureHunt>>

    @Query("SELECT * FROM treasureHunts WHERE id = :treasureHuntId")
    fun getTreasureHuntById(treasureHuntId: Long): TreasureHunt

    @Query("SELECT * FROM treasureHunts WHERE name LIKE :treasureHuntName")
    fun getTreasureHuntByName(treasureHuntName: String): TreasureHunt
}
