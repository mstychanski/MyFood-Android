package com.android.myfood.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.R

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val productName: TextView = itemView.findViewById(R.id.product_name)
    val productAmount: TextView = itemView.findViewById(R.id.product_amount)
    val productMeasureType: TextView = itemView.findViewById(R.id.measure_type)
   // val daysToExpire: TextView = itemView.findViewById(R.id.product_expire)
}