package com.android.myfood.storage

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.R
import com.android.myfood.storage.model.StorageItem
import io.grpc.InternalNotifyOnServerBuild
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.jvm.java as java

class StorageAdapter(val context: Context,
                     val storageList: ArrayList<StorageItem>,
                     val onClickListener: OnItemClickListener) :
RecyclerView.Adapter<StorageAdapter.ViewHolder>() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(storageList[position], context)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.storage_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storageList.size
    }


    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!),
    View.OnClickListener{
        val recordName = view?.findViewById<TextView>(R.id.product_name)
        val recordAmount = view?.findViewById<TextView>(R.id.product_amount)
        val recordExpiryDate = view?.findViewById<TextView>(R.id.product_expire)

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
        fun bind(item: StorageItem, context: Context) {
            recordName?.text = item.name
            recordAmount?.text = item.weight.toString()+" "+item.unit

            if(item.expiryDate == "" || item.expiryDate == null) {
                recordExpiryDate?.text = "--"
            }
            else{
                recordExpiryDate?.text = getDaysDif(item.expiryDate)
            }

        }


        @RequiresApi(Build.VERSION_CODES.O)
        private fun getDaysDif(toDate: String?): String {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val gDate = LocalDate.parse(toDate, format)
            val today: LocalDate = LocalDate.now()

            return when {
                ChronoUnit.DAYS.between(today, gDate) < 31 -> ChronoUnit.DAYS.between(today, gDate).toString() + " days"
                ChronoUnit.MONTHS.between(today, gDate) < 12 -> ChronoUnit.MONTHS.between(today, gDate).toString() + " months"
                else -> ChronoUnit.YEARS.between(today, gDate).toString() + " years"
            }
        }


        }

    interface OnItemClickListener {
       fun onItemClick(position: Int)
    }
}



