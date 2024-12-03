package com.ronik.bottomnavigation_jetpackcompose.data

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.ronik.bottomnavigation_jetpackcompose.R
import com.ronik.bottomnavigation_jetpackcompose.icon.BnjIcons
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.ChatRoute
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.HomeRoute
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.SettingRoute


/**
 * Type for the top level destinations in the application. Contains metadata about the destination
 * that is used in the top app bar and common navigation UI.
 *
 * @param selectedIcon The icon to be displayed in the navigation UI when this destination is
 * selected.
 * @param unselectedIcon The icon to be displayed in the navigation UI when this destination is
 * not selected.
 * @param iconTextId Text that to be displayed in the navigation UI.
 * @param titleTextId Text that is displayed on the top app bar.
 * @param route The route to use when navigating to this destination.
 * @param baseRoute The highest ancestor of this destination. Defaults to [route], meaning that
 * there is a single destination in that section of the app (no nested destinations).
 */

data class TopLevelRoute<T : Any>(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: T,
)


val TOP_LEVEL_DESTINATIONS= listOf(
    TopLevelRoute(
        selectedIcon = BnjIcons.HomeSelected,
        unselectedIcon = BnjIcons.HomeUnSelected,
        iconTextId = R.string.feature_home_title,
        titleTextId = R.string.feature_home_title,
        route = HomeRoute,
        ),
    TopLevelRoute(
        selectedIcon = BnjIcons.ChatSelected,
        unselectedIcon = BnjIcons.ChatUnSelected,
        iconTextId = R.string.feature_chat_title,
        titleTextId = R.string.feature_chat_title,
        route = ChatRoute,
    ),
    TopLevelRoute(
        selectedIcon = BnjIcons.SettingSelected,
        unselectedIcon = BnjIcons.SettingUnSelected,
        iconTextId = R.string.feature_setting_title,
        titleTextId = R.string.feature_setting_title,
        route = SettingRoute,
    ),
)



