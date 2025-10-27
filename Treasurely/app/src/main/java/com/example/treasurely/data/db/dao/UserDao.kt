package com.example.treasurely.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.db.relations.UserTreasureHuntCrossRef
import com.example.treasurely.data.db.relations.UserWithTreasureHunts
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserHuntCrossRef(crossRef: UserTreasureHuntCrossRef)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun getAllUsers(): Flow<List<User>>

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserWithTreasureHunts(userId: Int): Flow<UserWithTreasureHunts>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Long): User?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByName(username: String): User?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}
