package com.example.testapp.map.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.ActivityMapBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private ActivityMapBinding binding;
    private Intent intent;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.backwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.backwardButton.setTextColor(getResources().getColor(R.color.colorBlue));
                    binding.backwardButton.setBackground(getResources().getDrawable(R.drawable.background_white_round_solid));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.backwardButton.setTextColor(getResources().getColor(R.color.colorWhite));
                    binding.backwardButton.setBackground(getResources().getDrawable(R.drawable.background_white_round_corner));
                    finish();
                }

                return true;
            }
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