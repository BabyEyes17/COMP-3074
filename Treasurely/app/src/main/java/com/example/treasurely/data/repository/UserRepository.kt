package com.example.treasurely.data.repository

import com.example.treasurely.data.db.dao.UserDao
import com.example.treasurely.data.db.dao.UserTreasureHuntDao
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.db.relations.UserTreasureHuntCrossRef
import com.example.treasurely.data.db.utilities.PasswordUtils
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao, private val userTreasureHuntDao: UserTreasureHuntDao) {

    /*
    * -- Available User Actions --
    *
    * getAllUsers() returns Flow<List<User>>
    * getUserById(id: Long) returns User
    * getUserByEmail(email: String) returns User
    * getUserByUsername(username: String) returns User
    *
    * registerUser(email: String, username: String, password: String) returns boolean
    * loginUser(email: String, password: String) returns boolean
    *
    * deleteUser(user: User) returns boolean
    */


    /*-- User Retrieval --*/
    fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun getUserById(id: Long) = userDao.getUserById(id)

    suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    suspend fun getUserByUsername(username: String) = userDao.getUserByName(username)


    /*-- User Authentication --*/
    suspend fun registerUser(email: String, username: String, password: String): Boolean {

        val existingUser = userDao.getUserByEmail(email)

        if (existingUser != null) { return false }

        val salt = PasswordUtils.generateSalt()
        val hash = PasswordUtils.hashPassword(password, salt)

        val newUser = User(
            username = username,
            email = email,
            passwordHash = hash,
            salt = salt
        )

        userDao.insertUser(newUser)
        return true
    }

    suspend fun loginUser(email: String, password: String): Boolean {

        val user = userDao.getUserByEmail(email)

        if (user == null) { return false }

        val hashedInput = PasswordUtils.hashPassword(password, user.salt)

        if (hashedInput != user.passwordHash) { return false }

        return true
    }


    /*-- User Management --*/
    suspend fun joinTreasureHunt(userId: Long, treasureHuntId: Long): Boolean {

        val ref = UserTreasureHuntCrossRef(userId, treasureHuntId)
        val existingHunts = userTreasureHuntDao.getUserWithTreasureHunts(userId)
        var alreadyJoined = false

        existingHunts.collect { list ->

            alreadyJoined = list.any { userWithHunts ->
                userWithHunts.treasureHunts.any { it.id == treasureHuntId }
            }
        }

        if (alreadyJoined) { return false }

        userTreasureHuntDao.insertCrossRef(ref)
        return true
    }

    suspend fun leaveTreasureHunt(userId: Long, treasureHuntId: Long): Boolean {

        val ref = UserTreasureHuntCrossRef(userId, treasureHuntId)

        userTreasureHuntDao.deleteCrossRef(ref)
        return true
    }

    suspend fun deleteUser(id: Long): Boolean {

        val user = getUserById(id)

        if (user == null) { return false }

        userDao.deleteUser(user)
        return true
    }
}