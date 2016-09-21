package com.mindmines.smartmomma;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mindmines.smartmomma.Adapters.SearchAdapter;
import com.mindmines.smartmomma.Database.CategoryRepo;

import java.util.ArrayList;

public class DescriptionActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ImageView imageView;
    TextView title,des;
    TabLayout tabLayout;
    SearchAdapter arrayAdapter;
    SearchView search;
    ArrayList<String> sarrayList;
    ListView listView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

       Intent in =getIntent();
        Bundle bundle= in.getExtras();
        String catetiltle=bundle.getString("categoryname");


        title= (TextView) findViewById(R.id.activity_description_title);
        title.setText(catetiltle);

        des= (TextView) findViewById(R.id.activity_description_des);

        String s=android.text.Html.fromHtml(new CategoryRepo(DescriptionActivity.this).getCategoryDes(catetiltle)).toString();
        des.setText(s);

        imageView= (ImageView) findViewById(R.id.activity_description_imageview);
        switch (catetiltle){
            case "Meat/Deli":
                imageView.setImageResource(R.drawable.rmeatdeli);
                break;
            case "Dairy":
                imageView.setImageResource(R.drawable.rdairy);
                break;
            case "Drinks":
                imageView.setImageResource(R.drawable.rdrinks);
                break;
            case "Desserts":
                imageView.setImageResource(R.drawable.rdesserts);
                break;
            case "Fruits/Vegetables":
                imageView.setImageResource(R.drawable.rfruitsveggies);
                break;
            case "Beans/Grains":
                imageView.setImageResource(R.drawable.rgrainsbeans);
                break;
            case "Fish/Seafood":
                imageView.setImageResource(R.drawable.rfishseafood);
                break;
            case "Poultry/Eggs":
                imageView.setImageResource(R.drawable.rpoultryeggs);
                break;
        }


        sarrayList= new ArrayList<>();
        sarrayList=new CategoryRepo(getApplicationContext()).getCategoryList();
        if(sarrayList!=null){
            arrayAdapter = new SearchAdapter(sarrayList,DescriptionActivity.this);
            listView= (ListView) findViewById(R.id.activity_description_listview);
            listView.setAdapter(arrayAdapter);}
        linearLayout= (LinearLayout) findViewById(R.id.activity_description_linearlayout);
        tabLayout= (TabLayout) findViewById(R.id.tab_layout_des);


        tabLayout.setScrollPosition(1,0,true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i=tab.getPosition();
                if(i==1){
                    Intent in = new Intent(DescriptionActivity.this,MainActivity.class);
                    startActivity(in);
                }
                if(i==0){}
                if(i==2){
                    Intent in = new Intent(DescriptionActivity.this,UpgardeActivity.class);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        //search.setSubmitButtonEnabled(true);
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        final MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.collapseActionView();

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                listView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Write your code here
                listView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


                return false;
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
        linearLayout.setVisibility(View.GONE);


        return true;
    }
}
