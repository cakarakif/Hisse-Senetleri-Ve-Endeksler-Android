package com.example.imkbhissesenetleriveendeksler.RestApi;

import com.example.imkbhissesenetleriveendeksler.Models.Bourse;
import com.example.imkbhissesenetleriveendeksler.Models.Result;

import java.util.ArrayList;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getIntance() {
        return ourInstance;
    }


    public Call<ArrayList<Bourse>> getirCall() {
        Call<ArrayList<Bourse>> x = getRestApi().getir();
        return x;
    }

    public Call<ArrayList<Result>> managerGetResult() {
        Call<ArrayList<Result>> y = getRestApi().getirResult();

        return y;
    }
}
