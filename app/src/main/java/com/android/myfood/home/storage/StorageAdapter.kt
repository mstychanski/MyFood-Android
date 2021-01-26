package com.android.myfood.home.storage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.android.myfood.R
import com.android.myfood.home.storage.model.StorageItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class StorageAdapter(val context: Context,
                     val storageList: ArrayList<StorageItem>,
                     val onClickListener: OnItemClickListener) :
RecyclerView.Adapter<StorageAdapter.ViewHolder>() {



    var allDataList : List<StorageItem> = storageList


    var dataList : List<StorageItem> = listOf()




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], context)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.storage_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
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

                recordExpiryDate?.setTextColor(Color.parseColor("#000000"))
                recordExpiryDate?.text = "--"
            }
            else{
                recordExpiryDate?.text = getDaysDif(item.expiryDate)
            }

        }


        @SuppressLint("ResourceAsColor")
        @RequiresApi(Build.VERSION_CODES.O)
        private fun getDaysDif(toDate: String?): String {
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val gDate = LocalDate.parse(toDate, format)
            val today: LocalDate = LocalDate.now()


            var days = ChronoUnit.DAYS.between(today, gDate)
            var months = ChronoUnit.MONTHS.between(today, gDate)
            var years =  ChronoUnit.YEARS.between(today, gDate)

                when {
                    days > 0 -> {

                        recordExpiryDate?.setTextColor(Color.parseColor("#000000"))

                        return if (days.equals(1)) {
                            "Expires Tomorrow"
                        }else {
                            when {
                                years > 0 -> "$years years"
                                months > 0 -> "$months months"
                                else -> "$days days"
                            }
                        }
                    }
                    days < 0 -> {

                        recordExpiryDate?.setTextColor(Color.parseColor("#E71000"))

                        return if(days.equals(-1)) {
                            "Expired Yesterday"
                        }else {




                            when {
                                years < 0 -> {
                                    years = -years
                                    "Expired $years years ago"

                                }
                                months < 0 -> {
                                    months = -months
                                    "Expired $months months ago"
                                }
                                else -> {
                                    days = -days
                                    "Expired $days days ago"
                                }
                            }
                        }
                    }
                    else -> {
                        recordExpiryDate?.setTextColor(Color.parseColor("#E71000"))
                        return "Expires Today"
                    }
                }
            }

        }

    interface OnItemClickListener {
       fun onItemClick(position: Int)
    }

    fun showListByCatagory(storingPlace: String){

        when(storingPlace){
            "all" -> {
                this.dataList = allDataList
            }

            "freezer" -> {
                this.dataList = allDataList.filter { it.storingPlace == "Freezer" }
            }

            "fridge" -> {
                this.dataList = allDataList.filter { it.storingPlace == "Fridge" }
            }

            "pantry" -> {
                this.dataList = allDataList.filter { it.storingPlace == "Pantry" }
            }

            else ->{
                this.dataList = allDataList
            }
        }

        notifyDataSetChanged()
    }

}



