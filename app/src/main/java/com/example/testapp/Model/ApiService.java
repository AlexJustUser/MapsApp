package com.example.testapp.Model;

import com.example.testapp.AccessStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("/test/auth.cgi")
    Observable<List<AccessStatus>> checkAuth(@Query("username") String username, @Query("password") String password);

}
