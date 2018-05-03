package com.akqa.androiddev.android_login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // get reference to all views
        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_reset = findViewById(R.id.btn_reset) as Button
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_reset.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            et_user_name.setText("")
            et_password.setText("")
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text;

            if(user_name.trim().equals("") || password.trim().equals("") ){
                Toast.makeText(this@MainActivity, "Username and password are required.", Toast.LENGTH_LONG).show()
            }else {
                val myIntent = Intent(this@MainActivity, LoggedinActivity::class.java)
                myIntent.putExtra("key", "Welcome " + user_name) //Optional parameters
                this@MainActivity.startActivity(myIntent)

            }

        }
    }
}
