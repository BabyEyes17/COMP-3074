package com.example.treasurely.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treasurely.data.db.entities.Clue
import com.example.treasurely.data.repository.ClueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClueViewModel(private val repository: ClueRepository) : ViewModel() {

    /*
    * -- Available Clue Actions --
    *
    * getAllClues() returns Flow<List<Clue>>
    * getClueById(id: Long) returns Clue
    * getCluesForTreasureHunt(treasureHuntId: Long) returns Flow<List<Clue>>
    *
    * insertClue(clue: Clue) returns Long
    * updateClue(clue: Clue) returns Boolean
    * deleteClue(clue: Clue) returns Boolean
    *
    * recordUserProgress(userId: Long, clueId: Long) returns Boolean
    * removeUserProgress(userId: Long, clueId: Long) returns Boolean
    * getCluesForUser(userId: Long) returns Flow<List<UserClueProgressCrossRef>>
    */

    private val _currentClue = MutableStateFlow<Clue?>(null)
    val currentClue: StateFlow<Clue?> = _currentClue

    val allClues = repository.getAllClues()

    fun insertClue(clue: Clue, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val id = repository.insertClue(clue)
            onResult(id > 0)
        }
    }

    fun updateClue(clue: Clue, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.updateClue(clue)
            onResult(true)
        }
    }

    fun deleteClue(clue: Clue, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.deleteClue(clue)
            onResult(true)
        }
    }

    fun getClueById(id: Long, onResult: (Clue?) -> Unit) {
        viewModelScope.launch {
            val clue = repository.getClueById(id)
            _currentClue.value = clue
            onResult(clue)
        }
    }

    fun getCluesForTreasureHunt(treasureHuntId: Long) =
        repository.getCluesForTreasureHunt(treasureHuntId)

    /* -- User Progress -- */

    fun recordUserProgress(userId: Long, clueId: Long, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.recordUserProgress(userId, clueId)
            onResult(success)
        }
    }

    fun removeUserProgress(userId: Long, clueId: Long, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.removeUserProgress(userId, clueId)
            onResult(success)
        }
    }

    fun getCluesForUser(userId: Long) =
        repository.getCluesForUser(userId)
}
