package com.akqa.androiddev.android_login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.design.widget.Snackbar

import android.view.View
import com.akqa.androiddev.R
import com.akqa.androiddev.util.ApplicationUtil
import com.akqa.androiddev.util.ConnectivityReceiver
import com.akqa.androiddev.util.ConnectivityReceiver.ConnectivityReceiverListener
import android.R.id.button3
import android.R.id.button2
import android.R.id.button1
import android.content.DialogInterface


class MainActivity : AppCompatActivity() , ConnectivityReceiverListener {

    private lateinit var et_user_name: EditText
    private lateinit var et_password: EditText
    private lateinit var btn_reset: Button
    private lateinit var btn_submit: Button
    private var mSnackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        setContentView(R.layout.activity_main)

        // get reference to all views
        et_user_name = findViewById<EditText>(R.id.et_user_name)
        et_password = findViewById<EditText>(R.id.et_password)
        btn_reset = findViewById(R.id.btn_reset) as Button
        btn_submit = findViewById(R.id.btn_submit) as Button
  }




    private val onClickListener = object : View.OnClickListener {
        override fun onClick(v: View) {
            when (v.id) {
                R.id.btn_submit -> {
                    submit(et_user_name, et_password)
                }
                R.id.btn_reset -> {
                    reset(et_user_name, et_password)
                }

            }

        }
    }

    private fun reset(et_user_name: EditText, et_password: EditText){
        // clearing user_name and password edit text views on reset button click
        et_user_name.setText("")
        et_password.setText("")
    }

    private fun submit(username: EditText, password: EditText) {
        val user_name = username.text;
        val password = password.text;

        val userNameAllowed = ApplicationUtil.getProperty("username",applicationContext);
        val passwordAllowed = ApplicationUtil.getProperty("password",applicationContext);

        if (user_name.isNullOrEmpty() || password.isNullOrEmpty()) {
            Toast.makeText(this@MainActivity, "Username and password are required.", Toast.LENGTH_LONG).show()
        } else if (user_name.toString().equals(userNameAllowed) && password.toString().equals(passwordAllowed)) {
            val myIntent = Intent(this@MainActivity, LoggedinActivity::class.java)
            myIntent.putExtra("key", "Welcome " + user_name) //Optional parameters
            this@MainActivity.startActivity(myIntent)
        } else {
            Toast.makeText(this@MainActivity, "Invalid credentials.", Toast.LENGTH_LONG).show()
        }
    }


    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {

            val messageToUser = getResources().getString(R.string.offline_message)

            mSnackBar = Snackbar.make(findViewById(R.id.rootLayout), messageToUser, Snackbar.LENGTH_LONG)
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
            btn_submit.setOnClickListener(null)
            btn_reset.setOnClickListener(null)
        } else {
            mSnackBar?.dismiss()
            // set on-click listener
            btn_submit.setOnClickListener(onClickListener)
            btn_reset.setOnClickListener(onClickListener)
        }


    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this

    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}





