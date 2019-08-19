package com.example.tra_user

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {






    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    lateinit var choose:Button
    lateinit var back:Button
    lateinit var fab:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fab = findViewById(R.id.fab1)
        back = findViewById(R.id.back)
        back.setOnClickListener {
            intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
        choose = findViewById(R.id.choose)
        choose.setOnClickListener {
            ////
        }

        fab.setOnClickListener {

                alert()

        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    ///////////////////
    override fun onMapReady(googleMap: GoogleMap) {
        var Llong = intent.getStringExtra("Llong")
        var Llate = intent.getStringExtra("Llate")
        var Dlong = intent.getStringExtra("Dlong")
        var Dlate = intent.getStringExtra("Dlate")
        var name = intent.getStringExtra("name")


        map = googleMap

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)

        val Location = LatLng(Llate.toDouble(), Llong.toDouble())  // this is New York
        map.addMarker(MarkerOptions().position(Location).title("Order Location"))
        map.moveCamera(CameraUpdateFactory.newLatLng(Location))

        val Destination = LatLng(Dlate.toDouble(), Dlong.toDouble())  // this is New York
        map.addMarker(MarkerOptions().position(Destination).title("Order Destination"))
        map.moveCamera(CameraUpdateFactory.newLatLng(Destination))

        setUpMap()
    }
    ///////////////
    override fun onMarkerClick(p0: Marker?) = false
    ////////////////
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        map.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 11f))
            }
        }
    }

    /////////////////////
    private fun placeMarkerOnMap(location: LatLng) {
        // 1
        val markerOptions = MarkerOptions().position(location)
        // 2
        map.addMarker(markerOptions)
    }
    ////////////////////
    private fun alert(){


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
        builder.setMessage("information about the distance between the locations ")
        builder.setPositiveButton("ok",{ dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }


}
