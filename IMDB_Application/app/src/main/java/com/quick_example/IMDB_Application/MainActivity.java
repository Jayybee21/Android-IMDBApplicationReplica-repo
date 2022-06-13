package com.quick_example.IMDB_Application;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView NavView;
    Fragment fr = null;
    // private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";


    // Navigation Bar with cases to navigate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDisplay, new menu_fragment()).commit();
        NavView.setSelectedItemId(R.id.Mymenu);
        NavView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.Mymenu:
                    fr = new menu_fragment();
                    break;

                case R.id.category:
                    fr = new category_fragment();
                    break;

                case R.id.video:
                    fr = new video_fragment();
                    break;
                case R.id.saved:
                    fr = new saved_fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDisplay, fr).commit();
            return true;
        });

    }

}