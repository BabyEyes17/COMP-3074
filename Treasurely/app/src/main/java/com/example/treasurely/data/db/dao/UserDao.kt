package com.example.treasurely.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import com.example.treasurely.data.db.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users ORDER BY username ASC")
    fun getAllUsers(): Flow<List<User>>

    fun getAllUsersByTreasureHunt() // still needs implementation

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Long): User?

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByName(username: String): User?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}
