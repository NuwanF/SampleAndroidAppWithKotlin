package com.example.pupshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Contact : AppCompatActivity(), OnMapReadyCallback {

    private var mMapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.pupshop.R.layout.contact_layout)

        //val v: View = inflater.inflate(com.example.pupshop.R.layout.contact_layout, container, false)

        mMapView = findViewById<MapView>(com.example.pupshop.R.id.mapView2)
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.getMapAsync(this)

        val thePup: Pup.Dog = intent?.extras?.getSerializable("pup") as Pup.Dog
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onMapReady(map: GoogleMap) {
        map.addMarker(MarkerOptions().position(LatLng(51.8611135, -0.4977115)).title("Marker"))
    }

    override fun onPause() {
        mMapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }

//    override fun onSaveInstanceState() {
//        super.onSaveInstanceState(outState!!)
//        mMapView!!.onSaveInstanceState(outState)
//    }
}