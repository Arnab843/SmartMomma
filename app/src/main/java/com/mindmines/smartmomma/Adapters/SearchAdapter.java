package com.mindmines.smartmomma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.mindmines.smartmomma.DescriptionActivity;
import com.mindmines.smartmomma.MainActivity;
import com.mindmines.smartmomma.R;

import java.util.ArrayList;

/**
 * Created by pc on 9/17/2016.
 */

public class SearchAdapter extends BaseAdapter implements Filterable{
   ArrayList<String> arrayList;
   ArrayList<String> filterlist;
   Context c;
    CustomFilter filter;
    String s;



    public SearchAdapter(ArrayList<String> arrayList, Context c) {
        this.arrayList = arrayList;
        this.c = c;
        this.filterlist=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return  arrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         s =arrayList.get(position);

        LayoutInflater inflater =LayoutInflater.from(c);
        convertView= inflater.inflate(R.layout.listview_search,null);

        TextView tv = (TextView) convertView.findViewById(R.id.listview_search_textview);
        tv.setText(s);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(!s.equals("not found")){
                Intent indes =new Intent(c,DescriptionActivity.class);
                indes.putExtra("categoryname",arrayList.get(position));
                indes.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(indes);}
            }
        });
        return convertView;
    }


    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        if(filter == null)
        {
            filter=new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint != null && constraint.length()>0){
                constraint=constraint.toString().toUpperCase();
                ArrayList<String> filters=new ArrayList<String>();

                for(int i=0;i<filterlist.size();i++){
                    if(filterlist.get(i).toUpperCase().contains(constraint)){
                   String p= filterlist.get(i);

                        filters.add(p);
                }

                }
                results.count=filters.size();
                results.values=filters;
            }
            else
            {
                results.count=filterlist.size();
                results.values=filterlist;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
         arrayList= (ArrayList<String>) results.values;
            if(arrayList.isEmpty()){
                arrayList.add("not found");
            }
         notifyDataSetChanged();
        }
    }
}
