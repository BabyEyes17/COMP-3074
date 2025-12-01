package ca.gbc.treasurely.ui.poi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ca.gbc.treasurely.R
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
                title = {
                    Text(
                        "Points of Interest",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { onOpenScanner() }) {
                        Text("QR", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onOpenCreate,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_add),
                    contentDescription = "Create",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.size(26.dp)
                )
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
                label = {
                    Text(
                        "Search by name or tag",
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(pois, key = { it.id }) { poi ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .clickable { onOpenDetails(poi.id) },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(
                                poi.name,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                poi.address,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            val tags = poi.tags.joinToString(" • ")
                            if (tags.isNotBlank()) {
                                Spacer(Modifier.height(8.dp))
                                Text(
                                    tags,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
