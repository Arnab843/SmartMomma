package com.mindmines.smartmomma;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindmines.smartmomma.Database.CategoryRepo;

public class DescriptionActivity extends AppCompatActivity {
 ImageView imageView;
 TextView title,des;
 TabLayout tabLayout;
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
        des.setText(new CategoryRepo(DescriptionActivity.this).getCategoryDes(catetiltle));

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
            case "Dessets":
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

        tabLayout= (TabLayout) findViewById(R.id.tab_layout_des);


        tabLayout.setSelected(false);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i=tab.getPosition();
                if(i==0){
                    Intent in = new Intent(DescriptionActivity.this,MainActivity.class);
                    startActivity(in);
                }
                if(i==1){}
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
}
