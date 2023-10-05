package com.etisalat.recipes.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration

class AndroidUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDuration(duration: String): String {
        val parsedDuration = Duration.parse(duration)
        val hours = parsedDuration.toHours()
        val minutes = parsedDuration.toMinutesPart()

        return String.format("%dh %02dm", hours, minutes)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}