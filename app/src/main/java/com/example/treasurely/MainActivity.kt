package com.example.treasurely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.treasurely.data.AppDatabase
import com.example.treasurely.data.repository.PoiRepository
import com.example.treasurely.ui.details.PoiDetailsScreen
import com.example.treasurely.ui.poi.list.PoiListScreen
import com.example.treasurely.ui.theme.TreasurelyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TreasurelyTheme {
                val db = AppDatabase.getDatabase(applicationContext)
                val repo = PoiRepository(db.poiDao())

                val nav = rememberNavController()

                NavHost(navController = nav, startDestination = "list") {
                    composable("list") {
                        PoiListScreen(
                            repo = repo,
                            onOpenDetails = { id -> nav.navigate("details/$id") })
                    }
                    composable("details/{id}") { backStack ->
                        val id = backStack.arguments?.getString("id") ?: return@composable
                        PoiDetailsScreen(repo = repo, id = id, onBack = { nav.popBackStack() })
                    }
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TreasurelyTheme {
        Greeting("Android")
    }
}