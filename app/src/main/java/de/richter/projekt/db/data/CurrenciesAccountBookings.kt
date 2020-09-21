package de.richter.projekt.db.data

class CurrenciesAccountBookings(
    val datum: String,
    val typ: String,
    val wert: Double,
    val buchungswaehrung: String,
    val rate: Double,
)