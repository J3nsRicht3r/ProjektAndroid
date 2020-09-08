package de.richter.projekt.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
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
    private var portfolioName = ""

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
            meldung.setMessage("Portfolioname:")
            meldung.setPositiveButton("save") { _, _ ->
                portfolioName = etPortfolioName.text.toString()
            }
            meldung.setNegativeButton("cancel") { _, _ ->
                Toast.makeText(this, "abgebrochen", Toast.LENGTH_SHORT).show()
            }
            meldung.setView(etPortfolioName)
            meldung.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    private fun signOut() {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}