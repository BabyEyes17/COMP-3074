package com.example.treasurely.data.repository

import com.example.treasurely.data.db.entities.Clue
import kotlinx.coroutines.flow.Flow

interface ClueRepository {

    suspend fun insertClue(clue: Clue): Long
    suspend fun getAllClues(): Flow<List<Clue>>
    suspend fun getClueById(clueId: Long): Clue?
    suspend fun updateClue(clue: Clue): Int
    suspend fun deleteClue(clue: Clue): Int
}