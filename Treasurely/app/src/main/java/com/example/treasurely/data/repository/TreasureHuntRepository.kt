package com.example.treasurely.data.repository

import com.example.treasurely.data.db.entities.TreasureHunt
import com.example.treasurely.data.db.dao.TreasureHuntDao
import com.example.treasurely.data.db.utilities.TreasureHuntJoinCodeGenerator
import kotlinx.coroutines.flow.Flow

class TreasureHuntRepository(private val treasureHuntDao: TreasureHuntDao) {


    /*-- Create --*/
    suspend fun createTreasureHunt(

        name: String,
        description: String? = null,
        gpsStartingLocation: String,
        searchRadiusMeters: Int,
        reward: String? = null,
        coverImage: ByteArray? = null

    ): TreasureHunt {

        var joinCode: String

        do {
            joinCode = TreasureHuntJoinCodeGenerator.generateJoinCode()
        }

        while (treasureHuntDao.getTreasureHuntByJoinCode(joinCode) != null)

        val hunt = TreasureHunt(
            name = name,
            description = description,
            joinCode = joinCode,
            gpsStartingLocation = gpsStartingLocation,
            searchRadiusMeters = searchRadiusMeters,
            reward = reward,
            coverImage = coverImage
        )

        treasureHuntDao.insertTreasureHunt(hunt)
        return hunt
    }


    /*-- Read --*/
    fun getAllTreasureHunts(): Flow<List<TreasureHunt>> = treasureHuntDao.getAllTreasureHunts()

    suspend fun getTreasureHuntById(id: Long) = treasureHuntDao.getTreasureHuntById(id)

    suspend fun getTreasureHuntByName(name: String) = treasureHuntDao.getTreasureHuntByName(name)

    suspend fun getTreasureHuntByJoinCode(joinCode: String) = treasureHuntDao.getTreasureHuntByJoinCode(joinCode)


    /*-- Update --*/
    suspend fun updateTreasureHunt(hunt: TreasureHunt) { treasureHuntDao.updateTreasureHunt(hunt) }

    /*-- Delete --*/
    suspend fun deleteTreasureHunt(hunt: TreasureHunt) { treasureHuntDao.deleteTreasureHunt(hunt) }

}
