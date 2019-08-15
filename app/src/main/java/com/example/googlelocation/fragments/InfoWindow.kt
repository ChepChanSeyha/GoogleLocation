package com.example.googlelocation.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.googlelocation.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


class InfoWindow(context: Context) : GoogleMap.InfoWindowAdapter {

    val context: Context = context.applicationContext

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.custom_info_window, null)

        val tvGir = v.findViewById<View>(R.id.title) as TextView
        val tvDetails1 = v.findViewById<View>(R.id.des1) as TextView
        val tvDetails2 = v.findViewById<View>(R.id.des2) as TextView
        tvGir.text = "Cambodia"
        tvDetails1.text = "Your current location in cambodia"
        tvDetails2.text = "You stay in phnom penh"
        return v
    }

}
