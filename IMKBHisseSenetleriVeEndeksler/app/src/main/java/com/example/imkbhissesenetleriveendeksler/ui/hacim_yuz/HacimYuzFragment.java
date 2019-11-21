package com.example.imkbhissesenetleriveendeksler.ui.hacim_yuz;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.imkbhissesenetleriveendeksler.Adapters.BourseAdapter;
import com.example.imkbhissesenetleriveendeksler.Models.Bourse;
import com.example.imkbhissesenetleriveendeksler.R;
import com.example.imkbhissesenetleriveendeksler.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.Collections;

public class HacimYuzFragment extends Fragment {

    View root;

    BourseAdapter adp;
    ArrayList<Bourse> list,temp;
    ListView listView;
    EditText search;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_hisse_endeksler, container, false);
        tanimla();
        istek();
        ara();

        return root;
    }

    public void tanimla() {
        listView = (ListView) root.findViewById(R.id.list_view);
        search=(EditText)root.findViewById(R.id.searchFilter);
    }

    public void istek() {//Server'dan Json verisi alınıp işlendi.
        list = new ArrayList<>();
        temp= new ArrayList<>();
        Call<ArrayList<Bourse>> call = ManagerAll.getIntance().getirCall();
        call.enqueue(new Callback<ArrayList<Bourse>>() {
            @Override
            public void onResponse(Call<ArrayList<Bourse>> call, Response<ArrayList<Bourse>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    Collections.sort(list); //array list'e hacime göre sıralama işlemi yapıldı.

                    for(int i = 0; i < 100; i++){
                        temp.add(list.get(i));
                    }

                    adp = new BourseAdapter(temp, getActivity().getBaseContext(), getActivity());
                    listView.setAdapter(adp);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Bourse>> call, Throwable t) {

            }
        });
    }

    public void ara(){// arama işlemi için fonksiyon
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adp.getFilter().filter(charSequence);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}