package com.android.myfood.home.tobuy

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.Constants
import com.android.myfood.R
import com.android.myfood.home.storage.item.SwipeBackgroundHelper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.dialog_new_list.*
import kotlinx.android.synthetic.main.dialog_new_list.view.*
import kotlinx.android.synthetic.main.dialog_product_info.view.*
import kotlinx.android.synthetic.main.fragment_storage.*
import kotlinx.android.synthetic.main.fragment_to_buy.*
import java.util.ArrayList


class ToBuyFragment : Fragment(), ToBuyListAdapter.OnItemClickListener {


    var fragmentView : View? = null
    var firedatabase : FirebaseDatabase? = null
    var itemList : ArrayList<String> ? = null
    private var ref : DatabaseReference? = null

    var mRecyclerView : RecyclerView? =null

    lateinit var dialogBuilder: AlertDialog.Builder
    lateinit var dialog: AlertDialog

    var uid: String ? = null

    private var adapter: ToBuyListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {

        fragmentView= LayoutInflater.from(activity).inflate(R.layout.fragment_to_buy, container, false)

        firedatabase = FirebaseDatabase.getInstance()

        mRecyclerView = fragmentView?.findViewById(R.id.tobuy_recyclerview)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
        itemList = arrayListOf<String>()





        if(FirebaseAuth.getInstance().getCurrentUser() != null ) {
            uid = FirebaseAuth.getInstance().currentUser?.uid
        }
        ref = FirebaseDatabase.getInstance().getReference("Users").child(uid!!).child(Constants.DATABASE_TOBUY_LISTS)


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

                        itemList?.add(newItem!!)
                    }

                    adapter = ToBuyListAdapter(context!!, itemList!!, this@ToBuyFragment)

                    mRecyclerView?.setAdapter(adapter)

                }

            }
        })

        return  fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_new_list.setOnClickListener {

            val DialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_new_list, null)

            val editNewList = DialogView?.findViewById<TextView>(R.id.new_list_name)

            val Builder = AlertDialog.Builder(activity)
                    .setView(DialogView)

            val  AlertDialog = Builder.show()


            DialogView.btn_new_list_save.setOnClickListener {

                if(editNewList?.text.toString().trim() != "" && editNewList?.text.toString().trim() != null ) {
                    val newListName = editNewList?.text.toString().trim()

                    val intent = Intent(this.activity, ShoppingListActivity::class.java)
                    intent.putExtra("listName", newListName)

                    AlertDialog.dismiss()

                    startActivity(intent)
                }
                else
                    Toast.makeText(context, "Please enter the name of the list", Toast.LENGTH_LONG).show()


            }

            DialogView.btn_new_list_cancel.setOnClickListener {

                AlertDialog.dismiss()
            }
        }
    }


    var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(recyclerView: RecyclerView,
                                    viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var position = viewHolder.adapterPosition
                    var item = itemList!![position]
                    when(direction){
                        ItemTouchHelper.LEFT -> {
                            ContextCompat.getColor(requireContext(), R.color.red)
                            ref?.child(item)?.removeValue()
                            itemList!!.remove(item)
                            mRecyclerView!!.adapter!!.notifyItemRemoved(position)

                        }
                    }
                }

                override fun onChildDraw(
                        c: Canvas,
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        dX: Float,
                        dY: Float,
                        actionState: Int,
                        isCurrentlyActive: Boolean
                ) {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        val viewItem = viewHolder.itemView
                        SwipeBackgroundHelper.paintDrawCommandToStart(c, viewItem, R.drawable.ic_trash, dX)
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }


    override fun onItemClick(position: Int) {

        val listName = itemList!![position]

        val intent = Intent(this.activity, ShoppingListActivity::class.java)
        intent.putExtra("listName", listName)
        startActivity(intent)

    }
}