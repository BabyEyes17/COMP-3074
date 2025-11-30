package com.example.treasurely.data.repository

import com.example.treasurely.data.dao.UserDao
import com.example.treasurely.data.model.User

class UserRepository(private val dao: UserDao) {

    /* POST */
    suspend fun createUser(user: User) = dao.createUser(user)



    /* GET */
    fun getAllUsers() = dao.getAllUsers()

    fun getUserById(id: String) = dao.getUserById(id)

    fun getUserByName(name: String) = dao.getUserByName(name)



    /* PUT */
    suspend fun updateUser(user: User) = dao.updateUser(user)



    /* DELETE */
    suspend fun deleteUser(user: User) = dao.deleteUser(user)
}
