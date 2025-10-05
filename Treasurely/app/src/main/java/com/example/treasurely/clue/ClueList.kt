package com.example.treasurely.clue

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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