package de.richter.projekt.db.entity

import android.icu.math.BigDecimal

class AccountBookingsEntity(
    val Datum: String,
    val Typ: String,
    val Wert: BigDecimal,
    val Buchungswährung: String,
    val Steuern: BigDecimal,
    val Stück: BigDecimal,
    val ISIN: String,
    val WKN: String,
    val Ticker: String,
    val Wertpapiername: String,
    val Notiz: String
)