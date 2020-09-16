package de.richter.projekt.db.import

import java.math.BigDecimal


class DepotBookingsImportCsv {
    var datum: String? = null
    var typ: String? = null
    var wert: BigDecimal = BigDecimal(0.0)
    var buchungswaehrung: String? = null
    var gebuehren: BigDecimal = BigDecimal(0.0)
    var steuern: BigDecimal = BigDecimal(0.0)
    var stueck: BigDecimal = BigDecimal(0.0)
    var isin: String? = null
    var wkn: String? = null
    var ticker: String? = null
    var wertpapiername: String? = null
}
