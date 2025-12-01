package ca.gbc.treasurely.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ca.gbc.treasurely.data.RepoProvider

import ca.gbc.treasurely.ui.about.AboutScreen
import ca.gbc.treasurely.ui.poi.PoiListScreen
import ca.gbc.treasurely.ui.poi.crud.CreatePoiScreen
import ca.gbc.treasurely.ui.poi.crud.EditPoiScreen
import ca.gbc.treasurely.ui.poi.crud.DeletePoiScreen
import ca.gbc.treasurely.ui.poi.crud.PoiCrudViewModel
import ca.gbc.treasurely.ui.poi.details.PoiDetailsScreen
import ca.gbc.treasurely.ui.poi.qr.QrCodeScannerScreen
import ca.gbc.treasurely.ui.splash.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {

    val context = LocalContext.current
    val vm: PoiCrudViewModel = sharedViewModel()

    LaunchedEffect(Unit) {
        vm.init(RepoProvider.poiRepository(context))
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        /* SPLASH */
        composable(Screen.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Screen.PoiList.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        /* LIST */
        composable(Screen.PoiList.route) {
            PoiListScreen(
                viewModel = vm,
                onOpenDetails = { id -> navController.navigate(Screen.PoiDetails.route(id)) },
                onOpenCreate = { navController.navigate(Screen.CreatePoi.route) },
                onOpenScanner = { navController.navigate(Screen.QrScanner.route) }
            )
        }

        /* CREATE */
        composable(Screen.CreatePoi.route) {
            CreatePoiScreen(
                viewModel = vm,
                onCreated = { navController.navigate(Screen.PoiList.route) },
                onBack = { navController.popBackStack() }
            )
        }

        /* EDIT */
        composable(
            route = Screen.EditPoi.routeWithArg,
            arguments = Screen.EditPoi.navArguments
        ) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            EditPoiScreen(
                viewModel = vm,
                poiId = poiId,
                onUpdated = {
                    navController.navigate(Screen.PoiDetails.route(poiId))
                },
                onBack = { navController.popBackStack() }
            )
        }

        /* DELETE */
        composable(
            route = Screen.DeletePoi.routeWithArg,
            arguments = Screen.DeletePoi.navArguments
        ) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            DeletePoiScreen(
                viewModel = vm,
                poiId = poiId,
                onDeleted = { navController.navigate(Screen.PoiList.route) },
                onCancel = { navController.popBackStack() }
            )
        }

        /* DETAILS */
        composable(
            route = Screen.PoiDetails.routeWithArg,
            arguments = Screen.PoiDetails.navArguments
        ) { entry ->
            val poiId = entry.arguments?.getString("poiId")!!
            PoiDetailsScreen(
                viewModel = vm,
                poiId = poiId,
                onEdit = { navController.navigate(Screen.EditPoi.route(poiId)) },
                onDelete = { navController.navigate(Screen.DeletePoi.route(poiId)) },
                onBack = { navController.popBackStack() }
            )
        }

        /* QR SCANNER */
        composable(Screen.QrScanner.route) {
            QrCodeScannerScreen(
                viewModel = vm,
                onScanned = { id -> navController.navigate(Screen.PoiDetails.route(id)) },
                onBack = { navController.popBackStack() }
            )
        }

        /* ABOUT */
        composable(Screen.About.route) {
            AboutScreen()
        }
    }
}

@Composable
inline fun <reified T : ViewModel> sharedViewModel(): T {
    val owner = LocalViewModelStoreOwner.current
        ?: error("No ViewModelStoreOwner was found")
    return viewModel(owner)
}
