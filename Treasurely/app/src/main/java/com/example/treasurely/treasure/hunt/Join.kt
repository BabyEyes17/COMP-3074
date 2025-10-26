package com.example.treasurely.treasure.hunt




/*
* The join screen should contain:
*   - An input box for a 6 digit code
*   - A submit button that transfers you to the dashboard
*/



import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.treasurely.data.AppState

@Composable
fun JoinTreasureHunt(navController: NavController) {
    val ctx = LocalContext.current
    var code by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    // Valid only when exactly 6 digits
    val isValid = code.length == 6 && code.all { it.isDigit() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Join a Treasure Hunt",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = code,
            onValueChange = { input ->
                // keep only digits and clamp to 6 chars
                code = input.filter { it.isDigit() }.take(6)
                error = null
            },
            label = { Text("Enter 6-digit Code") },
            isError = error != null,
            supportingText = { if (error != null) Text(error!!) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            enabled = isValid,
            onClick = {
                AppState.join(code)
                    .onSuccess { hunt ->
                        Toast.makeText(ctx, "Joined ${hunt.name}", Toast.LENGTH_SHORT).show()
                        // go back to your dashboard route (change if your route id is different)
                        navController.navigate("dashboard") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    .onFailure { e -> error = e.message ?: "Unable to join" }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Join")
        }
        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Enter the 6-digit code you received to join a hunt.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 24.dp),
            textAlign = TextAlign.Center
        )
    }


    }

