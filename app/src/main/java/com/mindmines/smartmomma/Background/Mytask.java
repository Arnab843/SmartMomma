package com.mindmines.smartmomma.Background;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.mindmines.smartmomma.Database.CategoryRepo;
import com.mindmines.smartmomma.Database.DataRepo;
import com.mindmines.smartmomma.Dataclass.Category;
import com.mindmines.smartmomma.Dataclass.Data;
import com.mindmines.smartmomma.DiscliamerActivity;
import com.mindmines.smartmomma.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by pc on 9/15/2016.
 */

public class Mytask extends AsyncTask<String,Void,String> {
    Context c;

    public Mytask(Context c) {
        this.c = c;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream is = conn.getInputStream();
            InputStreamReader bis= new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(bis);

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line).append('\n');
            }

            return  sb.toString();
        } catch (MalformedURLException e) {

        } catch (ProtocolException e) {
        } catch (IOException e) {

        } catch (Exception e) {

        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String prsensentdate = PreferenceManager.getDefaultSharedPreferences(c).getString("updatedate","");
        DataRepo dataRepo = new DataRepo(c);
        CategoryRepo categoryRepo = new CategoryRepo(c);
        if(s!=null){
            try{
                JSONObject m = new JSONObject(s);
                String udate=m.getString("ModificationDate");
                if(!udate.equalsIgnoreCase(prsensentdate)){

                    //for data
                    JSONArray n= m.getJSONArray("Data");
                    for(int i =0;i<n.length();i++ ){
                        JSONObject o = n.getJSONObject(i);
                        Data data = new Data();

                        //fetching all the data
                        String name=o.getString("Name");
                        String categoryid=o.getInt("CategoryId")+"";
                        String ratingid=o.getInt("RatingId")+"";
                        String shortdes=o.getString("ShortDescr");
                        String additional=o.getString("AdditionalInfo");
                        String reason=o.getString("Reasons");
                        JSONObject p=o.getJSONObject("Category");
                        String categoryname=p.getString("Name");
                        String categorydes=p.getString("Description");
                        JSONObject q=o.getJSONObject("Rating");
                        String ratingname=q.getString("Name");
                        String ratinglevel=q.getInt("Level")+"";

                        data.setName(name);
                        data.setCategoryid(categoryid);
                        data.setRatingid(ratingid);
                        data.setShortdes(shortdes);
                        data.setAdditional(additional);
                        data.setReasons(reason);
                        data.setCategoryname(categoryname);
                        data.setCategorydes(categorydes);
                        data.setRatingname(ratingname);
                        data.setRatinglevel(ratinglevel);

                        dataRepo.insertData(data);

                    }
                 //for category
                 JSONObject a= m.getJSONObject("ReferenceData");
                 JSONArray b=a.getJSONArray("Categories");
                 for(int x=0;x<b.length();x++){

                     JSONObject c= b.getJSONObject(x);
                     Category category = new Category();

                     String ccode=c.getString("Code")+"";
                     String cname=c.getString("Name");
                     String cdes=c.getString("Description");

                      category.setCode(ccode);
                      category.setName(cname);
                      category.setDescription(cdes);

                      categoryRepo.insertcatgory(category);
                 }

                   //SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(c).edit();
                   // editor.putString("updatedate","").commit();

                }

                Intent in = new Intent(c,MainActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(in);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
