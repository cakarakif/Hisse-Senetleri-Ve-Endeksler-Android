package com.example.imkbhissesenetleriveendeksler.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.imkbhissesenetleriveendeksler.R;
import com.example.imkbhissesenetleriveendeksler.ui.hisse_endeksler.hisseDetay;
import com.example.imkbhissesenetleriveendeksler.Models.Bourse;

import java.util.ArrayList;

public class BourseAdapter extends BaseAdapter{

    private ArrayList<Bourse> bourseList,temp;
    private Context context;
    private Activity activity ;
    CustomFilter cs;
    private String flag;
    private Boolean key;

    public BourseAdapter(ArrayList<Bourse> bourseList, Context context , Activity activity) {
        this.bourseList = bourseList;
        this.context = context;
        this.activity = activity;
        this.temp= bourseList;
        flag="";
        key=true;
    }

    @Override
    public int getCount() {
        return bourseList.size();
    }

    @Override
    public Object getItem(int position) {
        return bourseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);

        LinearLayout layoutList ;
        layoutList = convertView.findViewById(R.id.layaoutList);

        TextView sembol,fiyat, fark, hacim, alis, satis, degisim;
        sembol = (TextView) convertView.findViewById(R.id.sembol);
        fiyat = (TextView) convertView.findViewById(R.id.fiyat);
        fark = (TextView) convertView.findViewById(R.id.fark);
        hacim = (TextView) convertView.findViewById(R.id.hacim);
        alis = (TextView) convertView.findViewById(R.id.alis);
        satis = (TextView) convertView.findViewById(R.id.satis);
        degisim = (TextView) convertView.findViewById(R.id.degisim);

        sembol.setText(bourseList.get(position).getSembol());
        fiyat.setText("" + bourseList.get(position).getFiyat());
        fark.setText("" + bourseList.get(position).getFark());
        hacim.setText("" + bourseList.get(position).getHacim());
        alis.setText("" + bourseList.get(position).getAlis());
        satis.setText("" + bourseList.get(position).getSatis());
        degisim.setText(bourseList.get(position).getDegisim());

        if(bourseList.get(position).getDegisim().equals("+"))
            convertView.setBackgroundColor(Color.argb(255, 0, 200, 0));
        else
            convertView.setBackgroundColor(Color.argb(255, 200, 0, 0));


        layoutList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, hisseDetay.class);
                    intent.putExtra("sembol",bourseList.get(position).getSembol());
                    intent.putExtra("fiyat",bourseList.get(position).getFiyat());
                    intent.putExtra("degisim",bourseList.get(position).getDegisim());
                    intent.putExtra("fark",bourseList.get(position).getFark());
                    intent.putExtra("hacim",bourseList.get(position).getHacim());
                    intent.putExtra("alis",bourseList.get(position).getAlis());
                    intent.putExtra("satis",bourseList.get(position).getSatis());
                    activity.startActivity(intent);
                }
            });



        return convertView;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Filter getFilter(){
        if(cs == null){
            cs = new CustomFilter();
        }
        return cs;
    }

    class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();

            if(constraint != null && constraint.length()>0){
                constraint = constraint.toString().toUpperCase();

                ArrayList<Bourse> filters=new ArrayList<>();
                for (int i=0; i<temp.size();i++){
                  if(temp.get(i).getSembol().toUpperCase().contains((constraint))){
                      Bourse bourse = new Bourse(temp.get(i).getSembol(),temp.get(i).getFiyat(),temp.get(i).getFark(),temp.get(i).getHacim(),temp.get(i).getAlis(),temp.get(i).getSatis(),temp.get(i).getDegisim());
                      filters.add(bourse);
                  }
                }
                results.count=filters.size();
                results.values=filters;
            }else{
                results.count=temp.size();
                results.values=temp;
            }



            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            bourseList =(ArrayList<Bourse>)filterResults.values;
            notifyDataSetChanged();
        }
    }

}
