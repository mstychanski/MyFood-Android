package com.android.myfood.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.myfood.R
import com.beust.klaxon.Parser

import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.gson.Gson

import com.google.gson.JsonObject

import com.squareup.okhttp.*
import org.json.JSONObject
import java.io.IOException



class ScannerActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var productName: String
    private lateinit var productAmount: String
    var newProduct: FetchedProduct ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
                val intent = Intent(this@ScannerActivity, AddProductActivity::class.java)
                requestDataAPI(it.text)
                if(newProduct != null)
                {
                    intent.putExtra("Name", newProduct?.name)
                    intent.putExtra("Amount", newProduct?.net_weight_value)
                    intent.putExtra("Unit", newProduct?.net_weight_unit)
                }

                startActivity(intent)
                finish()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun requestDataAPI(barcode: String){
        val url = "https://us.openfoodfacts.org/api/v0/product/$barcode.json"
        val request = Request.Builder().url(url).build()

        val text: String

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(request: Request?, e: IOException?) {
                println("Failed to execute request")
            }

            override fun onResponse(response: Response?) {
                val body = response?.body()?.string()
                println(body)

                var map: Map<String, Any> = HashMap()
                map = Gson().fromJson(body, map.javaClass)

                println("TEST: "+map)
                //newProduct?.name = map.

            }

        })
        println("TUTAJ:"+newProduct)
    }

}

data class FetchedProduct (
    var name: String,
    var net_weight_value: String,
    var net_weight_unit: String
)
