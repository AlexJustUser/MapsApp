package com.example.testapp.map.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMapBinding;
import com.example.testapp.login.ui.LoginActivity;
import com.example.testapp.mapslist.ui.MapsListActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private ActivityMapBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.backwardButton.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        intent = getIntent();
        binding.idMapPoint.setText(intent.getStringExtra("id"));
        binding.countryMapPoint.setText(intent.getStringExtra("country"));
        binding.nameMapPoint.setText(intent.getStringExtra("name"));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Float.parseFloat(intent.getStringExtra("lat")), Float.parseFloat(intent.getStringExtra("lon"))))
                .title("Marker" + intent.getStringExtra("name")));
    }


}