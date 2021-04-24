package com.example.testapp.map.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private ActivityMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        binding.backwardButton.setOnClickListener((v) -> {
            finish();
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        binding.idMapPoint.setText(getIntent().getStringExtra("id"));
        binding.countryMapPoint.setText(getIntent().getStringExtra("country"));
        binding.nameMapPoint.setText(getIntent().getStringExtra("name"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(Float.parseFloat(getIntent().getStringExtra("lat")), Float.parseFloat(getIntent().getStringExtra("lon")));
        googleMap.addMarker(new MarkerOptions()
                .position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}