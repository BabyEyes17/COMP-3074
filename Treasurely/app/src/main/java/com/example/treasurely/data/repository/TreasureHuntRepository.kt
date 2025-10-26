package com.example.treasurely.data.repository

import com.example.treasurely.data.db.dao.TreasureHuntDao
import kotlinx.coroutines.flow.Flow

class TreasureHuntRepository(private val treasureHunt: TreasureHunt) {
    fun getAllreasureHunts(): Flow<List<TreasureHunt>> = TreasureHuntDao.getAllTreasureHunts()
    suspend fun insertTreasureHunt(treasureHunt: TreasureHunt) = TreasureHuntDao.insert(treasureHunt)
    suspend fun deleteTreasureHunt(treasureHunt: TreasureHunt) = TreasureHuntDao.delete(treasureHunt)
}
