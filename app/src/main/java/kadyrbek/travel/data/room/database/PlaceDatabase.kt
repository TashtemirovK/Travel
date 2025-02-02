package kadyrbek.travel.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kadyrbek.travel.data.room.dao.PlaceDao
import kadyrbek.travel.data.room.entities.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}
