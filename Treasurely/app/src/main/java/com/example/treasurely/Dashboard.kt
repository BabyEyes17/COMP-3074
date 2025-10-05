package com.example.treasurely

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Dashboard(navController: NavController) {

    val activeHunts = listOf(
        "Treasure Hunt 1",
        "Treasure Hunt 2",
        "Treasure Hunt 3",
        "Treasure Hunt 4",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        Column {

            Text(
                text = "My Treasure Hunts",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(text = "250 Points")
                Text(text = "Total Clues Found: 12")
            }

            LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)) {

                items(activeHunts) { hunt ->

                    Text(
                        text = hunt,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,8.dp)
                            .clickable{ navController.navigate("clues") }

                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }

            Spacer(modifier = Modifier.size(8.dp))

        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Button(
                onClick = { navController.navigate("join_hunt") },
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) { Text("Join Hunt") }

            Button(
                onClick = { navController.navigate("create_hunt") },
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            ) { Text("Create Hunt") }

        }
    }
}