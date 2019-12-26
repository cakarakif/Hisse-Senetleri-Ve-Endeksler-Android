package com.example.imkbhissesenetleriveendeksler.ui.hisse_endeksler;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.imkbhissesenetleriveendeksler.Adapters.BourseAdapter;
import com.example.imkbhissesenetleriveendeksler.Models.Bourse;
import com.example.imkbhissesenetleriveendeksler.R;
import com.example.imkbhissesenetleriveendeksler.RestApi.ManagerAll;

import java.util.ArrayList;

public class HisseEndekslerFragment extends Fragment {

    View root;

    BourseAdapter adp;
    ArrayList<Bourse> list;
    ListView listView;
    EditText search;
    Button refresh;

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
        refresh=(Button)root.findViewById(R.id.refresh);
        refresh.setVisibility(View.INVISIBLE);

    }

    public void istek() { //Server'dan Json verisi alınıp işlendi.
        list = new ArrayList<>();
        Call<ArrayList<Bourse>> call = ManagerAll.getIntance().getirCall();
        call.enqueue(new Callback<ArrayList<Bourse>>() {
            @Override
            public void onResponse(Call<ArrayList<Bourse>> call, Response<ArrayList<Bourse>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    adp = new BourseAdapter(list, getActivity().getBaseContext(), getActivity());
                    adp.setFlag("HisseEndeksler");
                    listView.setAdapter(adp);
                }

            }
            @Override
            public void onFailure(Call<ArrayList<Bourse>> call, Throwable t) {
            }
        });
    }

    public void ara(){ // arama işlemi için fonksiyon
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