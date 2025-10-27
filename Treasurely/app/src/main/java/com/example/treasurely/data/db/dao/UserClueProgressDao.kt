package com.example.treasurely.data.db.dao

import androidx.room.*
import com.example.treasurely.data.db.relations.UserClueProgressCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface UserClueProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(crossRef: UserClueProgressCrossRef)

    @Delete
    suspend fun deleteProgress(crossRef: UserClueProgressCrossRef)

    @Query("SELECT * FROM user_clue_progress_join WHERE userId = :userId")
    fun getCluesForUser(userId: Long): Flow<List<UserClueProgressCrossRef>>

    @Query("SELECT * FROM user_clue_progress_join WHERE clueId = :clueId")
    fun getUsersForClue(clueId: Long): Flow<List<UserClueProgressCrossRef>>
}
