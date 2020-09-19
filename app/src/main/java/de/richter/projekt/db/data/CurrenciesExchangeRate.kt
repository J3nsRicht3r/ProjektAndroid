package de.richter.projekt.db.data

import com.google.gson.annotations.SerializedName

class CurrenciesExchangeRate(
    @SerializedName("result") val result: Result,
    @SerializedName("conversion") val arrayConversion: Array<Conversion>
)

class Result(val basic: String)
class Conversion(val target: String, val date: String, val rate: Double)