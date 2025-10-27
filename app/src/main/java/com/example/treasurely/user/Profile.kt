package com.example.treasurely.user

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.treasurely.data.db.entities.User
import com.example.treasurely.data.repository.UserRepository
import android.graphics.BitmapFactory
import com.example.treasurely.data.db.TreasurelyDatabase

@Composable
fun UserProfile(navController: NavController) {
    val context = LocalContext.current

    // Get logged-in userId from SharedPreferences
    val sharedPrefs = remember {
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    }
    val userId = remember { sharedPrefs.getLong("logged_in_user_id", 0L) }

    // Check if user is logged in
    if (userId == 0L) {
        // No user logged in, redirect to login
        ErrorScreen(
            message = "Please log in to view your profile",
            onBackToLogin = {
                navController.navigate("user_login") {
                    popUpTo(0) { inclusive = true }
                }
            }
        )
        return
    }

    // Get UserRepository from TreasurelyDatabase
    val database = remember {
        TreasurelyDatabase.getInstance(context)
    }
    val userRepository = remember {
        UserRepository(database.userDao())
    }

    val viewModel: UserProfileViewModel = viewModel(
        factory = UserProfileViewModelFactory(userRepository, userId)
    )

    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UserProfileUiState.Loading -> {
            LoadingScreen()
        }
        is UserProfileUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onBackToLogin = {
                    navController.navigate("user_login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        is UserProfileUiState.Success -> {
            UserProfileContent(
                user = state.user,
                navController = navController,
                onLogout = {
                    // Clear session data
                    sharedPrefs.edit().apply {
                        remove("logged_in_user_id")
                        apply()
                    }
                    viewModel.logout()
                    navController.navigate("user_login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onRefresh = { viewModel.refreshProfile() }
            )
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Loading profile...",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun ErrorScreen(
    message: String,
    onBackToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onBackToLogin,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Back to Login")
        }
    }
}

@Composable
private fun UserProfileContent(
    user: User,
    navController: NavController,
    onLogout: () -> Unit,
    onRefresh: () -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Profile Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture
                if (user.profilePhoto != null) {
                    val bitmap = BitmapFactory.decodeByteArray(
                        user.profilePhoto,
                        0,
                        user.profilePhoto.size
                    )
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(120.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Username
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Email
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }

        // Stats Section
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("Points", user.totalPoints.toString())
                    StatItem("Member Since", user.createdAt.year.toString())
                }
            }
        }

        // Account Settings Section
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Account Settings",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column {
                    SettingsItem("Edit Profile") {
                        navController.navigate("edit_profile/${user.id}")
                    }
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                    SettingsItem("Change Password") {
                        navController.navigate("change_password/${user.id}")
                    }
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                    SettingsItem("Notifications") {
                        Toast.makeText(context, "Notifications", Toast.LENGTH_SHORT).show()
                    }
                    HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                    SettingsItem("Privacy Settings") {
                        Toast.makeText(context, "Privacy Settings", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Logout Button
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Logging out...", Toast.LENGTH_SHORT).show()
                    onLogout()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Log Out", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = ">",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}