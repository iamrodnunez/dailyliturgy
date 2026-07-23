package com.comfortcross.liturgy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.comfortcross.liturgy.content.Liturgies
import com.comfortcross.liturgy.ui.rememberDailyOffice
import com.comfortcross.liturgy.ui.screens.AboutScreen
import com.comfortcross.liturgy.ui.screens.LiturgiesScreen
import com.comfortcross.liturgy.ui.screens.LiturgyScreen
import com.comfortcross.liturgy.ui.screens.PrayersScreen
import com.comfortcross.liturgy.ui.screens.ReadingsScreen
import com.comfortcross.liturgy.ui.screens.TodayScreen
import com.comfortcross.liturgy.ui.theme.ComfortCrossTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComfortCrossTheme {
                ComfortCrossApp()
            }
        }
    }
}

private enum class Dest(val route: String, val label: String, val icon: ImageVector) {
    TODAY("today", "Today", Icons.Filled.Home),
    LITURGIES("liturgies", "Liturgies", Icons.Filled.Spa),
    READINGS("readings", "Readings", Icons.AutoMirrored.Filled.MenuBook),
    PRAYERS("prayers", "Prayers", Icons.Filled.AutoStories),
    ABOUT("about", "About", Icons.Filled.Info),
}

/** A liturgy detail page, e.g. "liturgy/morning". Not a bottom-bar destination. */
private const val LITURGY_ROUTE = "liturgy/{id}"
private fun liturgyRoute(id: String) = "liturgy/$id"

@Composable
private fun ComfortCrossApp() {
    val navController = rememberNavController()
    val office = rememberDailyOffice()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                val currentEntry by navController.currentBackStackEntryAsState()
                val currentDest = currentEntry?.destination
                Dest.entries.forEach { dest ->
                    // The Liturgies tab stays lit while viewing a specific liturgy page.
                    val onLiturgyDetail =
                        currentDest?.route == LITURGY_ROUTE && dest == Dest.LITURGIES
                    val selected =
                        currentDest?.hierarchy?.any { it.route == dest.route } == true || onLiturgyDetail
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(dest.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(dest.icon, contentDescription = dest.label) },
                        label = { Text(dest.label, style = MaterialTheme.typography.labelMedium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                        ),
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = Dest.TODAY.route) {
            composable(Dest.TODAY.route) {
                TodayScreen(
                    office = office,
                    onOpenLiturgies = { navController.navigate(Dest.LITURGIES.route) },
                    onOpenReadings = { navController.navigate(Dest.READINGS.route) },
                    contentPadding = innerPadding,
                )
            }
            composable(Dest.LITURGIES.route) {
                LiturgiesScreen(
                    onOpenLiturgy = { id -> navController.navigate(liturgyRoute(id)) },
                    contentPadding = innerPadding,
                )
            }
            composable(LITURGY_ROUTE) { entry ->
                val liturgy = Liturgies.byId(entry.arguments?.getString("id"))
                    ?: Liturgies.all.first()
                LiturgyScreen(
                    title = liturgy.title,
                    subtitle = liturgy.subtitle,
                    blocks = liturgy.blocks,
                    daily = office.daily,
                    onOpenReadings = { navController.navigate(Dest.READINGS.route) },
                    contentPadding = innerPadding,
                )
            }
            composable(Dest.READINGS.route) {
                ReadingsScreen(office = office, contentPadding = innerPadding)
            }
            composable(Dest.PRAYERS.route) {
                PrayersScreen(contentPadding = innerPadding)
            }
            composable(Dest.ABOUT.route) {
                AboutScreen(office = office, contentPadding = innerPadding)
            }
        }
    }
}
