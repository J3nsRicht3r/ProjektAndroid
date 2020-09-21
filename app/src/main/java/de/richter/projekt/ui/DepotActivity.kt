package de.richter.projekt.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import de.richter.projekt.R
import de.richter.projekt.db.entity.DepotBookingsEntity
import de.richter.projekt.db.import.DepotBookingsImportCsv
import de.richter.projekt.db.repository.DepotBookingsRepository
import kotlinx.android.synthetic.main.activity_depot.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

class DepotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depot)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_depot, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.import_action -> {
                val depotBookingsImportCsv = DepotBookingsImportCsv(this)
                CoroutineScope(Dispatchers.IO).launch {
                    val csvFileArrayList = depotBookingsImportCsv.importCsv()
                    lateinit var datum: String
                    lateinit var typ: String
                    var wert: Double
                    lateinit var buchungswaehrung: String
                    var gebuehren: Double?
                    var steuern: Double?
                    var stueck: Double
                    var isin: String?
                    var wkn: String?
                    lateinit var ticker: String
                    lateinit var wertpapiername: String
                    val repository = DepotBookingsRepository(application)

                    csvFileArrayList!!.forEach { data ->
                        datum = data.datum
                        typ = data.typ
                        wert = data.wert
                        buchungswaehrung = data.buchungswaehrung
                        gebuehren = data.gebuehren
                        steuern = data.steuern
                        stueck = data.stueck
                        isin = data.isin
                        wkn = data.wkn
                        ticker = data.ticker
                        wertpapiername = data.wertpapiername

                        val entity = DepotBookingsEntity(
                            datum,
                            typ,
                            wert,
                            buchungswaehrung,
                            gebuehren,
                            steuern,
                            stueck,
                            isin,
                            wkn,
                            ticker,
                            wertpapiername
                        )
                        repository.insert(entity)
                    }
                    val result = repository.getAllDepotBookings()
                    result.forEach { depotBookingsEntity ->
                        Log.d("TAG", "onOptionsItemSelected: $depotBookingsEntity")
                    }
                }
            }
            R.id.output_action -> {
                val depotBookingsImportCsv = DepotBookingsImportCsv(this)
                val csvFileArrayList = depotBookingsImportCsv.importCsv()
                var sum: BigDecimal = BigDecimal.ZERO
                for (werte in csvFileArrayList!!.listIterator(2)) {
                    sum += werte.wert.toBigDecimal()
                }

                tv_output_depot.text = "Gesamt Summe: $sum € "
                tv_depot_details.text = "Datum|Typ|Wert|Währung|Stück|Name\n"
                csvFileArrayList.forEach { data ->
                    tv_depot_details.append(data.datum)
                    tv_depot_details.append(data.typ)
                    tv_depot_details.append(data.wert.toString())
                    tv_depot_details.append(data.buchungswaehrung)
                    tv_depot_details.append(data.stueck.toString())
                    tv_depot_details.append(data.wertpapiername)
                    tv_depot_details.append("\n")

                }

                Log.d("TAG", "onOptionsItemSelected: $csvFileArrayList ")
            }
        }
        return true
    }
}