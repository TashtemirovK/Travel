package kadyrbek.travel.data.model.main_screen

import androidx.annotation.DrawableRes

data class Place(
    val name: String,
    val location: String,
    val rating: Float,
    val isFavorite: Boolean,
    @DrawableRes val imageRes: Int,
)


