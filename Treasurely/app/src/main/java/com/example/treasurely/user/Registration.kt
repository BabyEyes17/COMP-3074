package com.example.treasurely.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.treasurely.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun UserRegistration(
    navController: NavController,
    viewModel: UserViewModel
) {
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Treasurely Registration",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(8.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(8.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                when {
                    email.isBlank() || username.isBlank() || password.isBlank() -> {
                        scope.launch { snackbarHostState.showSnackbar("All fields are required") }
                    }
                    password != confirmPassword -> {
                        scope.launch { snackbarHostState.showSnackbar("Passwords do not match") }
                    }
                    else -> {
                        scope.launch {
                            try {
                                viewModel.registerUser(email, username, password)
                                snackbarHostState.showSnackbar("Registration successful!")
                                navController.navigate("user_login")
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar("Error: ${e.message}")
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 8.dp)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = { navController.navigate("user_login") },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Go to Login")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
