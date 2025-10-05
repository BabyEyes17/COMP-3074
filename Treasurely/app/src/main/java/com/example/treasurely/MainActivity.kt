package com.example.treasurely

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.treasurely.ui.theme.TreasurelyTheme


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


@Composable
fun QRCodeScanner(navController: NavController) {

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.outline_qr_code_24),
                contentDescription = "Temporary QR Code",
                modifier = Modifier.size(200.dp)
            )
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .width(160.dp),

            onClick = {
                Toast.makeText(context, "You Found A Clue!", Toast.LENGTH_LONG).show()
                navController.navigate("clues")

            }
        )
        {
            Text("Scan", fontSize = 20.sp)
        }
    }
}


@Composable
fun ClueList(navController: NavController) {
    val context = LocalContext.current

    val treasureHunts = mapOf(
        "Hunt 1" to listOf("Clue 1", "Clue 2", "Clue 3"),
        "Hunt 2" to listOf("Clue 1", "Clue 2", "Clue 3"),
        "Hunt 3" to listOf("Clue 1", "Clue 2", "Clue 3"),
        "Hunt 4" to listOf("Clue 1", "Clue 2", "Clue 3")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        treasureHunts.forEach { (huntName, clues) ->
            item {
                Text(
                    text = huntName,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(clues) { clue ->
                Text(
                    text = clue,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
                        .clickable {
                            Toast
                                .makeText(context, "Opening $clue...", Toast.LENGTH_SHORT)
                                .show()
                        }
                )

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}


@Composable
fun ClueDetails(navController: NavController) {

    Text("Clue Details")
}


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


/*
* The create screen should contain:
*
*   - Input fields for:
*       + treasure hunt name
*       + description
*       + starting point (address, GPS coordinates, "start anywhere" toggle)
*       + search radius (minimum of farthest clue location)
*       + start datetime
*       + end datetime
*       + reward (for the user with the most points)
*       + cover image (optional)
*
*   - A clue create button
*
*   - Input fields for:
*       + clue name
*       + clue description
*       + location (GPS coordinates)
*       + points reward (automatically decreased depending on how many user's have found the clue)
*       + photo upload (optional)
*       + next clue (optional)
*       + next clue hint (optional)
*       + display next clue image (optional toggle)
*
*   - Each clue should have an automatically created printable QR code that would be placed at each clue location
*
*   - A submit button that transfers you to the dashboard
*/
@Composable
fun CreateTreasureHunt(navController: NavController) {

    Text("Create Treasure Hunt")
}


@Composable
fun UserProfile(navController: NavController) {

    Text("User Profile")
}


@Composable
fun UserRegistration(navController: NavController) {

    Text("User Registration")
}


@Composable
fun UserLogin(navController: NavController) {

    Text("User Login")

    Column {

        TextField(
            label = "Email"
        )

        TextField(
            label = "Password"
        )
    }
}

