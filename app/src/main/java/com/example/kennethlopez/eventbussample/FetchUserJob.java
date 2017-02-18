package com.example.kennethlopez.eventbussample;


import android.os.AsyncTask;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FetchUserJob extends AsyncTask<Void, Void, Void> {

    private String mUsername;

    public FetchUserJob(String username) {
        mUsername = username;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        try {
            Response<User> response = service.getUser(mUsername).execute();

            EventBus.getDefault().postSticky(new UserEvent(response.body()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}