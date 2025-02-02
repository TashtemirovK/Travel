package kadyrbek.travel.presentation.ui.components.places

import androidx.compose.runtime.Composable
import kadyrbek.travel.data.model.main_screen.Place

@Composable
fun MostViewedPlacesSection(places: List<Place>, onFavoriteClick: (Place) -> Unit) {
    PlacesList(places = places, onFavoriteClick = onFavoriteClick)
}

@Composable
fun NearbyPlacesSection(places: List<Place>, onFavoriteClick: (Place) -> Unit) {
    PlacesList(places = places, onFavoriteClick = onFavoriteClick)
}

@Composable
fun RecentPlacesSection(places: List<Place>, onFavoriteClick: (Place) -> Unit) {
    PlacesList(places = places, onFavoriteClick = onFavoriteClick)
}

@Composable
fun AllPlacesSection(places: List<Place>, onFavoriteClick: (Place) -> Unit) {
    PlacesList(places = places, onFavoriteClick = onFavoriteClick)
}
