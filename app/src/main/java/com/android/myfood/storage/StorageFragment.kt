package com.android.myfood.storage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.Constants
import com.android.myfood.R
import com.android.myfood.login.ui.LoginActivity
import com.android.myfood.model.StorageItem
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_storage.*


class StorageFragment : Fragment() {

    var fragmentView : View? = null
    var firedatabase : FirebaseDatabase? = null
    var itemList : ArrayList<StorageItem> ? = null
    private var ref : DatabaseReference ? = null

    var mRecyclerView : RecyclerView? =null

    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        fragmentView= LayoutInflater.from(activity).inflate(R.layout.fragment_storage, container, false)

        firedatabase = FirebaseDatabase.getInstance()

        mRecyclerView = fragmentView?.findViewById(R.id.recycler_view)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)


        itemList = arrayListOf<StorageItem>()

        var uid = FirebaseAuth.getInstance().currentUser?.uid

        ref = FirebaseDatabase.getInstance().getReference("Users").child(uid!!).child(Constants.DATABASE_ITEMS)


        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){
                    if(itemList!!.isNotEmpty()){
                        itemList!!.clear()
                    }
                    for (h in p0.children){
                        val newItem = h.getValue(StorageItem::class.java)
                        itemList?.add(newItem!!)
                    }

                    val adapter = StorageAdapter(context!!, itemList!!)

                    mRecyclerView?.setAdapter(adapter)

                }

            }
        })

        return  fragmentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            val intent = Intent(this.activity, AddProductActivity::class.java)
            startActivity(intent)
        }
    }

}