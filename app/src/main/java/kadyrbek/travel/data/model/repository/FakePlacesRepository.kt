package kadyrbek.travel.data.model.repository


import kadyrbek.travel.R
import kadyrbek.travel.data.model.main_screen.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class FakePlacesRepository {

    private val _places = MutableStateFlow(
        listOf(
//            Place("Ыссык-Кол", "Кыргызстан, Ыссык-Кол", 4.8f, false, R.drawable.img_eiffel_tower),
            Place("Эйфелева башня", "Франция, Париж", 4.8f, false, R.drawable.img_eiffel_tower),
            Place("Гранд-Каньон", "США, Аризона", 4.7f, true, R.drawable.img_grand_canyon),
            Place("Сиднейский Оперный театр", "Австралия, Сидней", 4.9f, false, R.drawable.img_syndey_opera),
            Place("Мачу-Пикчу", "Перу", 4.8f, true, R.drawable.img_machu_picchu)
        )
    )

    val places: StateFlow<List<Place>> = _places

    fun toggleFavorite(placeName: String) {
        _places.value = _places.value.map {
            if (it.name == placeName) it.copy(isFavorite = !it.isFavorite) else it
        }
    }
}



