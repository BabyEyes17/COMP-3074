package com.example.treasurely

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun TreasurelyNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        /* SPLASH */
        composable(Screen.Splash.route) {
            SplashScreen(
                onFinish = {
                    navController.navigate(Screen.PoiList.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        /* POI LIST */
        composable(Screen.PoiList.route) {
            PoiListScreen(
                onCreatePoi = { navController.navigate(Screen.CreatePoi.route) },
                onPoiClick = { poiId ->
                    navController.navigate(Screen.PoiDetails.route(poiId))
                },
                onTeamClick = { navController.navigate(Screen.UserList.route) },
                onAboutClick = { navController.navigate(Screen.About.route) }
            )
        }

        /* ADD POI */
        composable(Screen.CreatePoi.route) {
            CreatePoiScreen(onSaved = { navController.popBackStack() })
        }

        /* EDIT POI */
        composable(Screen.EditPoi.route) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            EditPoiScreen(
                poiId = poiId,
                onSaved = { navController.popBackStack() }
            )
        }

        /* DETAILS */
        composable(Screen.PoiDetails.route) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            PoiDetailsScreen(
                poiId = poiId,
                onScanQr = { navController.navigate(Screen.QrScanner.route(poiId)) },
                onMap = { navController.navigate(Screen.Map.route(poiId)) },
                onFullscreenMap = { navController.navigate(Screen.FullscreenMap.route(poiId)) }
            )
        }

        /* QR SCANNER */
        composable(Screen.QrScanner.route) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            QrScannerScreen(
                poiId = poiId,
                onFinished = { navController.popBackStack() }
            )
        }

        /* MAP / FULLSCREEN MAP */
        composable(Screen.Map.route) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            PoiMapScreen(poiId = poiId, onBack = { navController.popBackStack() })
        }

        composable(Screen.FullscreenMap.route) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            FullscreenMapScreen(poiId = poiId, onBack = { navController.popBackStack() })
        }

        /* TEAM LIST + CRUD */
        composable(Screen.UserList.route) {
            UserListScreen(
                onAddUser = { navController.navigate(Screen.AddUser.route) },
                onUserClick = { userId ->
                    navController.navigate(Screen.EditUser.route(userId))
                }
            )
        }

        composable(Screen.AddUser.route) {
            AddUserScreen(onSaved = { navController.popBackStack() })
        }

        composable(Screen.EditUser.route) { entry ->
            val userId = entry.arguments?.getString("userId")!!
            EditUserScreen(
                userId = userId,
                onSaved = { navController.popBackStack() }
            )
        }

        /* ABOUT */
        composable(Screen.About.route) {
            AboutScreen(onBack = { navController.popBackStack() })
        }
    }
}
