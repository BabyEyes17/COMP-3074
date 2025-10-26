package com.example.treasurely.user

import androidx.compose.foundation.layout.*
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
fun UserLogin(
    navController: NavController,
    viewModel: UserViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            text = "Treasurely Login",
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(16.dp))

        Button(
            onClick = {
                when {

                    email.isBlank() || password.isBlank() -> {
                        scope.launch { snackbarHostState.showSnackbar("All fields are required") }
                    }

                    else -> {
                        viewModel.loginUser(email, password) { success ->

                            scope.launch {

                                if (success) {
                                    snackbarHostState.showSnackbar("Welcome back!")
                                    navController.navigate("user_home")
                                }

                                else {
                                    snackbarHostState.showSnackbar("Invalid credentials")
                                }
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(horizontal = 8.dp)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = { navController.navigate("user_register") },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Go to Register")
        }

        SnackbarHost(hostState = snackbarHostState)
    }
}
