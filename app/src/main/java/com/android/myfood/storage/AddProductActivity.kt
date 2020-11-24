package com.android.myfood.storage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.myfood.Constants.DATABASE_ITEMS
import com.android.myfood.HomeActivity
import com.android.myfood.R
import com.android.myfood.model.StorageItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)


        btn_save.setOnClickListener {

            val name = form_name.text.toString().trim()
            val weight = form_weight.text.toString().trim()
            lateinit var unit: String
            lateinit var storingPlace: String
            val expiryDate:String = form_expiry_date.text.toString().trim()

            val checkedUnit = form_unit_type.checkedButtonId
            unit =
                if(checkedUnit == R.id.form_unit_gram)
                    "grams"
                else
                    "pieces"

            val checkedStoragePlace = form_storage_place.checkedButtonId
            storingPlace =
                when (checkedStoragePlace) {
                    R.id.form_fridge -> "fridge"
                    R.id.form_pantry -> "pantry"
                    else -> "freezer"
                }


            val newItem = StorageItem(name, storingPlace, weight.toLong(), unit, expiryDate)

            val uid = FirebaseAuth.getInstance().currentUser?.uid


            val ref = FirebaseDatabase.getInstance().getReference("Users").child(uid!!).child(DATABASE_ITEMS)

            val newItemId = ref.push().key

            ref.child(newItemId!!).setValue(newItem).addOnCompleteListener{
                Toast.makeText(applicationContext, "New Item Added", Toast.LENGTH_LONG).show()

            }


            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        btn_cancel.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }





    }



}