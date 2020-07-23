package com.example.high_erer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup_.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

            button.setOnClickListener(){
            if (email_edittext.text.toString().equals("user@gmail.com")
                && password_edittext.text.toString().equals("password")){
                    //"logged in successfully"
                    Toast.makeText(this , "logged in successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Content_Activity::class.java))
                    finish()
                }
            else {
                Toast.makeText(this , "logged in failed", Toast.LENGTH_SHORT).show()
            }

        }

        button.setOnClickListener{
            doLogin();
        }

        register_click.setOnClickListener{
            startActivity(Intent(this, signup_Activity::class.java))
            finish()
        }

    }

    private fun doLogin(){
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

        auth.signInWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString()).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    //Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                    // ...
                }
            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if (currentUser != null){
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, Content_Activity::class.java))
                finish()
            }
            else {
                Toast.makeText(baseContext, "Verify E mail address failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}