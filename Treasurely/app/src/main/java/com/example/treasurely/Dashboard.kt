package com.example.treasurely

import com.example.treasurely.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Dashboard(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize()
    )
    {

        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 72.dp)
        )
        {

            Text(
                text = "My Treasure Hunts",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp, start = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(text = "85 Points")
                Text(text = "Total Clues Found: 2")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Active Treasure Hunt",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { navController.navigate("treasure_hunt_details") }
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.gbc_logo),
                        contentDescription = "Campus Quest Logo",
                        modifier = Modifier.size(48.dp).padding(end = 8.dp)
                    )

                    Column {
                        Text(
                            text = "Campus Quest",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Explore the campus and uncover its secrets!",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Other Treasure Hunts",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                listOf(
                    "Treasure Hunt 1",
                    "Treasure Hunt 2",
                    "Treasure Hunt 3"
                ).forEach { hunt ->
                    Text(
                        text = hunt,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }
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
            )
            {
                Text("Join Hunt")
            }

            Button(
                onClick = { navController.navigate("create_hunt") },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            )
            {
                Text("Create Hunt")
            }
        }
    }
}
