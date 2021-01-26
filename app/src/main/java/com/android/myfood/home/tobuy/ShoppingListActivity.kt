package com.android.myfood.home.tobuy

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myfood.Constants
import com.android.myfood.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_shopping_list.*
import kotlinx.android.synthetic.main.dialog_new_list.view.*
import kotlinx.android.synthetic.main.fragment_storage.*
import kotlinx.android.synthetic.main.fragment_to_buy.*
import java.lang.Thread.sleep

class ShoppingListActivity : AppCompatActivity(), ShoppingListAdapter.OnItemClickListener {

    var ListName : String? = null
    var firedatabase : FirebaseDatabase? = null
    var itemList : ArrayList<String> ? = null
    private var ref : DatabaseReference? = null



    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    var uid: String ? = null

    private var adapter: ShoppingListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ListName = intent.getStringExtra("listName")

        shopping_list_name.text = ListName

        firedatabase = FirebaseDatabase.getInstance()

        shopping_list_recyclerview?.setHasFixedSize(true)
        shopping_list_recyclerview?.layoutManager = LinearLayoutManager(this)

        itemList = arrayListOf<String>()


        if(FirebaseAuth.getInstance().getCurrentUser() != null ) {
            uid = FirebaseAuth.getInstance().currentUser?.uid
        }

        ref = FirebaseDatabase.getInstance().getReference("Users").child(uid!!).child(Constants.DATABASE_TOBUY_LISTS).child(ListName!!)

        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if (p0!!.exists()) {
                    if (itemList!!.isNotEmpty()) {
                        itemList!!.clear()
                    }
                    for (h in p0.children) {
                        val newItem = h.key

                        //println("ItemId: "+newItem!!.keyId) //Checking key
                        itemList?.add(newItem!!)
                    }

                    adapter = ShoppingListAdapter(this@ShoppingListActivity, itemList!!, this@ShoppingListActivity)

                    shopping_list_recyclerview?.setAdapter(adapter)

                }

            }
        })

        btn_back.setOnClickListener {
                super.onBackPressed()
        }

        btn_new_item.setOnClickListener {

            val DialogView = LayoutInflater.from(this).inflate(R.layout.dialog_new_item, null)

            val editNewItem = DialogView?.findViewById<TextView>(R.id.new_item_name)

            val Builder = AlertDialog.Builder(this)
                    .setView(DialogView)

            val  AlertDialog = Builder.show()


            DialogView.btn_new_list_save.setOnClickListener {

                if(editNewItem?.text.toString().trim() != "") {
                    val newItemName = editNewItem?.text.toString().trim()

                    ref?.child(newItemName)?.setValue(newItemName)
                    adapter?.notifyDataSetChanged()

                    AlertDialog.dismiss()


                }
                else
                    Toast.makeText(this, "Please enter the name of the product", Toast.LENGTH_LONG).show()


            }

            DialogView.btn_new_list_cancel.setOnClickListener {

                AlertDialog.dismiss()
            }
        }
    }
    override fun onItemClick(position: Int) {

        var item = itemList!![position]

        sleep(1000)
        ref?.child(item!!)?.removeValue()
        itemList!!.remove(item)
        shopping_list_recyclerview!!.adapter!!.notifyItemRemoved(position)

    }
}