package de.richter.projekt.db.import

import android.content.Context
import android.util.Log
import de.richter.projekt.db.data.DepotBookings
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class DepotBookingsImportCsv(val context: Context) {
    private val datum = 0
    private val typ = 1
    private val wert = 2
    private val buchungswaehrung = 3
    private val gebuehren = 4
    private val steuern = 5
    private val stueck = 6
    private val isin = 7
    private val wkn = 8
    private val ticker = 9
    private val wertpapiername = 10

    fun importCsv(): ArrayList<DepotBookings>? {
        val fileName = "AktienDepot.csv"
        val input: InputStream?
        val fileReader: BufferedReader?
        var raw: String?
        try {
            val bookingsList = ArrayList<DepotBookings>()
            input = context.assets.open(fileName)
            val inputStreamReader = InputStreamReader(input)
            fileReader = BufferedReader(inputStreamReader)
            // Read the file line by line
            raw = fileReader.readLine()
            while (raw != null) {
                val tokens = raw.split(";")
                Log.d("TAG", "importCsv: $tokens ")
                if (tokens.size >= 0) {
                    val depotBooking = DepotBookings(
                        tokens[datum],
                        tokens[typ],
                        tokens[wert].toDouble(),
                        tokens[buchungswaehrung],
                        tokens[gebuehren].toDouble(),
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
