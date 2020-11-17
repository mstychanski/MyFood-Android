package com.android.myfood.storage

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.myfood.MainActivity
import com.android.myfood.R
import com.android.myfood.model.StorageItem
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)


        btn_save.setOnClickListener {

            val name = form_name.text.toString().trim()
            val weight = form_weight.text.toString().trim()





            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

//
//    final Calendar myCalendar = Calendar.getInstance();
//
//    EditText edittext= (EditText) findViewById(R.id.Birthday);
//    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//            int dayOfMonth) {
//            // TODO Auto-generated method stub
//            myCalendar.set(Calendar.YEAR, year);
//            myCalendar.set(Calendar.MONTH, monthOfYear);
//            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//            updateLabel();
//        }
//
//    };
//
//    edittext.setOnClickListener(new OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//            new DatePickerDialog(classname.this, date, myCalendar
//            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//        }
//    });
}