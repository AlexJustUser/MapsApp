package com.example.testapp.manager.api.model;

import java.util.List;

public class DataResponse {
    private String status;
    private String page;
    private List<MapResponse> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<MapResponse> getData() {
        return data;
    }

    public void setData(List<MapResponse> data) {
        this.data = data;
    }
}
