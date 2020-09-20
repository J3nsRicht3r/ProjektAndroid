package de.richter.projekt.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import de.richter.projekt.R
import de.richter.projekt.db.data.CurrenciesExchangeRate
import de.richter.projekt.db.entity.CurrenciesExchangeRateEntity
import de.richter.projekt.db.import.CurrenciesExchangeRateImportJson
import de.richter.projekt.db.repository.CurrenciesExchangeRateRepository
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = Firebase.auth

        bt_sign_out.setOnClickListener {
            signOut()
        }

        bt_new_portfolio.setOnClickListener {
            val etPortfolioName = EditText(this)
            val meldung = AlertDialog.Builder(this)
            val btNew = Button(this)
            lyGesamt.addView(btNew)
            btNew.textSize = 14.0f
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            btNew.layoutParams = lp
            meldung.setMessage("Portfolioname:")
            meldung.setPositiveButton("save") { _, _ ->
                btNew.text = etPortfolioName.text.toString()
            }
            meldung.setNegativeButton("cancel") { _, _ ->
                Toast.makeText(this, "abgebrochen", Toast.LENGTH_SHORT).show()
            }
            meldung.setView(etPortfolioName)
            meldung.show()
        }

        bt_portfolio.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PortfolioActivity::class.java
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.import_action -> {

                val currenciesExchangeRateImportJson = CurrenciesExchangeRateImportJson(this)
                val jsonFileString = currenciesExchangeRateImportJson.importJson()
                Log.d("TAG", "onOptionsItemSelected: ")
                val gson = Gson()
                var currenciesExchangeRate: CurrenciesExchangeRate =
                    gson.fromJson(jsonFileString, CurrenciesExchangeRate::class.java)
                lateinit var date: String
                var rate: Double = 0.0
                lateinit var target: String
                var array = currenciesExchangeRate.arrayConversion
                array.forEach { conversion ->
                    date = conversion.date
                    rate = conversion.rate.toDouble()
                    target = conversion.target
                }
                var entity = CurrenciesExchangeRateEntity(
                    currenciesExchangeRate.result.basic,
                    target,
                    date,
                    rate
                )
                Log.d("TAG", "onOptionsItemSelected: ${currenciesExchangeRate.result}")
                Log.d("TAG", "onOptionsItemSelected: ${currenciesExchangeRate.arrayConversion}")
                Log.d("TAG", "onOptionsItemSelected: ${currenciesExchangeRate.result.basic}")
                var repository = CurrenciesExchangeRateRepository(application)
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(entity)
                    val result = repository.getAll()
                    result.forEach { currenciesExchangeRateEntity ->
                        Log.d("TAG", "onOptionsItemSelected: $currenciesExchangeRateEntity")
                    }

                }

            }
        }
        return true
    }

    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}