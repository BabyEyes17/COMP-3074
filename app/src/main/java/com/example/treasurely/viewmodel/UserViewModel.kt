package com.example.treasurely.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

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

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    val allUsers = repository.getAllUsers()

    fun registerUser(email: String, username: String, password: String) {
        viewModelScope.launch {
            repository.registerUser(email, username, password)
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {

            val success = repository.loginUser(email, password)

            if (success) {
                _currentUser.value = repository.getUserByEmail(email)
            }

            else {
                _currentUser.value = null
            }

            onResult(success)
        }
    }

    private fun UserRepository.loginUser(
        email: String,
        password: String
    ) {
    }


    fun logoutUser() {
        _currentUser.value = null
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            repository.deleteUser(id)
        }
    }
}