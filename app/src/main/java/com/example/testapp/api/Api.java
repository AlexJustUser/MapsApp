package com.example.testapp.api;

import com.example.testapp.api.model.LoginResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Api {

    @GET("/test/auth.cgi")
    Observable<LoginResponse> checkAuth(@Query("username") String username, @Query("password") String password);

}
