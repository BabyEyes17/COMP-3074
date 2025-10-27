package com.example.treasurely.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    val allUsers by viewModel.allUsers.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when {
                    email.isBlank() || username.isBlank() || password.isBlank() -> {
                        scope.launch { snackbarHostState.showSnackbar("All fields must be filled.") }
                    }
                    password != confirmPassword -> {
                        scope.launch { snackbarHostState.showSnackbar("Passwords don’t match.") }
                    }
                    else -> {
                        scope.launch {
                            viewModel.registerUser(email, username, password) { success ->
                                if (success) {
                                    scope.launch { snackbarHostState.showSnackbar("Registration complete.") }
                                    email = ""
                                    username = ""
                                    password = ""
                                    confirmPassword = ""
                                } else {
                                    scope.launch { snackbarHostState.showSnackbar("Registration failed.") }
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 8.dp)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("user_login") },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Go to Login")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Registered Users (${allUsers.size})",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (allUsers.isEmpty()) {
            Text(
                text = "No users yet.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(allUsers) { user ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = user.username,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = user.email,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
