package kadyrbek.travel.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kadyrbek.travel.R
import kadyrbek.travel.navigation.NavRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val LobsterFontFamily = FontFamily(
    Font(R.font.lobster_regular, weight = FontWeight.Normal)
)

val RobotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Normal)
)

@Composable
fun SplashScreenBounceOnlyContent(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate(NavRoutes.MAIN) {
            popUpTo(NavRoutes.SPLASH) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A72D1),
                        Color(0xFF04376A)
                    )
                )
            )
    ) {
        BouncyContent()
    }
}

@Composable
fun BouncyContent() {
    var offsetY by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val newOffset = offsetY + dragAmount.y
                        offsetY = newOffset.coerceIn(-300f, 300f)
                    },
                    onDragEnd = {
                        scope.launch {
                            val start = offsetY
                            val end = 0f
                            val steps = 30
                            repeat(steps) { i ->
                                val fraction = i / steps.toFloat()
                                offsetY = lerp(start, end, fraction)
                                delay(10)
                            }
                            offsetY = end
                        }
                    }
                )
            }
            .offset { IntOffset(0, offsetY.toInt()) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Travel",
                    fontSize = 44.sp,
                    fontFamily = LobsterFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_planet),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .size(36.dp)
                )
            }
            Spacer(Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.find_dream_place),
                fontSize = 20.sp,
                fontFamily = RobotoFontFamily,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}