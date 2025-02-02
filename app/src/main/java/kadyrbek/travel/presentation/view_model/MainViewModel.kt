package kadyrbek.travel.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kadyrbek.travel.data.model.main_screen.Place
import kadyrbek.travel.data.model.repository.FakePlacesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FakePlacesRepository
) : ViewModel() {

    val places: StateFlow<List<Place>> = repository.places

    fun toggleFavorite(place: Place) {
        repository.toggleFavorite(place.name)
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    fun refreshData() {
        viewModelScope.launch {
            _isRefreshing.value = true
            delay(2000)
            _isRefreshing.value = false
        }
    }
}

