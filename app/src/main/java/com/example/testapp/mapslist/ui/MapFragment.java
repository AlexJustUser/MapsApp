package com.example.testapp.mapslist.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.testapp.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    private View view;
    private String id;
    private String country;
    private String name;
    private String lat;
    private String lon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.idMapPoint.setText(id);
        binding.countryMapPoint.setText(country);
        binding.nameMapPoint.setText(name);
//        binding.mapPoint.set
    }

    public void setIdMapPoint(String id) {
        this.id = id;
    }

    public void setCountryMapPoint(String country) {
        this.country = country;
    }

    public void setNameMapPoint(String name) {
        this.name = name;
    }

    public void setLatMapPoint(String lat) {
        this.lat = lat;
    }

    public void setLonMapPoint(String lon) {
        this.lon = lon;
    }
}