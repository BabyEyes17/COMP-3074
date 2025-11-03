package com.example.treasurely.data.repository

import com.example.treasurely.data.db.dao.ClueDao
import com.example.treasurely.data.db.dao.UserClueProgressDao
import com.example.treasurely.data.db.entities.Clue
import com.example.treasurely.data.db.relations.UserClueProgressCrossRef
import kotlinx.coroutines.flow.Flow

class ClueRepositoryImpl(
    private val clueDao: ClueDao,
    private val userClueProgressDao: UserClueProgressDao
) : ClueRepository {

    /*
    * -- Available Clue Actions --
    *
    * getAllClues() returns Flow<List<Clue>>
    * getClueById(id: Long) returns Clue
    * getCluesForTreasureHunt(treasureHuntId: Long) returns Flow<List<Clue>>
    *
    * insertClue(clue: Clue) returns Long
    * updateClue(clue: Clue) returns Unit
    * deleteClue(clue: Clue) returns Unit
    *
    * recordUserProgress(userId: Long, clueId: Long) returns Boolean
    * removeUserProgress(userId: Long, clueId: Long) returns Boolean
    * getCluesForUser(userId: Long) returns Flow<List<UserClueProgressCrossRef>>
    */


    /*-- Clue CRUD --*/
    override suspend fun insertClue(clue: Clue): Long = clueDao.insertClue(clue)

    override suspend fun getAllClues(): Flow<List<Clue>> = clueDao.getAllClues()

    override suspend fun getClueById(id: Long): Clue? = clueDao.getClueById(id)

    fun getCluesForTreasureHunt(treasureHuntId: Long): Flow<List<Clue>> =
        clueDao.getCluesForTreasureHunt(treasureHuntId)

    override suspend fun updateClue(clue: Clue) = clueDao.updateClue(clue)

    override suspend fun deleteClue(clue: Clue) = clueDao.deleteClue(clue)


    /*-- User-Clue Progress --*/
    suspend fun recordUserProgress(userId: Long, clueId: Long): Boolean {
        val ref = UserClueProgressCrossRef(userId, clueId)
        userClueProgressDao.insertProgress(ref)
        return true
    }

    suspend fun removeUserProgress(userId: Long, clueId: Long): Boolean {
        val ref = UserClueProgressCrossRef(userId, clueId)
        userClueProgressDao.deleteProgress(ref)
        return true
    }

    fun getCluesForUser(userId: Long): Flow<List<UserClueProgressCrossRef>> =
        userClueProgressDao.getCluesForUser(userId)
}
