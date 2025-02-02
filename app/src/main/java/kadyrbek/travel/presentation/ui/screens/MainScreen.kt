package kadyrbek.travel.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kadyrbek.travel.R
import kadyrbek.travel.data.model.main_screen.Place
import kadyrbek.travel.presentation.ui.components.places.AllPlacesSection
import kadyrbek.travel.presentation.ui.components.places.MostViewedPlacesSection
import kadyrbek.travel.presentation.ui.components.places.NearbyPlacesSection
import kadyrbek.travel.presentation.ui.components.places.RecentPlacesSection
import kadyrbek.travel.presentation.view_model.MainViewModel

@Composable
fun MainScreen(navController: NavController) {

    val viewModel: MainViewModel = hiltViewModel()
    val places by viewModel.places.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refreshData() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .verticalScroll(rememberScrollState())
        ) {
            GreetingSection()
            SearchSection()
            PopularPlacesHeader()

            var selectedTab by rememberSaveable { mutableStateOf(0) }
            CategoryTabs(selectedTab) { index -> selectedTab = index }

            when (selectedTab) {
                0 -> MostViewedPlacesSection(places) { viewModel.toggleFavorite(it) }
                1 -> NearbyPlacesSection(places) { viewModel.toggleFavorite(it) }
                2 -> RecentPlacesSection(places) { viewModel.toggleFavorite(it) }
                3 -> AllPlacesSection(places) { viewModel.toggleFavorite(it) }
            }
            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}

val montserratFamily = FontFamily(
    Font(R.font.montserrat_bold),
)

@Composable
fun GreetingSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 34.dp, start = 28.dp, end = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.hello_aibek),
                fontFamily = montserratFamily,
                fontSize = 30.sp,
                color = Color.Black,
            )
            Text(
                text = "👋",
                fontSize = 26.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_user),
            contentDescription = stringResource(R.string.ava),
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
        )
    }
    Text(
        text = stringResource(R.string.explore_the_world),
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.Gray,
            fontSize = 20.sp
        ),
        modifier = Modifier.padding(top = 10.dp, start = 28.dp)
    )
}

@Composable
fun SearchSection() {

    var searchQuery by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 38.dp)
            .padding(horizontal = 28.dp)
            .height(58.dp)
            .border(
                2.dp,
                Color.LightGray,
                RoundedCornerShape(20.dp)
            )
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 30.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_places),
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .width(2.dp)
                .height(24.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = stringResource(R.string.filter),
            tint = Color.LightGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

val PoppinsBold = FontFamily(
    Font(R.font.poppins_bold, weight = androidx.compose.ui.text.font.FontWeight.Bold)
)

@Composable
fun PopularPlacesHeader() {
    Column(
        modifier = Modifier.padding(horizontal = 28.dp)
    ) {
        Text(
            text = stringResource(R.string.popular_places),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontFamily = PoppinsBold
            ),
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp)
        )
    }
}

@Composable
fun CategoryTabs(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Самые просматриваемые", "Рядом", "Последнее", "Все")

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(horizontal = 28.dp),
        edgePadding = 0.dp,
        indicator = {},
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                modifier = Modifier
                    .padding(start = if (index == 0) 0.dp else 25.dp, end = 0.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        if (selectedTabIndex == index) Color.Black
                        else Color(0xFFFBFBFB)
                    ),
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (selectedTabIndex == index) Color.White
                        else Color.LightGray
                    )
                }
            )
        }
    }
}

@Composable
fun PlaceCard(
    place: Place,
    onFavoriteClick: (Boolean) -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .width(270.dp)
            .height(405.dp)
            .padding(top = 45.dp, end = 6.dp)
            .clip(RoundedCornerShape(22.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = true),
                onClick = {}
            ),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                onClick = { onFavoriteClick(!place.isFavorite) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(18.dp)
                    .size(40.dp)
                    .background(Color.LightGray, CircleShape)
                    .background(Color(0x1D1D1D66), CircleShape)
            ) {
                Icon(
                    imageVector = if (place.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = stringResource(R.string.text_favorite),
                    tint = if (place.isFavorite) Color.Red else Color.White,
                    modifier = Modifier
                        .size(28.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent)
                        )
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = place.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "Локация",
                        tint = Color.LightGray,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = place.location,
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = stringResource(R.string.rating),
                            modifier = Modifier.size(14.dp),
                            tint = Color.LightGray
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            text = place.rating.toString(),
                            color = Color.LightGray,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
