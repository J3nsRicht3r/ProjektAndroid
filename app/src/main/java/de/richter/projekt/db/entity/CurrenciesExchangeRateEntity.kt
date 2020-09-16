package de.richter.projekt.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "currenciesExchangeRate")
data class CurrenciesExchangeRateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "from") var from: String,
    @ColumnInfo(name = "to") var to: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "rate") var rate: BigDecimal
)