package com.example.treasurely

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavBar(navController: NavController) {

    NavigationBar {

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.outline_qr_code_scanner_24), contentDescription = "Scan QR Code", modifier = Modifier.size(32.dp)) },
            selected = false,
            onClick = {
                navController.navigate("qr_code_scanner")

            }
        )

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_question_mark_24), contentDescription = "Clues", modifier = Modifier.size(32.dp)) },
            selected = false,
            onClick = {
                navController.navigate("clues")

            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard", modifier = Modifier.size(32.dp)) },
            selected = false,
            onClick = {
                navController.navigate("dashboard")

            }
        )

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.outline_group_24), contentDescription = "Join Treasure Hunt", modifier = Modifier.size(32.dp)) },
            selected = false,
            onClick = {
                navController.navigate("join_hunt")

            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "User Profile", modifier = Modifier.size(32.dp)) },
            selected = false,
            onClick = {
                navController.navigate("user_profile")

            }
        )
    }
}