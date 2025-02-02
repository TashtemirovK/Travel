package kadyrbek.travel.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kadyrbek.travel.R
import kadyrbek.travel.navigation.NavRoutes

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Gray.copy(alpha = 0.15f), Color.Transparent)
                    )
                )
        )

        NavigationBar(containerColor = Color.White) {
            val tabs = listOf(
                Triple(NavRoutes.MAIN, R.drawable.ic_home, R.string.Main),
                Triple(NavRoutes.FAVORITE, R.drawable.ic_favorite, R.string.Favorite),
                Triple(NavRoutes.PROFILE, R.drawable.ic_profile, R.string.Profile)
            )

            tabs.forEach { (route, icon, label) ->
                val isSelected = currentRoute == route

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = stringResource(id = label),
                            tint = if (isSelected) Color.Black else Color.Gray
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = label),
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}


