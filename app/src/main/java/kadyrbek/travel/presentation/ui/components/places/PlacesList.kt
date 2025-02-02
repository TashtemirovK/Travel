package kadyrbek.travel.presentation.ui.components.places


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kadyrbek.travel.data.model.main_screen.Place
import kadyrbek.travel.presentation.ui.screens.PlaceCard

@Composable
fun PlacesList(places: List<Place>, onFavoriteClick: (Place) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(places) { place ->
            PlaceCard(
                place = place,
                onFavoriteClick = { isFavorite ->
                    onFavoriteClick(place.copy(isFavorite = isFavorite))
                }
            )
        }
    }
}
