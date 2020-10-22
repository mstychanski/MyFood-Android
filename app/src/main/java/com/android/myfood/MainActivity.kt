package com.android.myfood

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myfood.Model.StorageItem
import com.android.myfood.ViewHolder.ItemViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    internal var itemsList:MutableList<StorageItem> = ArrayList()
    internal lateinit var adapter:FirebaseRecyclerAdapter<StorageItem, ItemViewHolder>
    private lateinit var ref: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()

        ref = firebase.getReference("myFood")

            //View Init
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)

        //retrieving data
        retrieveData()

        //setting data
        setData()
        add_btn.setOnClickListener {
            val intent = Intent(this, AddItem::class.java)
            startActivity(intent)
        }
    }

    private fun setData() {
        val query = FirebaseDatabase.getInstance().reference.child("myFood")
        val options = FirebaseRecyclerOptions.Builder<StorageItem>()
            .setQuery(query, StorageItem::class.java)
            .build()

        adapter = object:FirebaseRecyclerAdapter<StorageItem, ItemViewHolder>(options)
        {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
                val itemView   = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.storage_item,parent,false)
                return ItemViewHolder(itemView)
            }


            override fun onBindViewHolder(
                holder: ItemViewHolder,
                position: Int,
                model: StorageItem
            ) {
                holder.productName.text = model.name
                holder.productAmount.text = model.weight.toString()
                holder.productMeasureType.text= model.weightMesureType
            }


        }
    }

    private fun retrieveData() {
        itemsList.clear()

        val ref = FirebaseDatabase.getInstance()
            .reference
            .child("myFood")

        ref.addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(itemSnapshot in snapshot.children)
                {
                    val item: StorageItem? = itemSnapshot.getValue(StorageItem::class.java)
                    itemsList.add(item!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR", ""+error.message)
            }

        })
    }


    override fun onStart() {
        if(adapter != null)
            adapter.stopListening()
        super.onStart()
    }

    override fun onStop() {
        if(adapter !=null)
            adapter.startListening()
        super.onStop()
    }
}