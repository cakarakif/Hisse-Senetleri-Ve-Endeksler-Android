package com.example.imkbhissesenetleriveendeksler.RestApi;

import com.example.imkbhissesenetleriveendeksler.Models.Bourse;
import com.example.imkbhissesenetleriveendeksler.Models.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("1dve1e")
    Call<ArrayList<Bourse>> getir();

    @GET("1c58du")
    Call<ArrayList<Result>> getirResult();
}
