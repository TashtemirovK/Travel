package kadyrbek.travel.data.model.repository

import kadyrbek.travel.data.model.main_screen.Place
import kadyrbek.travel.data.room.dao.PlaceDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlacesRepository(private val placeDao: PlaceDao) {

    fun getPlaces(): Flow<List<Place>> {
        return placeDao.getPlaces().map { entities ->
            entities.map { Place(it.name, it.location, it.rating, it.isFavorite, it.imageRes) }
        }
    }

    suspend fun toggleFavorite(placeName: String, isFavorite: Boolean) {
        placeDao.updateFavorite(placeName, isFavorite)
    }
}
