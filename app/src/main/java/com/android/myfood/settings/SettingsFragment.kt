package com.android.myfood.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.myfood.MainActivity
import com.android.myfood.R
import com.android.myfood.login.ui.LoginActivity
import com.firebase.ui.auth.AuthUI


class SettingsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    fun onClick(v: View) {
        if (v.id == R.id.btn_logout) {
            AuthUI.getInstance()
                .signOut(v.context)
                .addOnCompleteListener { // user is now signed out
                    startActivity(Intent(activity, LoginActivity::class.java))
                }
        }
    }

}