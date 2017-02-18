package com.example.kennethlopez.eventbussample;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/users/{username}")
    Call<User> getUser(
            @Path("username") String username
    );
}
