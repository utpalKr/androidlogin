package com.akqa.androiddev.android_login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.EditText
import android.widget.TextView


class LoggedinActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggedin);

        val value = getIntent().getStringExtra("key");
        var welcome_msg = findViewById(R.id.welcome_msg) as TextView
        welcome_msg.setText(value);



    }
}