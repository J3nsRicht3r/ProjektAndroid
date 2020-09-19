package de.richter.projekt.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies_exchange_rate")
data class CurrenciesExchangeRateEntity(
    @PrimaryKey var target: String,
    @ColumnInfo(name = "basic") var basic: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "rate") var rate: Double
)