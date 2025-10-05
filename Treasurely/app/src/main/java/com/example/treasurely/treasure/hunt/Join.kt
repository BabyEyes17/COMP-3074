package com.example.treasurely.treasure.hunt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/*
* The join screen should contain:
*   - An input box for a 6 digit code
*   - A submit button that transfers you to the dashboard
*/
@Composable
fun JoinTreasureHunt(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Join a Treasure Hunt",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )


        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Enter Hunt Code") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )


        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { navController.navigate("dashboard") },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 8.dp)
        )
        {
            Text("Join Hunt")
        }


        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Enter the 6-digit code you received to join a hunt.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}