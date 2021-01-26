package com.android.myfood.home.storage

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.myfood.Constants.DATABASE_ITEMS
import com.android.myfood.R
import com.android.myfood.home.HomeActivity
import com.android.myfood.home.storage.model.StorageItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_product.*
import java.text.SimpleDateFormat
import java.util.*


class AddProductActivity : AppCompatActivity() {


    val calendar = Calendar.getInstance()
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val extras = intent.extras

        val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateForm()
        }

        form_expiry_date.setOnClickListener {
             DatePickerDialog(this, date, calendar
                     .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                     calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        btn_save.setOnClickListener {

            val name = form_name.text.toString().trim()
            val weight = form_weight.text.toString().trim()
            lateinit var unit: String
            lateinit var storingPlace: String
            val expiryDate:String = form_expiry_date.text.toString().trim()

            val checkedUnit = form_unit_type.checkedButtonId
            unit =
                    when (checkedUnit) {
                        R.id.form_unit_gram -> "grams"
                        R.id.form_unit_amount -> "pieces"
                        R.id.form_unit_ml -> "milliliters"
                        else -> ""
                    }

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

        btn_scanner.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
        }


        if(extras != null)
        {
            val toastText = extras.getString("Toast")
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
            if(extras.getBoolean("IsFound"))
                form_name.setText(extras.getString("Name"))
            //form_weight.hint = intent.getStringExtra("Weight")

//            val unit = intent.getStringExtra("Unit")
//
//            when(unit)
//            {
//                "g" -> {
//                    form_unit_type.checkedButtonId = form_unit_gram.id
//
//                }
//
//
//            }


        }
    }


    private fun updateForm() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        form_expiry_date.setText(sdf.format(calendar.getTime()))
    }

}