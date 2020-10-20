package com.android.myfood

import java.time.LocalDate

data class StorageItem(
        val productName: String, val productAmount: Double, val ProductMeasureType: String, val ProductExpirationDate: LocalDate )
