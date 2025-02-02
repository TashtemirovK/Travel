package kadyrbek.travel.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kadyrbek.travel.data.model.repository.FakePlacesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlacesRepository(): FakePlacesRepository {
        return FakePlacesRepository()
    }

}
