package kadyrbek.travel.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val name: String,
    val location: String,
    val rating: Float,
    val isFavorite: Boolean,
    val imageRes: Int
)
