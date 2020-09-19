package de.richter.projekt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.richter.projekt.db.dao.DepotBookingsDao
import de.richter.projekt.db.entity.DateTimeConverter
import de.richter.projekt.db.entity.DepotBookingsEntity

@Database(entities = arrayOf(DepotBookingsEntity::class), version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class DepotBookingsDatabase : RoomDatabase() {
    abstract fun depotBookingsDao(): DepotBookingsDao

    object Factory {
        private var instance: DepotBookingsDatabase? = null

        fun getInstance(context: Context): DepotBookingsDatabase {
            if (instance == null) {
                synchronized(DepotBookingsDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            DepotBookingsDatabase::class.java,
                            "depot_bookings"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance!!
        }
    }
}