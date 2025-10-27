package com.example.treasurely.data.db.dao

import androidx.room.*
import com.example.treasurely.data.db.entities.Clue
import kotlinx.coroutines.flow.Flow

@Dao
interface ClueDao {

    /*-- Create --*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClue(clue: Clue): Long


    /*-- Read --*/
    @Query("SELECT * FROM clues ORDER BY id ASC")
    fun getAllClues(): Flow<List<Clue>>

    @Query("SELECT * FROM clues WHERE id = :clueId LIMIT 1")
    suspend fun getClueById(clueId: Long): Clue?

    @Query("SELECT * FROM clues WHERE treasureHuntId = :treasureHuntId ORDER BY id ASC")
    fun getCluesForTreasureHunt(treasureHuntId: Long): Flow<List<Clue>>


    /*-- Update --*/
    @Update
    suspend fun updateClue(clue: Clue)


    /*-- Delete --*/
    @Delete
    suspend fun deleteClue(clue: Clue)
}
