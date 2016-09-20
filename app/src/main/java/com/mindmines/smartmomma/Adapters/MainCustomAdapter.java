package com.mindmines.smartmomma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindmines.smartmomma.Dataclass.Cate;
import com.mindmines.smartmomma.R;

import java.util.ArrayList;


public class MainCustomAdapter extends BaseAdapter {
Context c;
ArrayList<Cate> arrayList;

    public MainCustomAdapter(ArrayList<Cate> arrayList, Context c) {
        this.arrayList = arrayList;
        this.c = c;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Cate cate = new Cate();
        cate =arrayList.get(position);
         int img=cate.getImage();
           String txt=cate.getName();

        LayoutInflater inflater =LayoutInflater.from(c);
        convertView= inflater.inflate(R.layout.cardviewmain_layout,null);

        TextView tv = (TextView) convertView.findViewById(R.id.main_activity_carddview_textview);
        ImageView iv=(ImageView)convertView.findViewById(R.id.main_activity_cardview_iamgeview);

        iv.setImageResource(img);
        tv.setText(txt);

        return convertView;
    }
}
