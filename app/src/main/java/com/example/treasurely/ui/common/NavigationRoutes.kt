package com.example.treasurely.ui.common

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object PoiList : Screen("poi_list")

    object Createpoi : Screen("create_poi")

    object EditPoi : Screen("edit_poi/{poiId}") {

        fun route(poiId: String) = "edit_poi/$poiId"
    }

    object PoiDetails : Screen("poi_details/{poiId}") {

        fun route(poiId: String) = "poi_details/$poiId"
    }

    object QrScanner : Screen("qr_scanner/{poiId}") {

        fun route(poiId: String) = "qr_scanner/$poiId"
    }

    object Map : Screen("map/{poiId}") {

        fun route(poiId: String) = "map/$poiId"
    }

    object FullscreenMap : Screen("fullscreen_map/{poiId}") {

        fun route(poiId: String) = "fullscreen_map/$poiId"
    }

    object UserList : Screen("user_list")

    object AddUser : Screen("add_user")

    object EditUser : Screen("edit_user/{userId}") {

        fun route(userId: String) = "edit_user/$userId"
    }

    object About : Screen("about")
}
