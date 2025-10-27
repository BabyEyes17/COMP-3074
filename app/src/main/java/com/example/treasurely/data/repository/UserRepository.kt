package com.example.treasurely.data.repository

import com.example.treasurely.data.db.dao.UserDao
import com.example.treasurely.data.db.entities.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: Long) {
        userDao.deleteUser(user)
    }

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    suspend fun getUserById(userId: Long): User? {
        return userDao.getUserById(userId)
    }

    suspend fun getUserByName(username: String): User? {
        return userDao.getUserByName(username)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun updateUserPoints(userId: Long, points: Int) {
        val user = getUserById(userId)
        user?.let {
            val updatedUser = it.copy(totalPoints = it.totalPoints + points)
            deleteUser(it)
            insertUser(updatedUser)
        }
    }

    suspend fun updateUserProfile(userId: Long, username: String?, email: String?, profilePhoto: ByteArray?) {
        val user = getUserById(userId)
        user?.let {
            val updatedUser = it.copy(
                username = username ?: it.username,
                email = email ?: it.email,
                profilePhoto = profilePhoto ?: it.profilePhoto
            )
            deleteUser(it)
            insertUser(updatedUser)
        }
    }
}