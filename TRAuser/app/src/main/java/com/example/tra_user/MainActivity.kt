package com.example.tra_user

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var login:Button
    lateinit var password:EditText
    var myAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        login = findViewById(R.id.loginbtn)
        login.setOnClickListener {
            val email1 = email.text.toString().trim()
            val password1 = password.text.toString().trim()

            signIn(email1,password1)




        }

    }
    private fun signIn(em:String,Pass:String){
        myAuth.signInWithEmailAndPassword(em,Pass).addOnCompleteListener(this,OnCompleteListener {task ->
            if (task.isSuccessful){
                val intent = Intent(this,main::class.java)
                startActivity(intent)

                Toast.makeText(this,"done",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"something wring",Toast.LENGTH_LONG).show()
            }
        })
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
