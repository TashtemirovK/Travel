package kadyrbek.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kadyrbek.travel.navigation.SetupNavHost
import kadyrbek.travel.presentation.ui.screens.MainScreen
import kadyrbek.travel.presentation.ui.screens.SplashScreenBounceOnlyContent
import kadyrbek.travel.presentation.view_model.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController)
            }
        }
    }
}
