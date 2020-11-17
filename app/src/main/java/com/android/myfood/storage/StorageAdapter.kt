package com.android.myfood.storage

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.R
import com.android.myfood.model.StorageItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class StorageAdapter(val context: Context, val storageList: ArrayList<StorageItem>) :
RecyclerView.Adapter<StorageAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bind(storageList[position], context)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.storage_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storageList.size
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val recordName = view?.findViewById<TextView>(R.id.product_name)
        val recordAmount = view?.findViewById<TextView>(R.id.product_amount)
        val recordExpiryDate = view?.findViewById<TextView>(R.id.product_expire)
      // val recordDate = view?.findViewById<TextView>(R.id.record_date)



        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: StorageItem, context: Context) {
            recordName?.text = item.name
            recordAmount?.text = item.weight.toString()+" "+item.unit

            if(item.expiryDate == "" || item.expiryDate == null) {
                recordExpiryDate?.text = "--"
            }
            else{
                recordExpiryDate?.text = getDaysDif(item.expiryDate).toString() + " days"
            }

        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun getDaysDif(toDate: String?): Long {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val gDate = LocalDate.parse(toDate, format)
            val today: LocalDate = LocalDate.now()
            return ChronoUnit.DAYS.between(today, gDate)
        }


    }
}