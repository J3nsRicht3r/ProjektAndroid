package de.richter.projekt.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.richter.projekt.db.data.CurrenciesExchangeRate
import de.richter.projekt.db.entity.DateTimeConverter

@Database(entities = arrayOf(CurrenciesExchangeRate::class), version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class CurrenciesExchangeRateDatabase : RoomDatabase()