package com.android.myfood.home.storage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible

import com.android.myfood.R
import com.android.myfood.home.storage.model.FoodFactsProduct
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.gson.Gson

import com.squareup.okhttp.*
import kotlinx.android.synthetic.main.activity_scanner.*
import org.json.JSONObject
import java.io.IOException



class ScannerActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    var scannedProduct : FoodFactsProduct ? = null
    var toastText : String ? = null

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

            requestDataAPI(it.text)

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

            override fun onResponse(response: Response) {
                if(response.isSuccessful) {
                            val body = response.body().string()


                            scannedProduct = FoodFactsProduct.fromJson(body.toString())
                            //Toast.makeText(this@ScannerActivity, scannedProduct!!.product?.productName as String, Toast.LENGTH_SHORT).show()
                        }
                else{
                    Log.e("in ", " response is not successful" )
                }
                val intent = Intent(this@ScannerActivity, AddProductActivity::class.java)
                if(scannedProduct != null) {
                    toastText = if (scannedProduct?.status!! == 1) {
                        if(scannedProduct?.product?.productName == null){
                            "Product not found!"
                        } else {
                            intent.putExtra("Name", scannedProduct?.product?.productName.toString())
                            intent.putExtra("IsFound", true)
                            "${scannedProduct?.product?.productName.toString()} found!"
                        }
                    } else
                        "Product not found!"


                    intent.putExtra("Toast", toastText)
                }


                startActivity(intent)

            }

        })


    }



}

