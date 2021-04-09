package com.example.testapp.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/test/auth.cgi")
    Call<String> checkAuth(@Query("username") String username, @Query("password") String password);

}
