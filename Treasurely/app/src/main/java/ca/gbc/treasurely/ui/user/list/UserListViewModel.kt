package ca.gbc.treasurely.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.gbc.treasurely.data.model.User
import ca.gbc.treasurely.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repo: UserRepository
) : ViewModel() {

    val users: LiveData<List<User>> = repo.getAllUsers()

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }
}
