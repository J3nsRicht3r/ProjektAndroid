package de.richter.projekt.db.import

import android.content.Context
import android.util.Log
import de.richter.projekt.db.data.AccountBookings
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class AccountBookingsImportCsv(val context: Context) {
    private val datum = 0
    private val typ = 1
    private val wert = 2
    private val buchungswaehrung = 3
    private val steuern = 4
    private val stueck = 5
    private val isin = 6
    private val wkn = 7
    private val ticker = 8
    private val wertpapiername = 9

    fun importCsv(): ArrayList<AccountBookings>? {
        val fileName = "Verrechnungskonto.csv"
        val input: InputStream?
        val fileReader: BufferedReader?
        var raw: String?
        try {
            val bookingsList = ArrayList<AccountBookings>()
            input = context.assets.open(fileName)
            val inputStreamReader = InputStreamReader(input)
            fileReader = BufferedReader(inputStreamReader)
            // Read the file line by line
            raw = fileReader.readLine()
            while (raw != null) {
                val tokens = raw.split(";")
                Log.d("TAG", "importCsv: $tokens ")
                if (tokens.size >= 0) {
                    val depotBooking = AccountBookings(
                        tokens[datum],
                        tokens[typ],
                        tokens[wert].toDouble(),
                        tokens[buchungswaehrung],
                        tokens[steuern].toDouble(),
                        tokens[stueck].toDouble(),
                        tokens[isin],
                        tokens[wkn],
                        tokens[ticker],
                        tokens[wertpapiername]
                    )
                    bookingsList.add(depotBooking)
                }
                raw = fileReader.readLine()
            }
            return bookingsList


        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return null
    }
}
