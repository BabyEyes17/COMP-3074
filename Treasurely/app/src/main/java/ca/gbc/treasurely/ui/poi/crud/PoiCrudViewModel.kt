package ca.gbc.treasurely.ui.poi.crud

import android.net.Uri
import androidx.lifecycle.*
import ca.gbc.treasurely.data.model.PointOfInterest
import ca.gbc.treasurely.data.repository.PoiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PoiCrudViewModel : ViewModel() {

    private var repo: PoiRepository? = null

    private val _ready = MutableLiveData(false)
    val ready: LiveData<Boolean> get() = _ready

    private val _scannerResult = MutableLiveData<String?>()
    val scannerResult: LiveData<String?> get() = _scannerResult

    fun init(repo: PoiRepository) {
        this.repo = repo
        _ready.value = true
    }

    fun searchPoi(query: String): LiveData<List<PointOfInterest>> {
        val r = repo ?: return MutableLiveData(emptyList())
        return if (query.isBlank()) {
            r.getAllPoi()
        } else {
            r.searchByNameOrTag(query)
        }
    }

    fun loadPoi(id: String): LiveData<PointOfInterest?> {
        val r = repo ?: return MutableLiveData(null)
        return r.getPoiById(id)
    }

    fun createPoi(poi: PointOfInterest) {
        viewModelScope.launch(Dispatchers.IO) {
            repo?.createPoi(poi)
        }
    }

    fun updatePoi(poi: PointOfInterest) {
        viewModelScope.launch(Dispatchers.IO) {
            repo?.updatePoi(poi)
        }
    }

    fun deletePoi(poi: PointOfInterest) {
        viewModelScope.launch(Dispatchers.IO) {
            repo?.deletePoi(poi)
        }
    }

    fun handleQrScan(result: String?) {
        _scannerResult.postValue(result)
    }

    fun clearScannerResult() {
        _scannerResult.value = null
    }
}
