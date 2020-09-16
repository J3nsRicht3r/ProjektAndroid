package de.richter.projekt.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import de.richter.projekt.R
import kotlinx.android.synthetic.main.activity_home.*

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


    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}