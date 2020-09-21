package de.richter.projekt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.richter.projekt.db.dao.CurrenciesAccountBookingsDao
import de.richter.projekt.db.entity.CurrenciesAccountBookingsEntity
import de.richter.projekt.db.entity.DateTimeConverter

@Database(entities = arrayOf(CurrenciesAccountBookingsEntity::class), version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class CurrenciesAccountBookingsDatabase : RoomDatabase() {
    abstract fun currenciesAccountBookingsDao(): CurrenciesAccountBookingsDao

    object Factory {
        private var instance: CurrenciesAccountBookingsDatabase? = null

        fun getInstance(context: Context): CurrenciesAccountBookingsDatabase {
            if (instance == null) {
                synchronized(CurrenciesAccountBookingsDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            CurrenciesAccountBookingsDatabase::class.java,
                            "currencies_account_bookings"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance!!
        }
    }
}