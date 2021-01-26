package com.android.myfood.home.storage.model

data class StorageItem(
        val name:         String? = null,
        val storingPlace: String? = null,
        val weight:       Long?   = null,
        val unit:         String? = null,
        val expiryDate:   String? = null
){
        var keyId:        String? = null
}
