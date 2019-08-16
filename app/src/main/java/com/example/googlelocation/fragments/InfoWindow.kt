package com.example.googlelocation.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.googlelocation.MarkerInfo
import com.example.googlelocation.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.gson.Gson


class InfoWindow(context: Context) : GoogleMap.InfoWindowAdapter {

    val context: Context = context.applicationContext

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.custom_info_window, null)

        val title = v.findViewById<TextView>(R.id.title)
        val tvDetails1 = v.findViewById<TextView>(R.id.des1)
        val position1 = v.findViewById<TextView>(R.id.des2)
        val image = v.findViewById<ImageView>(R.id.imageView3)

        val imageV = image.setImageResource(R.mipmap.cambodia).toString()
        val latLng = marker.position.toString()
//        val gg = marker.snippet

        val gson = Gson()
        val information = MarkerInfo("Your current location", "Phnom Penh, Cambodia", latLng, imageV)

        val json = gson.toJson(information)

        title.text = information.title
        tvDetails1.text = information.description1
        position1.text = information.latLng

        return v
    }

}
