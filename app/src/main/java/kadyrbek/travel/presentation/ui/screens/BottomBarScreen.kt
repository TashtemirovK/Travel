package kadyrbek.travel.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kadyrbek.travel.navigation.NavRoutes
import kadyrbek.travel.presentation.ui.components.BottomNavigationBar

@Composable
fun BottomBarScreen() {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            BottomNavHost(bottomNavController)
        }
    }
}

@Composable
fun BottomNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.MAIN
    ) {
        composable(NavRoutes.MAIN) { MainScreen(navController) }
        composable(NavRoutes.FAVORITE) { FavoriteScreen(navController) }
        composable(NavRoutes.PROFILE) { ProfileScreen(navController) }
    }
}