@file:Suppress("PackageName")

package com.android.myfood.Model

import java.time.LocalDate

data class StorageItem(
        val name: String, val storingPlace: String, val weight: Double, val weightMesureType: String) //, val ProductExpirationDate: LocalDate
