package com.example.treasurely.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.treasurely.data.db.entities.TreasureHunt
import com.example.treasurely.data.db.relations.TreasureHuntWithUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface TreasureHuntDao {

    /*-- Create --*/
    @Insert
    suspend fun insertTreasureHunt(treasureHunt: TreasureHunt)


    /*-- Read --*/
    @Query("SELECT * FROM treasure_hunts ORDER BY name ASC")
    fun getAllTreasureHunts(): Flow<List<TreasureHunt>>

    @Transaction
    @Query("SELECT * FROM treasure_hunts WHERE id = :id")
    fun getTreasureHuntWithUsers(id: Int): Flow<TreasureHuntWithUsers>

    @Query("SELECT * FROM treasure_hunts WHERE id = :id LIMIT 1")
    suspend fun getTreasureHuntById(id: Long): TreasureHunt?

    @Query("SELECT * FROM treasure_hunts WHERE name = :name LIMIT 1")
    suspend fun getTreasureHuntByName(name: String): TreasureHunt?

    @Query("SELECT * FROM treasure_hunts WHERE joinCode = :joinCode LIMIT 1")
    suspend fun getTreasureHuntByJoinCode(joinCode: String): TreasureHunt?


    /*-- Update --*/
    @Update
    suspend fun updateTreasureHunt(hunt: TreasureHunt)


    /*-- Delete --*/
    @Delete
    suspend fun deleteTreasureHunt(treasureHunt: TreasureHunt)


}
