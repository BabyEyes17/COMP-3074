package ca.gbc.treasurely.ui.poi.details

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import ca.gbc.treasurely.ui.common.LoadingView
import ca.gbc.treasurely.ui.common.StarRating
import ca.gbc.treasurely.ui.poi.crud.PoiCrudViewModel
import ca.gbc.treasurely.utils.generateQrCode
import ca.gbc.treasurely.utils.saveQrToGallery

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoiDetailsScreen(
    viewModel: PoiCrudViewModel,
    poiId: String,
    onEdit: (String) -> Unit,
    onDelete: (String) -> Unit,
    onBack: () -> Unit
) {
    val ready by viewModel.ready.observeAsState(false)
    if (!ready) return LoadingView()

    val poi by viewModel.loadPoi(poiId).observeAsState(null)
    if (poi == null) return LoadingView()

    val context = LocalContext.current

    val qrBitmap: Bitmap = remember(poiId) {
        generateQrCode(poiId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        poi!!.name,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("←", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                actions = {
                    IconButton(onClick = { onEdit(poiId) }) {
                        Text("Edit", color = MaterialTheme.colorScheme.onPrimary)
                    }
                    IconButton(onClick = { onDelete(poiId) }) {
                        Text("Del", color = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Information",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(12.dp))
                    Text("Address: ${poi!!.address}", style = MaterialTheme.typography.bodyMedium)
                    Text("Task: ${poi!!.task}", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "Tags: ${poi!!.tags.joinToString(" • ")}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text("Lat: ${poi!!.latitude}", style = MaterialTheme.typography.bodySmall)
                    Text("Lng: ${poi!!.longitude}", style = MaterialTheme.typography.bodySmall)
                }
            }

            var currentRating by remember { mutableStateOf(poi!!.rating ?: 0) }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Your Rating",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(8.dp))
                    StarRating(
                        rating = currentRating,
                        onRatingSelected = { new ->
                            currentRating = new
                            viewModel.updateRating(poi!!, new)
                        }
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        bitmap = qrBitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier.size(220.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Scan this QR code to open this POI",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            saveQrToGallery(context, qrBitmap, poi!!.name)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text("Download QR Code")
                    }
                }
            }
        }
    }
}
