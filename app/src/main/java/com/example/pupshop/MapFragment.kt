package com.example.pupshop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {
    companion object {
        fun newInstance(lat: Double, lng: Double): MapFragment {
            val fragment = MapFragment()
            val args = Bundle()
            args.putDouble("lat", lat)
            args.putDouble("lng", lng)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_map, container, false)

        // Initialize map fragment
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?

        // Async map
        supportMapFragment!!.getMapAsync { googleMap ->

            val lat = arguments?.getDouble("lat", 0.0)
            val lng = arguments?.getDouble("lng", 0.0)

            googleMap.setOnMapLoadedCallback {
                val location = LatLng(lat!!, lng!!)
                googleMap.addMarker(MarkerOptions().position(location).title(""))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            }

            // When map is loaded
            googleMap.setOnMapClickListener { latLng ->
                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)
                googleMap.clear()
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                googleMap.addMarker(markerOptions)
            }
        }
        return view
    }
}