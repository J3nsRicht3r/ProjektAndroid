package de.richter.projekt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.richter.projekt.db.dao.CurrenciesExchangeRateDao
import de.richter.projekt.db.entity.CurrenciesExchangeRateEntity
import de.richter.projekt.db.entity.DateTimeConverter

@Database(entities = arrayOf(CurrenciesExchangeRateEntity::class), version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class CurrenciesExchangeRateDatabase : RoomDatabase() {
    abstract fun currenciesExchangeRateDao(): CurrenciesExchangeRateDao

    object Factory {
        private var instance: CurrenciesExchangeRateDatabase? = null

        fun getInstance(context: Context): CurrenciesExchangeRateDatabase {
            if (instance == null) {
                synchronized(CurrenciesExchangeRateDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            CurrenciesExchangeRateDatabase::class.java,
                            "currencies_exchange_rate"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance!!
        }
    }
}