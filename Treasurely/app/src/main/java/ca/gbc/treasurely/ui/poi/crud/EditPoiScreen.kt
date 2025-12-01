package ca.gbc.treasurely.ui.poi.crud

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                title = {
                    Text(
                        "Edit POI",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("←", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
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
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Text(
                    "Save",
                    style = MaterialTheme.typography.titleMedium
                )
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
