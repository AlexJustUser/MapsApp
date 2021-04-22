package com.example.testapp.mapslist.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.testapp.R;
import com.example.testapp.api.ApiManager;
import com.example.testapp.api.model.DataResponse;
import com.example.testapp.api.model.MapResponse;
import com.example.testapp.mapslist.ui.MapsListView;
import java.util.List;

public class MapsListPresenter implements IMapsListPresenter {

    private ApiManager apiManager;
    private MapsListView view;
    private Context context;
    private SharedPreferences prefs;

    public MapsListPresenter(MapsListView view, Context context) {
        this.apiManager = new ApiManager();
        this.view = view;
        this.context = context;
        prefs = context.getSharedPreferences(context.getString(R.string.package_name), context.MODE_PRIVATE);
    }

    @Override
    public void getMapsList(String p){
        apiManager.getMapsList(prefs.getString(context.getString(R.string.code), ""), p, this);
    }

    @Override
    public void displayMapsList(DataResponse dataResponse){
        view.displayMapsList(dataResponse);
    }

}
