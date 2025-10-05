package com.example.treasurely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.treasurely.ui.theme.TreasurelyTheme

/* Composable UI file access */
import com.example.treasurely.user.UserLogin
import com.example.treasurely.user.UserRegistration
import com.example.treasurely.clue.ClueList
import com.example.treasurely.clue.QRCodeScanner
import com.example.treasurely.treasure.hunt.CreateTreasureHunt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasurelyTheme {

                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { NavBar(navController) }
                )
                {
                        innerPadding -> Box(

                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                )
                {
                    NavHost(
                        navController = navController,
                        startDestination = "user_registration"
                    )
                    {
                        composable("dashboard") { Dashboard(navController) }
                        composable("qr_code_scanner") { QRCodeScanner(navController) }
                        composable("clues") { ClueList(navController) }
                        composable("join_hunt") { JoinTreasureHunt(navController) }
                        composable("create_hunt") { CreateTreasureHunt(navController) }
                        composable("user_profile") { UserProfile(navController) }
                        composable("user_registration") { UserRegistration(navController) }
                        composable("user_login") { UserLogin(navController) }
                        composable("treasure_hunt_details") { TreasureHuntDetails(navController) }
                        composable("clue_details") { ClueDetails(navController) }
                    }
                }
                }
            }
        }
    }
}


@Composable
fun ClueDetails(navController: NavController) {

    Text("Clue Details")
}


@Composable
fun TreasureHuntDetails(navController: NavController) {

    Text("Treasure Hunt Details")
}


/*
* The join screen should contain:
*   - An input box for a 6 digit code
*   - A submit button that transfers you to the dashboard
*/
@Composable
fun JoinTreasureHunt(navController: NavController) {

    Text("Join Treasure Hunt")
}


@Composable
fun UserProfile(navController: NavController) {

    Text("User Profile")
}
