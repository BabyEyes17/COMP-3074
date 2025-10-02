package com.example.treasurely

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.treasurely.ui.theme.TreasurelyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasurelyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        NavBar()
                    }
                }
            }
        }
    }
}

@Composable
fun NavBar() {

    NavigationBar {

        val context = LocalContext.current
        var selectedIndex by remember { mutableIntStateOf(-1) }

        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.outline_qr_code_scanner_24), contentDescription = "Scan QR Code") },
            //label = { Text("Create") },
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()}
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            //label = { Text("Home") },
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()}
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Create Treasure Hunt") },
            //label = { Text("Create") },
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                Toast.makeText(context, "Hey There!", Toast.LENGTH_SHORT).show()}
        )


    }
}