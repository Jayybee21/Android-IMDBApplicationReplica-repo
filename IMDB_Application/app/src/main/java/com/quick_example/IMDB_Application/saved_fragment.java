package com.quick_example.IMDB_Application;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link saved_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class saved_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // variables
    String image;
    String rating;
    String title;
    String description;
    String releaseDate;
    String trailer;
    String keywords;
    SQLite_Queries sqTest;
    ListView listDatabase;
    ImageView imgList;
    Button btnDeleteAll;
    ArrayList<menu_skeleton> ar2 = new ArrayList<>();

    public saved_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static saved_fragment newInstance(String param1, String param2) {
        saved_fragment fragment = new saved_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_saved_fragment, container, false);
        listDatabase = (ListView)RootView.findViewById(R.id.listView_Saved);
        btnDeleteAll = (Button)RootView.findViewById(R.id.btnDeleteAllSaved);
        imgList = (ImageView) RootView.findViewById(R.id.imgListEmpty);
        sqTest = new SQLite_Queries(getContext());

        // with the help of sqlite query class we are reading values saved from the database and we are adding them to the arraylist
        ArrayList<menu_skeleton> dataDisplay = sqTest.readFromDB();

        for (int i = 0 ; i < dataDisplay.size() ; i++) {
            menu_skeleton ms = new menu_skeleton();
            Log.e("DATABASE :" , "" + dataDisplay.get(i).getMovie_title());
            ms.setMovie_image(dataDisplay.get(i).getMovie_image());
            ms.setMovie_title(dataDisplay.get(i).getMovie_title());
            image = dataDisplay.get(i).getMovie_image();
            rating = dataDisplay.get(i).getMovie_rating();
            title = dataDisplay.get(i).getMovie_title();
            releaseDate = dataDisplay.get(i).getMovie_release();
            description = dataDisplay.get(i).getMovie_description();
            trailer = dataDisplay.get(i).getMovie_trailer();
            keywords = dataDisplay.get(i).getMovie_keywords();
            ms.setMovie_image(image);
            ms.setMovie_rating(rating);
            ms.setMovie_title(title);
            ms.setMovie_release(releaseDate);
            ms.setMovie_description(description);
            ms.setMovie_trailer(trailer);
            ms.setMovie_keywords(keywords);
            ar2.add(ms);

        }
        return RootView;
        // Inflate the layout for this fragment
       }

    @Override
    public void onStart() {
        super.onStart();

        SavedAdapter ma =new SavedAdapter(getActivity(),ar2);
        listDatabase.setAdapter(ma);


        // we are using intents to save values from database depending on item click
        listDatabase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                menu_skeleton ms2 = (menu_skeleton) adapterView.getAdapter().getItem(i);

                // creation of intent
                Intent it2 = new Intent(getActivity().getApplicationContext(), savedMovies_details.class);

                // we are adding values for each intent
                it2.putExtra("savedRelease", ms2.getMovie_release());
                Log.d("INTENT DATA STORED","" + ms2.getMovie_release());
                it2.putExtra("savedTitle", ms2.getMovie_title());
                it2.putExtra("savedDescription", ms2.getMovie_description());
                it2.putExtra("savedRating", ms2.getMovie_rating());
                it2.putExtra("savedTrailer", ms2.getMovie_trailer());
                it2.putExtra("savedImage", ms2.getMovie_image());
                it2.putExtra("savedKeywords", ms2.getMovie_keywords());

                // this methot will save the intent and send us to the saved movies detailed class
                startActivity(it2);
            }
        });


        // if list view is empty display image and hide button
        if (listDatabase.getCount() == 0){
        imgList.setVisibility(View.VISIBLE);
        btnDeleteAll.setVisibility(View.INVISIBLE);
        }
        // if not hide image display button
        else {
            imgList.setVisibility(View.INVISIBLE);
            btnDeleteAll.setVisibility(View.VISIBLE);
        }

        // we remove all the items from the list view and in the database
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // with the help of this message we are deleting everything from the database
                sqTest.deleteAll();
                toastMessage("Saved List Wiped!");
            }
        });
    }

    // we created this method to write toasts easily
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }
}