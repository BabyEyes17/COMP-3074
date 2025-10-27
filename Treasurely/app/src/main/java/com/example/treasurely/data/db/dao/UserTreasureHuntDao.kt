package com.example.treasurely.data.db.dao

import androidx.room.*
import com.example.treasurely.data.db.relations.TreasureHuntWithUsers
import com.example.treasurely.data.db.relations.UserTreasureHuntCrossRef
import com.example.treasurely.data.db.relations.UserWithTreasureHunts
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTreasureHuntDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCrossRef(crossRef: UserTreasureHuntCrossRef)

    @Delete
    suspend fun deleteCrossRef(crossRef: UserTreasureHuntCrossRef)

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserWithTreasureHunts(userId: Long): Flow<List<UserWithTreasureHunts>>

    @Transaction
    @Query("SELECT * FROM treasure_hunts WHERE id = :treasureHuntId")
    fun getTreasureHuntWithUsers(treasureHuntId: Long): Flow<List<TreasureHuntWithUsers>>
}
