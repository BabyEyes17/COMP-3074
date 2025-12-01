package ca.gbc.treasurely.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.gbc.treasurely.data.model.User
import ca.gbc.treasurely.data.repository.UserRepository
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val repo: UserRepository,
    private val userId: String? = null
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    init {
        if (userId != null) {
            // LiveData from DAO → directly exposed
            repo.getUserById(userId).observeForever {
                _user.value = it
            }
        }
    }

    fun saveUser(name: String, email: String, phone: String, onDone: () -> Unit) {
        viewModelScope.launch {
            if (userId == null) {
                repo.createUser(User(name = name, email = email, phone = phone))
            } else {
                repo.updateUser(User(id = userId, name = name, email = email, phone = phone))
            }
            onDone()
        }
    }
}
