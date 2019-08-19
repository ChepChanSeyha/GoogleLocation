package com.example.googlelocation.fragments

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.googlelocation.MarkerInfo
import com.example.googlelocation.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_maps.*


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    override fun onInfoWindowClick(p0: Marker?) {
        Toast.makeText(context, "Current Location", Toast.LENGTH_LONG).show()
    }

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var marker: Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_maps, container, false)
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        buildLocationCallback()

        btn_getDirection.setOnClickListener {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }

        btn_stopDirection.setOnClickListener {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun buildLocationCallback() {
        locationRequest = LocationRequest()
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
//                for (location in locationResult.locations){
                    // Update UI with location data
                    // ...
                    setMarker(locationResult.lastLocation)
//                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLastKnownLocation()

        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.isMyLocationEnabled = true

        // custom info window
        val markerInfoWindowAdapter = context!!.let { com.example.googlelocation.fragments.InfoWindow(it) }
        googleMap.setInfoWindowAdapter(markerInfoWindowAdapter)
        googleMap.setOnInfoWindowClickListener(this)
        marker?.showInfoWindow()

        //Dark mode on map
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json))

    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
               if (location != null) {
                   setMarker(location)
               }
            }
    }

    @SuppressLint("MissingPermission")
    private fun setMarker(location: Location) {
        val lateLong1 = LatLng(location.latitude, location.longitude)

        if(marker == null){
            val markerOptions = MarkerOptions().position(lateLong1)
            marker = mMap.addMarker(markerOptions.title("Current Location"))
            moveCamera(location)
        }

        //
        val information = MarkerInfo("Your current location", "Phnom Penh, Cambodia", R.mipmap.cambodia)

        val gSon = Gson()

        val jsonString = gSon.toJson(information)
        marker?.snippet = jsonString
    }

    private fun moveCamera(location: Location){
        val lateLong2 = LatLng(location.latitude, location.longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lateLong2))
    }

}
