package de.richter.projekt.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_bookings")
data class AccountBookingsEntity(
    @ColumnInfo(name = "datum") val datum: String,
    @ColumnInfo(name = "typ") val typ: String,
    @ColumnInfo(name = "wert") val wert: Double,
    @ColumnInfo(name = "buchungswaehrung") val buchungswaehrung: String,
    @ColumnInfo(name = "steuern") val steuern: Double?,
    @ColumnInfo(name = "stueck") val stueck: Double?,
    @ColumnInfo(name = "isin") val isin: String?,
    @ColumnInfo(name = "wkn") val wkn: String?,
    @ColumnInfo(name = "ticker") val ticker: String?,
    @ColumnInfo(name = "wertpapiername") val wertpapiername: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}