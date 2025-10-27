//package com.example.treasurely.data.db.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Delete
//import androidx.room.Query
//import com.example.treasurely.data.db.entities.Clue
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface ClueDao {
//
//    @Insert
//    suspend fun insert(clue: Clue)
//
//    @Delete
//    suspend fun delete(clue: Clue)
//
//    @Query("SELECT * FROM clues")
//    fun getAllClues(): Flow<List<Clue>>
//
//    @Query("SELECT * FROM clues WHERE id = :clueId")
//    fun getClueById(clueId: Long): Clue
//
//    @Query("SELECT * FROM clues WHERE name LIKE :clueName")
//    fun getClueByName(clueName: String): Clue
//}
