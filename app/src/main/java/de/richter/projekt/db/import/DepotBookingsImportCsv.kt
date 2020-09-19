package de.richter.projekt.db.import

import android.content.Context
import de.richter.projekt.db.data.DepotBookings
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

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
        var fileReader: BufferedReader? = null
        var raw: String?
        try {
            val bookings = ArrayList<DepotBookings>()

            fileReader = BufferedReader(FileReader(fileName))
            // Read the file line by line starting from the second line
            raw = fileReader.readLine()
            while (raw != null) {
                val tokens = raw.split(";")
                if (tokens.size > 0) {
                    val bookings = DepotBookings(
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

                }
            }
            return bookings


        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            fileReader!!.close()
        }
        return null
    }
}
