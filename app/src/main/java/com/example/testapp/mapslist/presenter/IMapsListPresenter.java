package com.example.testapp.mapslist.presenter;

import com.example.testapp.manager.api.model.DataResponse;

public interface IMapsListPresenter {
    void getMapsList(int pageNum);
    void displayMapsList(DataResponse dataRespons);
}
