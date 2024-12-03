package com.ronik.bottomnavigation_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ronik.bottomnavigation_jetpackcompose.data.TOP_LEVEL_DESTINATIONS
import com.ronik.bottomnavigation_jetpackcompose.data.TopLevelRoute
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.ChatRoute
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.ChatScreen
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.HomeRoute
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.HomeScreen
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.SettingRoute
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.SettingScreen
import com.ronik.bottomnavigation_jetpackcompose.ui.navigation.componets.BottomNavigationActions
import com.ronik.bottomnavigation_jetpackcompose.ui.theme.BottomNavigationJetpackComposeTheme
import kotlinx.serialization.Serializable

@Serializable
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationJetpackComposeTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            val navigationActions =
                                remember(navController) { BottomNavigationActions(navController) }
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            BottomNavigationBar(
                                currentDestination = currentDestination,
                                navigateToTopLevelDestination = navigationActions::navigateTo
                            )
                        })
                    { innerPadding ->
                        BottomNavHost(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(
    currentDestination: NavDestination?,
    navigateToTopLevelDestination: (TopLevelRoute<*>) -> Unit,
    ){
    BottomNavigation {
        TOP_LEVEL_DESTINATIONS.forEach { topLevelRoute ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector =if (currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true) topLevelRoute.selectedIcon else topLevelRoute.unselectedIcon,
                        contentDescription = stringResource(topLevelRoute.titleTextId)
                    )
                },
                label = {
                    Text(
                        text=stringResource(topLevelRoute.titleTextId)
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                onClick ={navigateToTopLevelDestination(topLevelRoute)},
                modifier =Modifier.background(MaterialTheme.colorScheme.background),
                alwaysShowLabel = true

            )

        }
    }

}

@Composable
private fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen()
        }
        composable<ChatRoute> {
            ChatScreen()
        }
        composable<SettingRoute> {
            SettingScreen()
        }

    }
}









