package com.example.googlelocation.fragments

import android.annotation.SuppressLint
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

    @SuppressLint("ResourceType")
    override fun getInfoContents(marker: Marker): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.custom_info_window, null)

        val title = v.findViewById<TextView>(R.id.title)
        val tvDetails1 = v.findViewById<TextView>(R.id.description)
        val image = v.findViewById<ImageView>(R.id.imageView3)

//        val jsonString = marker.snippet
//        val titleMarker = marker.title

        // jsonString -> Object
//        val gSon = Gson()
//        val stringJson = """{
//            "title": "Your current location",
//            "description1": "Phnom Penh, Cambodia",
//            "imageView": "cbvzxcvzsv"
//            }"""
//
//        val obj = gSon.fromJson(stringJson, MarkerInfo::class.java)
//
//        title.text = obj.title
//        tvDetails1.text = obj.description1
//        obj.imageView = image.setImageResource(R.mipmap.cambodia).toString()

        val jsonString = marker.snippet
        val gSon = Gson()

        val obj = gSon.fromJson(jsonString, MarkerInfo::class.java)

        title.text = obj.title
        tvDetails1.text = obj.description1
        image.setImageDrawable(context.getDrawable(obj.imageId))


        return v
    }

}
