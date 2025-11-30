package com.example.treasurely.ui.poi.list

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
import com.example.treasurely.data.model.PointOfInterest
import com.example.treasurely.data.repository.PoiRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoiListScreen(
    repo: PoiRepository,
    onOpenDetails: (String) -> Unit
) {
    var q by remember { mutableStateOf("") }

    val pois by (if (q.isBlank()) repo.getAllPoi() else repo.searchByNameOrTag(q.trim()))
        .observeAsState(emptyList())

    Scaffold(topBar = { TopAppBar(title = { Text("Points of Interest") }) }) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp).fillMaxSize()
        ) {
            OutlinedTextField(
                value = q,
                onValueChange = { q = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Search by name or tag") }
            )

            Spacer(Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(pois, key = { it.id }) { poi ->
                    Card(
                        Modifier.fillMaxWidth().clickable { onOpenDetails(poi.id) }
                    ) {
                        Column(Modifier.padding(14.dp)) {
                            Text(poi.name, style = MaterialTheme.typography.titleMedium)
                            Text(
                                poi.address,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            val tagPreview = poi.tags.take(4).joinToString(" • ")
                            if (tagPreview.isNotBlank()) {
                                Spacer(Modifier.height(6.dp))
                                Text(tagPreview, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}
