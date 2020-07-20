package com.example.high_erer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        register_click.setOnClickListener{
            startActivity(Intent(this, signup_Activity::class.java))
            finish()
        }



    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?){

    }
}