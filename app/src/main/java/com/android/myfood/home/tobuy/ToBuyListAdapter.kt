package com.android.myfood.home.tobuy

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.R


class ToBuyListAdapter(val context: Context,
                       val toBuyList: ArrayList<String>,
                       val onClickListener: OnItemClickListener
) :
        RecyclerView.Adapter<ToBuyListAdapter.ViewHolder>() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(toBuyList[position], context)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.tobuy_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toBuyList.size
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!),
            View.OnClickListener{
        val shoppingListName = view?.findViewById<TextView>(R.id.tobuy_list_name)


        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                onClickListener.onItemClick(position)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: String, context: Context) {
            shoppingListName?.text = item

        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}