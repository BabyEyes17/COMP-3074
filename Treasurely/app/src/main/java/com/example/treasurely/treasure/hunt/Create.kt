package com.example.treasurely.treasure.hunt

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.treasurely.viewmodel.TreasureHuntViewModel
import kotlinx.coroutines.launch

/*
* The create screen should contain:
*
*   - Input fields for:
*       + treasure hunt name
*       + description
*       + starting point (address, GPS coordinates, "start anywhere" toggle)
*       + search radius (minimum of farthest clue location)
*       + start datetime
*       + end datetime
*       + reward (for the user with the most points)
*       + cover image (optional)
*
*   - A clue create button
*
*   - Input fields for:
*       + clue name
*       + clue description
*       + location (GPS coordinates)
*       + points reward (automatically decreased depending on how many user's have found the clue)
*       + photo upload (optional)
*       + next clue (optional)
*       + next clue hint (optional)
*       + display next clue image (optional toggle)
*
*   - Each clue should have an automatically created printable QR code that would be placed at each clue location
*
*   - A submit button that transfers you to the dashboard
*/

@Composable
fun CreateTreasureHunt(
    navController: NavController,
    viewModel: TreasureHuntViewModel
)
{
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var gpsLocation by remember { mutableStateOf("") }
    var radius by remember { mutableStateOf("") }
    var reward by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Text(
            text = "Create Treasure Hunt",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Hunt Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = gpsLocation,
            onValueChange = { gpsLocation = it },
            label = { Text("Starting GPS Location") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = radius,
            onValueChange = { radius = it },
            label = { Text("Search Radius (m)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = reward,
            onValueChange = { reward = it },
            label = { Text("Reward") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

            },

            modifier = Modifier.padding(0.dp, 8.dp),
        )
        {
            Text("Add Clue")
        }

        Button(
            onClick = {
                scope.launch {

                    if (name.isBlank() || gpsLocation.isBlank() || radius.isBlank()) {
                        Toast.makeText(
                            context,
                            "Please fill all required fields.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else {
                        try {

                            val radiusInt = radius.toIntOrNull() ?: 0

                            viewModel.createTreasureHunt(
                                name = name,
                                description = description.ifBlank { null },
                                gpsStartingLocation = gpsLocation,
                                searchRadiusMeters = radiusInt,
                                reward = reward.ifBlank { null },
                                coverImage = null
                            )
                            { hunt ->

                                if (hunt != null) {

                                    Toast.makeText(
                                        context,
                                        "Hunt created! Code: ${hunt.joinCode}",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("dashboard")
                                }

                                else {
                                    Toast.makeText(
                                        context,
                                        "Failed to create hunt.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                        catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Error: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 8.dp)
        )
        {
            Text("Create")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        {
            Text("Cancel")
        }
    }
}
