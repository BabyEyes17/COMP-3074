package ca.gbc.treasurely

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import ca.gbc.treasurely.ui.TreasurelyApp
import ca.gbc.treasurely.ui.navigation.AppNavHost
import ca.gbc.treasurely.ui.theme.TreasurelyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TreasurelyApp()
        }
    }
}
