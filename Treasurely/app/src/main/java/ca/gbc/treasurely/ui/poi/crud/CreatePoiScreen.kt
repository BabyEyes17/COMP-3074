package ca.gbc.treasurely.ui.poi.crud

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import ca.gbc.treasurely.ui.common.LoadingView
import ca.gbc.treasurely.ui.common.PoiForm
import kotlinx.coroutines.launch
import ca.gbc.treasurely.data.model.PointOfInterest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePoiScreen(
    viewModel: PoiCrudViewModel,
    onCreated: () -> Unit,
    onBack: () -> Unit
) {
    val ready by viewModel.ready.observeAsState(false)
    if (!ready) return LoadingView()

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var task by remember { mutableStateOf("") }
    var tags by remember { mutableStateOf("") }
    var latitude by remember { mutableStateOf("") }
    var longitude by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create POI") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    viewModel.createPoi(
                        PointOfInterest(
                            name = name,
                            address = address,
                            task = task,
                            tags = tags.split(",").map { it.trim() },
                            latitude = latitude.toDoubleOrNull() ?: 0.0,
                            longitude = longitude.toDoubleOrNull() ?: 0.0,
                            qrCodeValue = name.lowercase() + "-qr"
                        )
                    )
                    onCreated()
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
