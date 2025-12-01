package ca.gbc.treasurely.ui.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "About Treasurely",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        SectionCard(title = "App Purpose") {
            Text(
                text = "Treasurely is a location-based field game that allows users to create, " +
                        "explore, and complete treasure hunts around real-world locations. " +
                        "Players scan QR codes, follow clues, and discover Points of Interest (POIs) to complete the hunt.",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ----- TEAM -----
        SectionCard(title = "Team Members") {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("• Jayden Lewis — 101484621")
                Text("• Aidan Repchik — 101535819")
                Text("• [Teammate 3 Name] — [ID]")
                Text("• [Teammate 4 Name] — [ID]")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        SectionCard(title = "Course Information") {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("COMP 3074 — Mobile App Development")
                Text("Professor: [Professor Name]")
                Text("George Brown College")
                Text("Winter 2025")
            }
        }
    }
}

@Composable
fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(18.dp)) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            content()
        }
    }
}
