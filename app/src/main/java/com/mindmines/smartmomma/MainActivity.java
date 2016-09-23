package com.mindmines.smartmomma;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindmines.smartmomma.Adapters.MainCustomAdapter;
import com.mindmines.smartmomma.Adapters.SearchAdapter;
import com.mindmines.smartmomma.Database.CategoryRepo;
import com.mindmines.smartmomma.Database.DataRepo;
import com.mindmines.smartmomma.Dataclass.Cate;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    GridView girdView;
    ArrayList<String> sarrayList;
    TextView textView;
    SearchAdapter arrayAdapter;
    ListView listView;
    android.widget.Filter filter;
    ArrayList<Cate> cateArrayList;
    SearchView search;
    TabLayout tabLayout;
    ImageView imageView;

    int[] images={R.drawable.ic_dairy,R.drawable.ic_dessert,R.drawable.ic_drinks,R.drawable.ic_fishandseafood,
            R.drawable.ic_fruitsandveggies,R.drawable.ic_grainandbeans,R.drawable.ic_meatanddeli,R.drawable.ic_poultryandeggs};
    String[] names={"Dairy","Desserts","Drinks","Fish/Seafood","Fruits/Vegetables","Beans/Grains","Meat/Deli","Poultry/Eggs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences= getSharedPreferences("disclaimer",MODE_PRIVATE);
        boolean showdosclaimer=sharedPreferences.getBoolean("dbol",true);
        if(showdosclaimer){
        Intent intent1 = new Intent(this,DiscliamerActivity.class);
        startActivity(intent1);}
        //


        setContentView(R.layout.activity_main);
        setTitle("");
        imageView = (ImageView) findViewById(R.id.main_activity_image);
        textView=(TextView)findViewById(R.id.main_activity_text);

        //adding data to catearraylist
        cateArrayList = new ArrayList<>();
        for(int i=0;i<names.length;i++)
        {Cate cate = new Cate(images[i],names[i]);
            cateArrayList.add(cate);
        }


         //
        girdView = (GridView) findViewById(R.id.main_activity_gridview);
        MainCustomAdapter mainCustomAdapter = new MainCustomAdapter(cateArrayList,MainActivity.this);
        girdView.setAdapter(mainCustomAdapter);
       girdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent indes =new Intent(MainActivity.this,DescriptionActivity.class);
               indes.putExtra("categoryname",cateArrayList.get(position).getName());
               startActivity(indes);

           }
       });
          //for search
        sarrayList= new ArrayList<>();
        sarrayList=new CategoryRepo(getApplicationContext()).getCategoryList();
        if(sarrayList!=null){
        arrayAdapter = new SearchAdapter(sarrayList,MainActivity.this);
        listView= (ListView) findViewById(R.id.mainactivity_listview);
        listView.setAdapter(arrayAdapter);}

        //  listView.setTextFilterEnabled(false);

       //for tabs
       tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setScrollPosition(1,0,true);

       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
            int i=tab.getPosition();
               if(i==1){
                   Intent in = new Intent(MainActivity.this,MainActivity.class);
                   startActivity(in);

               }

           if(i==2){
               Intent in = new Intent(MainActivity.this,UpgardeActivity.class);
               startActivity(in);

           }
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });


    }



    public boolean isInternetAvail() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        //search.setSubmitButtonEnabled(true);
        search.setIconifiedByDefault(false);
       // search.setQuery("india",false);
        search.setQueryHint("Search");
        search.setOnQueryTextListener(this);

        final MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.collapseActionView();

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                listView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                girdView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Write your code here
                listView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                girdView.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


                return true;
            }
        });

        return true;
    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {


    arrayAdapter.getFilter().filter(newText);
    listView.setVisibility(View.VISIBLE);
    imageView.setVisibility(View.GONE);
    girdView.setVisibility(View.GONE);
    textView.setVisibility(View.GONE);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
         System.exit(0);
    }
}