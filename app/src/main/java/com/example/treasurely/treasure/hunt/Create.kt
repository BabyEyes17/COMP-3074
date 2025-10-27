package com.example.treasurely.treasure.hunt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
fun CreateTreasureHunt(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
    )
    {

        Text(
            text = "Treasure Hunt Creator",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        )


        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        )
        {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    "Hunt Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Treasure Hunt Name") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Description") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Starting Point (Address / GPS)") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Search Radius (km)") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Start Date & Time") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("End Date & Time") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Reward") })
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        )
        {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    "Clue Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Clue Name") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Clue Description") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Clue Location (GPS)") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Points Reward") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Next Clue (optional)") })
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(value = "", onValueChange = {}, label = { Text("Next Clue Hint (optional)") })
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.5f)) {
                Text("Add Clue")
            }

            Button(onClick = { navController.navigate("dashboard") }, modifier = Modifier.fillMaxWidth(0.5f)) {
                Text("Submit")
            }
        }
    }
}