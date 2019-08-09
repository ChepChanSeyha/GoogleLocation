package com.example.googlelocation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.location.LocationResult
import java.lang.Exception
import java.lang.StringBuilder

class MyLocationService : BroadcastReceiver() {

    companion object {
        const val ACTION_PROCESS_UPDATE = ""
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val action = intent!!.action

            if (action.equals(ACTION_PROCESS_UPDATE)) {
                val result = LocationResult.extractResult(intent!!)

                if (result != null) {
                    val location = result.lastLocation
                    val strLocation = StringBuilder(location.latitude.toString())
                        .append("/")
                        .append(location.longitude.toString())
                    val strLatitude = StringBuilder(location.latitude.toString())
                    val strLongitude = StringBuilder(location.longitude.toString())

                    try {

                    }catch (e:Exception) {
                        Toast.makeText(context, strLocation, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}
