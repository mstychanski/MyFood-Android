package com.android.myfood

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.temporal.ChronoUnit


class ItemAdapter(private val itemList: List<StorageItem>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.storage_item,
            parent, false
        )

        return ItemViewHolder(itemView)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.productName.text = currentItem.productName
        holder.productAmount.text = currentItem.productAmount.toString()
        holder.productMeasureType.text = currentItem.ProductMeasureType
        holder.daysToExpire.text = getDaysDif(currentItem.ProductExpirationDate).toString()+" days"
    }

    override fun getItemCount() = itemList.size
    

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productAmount: TextView = itemView.findViewById(R.id.product_amount)
        val productMeasureType: TextView = itemView.findViewById(R.id.measure_type)
        val daysToExpire: TextView = itemView.findViewById(R.id.product_expire)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDaysDif(toDate: LocalDate): Long {
        val today: LocalDate = LocalDate.now()
        return ChronoUnit.DAYS.between(today, toDate)
    }
}

