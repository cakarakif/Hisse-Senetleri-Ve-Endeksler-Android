package com.example.imkbhissesenetleriveendeksler.ui.hisse_endeksler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imkbhissesenetleriveendeksler.Models.Result;
import com.example.imkbhissesenetleriveendeksler.R;
import com.example.imkbhissesenetleriveendeksler.RestApi.ManagerAll;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hisseDetay extends AppCompatActivity {
    private TextView  sembol,  fiyat,  fark,  hacim,  alis,  satis,  degisim,  günlük_düsük,  günlük_yüksek,  adet,  tavan,  taban;
    private Button takip;
    private ArrayList<Result> liste;
    private double fiyatNum;
    private String sembolIsim;

    //
    private LineChart lineChart;
    private LineData lineData;
    int upval[]=new int[30];//aylık gösterim için değişkenler

    static public List<String> addedList = new ArrayList<>();
    boolean ans ;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hisse_detay);
        tanimla();
        //istek();//şuanlık kapalı gerçek veri için düzenlenebilir.
        atama(liste);//bu kapatılır istek() çalışacağı zaman
        showChart();
        ListemeEkle();
    }

    public void tanimla() {
        sembol = (TextView) findViewById(R.id.smbl);
        fiyat = (TextView) findViewById(R.id.fyt);
        fark = (TextView) findViewById(R.id.frk);
        hacim = (TextView) findViewById(R.id.hcm);
        alis = (TextView) findViewById(R.id.als);
        satis = (TextView) findViewById(R.id.sts);
        degisim = (TextView) findViewById(R.id.dgsm);
        günlük_düsük = (TextView) findViewById(R.id.gnlk_dsk);
        günlük_yüksek = (TextView) findViewById(R.id.gnlk_yksk);
        adet = (TextView) findViewById(R.id.adt);
        tavan = (TextView) findViewById(R.id.tvn);
        taban = (TextView) findViewById(R.id.tbn);
        takip = (Button) findViewById(R.id.takip);


    }

    public void atama(ArrayList<Result> list) {
        Intent getinfo= getIntent();
        Bundle info = getinfo.getExtras();
        fiyatNum=(double)(info.get("fiyat"));

        sembolIsim=info.getString("sembol");
        sembol.setText(info.getString("sembol"));
        fiyat.setText("" + info.get("fiyat"));
        fark.setText("" +info.get("fark"));
        hacim.setText("" +info.get("hacim"));
        alis.setText("" + info.get("alis"));
        satis.setText("" + info.get("satis"));
        degisim.setText(info.getString("degisim"));
        günlük_düsük.setText("" +new DecimalFormat("##.##").format(fiyatNum-(0.6)));
        günlük_yüksek.setText("" +new DecimalFormat("##.##").format(fiyatNum-(0.4)));
        Random r = new Random();
        adet.setText("" + (int)(1000 + 9999* r.nextDouble()));
        tavan.setText("" +new DecimalFormat("##.##").format(fiyatNum+(0.74)));
        taban.setText("" +new DecimalFormat("##.##").format(fiyatNum-(0.45)));
        ans= addedList.contains(sembolIsim);

        if(ans){
            takip.setText("LİSTEMDEN ÇIKAR");
        }else{
            takip.setText("LİSTEME EKLE");
        }
    }


    public void istek() {
        liste = new ArrayList<>();
        Call<ArrayList<Result>> call = ManagerAll.getIntance().managerGetResult();
        call.enqueue(new Callback<ArrayList<Result>>() {
            @Override
            public void onResponse(Call<ArrayList<Result>> call, Response<ArrayList<Result>> response) {
                if (response.isSuccessful()) {
                    liste = response.body();
                    atama(liste);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Result>> call, Throwable t) {

            }
        });
    }

    public void showChart(){//Grafiksel Gösterim

        lineChart=(LineChart)findViewById(R.id.chart);
        lineData=new LineData(getXvalues(),getLineDataValues());
        lineChart.setData(lineData);
        lineChart.setDescription("Hisse Detayı");
        lineChart.animateY(3000);
        lineChart.invalidate();
    }

    private List<ILineDataSet> getLineDataValues(){//Chart için gerekli ek fonksiyon
        ArrayList<ILineDataSet> lineDataSets=null;

        ArrayList<Entry> upvalList=new ArrayList<>();
        Random r = new Random();
        for (int i=0;i< upval.length;i++){
            upvalList.add(new Entry((float)(1 + 10* r.nextDouble()),i));
        }

        LineDataSet lineDataSet1=new LineDataSet(upvalList,"Aylık Değerler");
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setCircleColor(Color.BLACK);

        lineDataSets=new ArrayList<>();
        lineDataSets.add(lineDataSet1);

        return lineDataSets;
    }

    private List<String> getXvalues(){//Chart için gerekli ek fonksiyon

        ArrayList<String> xvalues=new ArrayList<>();

        for(int i = 1 ; i <= upval.length ; i++){
            xvalues.add("DAY"+i);
        }
        return xvalues;
    }

    public void ListemeEkle(){
        takip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean ans = addedList.contains(sembolIsim);

                if(ans){
                    addedList.remove(new String(sembolIsim));
                    takip.setText("LİSTEME EKLE");
                    Toast.makeText(getApplicationContext(),"Listemden Çıkarıldı!",Toast.LENGTH_LONG).show();
                }else{
                    addedList.add(sembolIsim);
                    takip.setText("LİSTEMDEN ÇIKAR");
                    Toast.makeText(getApplicationContext(),"Listeme Eklendi!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
