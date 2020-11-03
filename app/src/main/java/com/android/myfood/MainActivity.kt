package com.android.myfood


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.Adapter.ItemAdapter
import com.android.myfood.Model.StorageItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //Variables
    val ITEM_COUNT = 21
    var total_item = 0
    var last_visible_item = 0
    lateinit var adapter: ItemAdapter

    var isLoading=false
    var isMaxData=false

    var last_node:String?=""
    var last_key:String?=""

    val currentUser = FirebaseAuth.getInstance().currentUser!!

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.app_bar, menu)
//        return true
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLastKey()

        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(recycler_view.context, layoutManager.orientation)
        recycler_view.addItemDecoration(dividerItemDecoration)

        adapter = ItemAdapter(this)
        recycler_view.adapter = adapter

        getItems()

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                total_item = layoutManager.itemCount
                last_visible_item = layoutManager.findLastCompletelyVisibleItemPosition()

                if (!isLoading && total_item <= last_visible_item + ITEM_COUNT) {
                    getItems()
                    isLoading = true
                }
            }
        })

//        add_btn.setOnClickListener {
//            val intent = Intent(this, AddItem::class.java)
//            startActivity(intent)
//        }
    }

    private fun getItems() {
    if(!isMaxData)
    {
        val query:Query
        if(TextUtils.isEmpty(last_node))
            query = FirebaseDatabase.getInstance().reference
                    .child(currentUser.uid)
                    .child("MyFood")
                    .orderByKey()
                    .limitToLast(ITEM_COUNT)
        else
            query = FirebaseDatabase.getInstance().reference
                    .child(currentUser.uid)
                    .child("MyFood")
                    .orderByKey()
                    .startAt(last_node)
                    .limitToLast(ITEM_COUNT)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.hasChildren()) {
                    val itemList = ArrayList<StorageItem>()
                    for (snapshot in snapshot.children) {
                        val newItem = StorageItem(
                                snapshot.key!!,
                                snapshot.child("name").value as String,
                                snapshot.child("weight").value as Double,
                                snapshot.child("storingPlace").value as String,
                                snapshot.child("weightMesureTime").value as String
                        )
                        itemList.add(snapshot.getValue(StorageItem::class.java)!!)
                    }
                    last_node = itemList[itemList.size - 1].id

                    if (!last_node.equals(last_key))
                        itemList.removeAt(itemList.size - 1)
                    else
                        last_node = "end"

                    adapter.addAll(itemList)
                    isLoading = false
                }
                isLoading = false
                isMaxData = true
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    }

    private fun getLastKey() {


        val get_last_key = FirebaseDatabase.getInstance().getReference()
                .child(currentUser.uid)
                .child("MyFood")
                .orderByKey()
                .limitToLast(1)
                get_last_key.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (userSnapshot in snapshot.children)
                            last_key = userSnapshot.key
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }

                })
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()

        if (adapter != null) {
            adapter!!.stopListening()
        }
    }
}