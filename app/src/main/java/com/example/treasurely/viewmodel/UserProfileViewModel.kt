package com.example.treasurely.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class UserProfileUiState {
    object Loading : UserProfileUiState()
    data class Success(val user: User) : UserProfileUiState()
    data class Error(val message: String) : UserProfileUiState()
}

class UserProfileViewModel(
    private val userRepository: UserRepository,
    private val userId: Long
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserProfileUiState>(UserProfileUiState.Loading)
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            _uiState.value = UserProfileUiState.Loading
            try {
                val user = userRepository.getUserById(userId)
                if (user != null) {
                    _uiState.value = UserProfileUiState.Success(user)
                } else {
                    _uiState.value = UserProfileUiState.Error("User not found")
                }
            } catch (e: Exception) {
                _uiState.value = UserProfileUiState.Error(
                    e.message ?: "An error occurred while loading profile"
                )
            }
        }
    }

    fun refreshProfile() {
        loadUserProfile()
    }

    fun logout() {
        viewModelScope.launch {
            // Add any cleanup logic here
            // For example: clear session tokens, preferences, etc.
        }
    }
}

class UserProfileViewModelFactory(
    private val userRepository: UserRepository,
    private val userId: Long
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            return UserProfileViewModel(userRepository, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}