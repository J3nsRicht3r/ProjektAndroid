package de.richter.projekt.db.data

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class CurrenciesExchangeRate(
    @SerializedName("result") val from: From,
    @SerializedName("conversion") val conversion: Array<Conversion>
)

class From(val from: String)
class Conversion(val to: String, val date: String, val rate: BigDecimal)