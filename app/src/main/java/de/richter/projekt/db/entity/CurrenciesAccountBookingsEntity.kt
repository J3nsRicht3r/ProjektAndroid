package de.richter.projekt.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies_account_bookings")
data class CurrenciesAccountBookingsEntity(
    @ColumnInfo(name = "datum") val datum: String,
    @ColumnInfo(name = "typ") val typ: String,
    @ColumnInfo(name = "wert") val wert: Double,
    @ColumnInfo(name = "buchungswaehrung") val buchungswaehrung: String,
    @ColumnInfo(name = "rate") val rate: Double?

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}