package com.akqa.androiddev.util

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException
import java.util.*


object ApplicationUtil {

    @Throws(IOException::class)
    fun getProperty(key: String, context: Context): String {
        val properties = Properties()
        val assetManager = context.getAssets()
        val inputStream = assetManager.open("config.properties")
        properties.load(inputStream)

        return properties.getProperty(key)
    }
}