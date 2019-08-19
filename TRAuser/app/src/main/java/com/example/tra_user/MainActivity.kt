package com.example.tra_user

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var btn1:Button
    lateinit var tv:TextView
    lateinit var imageButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.tv1)
        imageButton = findViewById(R.id.imageButton)
        imageButton.setOnClickListener {
            alert()

        }




        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener{
            intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
    }
    private fun alert(){


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
        builder.setMessage("Welcome to TRA-user app, here you can take order and deliver them and use the maps on the app ")
        builder.setPositiveButton("ok",{ dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }

}
class order(val loctionLongTude:String
            , val locationLatetude:String
            , val destinationLongtude:String
            , val destinationLatetude:String
            , val orderName:String
            , val size:String){
    constructor():this("0.0","0.0","0.0","0.0","0.0","0.0"){

    }
}
