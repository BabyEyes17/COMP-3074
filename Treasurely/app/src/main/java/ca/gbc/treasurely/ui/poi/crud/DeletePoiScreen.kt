package ca.gbc.treasurely.ui.poi.crud

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ca.gbc.treasurely.ui.common.LoadingView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletePoiScreen(
    viewModel: PoiCrudViewModel,
    poiId: String,
    onDeleted: () -> Unit,
    onCancel: () -> Unit
) {
    val ready by viewModel.ready.observeAsState(false)
    if (!ready) return LoadingView()

    val poi by viewModel.loadPoi(poiId).observeAsState(null)
    if (poi == null) return LoadingView()

    val scope = rememberCoroutineScope()

    Scaffold(topBar = { TopAppBar(title = { Text("Delete POI") }) }) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Are you sure you want to delete:")
            Text(poi!!.name, style = MaterialTheme.typography.headlineSmall)

            Spacer(Modifier.height(20.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = onCancel) { Text("Cancel") }

                Button(
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                    onClick = {
                        scope.launch {
                            viewModel.deletePoi(poi!!)
                            onDeleted()
                        }
                    }
                ) { Text("Delete") }
            }
        }
    }
}
