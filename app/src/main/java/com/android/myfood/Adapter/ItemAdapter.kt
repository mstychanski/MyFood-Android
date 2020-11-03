package com.android.myfood.Adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.Model.StorageItem
import com.android.myfood.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import kotlinx.android.synthetic.main.storage_item.view.*
import org.w3c.dom.Text

class ItemAdapter(internal var context: Context): FirebaseRecyclerAdapter<StorageItem, ItemAdapter.ItemViewHolder>(),
    Parcelable {

    internal var itemList: MutableList<StorageItem>

    val lastItemId:String?
        get() = itemList[itemList.size - 1].id


    fun addAll (newItems:List<StorageItem>)
    {
        val init = itemList.size
        itemList.addAll(newItems)
        notifyItemRangeChanged(init, newItems.size)
    }

    fun removeLastItem()
    {
        itemList.removeAt(itemList.size - 1)
    }

    init
    {
       this.itemList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.storage_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
    return itemList.size
    }


    inner class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        internal var productName:TextView
        internal var productAmount:TextView
        internal var productMesureType:TextView
        init
        {
            productName=itemView.findViewById<TextView>(R.id.product_name);
            productAmount=itemView.findViewById<TextView>(R.id.product_amount);
            productMesureType=itemView.findViewById<TextView>(R.id.measure_type);
        }

    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, model: StorageItem) {
        holder.productName.text = itemList[position].name
        holder.productAmount.text = itemList[position].weight.toString()
        holder.productMesureType.text = itemList[position].weightMesureType
    }

}