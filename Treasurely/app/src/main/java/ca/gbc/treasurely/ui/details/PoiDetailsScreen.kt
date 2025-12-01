package ca.gbc.treasurely.ui.poi.details
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import ca.gbc.treasurely.data.repository.PoiRepository
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PoiDetailsScreen(
//    repo: PoiRepository,
//    id: String,
//    onBack: () -> Unit
//) {
//    val poi by repo.getPoiById(id).observeAsState(null)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(poi?.name ?: "Details") },
//                navigationIcon = { IconButton(onClick = onBack) { Text("←") } }
//            )
//        }
//    ) { padding ->
//        val p = poi
//        if (p == null) {
//            Box(Modifier.padding(padding).fillMaxSize(), contentAlignment = Alignment.Center) {
//                CircularProgressIndicator()
//            }
//        } else {
//            Column(
//                Modifier.padding(padding).padding(16.dp).fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                Text("Address", style = MaterialTheme.typography.labelLarge)
//                Text(p.address)
//
//                Text("Task", style = MaterialTheme.typography.labelLarge)
//                Text(p.task)
//
//                Text("Tags", style = MaterialTheme.typography.labelLarge)
//                Text(p.tags.joinToString(", ").ifBlank { "None" })
//            }
//        }
//    }
//}
