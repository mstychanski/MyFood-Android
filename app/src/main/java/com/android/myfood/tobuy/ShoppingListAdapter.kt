package com.android.myfood.tobuy

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.Constants
import com.android.myfood.R
import com.google.firebase.database.FirebaseDatabase

class ShoppingListAdapter(val context: Context,
                          val shoppingList: ArrayList<String>,
                          val onClickListener: OnItemClickListener) :
        RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shoppingList[position], context)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.shopping_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!), View.OnClickListener {
        val shoppingItemName = view?.findViewById<TextView>(R.id.shopping_list_item_name)
        val checkboxItem = view?.findViewById<CheckBox>(R.id.checkbox_item)


        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                onClickListener.onItemClick(position)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: String, context: Context) {
            shoppingItemName?.text = item
            checkboxItem?.setOnClickListener(this)

        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}