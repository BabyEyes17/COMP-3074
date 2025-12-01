package ca.gbc.treasurely.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import ca.gbc.treasurely.ui.navigation.AppNavHost
import ca.gbc.treasurely.ui.navigation.BottomNavItem

@Composable
fun TreasurelyApp() {
    val navController = rememberNavController()

    val bottomItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Create,
        BottomNavItem.Scan,
        BottomNavItem.About
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentBackStackEntry?.destination?.route

                bottomItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = { navController.navigate(item.route) },
                        icon = { Icon(painterResource(id = item.icon), contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) {
        AppNavHost(navController = navController, paddingValues = it)
    }
}
