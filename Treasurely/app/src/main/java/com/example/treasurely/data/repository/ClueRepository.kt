package com.example.treasurely.data.repository

import com.example.treasurely.data.db.dao.ClueDao
import com.example.treasurely.data.db.entities.Clue
import kotlinx.coroutines.flow.Flow

class ClueRepository(private val clueDao: ClueDao) {
    fun getAllClues(): Flow<List<Clue>> = clueDao.getAllClues()
    suspend fun insertClue(clue: Clue) = clueDao.insert(clue)
    suspend fun deleteClue(clue: Clue) = clueDao.delete(clue)
}
