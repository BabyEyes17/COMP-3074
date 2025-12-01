package ca.gbc.treasurely.ui.poi.crud

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.gbc.treasurely.ui.common.LoadingView
import ca.gbc.treasurely.ui.common.PoiForm
import kotlinx.coroutines.launch
import ca.gbc.treasurely.data.model.PointOfInterest
import ca.gbc.treasurely.R

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
                title = {
                    Text(
                        "Create POI",
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
