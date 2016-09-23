package com.mindmines.smartmomma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mindmines.smartmomma.Background.Mytask;

import java.util.Date;

public class DiscliamerActivity extends AppCompatActivity {
Button agree,disagree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discliamer);

        agree= (Button) findViewById(R.id.disclaimer_agree_button);
        disagree= (Button) findViewById(R.id.disclaimer_disagree_button);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =getSharedPreferences("disclaimer",MODE_PRIVATE).edit();
                editor.putBoolean("dbol",false).commit();
                if (isInternetAvail()) {
                    Date date = new Date();
                    //  SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
                    //  String requiredurl=""+ft.format(date);
                    Mytask mytask = new Mytask(getApplicationContext());
                    mytask.execute("http://smartmommafoodfinderwebapi.azurewebsites.net/v1/food/getdataupdate?dataDateTime=2016-08-14T11:11:11");
                }
                else{
                    Toast.makeText(DiscliamerActivity.this,"Get internent connection",Toast.LENGTH_LONG).show();
                }

            }
        });

        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                startActivity(intent);
                finish();
                System.exit(0);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);

    }
}
