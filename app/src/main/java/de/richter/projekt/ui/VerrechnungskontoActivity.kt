package de.richter.projekt.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import de.richter.projekt.R
import de.richter.projekt.db.entity.AccountBookingsEntity
import de.richter.projekt.db.import.AccountBookingsImportCsv
import de.richter.projekt.db.repository.AccountBookingsRepository
import kotlinx.android.synthetic.main.activity_verrechnungskonto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

class VerrechnungskontoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verrechnungskonto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_depot, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.import_action -> {
                val accountBookingsImportCsv = AccountBookingsImportCsv(this)
                CoroutineScope(Dispatchers.IO).launch {
                    val csvFileArrayList = accountBookingsImportCsv.importCsv()
                    lateinit var datum: String
                    lateinit var typ: String
                    var wert: Double = 0.0
                    lateinit var buchungswaehrung: String
                    var steuern: Double? = 0.0
                    var stueck: Double? = 0.0
                    var isin: String? = null
                    var wkn: String? = null
                    var ticker: String? = null
                    var wertpapiername: String? = null
                    val repository = AccountBookingsRepository(application)

                    csvFileArrayList!!.forEach { data ->
                        datum = data.datum
                        typ = data.typ
                        wert = data.wert
                        buchungswaehrung = data.buchungswaehrung
                        steuern = data.steuern
                        stueck = data.stueck
                        isin = data.isin
                        wkn = data.wkn
                        ticker = data.ticker
                        wertpapiername = data.wertpapiername

                        val entity = AccountBookingsEntity(
                            datum,
                            typ,
                            wert,
                            buchungswaehrung,
                            steuern,
                            stueck,
                            isin,
                            wkn,
                            ticker,
                            wertpapiername
                        )
                        repository.insert(entity)
                    }
                    val result = repository.getAllAccountBookings()
                    result.forEach { accountBookingsEntity ->
                        Log.d("TAG", "onOptionsItemSelected: $accountBookingsEntity")
                    }
                }
            }
            R.id.output_action -> {
                val accountBookingsImportCsv = AccountBookingsImportCsv(this)
                val csvFileArrayList = accountBookingsImportCsv.importCsv()
                var sum: BigDecimal = BigDecimal.ZERO
                for (werte in csvFileArrayList!!.listIterator(2)) {
                    sum += werte.wert.toBigDecimal()
                }

                tv_output_verrechnung.text = "Gesamt Summe: $sum € "
                tv_verrechnung_details.text = "Datum|Typ|Wert|Währung|Name\n"
                csvFileArrayList.forEach { data ->
                    tv_verrechnung_details.append(data.datum)
                    tv_verrechnung_details.append(data.typ)
                    tv_verrechnung_details.append(data.wert.toString())
                    tv_verrechnung_details.append(data.buchungswaehrung)
                    tv_verrechnung_details.append(data.wertpapiername)
                    tv_verrechnung_details.append("\n")
                }
                Log.d("TAG", "onOptionsItemSelected: $csvFileArrayList ")
            }
        }
        return true
    }
}