package de.richter.projekt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.richter.projekt.R
import kotlinx.android.synthetic.main.activity_portfolio.*

class PortfolioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)
        bt_fremdwaehrung.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    FremdwaehrungskontoActivity::class.java
                )
            )
        }

        bt_depot.setOnClickListener { startActivity(Intent(this, DepotActivity::class.java)) }

        bt_verrechnungskonto.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    VerrechnungskontoActivity::class.java
                )
            )
        }
    }
}