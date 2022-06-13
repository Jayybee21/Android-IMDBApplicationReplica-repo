package com.quick_example.IMDB_Application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class savedMovies_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_movies_details);

        //We retrieve all the saved information from the SQLite DB that we saved and sent via intent
        String intentRelease = getIntent().getStringExtra("savedRelease");
        String intentTitle = getIntent().getStringExtra("savedTitle");
        String intentDescription = getIntent().getStringExtra("savedDescription");
        String intentRating = getIntent().getStringExtra("savedRating");
        String intentTrailer = getIntent().getStringExtra("savedTrailer");
        String intentImage = getIntent().getStringExtra("savedImage");
        String intentKeywords = getIntent().getStringExtra("savedKeywords");

        //We call the TextViews and Image View to be able to display the info from the intent
        ImageView img_savedMovie = (ImageView) findViewById(R.id.saved_imgDisplay);
        TextView txt_release = (TextView)findViewById(R.id.saved_contentDate);
        TextView txt_title = (TextView)findViewById(R.id.saved_titleDisplay);
        TextView txt_rating = (TextView)findViewById(R.id.saved_contentRating);
        TextView txt_description = (TextView)findViewById(R.id.saved_contentSummary);
        TextView txt_trailer = (TextView)findViewById(R.id.saved_trailer);
        TextView txt_keywords = (TextView)findViewById(R.id.saved_keywords);

        //We set the corresponding texts and image that we retrieved from our intents
        txt_title.setText(intentTitle);
        txt_release.setText(intentRelease);
        txt_rating.setText(intentRating);
        txt_description.setText(intentDescription);
        txt_trailer.setText(intentTrailer);
        txt_keywords.setText(intentKeywords);

        //We use picasso as we aren't saving the images on our local device
        //We are just retrieving them from the Web

        Picasso.get().load(intentImage).into(img_savedMovie);

        //We call the SQLite Query class for the button remove

        SQLite_Queries removeQuery = new SQLite_Queries(this);

        //Now, we call the button delete so that the user can remove the movie from his saved list
        Button btnRemoveFromList = (Button)findViewById(R.id.btnRemove);
        btnRemoveFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeQuery.removeFromDB(txt_title.getText() + "");
               toastMessage("Removed from saved list !");
            }
        });

    }

    //As we did in other classes, we have created a Toast method that lets us create as
    //many as we want with ease k=just by calling the method !
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}