package com.example.treasurely.data.repository

import com.example.treasurely.data.db.dao.UserDao
import com.example.treasurely.data.db.entities.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
    suspend fun insertUser(user: User) = userDao.insert(user)
    suspend fun deleteUser(user: User) = userDao.delete(user)
}