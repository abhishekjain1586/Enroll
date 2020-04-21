package com.locationtracker.myapplication.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {

    fun isNetworkConnected(context: Activity) : Boolean {
        var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected;
    }

}