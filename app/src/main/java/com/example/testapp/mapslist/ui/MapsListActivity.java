package com.example.testapp.mapslist.ui;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapp.api.model.DataResponse;
import com.example.testapp.api.model.MapResponse;
import com.example.testapp.databinding.ActivityMapsListBinding;
import com.example.testapp.mapslist.presenter.IMapsListPresenter;
import com.example.testapp.mapslist.presenter.MapsListPresenter;
import java.util.List;

public class MapsListActivity extends AppCompatActivity implements MapsListView, MyRecyclerViewAdapter.ItemClickListener {

    private ActivityMapsListBinding binding;
    private IMapsListPresenter presenter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private MyRecyclerViewAdapter adapter;
    private List<MapResponse> mapsList;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

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
                loadNextDataFromApi(page+1);
            }
        };
        binding.listOfMaps.addOnScrollListener(scrollListener);

        presenter.getMapsList("1");
    }

    public void loadNextDataFromApi(int offset) {
        presenter.getMapsList(String.valueOf(offset));
    }

    @Override
    public void displayMapsList(DataResponse dataResponse){
        if(dataResponse.getPage().equals("1")){
            mapsList = dataResponse.getData();
            myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, mapsList);
            adapter =  myRecyclerViewAdapter;
            adapter.setClickListener(this);
            binding.listOfMaps.setAdapter(adapter);
        }
        else{
            for(MapResponse map: dataResponse.getData()){
                mapsList.add(map);
            }
            myRecyclerViewAdapter.updateData(mapsList);
            adapter.notifyItemInserted(mapsList.size()-dataResponse.getData().size());
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        adapter.getItem(position).
    }

}
