@file:Suppress("DEPRECATION")

package com.android.myfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.android.myfood.login.ui.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
            if( user != null)
            {
                val signInIntent = Intent(this, HomeActivity::class.java)
                startActivity(signInIntent)
            }
            else
            {
                val welcomeIntent = Intent(this, WelcomeActivity::class.java)
                startActivity(welcomeIntent)
            }
        }, 2000)
    }
}