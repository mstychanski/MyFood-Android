package com.android.myfood.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.myfood.Credencials
import com.android.myfood.home.HomeActivity
import com.android.myfood.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class LoginActivity : AppCompatActivity() {

    private var inputEmail: EditText? = null
    private var inputPassword:EditText? = null
    private var btnSignup: Button? =null
    private var btnLogin :Button?=null
    private var btnReset:Button? =null
    private var progressBar: ProgressBar?=null
    private var auth:FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        setContentView(R.layout.activity_login)
        inputEmail = findViewById(R.id.email) as EditText
        inputPassword = findViewById(R.id.password) as EditText
        btnSignup = findViewById(R.id.btn_register) as Button
        btnLogin = findViewById(R.id.btn_signin) as Button
        btnReset = findViewById(R.id.btn_reset_password) as Button
        progressBar = findViewById(R.id.progressbar_login) as ProgressBar

        auth = FirebaseAuth.getInstance()

        btnSignup!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        })
        btnReset!!.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@LoginActivity, ResetPasswordActivity::class.java))
        })
        btnLogin!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(applicationContext,"Please Entre your email.",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Please Enter your Password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            progressBar!!.setVisibility(View.VISIBLE)

            auth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener {
                        task ->
                        progressBar!!.setVisibility(View.VISIBLE)

                        if (!task.isSuccessful){
                            if (password.length < 6){
                                inputPassword!!.setError(getString(R.string.minimum_password))
                            }else{
                                Toast.makeText(this@LoginActivity,getString(R.string.auth_failed),Toast.LENGTH_LONG).show()
                            }
                        }else{
                            Credencials.USER_ID = auth!!.currentUser?.uid!!
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            finish()
                        }
                    })
        })

    }

}


