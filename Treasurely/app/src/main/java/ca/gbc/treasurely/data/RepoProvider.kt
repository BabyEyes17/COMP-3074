package ca.gbc.treasurely.data

import android.content.Context
import ca.gbc.treasurely.data.repository.PoiRepository

object RepoProvider {

    private var _appDb: AppDatabase? = null
    private var _poiRepo: PoiRepository? = null

    fun poiRepository(context: Context): PoiRepository {
        if (_poiRepo == null) {
            val db = _appDb ?: AppDatabase.getInstance(context).also { _appDb = it }
            _poiRepo = PoiRepository(db.poiDao())
        }
        return _poiRepo!!
    }
}
