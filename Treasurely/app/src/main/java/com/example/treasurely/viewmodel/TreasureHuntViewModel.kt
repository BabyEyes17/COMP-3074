package com.example.treasurely.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treasurely.data.db.entities.TreasureHunt
import com.example.treasurely.data.repository.TreasureHuntRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TreasureHuntViewModel(private val repository: TreasureHuntRepository) : ViewModel() {

    /*
    * -- Available Treasure Hunt Actions --
    *
    * getAllTreasureHunts() returns Flow<List<TreasureHunt>>
    * getTreasureHuntById(id: Long) returns TreasureHunt?
    * getTreasureHuntByName(name: String) returns TreasureHunt?
    * getTreasureHuntByJoinCode(joinCode: String) returns TreasureHunt?
    * createTreasureHunt(...) returns TreasureHunt
    * updateTreasureHunt(hunt: TreasureHunt) returns Boolean
    * deleteTreasureHunt(hunt: TreasureHunt) returns Boolean
    */

    private val _currentTreasureHunt = MutableStateFlow<TreasureHunt?>(null)
    val currentTreasureHunt: StateFlow<TreasureHunt?> = _currentTreasureHunt

    val allTreasureHunts = repository.getAllTreasureHunts()


    /* -- Create -- */
    fun createTreasureHunt(
        name: String,
        description: String?,
        gpsStartingLocation: String,
        searchRadiusMeters: Int,
        reward: String?,
        coverImage: ByteArray?,
        onResult: (TreasureHunt?) -> Unit
    )
    {
        viewModelScope.launch {
            val hunt = repository.createTreasureHunt(
                name = name,
                description = description,
                gpsStartingLocation = gpsStartingLocation,
                searchRadiusMeters = searchRadiusMeters,
                reward = reward,
                coverImage = coverImage
            )
            _currentTreasureHunt.value = hunt
            onResult(hunt)
        }
    }


    /* -- Read -- */
    fun getTreasureHuntById(id: Long, onResult: (TreasureHunt?) -> Unit) {
        viewModelScope.launch {
            val hunt = repository.getTreasureHuntById(id)
            _currentTreasureHunt.value = hunt
            onResult(hunt)
        }
    }

    fun getTreasureHuntByName(name: String, onResult: (TreasureHunt?) -> Unit) {
        viewModelScope.launch {
            val hunt = repository.getTreasureHuntByName(name)
            _currentTreasureHunt.value = hunt
            onResult(hunt)
        }
    }

    fun getTreasureHuntByJoinCode(joinCode: String, onResult: (TreasureHunt?) -> Unit) {
        viewModelScope.launch {
            val hunt = repository.getTreasureHuntByJoinCode(joinCode)
            _currentTreasureHunt.value = hunt
            onResult(hunt)
        }
    }


    /* -- Update -- */
    fun updateTreasureHunt(hunt: TreasureHunt, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.updateTreasureHunt(hunt)
            onResult(true)
        }
    }


    /* -- Delete -- */
    fun deleteTreasureHunt(hunt: TreasureHunt, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.deleteTreasureHunt(hunt)
            onResult(true)
        }
    }
}
