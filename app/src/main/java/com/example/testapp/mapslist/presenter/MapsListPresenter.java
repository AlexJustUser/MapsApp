package com.example.testapp.mapslist.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.testapp.R;
import com.example.testapp.manager.api.ApiManager;
import com.example.testapp.manager.api.model.DataResponse;
import com.example.testapp.mapslist.ui.MapsListView;
import com.example.testapp.sharedpreferences.SharedPreferencesManager;

public class MapsListPresenter implements IMapsListPresenter {
    private ApiManager apiManager;
    private MapsListView view;
    private Context context;
    private SharedPreferencesManager sharedPreferencesManager;

    public MapsListPresenter(MapsListView view, Context context) {
        this.apiManager = new ApiManager();
        this.view = view;
        this.context = context;
        sharedPreferencesManager = new SharedPreferencesManager(context);
    }

    @Override
    public void getMapsList(int pageNum){
        apiManager.getMapsList(sharedPreferencesManager.getSharePrefAttr(context.getString(R.string.code)), pageNum, this);
    }

    @Override
    public void displayMapsList(DataResponse dataResponse){
        view.displayMapsList(dataResponse);
    }

}
