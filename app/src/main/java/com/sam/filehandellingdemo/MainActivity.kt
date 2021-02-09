package com.sam.filehandellingdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val btnDisplay = findViewById<Button>(R.id.btn_display)
        // upon "save" button click, information from editData will be saved to file
        btnSave.setOnClickListener {
            val fileVal = findViewById<EditText>(R.id.et_file).toString()
            val fileData = findViewById<EditText>(R.id.et_file_data).toString()
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = openFileOutput(fileVal, Context.MODE_PRIVATE)
                fileOutputStream.write(fileData.toByteArray())
            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            showToast("Saved to file")
        }
        // upon "save" button click, information from editData will be displayed
        btnDisplay.setOnClickListener {
            val fileVal = findViewById<EditText>(R.id.et_file).toString()
            val fileData = findViewById<EditText>(R.id.et_file_data)
            if (fileVal!=null && fileVal.trim()!=""){
                try {
                    var fileInputStream: FileInputStream? = null
                    fileInputStream = openFileInput(fileVal)
                    var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                    val bufferReader: BufferedReader = BufferedReader(inputStreamReader)
                    val stringBuilder: StringBuilder = StringBuilder()
                    var text: String? = null
                    while ({text = bufferReader.readLine(); text} ()!= null){
                        stringBuilder.append(text)
                    }
                    fileData.setText(stringBuilder.toString())
                }catch (e: FileNotFoundException){
                    e.printStackTrace()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }else{
                showToast("Name of the file cannot be blank")
            }
        }

    }
    fun Context.showToast(text: CharSequence,duration: Int = Toast.LENGTH_LONG){
        Toast.makeText(this,text,duration).show()
    }
}