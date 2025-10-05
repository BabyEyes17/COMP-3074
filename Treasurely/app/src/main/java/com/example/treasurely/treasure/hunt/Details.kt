package com.example.treasurely.treasure.hunt

import com.example.treasurely.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TreasureHuntDetails(navController: NavController) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    )
    {
        Column {
            Image(
                painter = painterResource(id = R.drawable.gbc_logo),
                contentDescription = "GBC Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )


            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            )
            {
                Text(
                    text = "Campus Quest",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                Text(
                    text = "Explore the campus and uncover its secrets!",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


                Text("Starting Point: George Brown College, Casa Loma Campus")
                Text("Search Radius: 1 km")


                Spacer(modifier = Modifier.height(8.dp))


                Text("Start: Oct 10, 2025 - 10:00 AM")
                Text("End: Oct 10, 2025 - 4:00 PM")


                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = "Reward: Free lunch at Subway",
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
