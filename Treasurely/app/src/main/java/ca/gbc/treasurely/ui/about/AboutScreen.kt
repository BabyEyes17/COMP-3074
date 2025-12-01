package ca.gbc.treasurely.ui.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {

    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scroll)
    ) {

        // ---- TOP BAR ----
        TopAppBar(
            title = {
                Text(
                    "About Treasurely",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(Modifier.height(20.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            InfoCard(
                title = "App Purpose",
                text = """
Treasurely is a location-based field game that allows players to create, explore, and complete treasure hunts around real-world locations.

Players scan QR codes, follow clues, and discover Points of Interest (POIs) to progress and complete the hunt.
                """.trimIndent()
            )

            Spacer(Modifier.height(16.dp))

            InfoCard(
                title = "Team Members",
                text = """
• Aidan Repchik — 101535819
• Henil Patel — 101511850
• Jayden Lewis — 101484621
• Sami Ar Rahman — 101488786
                """.trimIndent()
            )

            Spacer(Modifier.height(16.dp))

            InfoCard(
                title = "Course Information",
                text = """
COMP 3074 — Mobile App Development
Professor: Przemyslaw Pawluk
George Brown College
                """.trimIndent()
            )

            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun InfoCard(
    title: String,
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {

            // ---- COLORED HEADER ----
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // ---- BODY ----
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
