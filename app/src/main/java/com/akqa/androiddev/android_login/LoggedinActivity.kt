package com.akqa.androiddev.android_login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent



class LoggedinActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  val intent = intent
      //  val value = intent.getStringExtra("key")
        setContentView(R.layout.activity_loggedin);
    }
}