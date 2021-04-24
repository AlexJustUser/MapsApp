package com.example.testapp.manager.api;

import com.example.testapp.manager.api.model.DataResponse;
import com.example.testapp.manager.api.model.LoginResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("/test/auth.cgi")
    Observable<LoginResponse> checkAuth(@Query("username") String username, @Query("password") String password);

    @GET("/test/data.cgi")
    Observable<DataResponse> getMapsList(@Query("code") String code, @Query("p") String pageNum);

}
