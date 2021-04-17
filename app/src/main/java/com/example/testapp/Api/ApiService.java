package com.example.testapp.Api;

import com.example.testapp.login.LoginResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("/test/auth.cgi")
    Observable<LoginResponse> checkAuth(@Query("username") String username, @Query("password") String password);

}
