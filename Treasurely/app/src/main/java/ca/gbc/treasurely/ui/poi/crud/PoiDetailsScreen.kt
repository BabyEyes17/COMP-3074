package ca.gbc.treasurely.ui.poi.details

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap
import ca.gbc.treasurely.ui.common.LoadingView
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

    // Generate QR code ONCE
    val qrBitmap: Bitmap = remember(poiId) {
        generateQrCode(poiId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(poi!!.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("←") }
                },
                actions = {
                    IconButton(onClick = { onEdit(poiId) }) { Text("Edit") }
                    IconButton(onClick = { onDelete(poiId) }) { Text("Del") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Address: ${poi!!.address}")
            Text("Task: ${poi!!.task}")
            Text("Tags: ${poi!!.tags.joinToString(" • ")}")
            Text("Lat: ${poi!!.latitude}")
            Text("Lng: ${poi!!.longitude}")

            Spacer(Modifier.height(20.dp))

            /* QR CODE DISPLAY */
            Image(
                bitmap = qrBitmap.asImageBitmap(),
                contentDescription = "QR Code for ${poi!!.name}",
                modifier = Modifier.size(220.dp)
            )

            Text("Scan this QR code to open this POI", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    saveQrToGallery(context, qrBitmap, poi!!.name)
                }
            ) {
                Text("Download QR Code")
            }
        }
    }
}
