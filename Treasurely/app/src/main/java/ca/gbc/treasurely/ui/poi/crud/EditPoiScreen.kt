package ca.gbc.treasurely.ui.poi.crud

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ca.gbc.treasurely.ui.common.LoadingView
import ca.gbc.treasurely.ui.common.PoiForm
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPoiScreen(
    viewModel: PoiCrudViewModel,
    poiId: String,
    onUpdated: () -> Unit,
    onBack: () -> Unit
) {
    val ready by viewModel.ready.observeAsState(false)
    if (!ready) return LoadingView()

    val poi by viewModel.loadPoi(poiId).observeAsState(null)
    if (poi == null) return LoadingView()

    var name by remember { mutableStateOf(poi!!.name) }
    var address by remember { mutableStateOf(poi!!.address) }
    var task by remember { mutableStateOf(poi!!.task) }
    var tags by remember { mutableStateOf(poi!!.tags.joinToString(",")) }
    var latitude by remember { mutableStateOf(poi!!.latitude.toString()) }
    var longitude by remember { mutableStateOf(poi!!.longitude.toString()) }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit POI") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    viewModel.updatePoi(
                        poi!!.copy(
                            name = name,
                            address = address,
                            task = task,
                            tags = tags.split(",").map { it.trim() },
                            latitude = latitude.toDouble(),
                            longitude = longitude.toDouble()
                        )
                    )
                    onUpdated()
                }
            }) {
                Text("Save")
            }
        }
    ) { padding ->
        PoiForm(
            name = name, onNameChange = { name = it },
            address = address, onAddressChange = { address = it },
            task = task, onTaskChange = { task = it },
            tags = tags, onTagsChange = { tags = it },
            latitude = latitude, onLatitudeChange = { latitude = it },
            longitude = longitude, onLongitudeChange = { longitude = it },
            modifier = Modifier.padding(padding)
        )
    }
}
