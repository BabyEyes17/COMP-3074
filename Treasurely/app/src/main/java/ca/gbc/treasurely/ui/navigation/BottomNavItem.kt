package ca.gbc.treasurely.ui.navigation

import androidx.annotation.DrawableRes
import ca.gbc.treasurely.R

sealed class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomNavItem("poi_list", "Home", R.drawable.icon_home)
    object Create : BottomNavItem("create_poi", "New", R.drawable.icon_add)
    object Scan : BottomNavItem("qr_scanner", "Scan", R.drawable.qrcode_solid_full)
    object About : BottomNavItem("about", "About", R.drawable.circle_info_solid_full)
}
