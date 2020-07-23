package com.example.high_erer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup_.*

class signup_Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_)

        auth = FirebaseAuth.getInstance()

        registerbutton.setOnClickListener{
            signupUser()
        }
    }

    fun signupUser() {
        if (email_edittext.text.toString().isEmpty()){
            email_edittext.error = "Please Enter E-mail"
            email_edittext.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_edittext.text.toString()).matches()){
            email_edittext.error = "Enter valid E-mail"
            email_edittext.requestFocus()
            return
        }

        if (password_edittext.text.toString().isEmpty()){
            password_edittext.error = "lease enter password"
            password_edittext.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, MainActivity ::class.java))
                                finish()
                                //Log.d(TAG, "Email sent.")
                            }
                        }
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    }
}