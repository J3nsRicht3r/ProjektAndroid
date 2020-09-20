package de.richter.projekt.db.data

class AccountBookings(
    val datum: String,
    val typ: String,
    val wert: Double,
    val buchungswaehrung: String,
    val steuern: Double?,
    val stueck: Double?,
    val isin: String?,
    val wkn: String?,
    val ticker: String?,
    val wertpapiername: String?
)