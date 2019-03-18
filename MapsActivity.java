package com.example.shantanu.odishaayushman;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng odisha=new LatLng(20.9517, 85.0985);
        LatLng odisha1=new LatLng(20.457838, 85.871536);
        LatLng odisha2=new LatLng(20.651484,84.629814);
        LatLng odisha3=new LatLng(20.650694,84.631775);
        LatLng odisha4=new LatLng(20.1084,85.8342);
        LatLng odisha5=new LatLng(22.2254,84.8284);

        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(odisha1).title("RELAX Hospital, Cuttack, Orrisa, Odisha, India"));
        mMap.addMarker(new MarkerOptions().position(odisha2).title("Primary Health Care Center ,Pataka, Odisha, India"));
        mMap.addMarker(new MarkerOptions().position(odisha3).title("Jyotirmayee Medicine Store,Pataka,Odisha,India"));
        mMap.addMarker(new MarkerOptions().position(odisha4).title("Pipili Government Hospital, Odisha, India"));
        mMap.addMarker(new MarkerOptions().position(odisha5).title("Rourkela Government Hospitla,Odisha ,India"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(odisha));
    }


}
