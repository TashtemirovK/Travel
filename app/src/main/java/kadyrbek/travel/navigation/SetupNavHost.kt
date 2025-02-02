package kadyrbek.travel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kadyrbek.travel.presentation.ui.screens.BottomBarScreen
import kadyrbek.travel.presentation.ui.screens.SplashScreenBounceOnlyContent

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.SPLASH
    ) {
        composable(NavRoutes.SPLASH) {
            SplashScreenBounceOnlyContent(navController)
        }
        composable(NavRoutes.MAIN) {
            BottomBarScreen()
        }
    }
}


