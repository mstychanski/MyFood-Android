package com.android.myfood

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.*
import java.time.LocalDate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var ref: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storageList = generateDummyList(20)
        val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()

        ref = firebase.getReference("myFood")

        recycler_view.adapter = ItemAdapter(storageList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        add_btn.setOnClickListener {
            val intent = Intent(this, AddItem::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateDummyList(size: Int): List<StorageItem> {
        val list = ArrayList<StorageItem>()

        for (i in 0 until size) {
            val MeasureType = when (i % 3) {
                0 -> "grams"
                1 -> "pieces"
                else -> "mililiters"
            }
            val dummyAmount = i*5.4+1

            val item = StorageItem("Item $i", dummyAmount ,  MeasureType, LocalDate.now())
            list += item
        }
        return list
    }
}