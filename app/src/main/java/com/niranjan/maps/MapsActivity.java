package com.niranjan.maps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private FirebaseDatabase firebaseDatabase;
    private GoogleMap mMap;
      double X_t=16.076738797879788;
    double Y_t=108.15499172116395;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                position a= snapshot.getValue(position.class);
                   X_t= a.X;
                   Y_t= a.Y;

                Log.d("DEBUG*value", a.toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("DEBUG*value", "Fail Read GPS");
            }
        });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng mdu = new LatLng(X_t, Y_t);
        mMap.addMarker(new MarkerOptions().position(mdu).title("BK Univercity").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mdu));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mdu,10));


    }
}