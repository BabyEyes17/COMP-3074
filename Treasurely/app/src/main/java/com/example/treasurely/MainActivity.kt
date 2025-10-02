package com.example.treasurely

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.treasurely.ui.theme.TreasurelyTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasurelyTheme {

                var selectedScreen by remember { mutableIntStateOf(0) }

                Scaffold(
                    bottomBar = { NavBar(selectedScreen) { selectedScreen = it } }
                )
                {
                    innerPadding -> Box(

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    )
                    {
                        when (selectedScreen) {
                            0 -> Dashboard()
                            1 -> QRCodeScanner()
                            2 -> ClueList()
                            3 -> JoinTreasureHunt()
                            4 -> CreateTreasureHunt()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NavBar(selectedScreen: Int, onSelect: (Int) -> Unit) {

    val context = LocalContext.current

    NavigationBar {

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.outline_qr_code_scanner_24), contentDescription = "Scan QR Code", modifier = Modifier.size(32.dp)) },
            selected = selectedScreen  == 1,
            onClick = {
                onSelect(1)
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()

            }
        )

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.baseline_question_mark_24), contentDescription = "Clues", modifier = Modifier.size(32.dp)) },
            selected = selectedScreen  == 2,
            onClick = {
                onSelect(2)
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()

            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard", modifier = Modifier.size(32.dp)) },
            selected = selectedScreen  == 0,
            onClick = {
                onSelect(0)
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()

            }
        )

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.outline_group_24), contentDescription = "Join Treasure Hunt", modifier = Modifier.size(32.dp)) },
            selected = selectedScreen  == 3,
            onClick = {
                onSelect(3)
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()

            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Create Treasure Hunt", modifier = Modifier.size(32.dp)) },
            selected = selectedScreen  == 4,
            onClick = {
                onSelect(4)
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()

            }
        )
    }
}


@Composable
fun QRCodeScanner() {
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

            onClick = { Toast.makeText(context, "Scanning...", Toast.LENGTH_SHORT).show() }
        )
        {
            Text("Scan", fontSize = 20.sp)
        }
    }
}


@Composable
fun ClueList() {

    Text("Clue List")
}


/*
* The dashboard should contain:
*   - Any active treasure hunts the user is a part of
*/
@Composable
fun Dashboard() {
    val hunts = listOf(
        "Casa Loma Campus Hunt",
        "TTC Subway Treasure Hunt",
    )


}


/*
* The join screen should contain:
*   - An input box for a 6 digit code (corresponds with a specific hunt)
*   - A submit button that if correct, transfers you to the dashboard
*/
@Composable
fun JoinTreasureHunt() {

    Text("Join Treasure Hunt")
}


@Composable
fun CreateTreasureHunt() {

    Text("Create Treasure Hunt")
}
