package ca.gbc.treasurely.ui.poi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ca.gbc.treasurely.data.model.PointOfInterest
import ca.gbc.treasurely.ui.common.LoadingView
import ca.gbc.treasurely.ui.poi.crud.PoiCrudViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoiListScreen(
    viewModel: PoiCrudViewModel,
    onOpenDetails: (String) -> Unit,
    onOpenCreate: () -> Unit,
    onOpenScanner: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    val ready by viewModel.ready.observeAsState(false)
    if (!ready) return LoadingView()

    val pois by viewModel.searchPoi(query).observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Points of Interest") },
                actions = {
                    IconButton(onClick = { onOpenScanner() }) { Text("QR") }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onOpenCreate) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search by name or tag") },
                singleLine = true
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(pois, key = { it.id }) { poi ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onOpenDetails(poi.id) }
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(poi.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                poi.address,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            val tags = poi.tags.joinToString(" • ")
                            if (tags.isNotBlank()) {
                                Spacer(Modifier.height(8.dp))
                                Text(tags, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}
