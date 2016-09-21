package com.mindmines.smartmomma;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class UpgardeActivity extends AppCompatActivity {
    TabHost tabHost;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgarde);

        tabLayout= (TabLayout) findViewById(R.id.tab_layout_up);



        tabLayout.setScrollPosition(2,0,true);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i=tab.getPosition();

                switch (i){
                    case 1:
                        Intent in = new Intent(UpgardeActivity.this,MainActivity.class);
                        startActivity(in);
                        break;
                    case 2:
                        Intent in2 = new Intent(UpgardeActivity.this,UpgardeActivity.class);
                        startActivity(in2);
                        break;

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



}

