package ca.gbc.treasurely.ui.poi.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.gbc.treasurely.data.model.PointOfInterest
import ca.gbc.treasurely.data.repository.PoiRepository

class PoiListViewModel : ViewModel() {

    private var repo: PoiRepository? = null

    private val _ready = MutableLiveData(false)
    val ready: LiveData<Boolean> get() = _ready

    fun init(repo: PoiRepository) {
        this.repo = repo
        _ready.value = true
    }

    fun search(query: String): LiveData<List<PointOfInterest>> {
        val r = repo
            ?: return MutableLiveData(emptyList()) // repo not ready yet

        return if (query.isBlank()) {
            r.getAllPoi()
        } else {
            r.searchByNameOrTag(query)
        }
    }
}
