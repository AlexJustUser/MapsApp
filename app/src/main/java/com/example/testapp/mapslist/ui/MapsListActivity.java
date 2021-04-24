package com.example.testapp.mapslist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapp.manager.api.model.DataResponse;
import com.example.testapp.manager.api.model.MapResponse;
import com.example.testapp.databinding.ActivityMapsListBinding;
import com.example.testapp.map.ui.MapActivity;
import com.example.testapp.mapslist.presenter.IMapsListPresenter;
import com.example.testapp.mapslist.presenter.MapsListPresenter;
import java.util.List;

public class MapsListActivity extends AppCompatActivity implements MapsListView, MyRecyclerViewAdapter.ItemClickListener {

    private ActivityMapsListBinding binding;
    private IMapsListPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private MyRecyclerViewAdapter adapter;
    private List<MapResponse> mapsList;
    private Intent intent;

    private static final int DEFAULT_PAGE_NUMBER  = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    public void init() {
        presenter = new MapsListPresenter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.listOfMaps.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(++page);
            }
        };
        binding.listOfMaps.addOnScrollListener(scrollListener);

        presenter.getMapsList(DEFAULT_PAGE_NUMBER);
    }

    public void loadNextDataFromApi(int pageNum) {
        presenter.getMapsList(pageNum);
    }

    @Override
    public void displayMapsList(DataResponse dataResponse){
        if(dataResponse.getPage().equals("1")){
            mapsList = dataResponse.getData();
            adapter = new MyRecyclerViewAdapter(this, mapsList);
            adapter.setClickListener(this);
            binding.listOfMaps.setAdapter(adapter);
        }
        else{
            for(MapResponse map: dataResponse.getData()){
                mapsList.add(map);
            }
            adapter.updateData(mapsList);
            adapter.notifyItemInserted(mapsList.size()-dataResponse.getData().size());
        }
    }

    @Override
    public void onItemClick(View view, MapResponse item) {
        intent = new Intent(MapsListActivity.this, MapActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("name", item.getName());
        intent.putExtra("country", item.getCountry());
        intent.putExtra("lon", item.getLon());
        intent.putExtra("lat", item.getLat());
        startActivity(intent);
    }

}
