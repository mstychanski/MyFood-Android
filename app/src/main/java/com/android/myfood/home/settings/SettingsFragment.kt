package com.android.myfood.home.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.myfood.Constants
import com.android.myfood.Credencials
import com.android.myfood.MainActivity
import com.android.myfood.R
import com.android.myfood.home.tobuy.ShoppingListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.dialog_change_name.view.*
import kotlinx.android.synthetic.main.dialog_new_list.view.*
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    var database : FirebaseDatabase ? = null
    var ref : DatabaseReference ? = null



    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()
        ref = database!!.getReference().child(Credencials.USER_ID).child(Constants.DATABASE_ACCOUNT).child("Name")
        ref?.addValueEventListener( object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(postSnapshot in snapshot.children)
                    settings_user_name.text = snapshot.child("Name").getValue(String::class.java)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        settings_logout_btn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this.activity, MainActivity::class.java))
        }

        settings_change_name_btn.setOnClickListener {

            val DialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_change_name, null)

            val editName = DialogView?.findViewById<TextView>(R.id.change_name_form)

            val Builder = AlertDialog.Builder(activity)
                    .setView(DialogView)

            val  AlertDialog = Builder.show()

            DialogView.change_name_save_btn.setOnClickListener {

                if(editName?.text.toString().trim() != "" && editName?.text.toString().trim() != null ) {
                    val newName = editName?.text.toString().trim()

                    ref?.child("Name")?.setValue(newName)

                    AlertDialog.dismiss()




                }
                else
                    Toast.makeText(context, "Please enter the name of the list", Toast.LENGTH_LONG).show()


            }

            DialogView.change_name_cancel_btn.setOnClickListener {

                AlertDialog.dismiss()
            }
        }


    }

}