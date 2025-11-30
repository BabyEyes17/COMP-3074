package com.example.treasurely.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.treasurely.data.model.User

interface UserDao {



    /* POST */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user: User)



    /* GET */
    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: String): LiveData<User>

    @Query("SELECT * FROM users WHERE name LIKE '%' || :name || '%'")
    fun getUserByName(name: String): LiveData<List<User>>



    /* PUT */
    @Update
    suspend fun updateUser(user: User)



    /* DELETE */
    @Delete
    suspend fun deleteUser(user: User)
}
