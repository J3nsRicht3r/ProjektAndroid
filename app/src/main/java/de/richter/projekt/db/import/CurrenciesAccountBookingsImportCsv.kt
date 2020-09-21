package de.richter.projekt.db.import

import android.content.Context
import android.util.Log
import de.richter.projekt.db.data.CurrenciesAccountBookings
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class CurrenciesAccountBookingsImportCsv(val context: Context) {
    private val datum = 0
    private val typ = 1
    private val wert = 2
    private val buchungswaehrung = 3
    private val rate = 4

    fun importCsv(): ArrayList<CurrenciesAccountBookings>? {
        val fileName = "currencie_account_bookings.csv"
        val input: InputStream?
        val fileReader: BufferedReader?
        var raw: String?
        try {
            val bookingsList = ArrayList<CurrenciesAccountBookings>()
            input = context.assets.open(fileName)
            val inputStreamReader = InputStreamReader(input)
            fileReader = BufferedReader(inputStreamReader)
            // Read the file line by line
            raw = fileReader.readLine()
            while (raw != null) {
                val tokens = raw.split(";")
                Log.d("TAG", "importCsv: $tokens ")
                if (tokens.size >= 0) {
                    val depotBooking = CurrenciesAccountBookings(
                        tokens[datum],
                        tokens[typ],
                        tokens[wert].toDouble(),
                        tokens[buchungswaehrung],
                        tokens[rate].toDouble()
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