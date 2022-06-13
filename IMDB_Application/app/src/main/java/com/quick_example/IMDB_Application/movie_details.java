package com.quick_example.IMDB_Application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class movie_details extends AppCompatActivity {

    SQLite_Queries sqlrun;
    String retrieve_image;

    // API from the id taken from intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        String imgURL;
        String key = "8988800409msh25b5be8fcfdd627p182fc9jsnc17c7a1cd2b1";
        TextView res_Header = (TextView)findViewById(R.id.movie_titleDisplay);
        String intentID = getIntent().getStringExtra("theID");
        String intentID2 = getIntent().getStringExtra("theID2");
        String url = null;

        // intent for menu
        if(intentID != null){
            url = "https://data-imdb1.p.rapidapi.com/movie/id/" + intentID + "/";
        }

        // intent for movies by id
        if(intentID2 != null){
            url = "https://data-imdb1.p.rapidapi.com/movie/id/" + intentID2+ "/";
        }

        // API Request
        CustomStringRequest csrDetails = new CustomStringRequest(Request.Method.GET, url, key, onSuccess, onFailed);

        try {
            VolleySingleton volleySingleton = null;
            volleySingleton = VolleySingleton.getInstance(this.getApplicationContext());
            RequestQueue queue = volleySingleton.getRequestInstance();
            queue.add(csrDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //to save in My List

        sqlrun = new SQLite_Queries(this);
        Button btnSaveToDatabase = (Button)findViewById(R.id.btnAdd);
        btnSaveToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView title = (TextView)findViewById(R.id.movie_titleDisplay);
                TextView release = (TextView)findViewById(R.id.movie_contentDate);
                TextView rating = (TextView)findViewById(R.id.movie_contentRating);
                TextView description = (TextView)findViewById(R.id.movie_contentSummary);
                TextView trailer = (TextView)findViewById(R.id.movie_trailer);
                TextView keywords = (TextView)findViewById(R.id.movie_keywords);



                String strImg,strTitle,strRelease,strRating,strDescription,strTrailer,strKeywords;
                strImg = retrieve_image;
                strTitle = title.getText().toString();
                strRelease = release.getText().toString();
                strRating = rating.getText().toString();
                strDescription = description.getText().toString();
                strTrailer = trailer.getText().toString();
                strKeywords = keywords.getText().toString();

                String queryChecker;
                queryChecker = sqlrun.checkFromDB(strTitle);
                if (queryChecker == null){
                    sqlrun.addData(intentID,strRelease,strTitle,strDescription,strRating,strTrailer,strImg,strKeywords);
                    toastMessage("Movie Saved!");

                }
                else {
                    toastMessage("Already Saved On Device !");
                }
            }
        });
    }

    Response.ErrorListener onFailed = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley2", error.getMessage());
        }
    };
    Response.Listener<String> onSuccess = new Response.Listener<String>() {
        //Getting Info from API

        @Override
        public void onResponse(String response) {


            ImageView img2 = (ImageView)findViewById(R.id.movie_imgDisplay);
            TextView title2 = (TextView)findViewById(R.id.movie_titleDisplay);
            TextView release2 = (TextView)findViewById(R.id.movie_contentDate);
            TextView rating2 = (TextView)findViewById(R.id.movie_contentRating);
            TextView description2 = (TextView)findViewById(R.id.movie_contentSummary);
            TextView trailer2 = (TextView)findViewById(R.id.movie_trailer);
            TextView keywords2 = (TextView)findViewById(R.id.movie_keywords);

            JSONObject jsonObject2 = null;
            JSONObject results3 = null;
            JSONArray keywords = null;

            try {
                jsonObject2 = new JSONObject(response);
                results3 = jsonObject2.getJSONObject("results");
                String jso_title = "" + results3.getString("title");
                String jso_release = "" + results3.getString("release");
                String jso_rating = "" + results3.getString("rating");
                String jso_trailer = "" + results3.getString("trailer");
                String jso_image = "" + results3.getString("image_url");

                retrieve_image = jso_image;
                String jso_description = "" + results3.getString("description");
                title2.setText(jso_title);
                release2.setText(jso_release);
                rating2.setText(jso_rating);
                trailer2.setText(jso_trailer);
                description2.setText(jso_description);
                keywords = results3.getJSONArray("keywords");

                JSONObject finalResults = results3;
                trailer2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent google = new Intent(Intent.ACTION_VIEW);
                        try {
                            google.setData(Uri.parse(finalResults.getString("trailer")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(google);
                    }
                });

                // keywords
                String k = null;
                for(int i = 0; i< keywords.length(); i++){
                    JSONObject keyword = keywords.getJSONObject(i);
                    k += keyword.getString("keyword") + " ";
                }
                keywords2.setText(k);

                Picasso.get().load(jso_image).into(img2);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }};
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}