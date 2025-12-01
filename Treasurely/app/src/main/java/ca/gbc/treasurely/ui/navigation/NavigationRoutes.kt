package ca.gbc.treasurely.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object PoiList : Screen("poi_list")

    object CreatePoi : Screen("create_poi")

    object EditPoi : Screen("edit_poi/{poiId}") {
        fun route(poiId: String) = "edit_poi/$poiId"
        const val routeWithArg = "edit_poi/{poiId}"
        val navArguments = listOf(
            navArgument("poiId") { type = NavType.StringType }
        )
    }

    object DeletePoi : Screen("delete_poi/{poiId}") {
        fun route(poiId: String) = "delete_poi/$poiId"
        const val routeWithArg = "delete_poi/{poiId}"
        val navArguments = listOf(
            navArgument("poiId") { type = NavType.StringType }
        )
    }

    object PoiDetails : Screen("poi_details/{poiId}") {
        fun route(poiId: String) = "poi_details/$poiId"
        const val routeWithArg = "poi_details/{poiId}"
        val navArguments = listOf(
            navArgument("poiId") { type = NavType.StringType }
        )
    }

    object QrScanner : Screen("qr_scanner") // simpler, no args

    object Map : Screen("map/{poiId}") {
        fun route(id: String) = "map/$id"
    }

    object FullscreenMap : Screen("fullscreen_map/{poiId}") {
        fun route(id: String) = "fullscreen_map/$id"
    }

    object UserList : Screen("user_list")
    object AddUser : Screen("add_user")

    object EditUser : Screen("edit_user/{userId}") {
        fun route(id: String) = "edit_user/$id"
    }

    object About : Screen("about")
}
