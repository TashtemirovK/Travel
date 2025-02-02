package kadyrbek.travel.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kadyrbek.travel.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController) {

    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                delay(1000)
                isRefreshing = false
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(28.dp)
                .background(Color(0xFFFFFFFF))
        ) {
            PlaceDetailCard(onBackClick = { navController.popBackStack() })
            var selectedTab by remember { mutableStateOf(0) }
            OverviewAndDetailsSection(selectedTab = selectedTab,
                onTabSelected = { selectedTab = it })
            OverviewIconsSection()
            PlaceDescriptionSection()
            BookingButton(onClick = {})
        }
    }
}

val interFontFamily = FontFamily(
    Font(R.font.inter_bold, FontWeight.Bold)
)

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal)
)

@Composable
fun PlaceDetailCard(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(374.dp)
            .height(460.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .shadow(10.dp, RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_son_kol),
            contentDescription = stringResource(R.string.location),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.TopStart)
                .background(Color(0xFF1D1D1D).copy(alpha = 0.4f), CircleShape)
                .size(44.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(R.string.arraw_back),
                tint = Color.White
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.TopEnd)
                .background(Color(0xFF1D1D1D).copy(alpha = 0.4f), CircleShape)
                .size(44.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_archive),
                contentDescription = stringResource(R.string.save),
                tint = Color.White
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent),
                        startY = 0f
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.lake_son_kol),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 24.sp,
                        fontFamily = interFontFamily,
                        color = Color.White
                    )
                )
                Text(
                    text = stringResource(R.string.price),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = robotoFontFamily1,
                        fontSize = 16.sp,
                        color = Color(0xFFCAC8C8)
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = stringResource(R.string.locationn),
                        tint = Color.LightGray,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(R.string.naryn_kyrgyzstan),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = robotoFontFamily1,
                            fontSize = 18.sp,
                            color = Color(0xFFCAC8C8)
                        )
                    )
                }
                Text(
                    text = stringResource(R.string._230),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = robotoFontFamily1,
                        fontSize = 26.sp,
                        color = Color(0xFFCAC8C8),
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}

val interFamily = FontFamily(
    Font(R.font.inter_bold, FontWeight.Bold)
)

@Composable
fun OverviewAndDetailsSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(65.dp)

    ) {
        Text(
            text = stringResource(R.string.review),
            fontSize = 22.sp,
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 40.dp)
                .clickable { onTabSelected(0) }
        )
        Text(
            text = stringResource(R.string.details),
            fontSize = 16.sp,
            fontFamily = interFamily,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 48.dp)
                .clickable { onTabSelected(1) }
        )
    }
}

@Composable
fun OverviewIconsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoItem(
            icon = painterResource(id = R.drawable.ic_clock),
            text = stringResource(R.string._8),
            onClick = {}
        )

        InfoItem(
            icon = painterResource(id = R.drawable.ic_cloud),
            text = stringResource(R.string._16_c),
            onClick = {}
        )

        InfoItem(
            icon = painterResource(id = R.drawable.ic_rating),
            text = stringResource(R.string._4_5),
            onClick = {}
        )
    }
}

@Composable
fun InfoItem(icon: Painter, text: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color(0xFF1D1D1D)
            )
        }

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF7E7E7E)
        )
    }
}

val robotoFontFamily1 = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal)
)

@Composable
fun PlaceDescriptionSection() {
    Text(
        text = stringResource(id = R.string.place_description),
        fontSize = 18.sp,
        fontFamily = robotoFontFamily1,
        fontWeight = FontWeight.Normal,
        color = Color(0xFFA5A5A5),
        lineHeight = 22.sp,
        modifier = Modifier.padding(top = 34.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))
}

@Composable
fun BookingButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            val robotoFontFamily = FontFamily(
                Font(R.font.roboto_regular, FontWeight.Normal)
            )
            Text(
                text = stringResource(R.string.booking_now),
                color = Color.White, // Белый цвет текста
                fontSize = 20.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_booking),
                contentDescription = stringResource(R.string.booking_icon),
                tint = Color.White,
                modifier = Modifier.size(23.dp)
            )
        }
    }
}














