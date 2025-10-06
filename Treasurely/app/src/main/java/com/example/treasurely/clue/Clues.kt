package com.example.treasurely.clue

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.treasurely.R

@Composable
fun ClueList(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "Campus Quest",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 24.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                )
                {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text("Main Entrance", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Description: Find the sign that guides.", color = Color.Gray)
                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Location: 43.6767° N, 79.4123° W", color = Color.Gray)
                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Points Reward: 200", color = Color.Gray)
                        Spacer(modifier = Modifier.height(12.dp))

                        Image(
                            painter = painterResource(id = R.drawable.gbc_main_entrance),
                            contentDescription = "Clue Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Next Hint: Seek where knowledge rests.", color = Color.Gray)
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                )
                {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text("Library", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Description: ", color = Color.Gray)
                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Location: 43.6761° N, 79.4118° W", color = Color.Gray)
                        Spacer(modifier = Modifier.height(6.dp))

                        Text("Points Reward: 300", color = Color.Gray)
                        Spacer(modifier = Modifier.height(12.dp))

                        Image(
                            painter = painterResource(id = R.drawable.gbc_library),
                            contentDescription = "Clue Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Next Hint: Follow the scent of coffee and snacks.", color = Color.Gray)

                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Photo Hint", color = Color.Gray)
                        Spacer(modifier = Modifier.height(4.dp))

                        Image(
                            painter = painterResource(id = R.drawable.gbc_cafeteria),
                            contentDescription = "Clue Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                    }
                }
            }
        }
    }
}
