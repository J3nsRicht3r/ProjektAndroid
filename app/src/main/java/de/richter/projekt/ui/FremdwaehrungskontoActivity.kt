package de.richter.projekt.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import de.richter.projekt.R
import de.richter.projekt.db.entity.CurrenciesAccountBookingsEntity
import de.richter.projekt.db.import.CurrenciesAccountBookingsImportCsv
import de.richter.projekt.db.repository.CurrenciesAccountBookingsRepository
import kotlinx.android.synthetic.main.activity_fremdwaerungskonto.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal

class FremdwaehrungskontoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fremdwaerungskonto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_depot, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.import_action -> {
                val currenciesAccountBookingsImportCsv = CurrenciesAccountBookingsImportCsv(this)
                CoroutineScope(Dispatchers.IO).launch {
                    val csvFileArrayList = currenciesAccountBookingsImportCsv.importCsv()
                    lateinit var datum: String
                    lateinit var typ: String
                    var wert: Double
                    lateinit var buchungswaehrung: String
                    var rate: Double
                    val repository = CurrenciesAccountBookingsRepository(application)

                    csvFileArrayList!!.forEach { data ->
                        datum = data.datum
                        typ = data.typ
                        wert = data.wert
                        buchungswaehrung = data.buchungswaehrung
                        rate = data.rate

                        val entity = CurrenciesAccountBookingsEntity(
                            datum,
                            typ,
                            wert,
                            buchungswaehrung,
                            rate
                        )
                        repository.insert(entity)
                    }
                    val result = repository.getAllCurrenciesAccountBookings()
                    result.forEach { currenciesAccountBookingsEntity ->
                        Log.d("TAG", "onOptionsItemSelected: $currenciesAccountBookingsEntity")
                    }
                }
            }
            R.id.output_action -> {
                val currenciesAccountBookingsImportCsv = CurrenciesAccountBookingsImportCsv(this)
                val csvFileArrayList = currenciesAccountBookingsImportCsv.importCsv()
                var sum: BigDecimal = BigDecimal.ZERO
                for (werte in csvFileArrayList!!.listIterator(2)) {
                    sum += werte.wert.toBigDecimal()
                }

                tv_output_fremdwaehrung.text = "Gesamt Summe: $sum $ "
                tv_fremdwaehrung_details.text = "Datum|Typ|Wert|Währung|Kurs\n"
                csvFileArrayList.forEach { data ->
                    tv_fremdwaehrung_details.append(data.datum)
                    tv_fremdwaehrung_details.append(data.typ)
                    tv_fremdwaehrung_details.append(data.wert.toString())
                    tv_fremdwaehrung_details.append(data.buchungswaehrung)
                    tv_fremdwaehrung_details.append(data.rate.toString())
                    tv_fremdwaehrung_details.append("\n")

                }

                Log.d("TAG", "onOptionsItemSelected: $csvFileArrayList ")
            }
        }
        return true
    }
}
