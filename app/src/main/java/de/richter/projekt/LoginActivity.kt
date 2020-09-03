package de.richter.projekt

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        bt_login.setOnClickListener(this)
        bt_register.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val i = view!!.id

        if (i == R.id.bt_register) {
            toRegister(et_mail_address.text.toString(), et_password.text.toString())
        } else if (i == R.id.bt_login) {
            signIn(et_mail_address.text.toString(), et_password.text.toString())
        }
//        } else if (i == R.id.btn_sign_out) {
//            signOut()
//
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth!!.currentUser
        updateUI(currentUser)
    }

    private fun toRegister(email: String, password: String) {
        if (!validateForm(email, password)) {
            return
        }
        auth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val currentUser = auth!!.currentUser
                    updateUI(currentUser)
                } else {
                    updateUI(null)
                }

            }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm(email, password)) {
            return
        }
        auth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // update UI with the signed-in user's information
//                    val currentUser = auth.getCurrentUser()
//                    updateUI(currentUser)
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Authentication failed!", Toast.LENGTH_SHORT)
                        .show()
                    updateUI(null)
                }
            }
    }

    private fun validateForm(email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 8) {
            Toast.makeText(
                applicationContext,
                "Password too short, enter minimum 8 characters!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            et_password.visibility = View.GONE
            bt_login.visibility = View.VISIBLE
        } else {
            et_mail_address.visibility = View.VISIBLE
            bt_login.visibility = View.GONE
        }
    }
}