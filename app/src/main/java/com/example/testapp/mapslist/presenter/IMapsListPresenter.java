package com.example.testapp.mapslist.presenter;

import com.example.testapp.api.model.DataResponse;
import com.example.testapp.api.model.MapResponse;
import java.util.List;

public interface IMapsListPresenter {

    void getMapsList(String p);
    void displayMapsList(DataResponse dataRespons);
}
