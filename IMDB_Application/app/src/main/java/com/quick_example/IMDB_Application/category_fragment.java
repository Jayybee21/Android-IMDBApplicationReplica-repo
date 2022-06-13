package com.quick_example.IMDB_Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link category_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */



public class category_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //Variables
    ArrayList<Category_Skeleton> csa = new ArrayList<>();
    ArrayList<Category_Skeleton> csaId = new ArrayList<>();
    String image;
    String rating;
    String title;
    String clickedGenre;
    String releaseDate;
    String movieIdText;
    String movieId;
    String urlGenresByMovie;
    String genre;
    ListView lv;
    GridView gv;

    //API Links
    String urlGenres = "https://data-imdb1.p.rapidapi.com/genres/";
    String urlGenresMov = "https://data-imdb1.p.rapidapi.com/movie/byGen/";
    String urlImage = "https://data-imdb1.p.rapidapi.com/movie/id/";
    String imgURL;

    //API Token
    String key = "8988800409msh25b5be8fcfdd627p182fc9jsnc17c7a1cd2b1";


    public category_fragment() {
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
    public static category_fragment newInstance(String param1, String param2) {
        category_fragment fragment = new category_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // In this method we are calling the Genres API so that we can put them in the list view
    @Override
    public void onStart() {
        super.onStart();
        Log.d("MSG : ", "In view created");
        lv.setVisibility(View.VISIBLE);
        gv.setVisibility(View.INVISIBLE);
        csa = new ArrayList<Category_Skeleton>();

        CustomStringRequest customStringRequest = new CustomStringRequest(Request.Method.GET, urlGenres, key, onSuccees, onFailed);

        try {
            VolleySingleton volleySingleton = null;
            volleySingleton = VolleySingleton.getInstance(getActivity().getApplicationContext());
            RequestQueue queue = volleySingleton.getRequestInstance();
            queue.add(customStringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// This onSuccess fills the genres listview and find the movie id's by their genres.
    Response.Listener<String> onSuccees = new Response.Listener<String>() {
        @Override
        public synchronized void onResponse(String response) {

            try {

                JSONObject jsonObject = null;
                JSONArray results = null;
                jsonObject = new JSONObject(response);
                results= jsonObject.getJSONArray("results");

                /* we use this for loop so that we can retreive all the genres
                   in the api and put them in the arraylist */
                for(int i = 0; i < results.length(); i++){
                    Category_Skeleton cs = new Category_Skeleton();
                    JSONObject jso = results.getJSONObject(i);
                    genre = jso.getString("genre");
                    Log.d("MSG : ", " genre : " + genre);
                    cs.setGenre(genre);
                    csa.add(cs);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Adapter
            CategoryAdapter adapter = new CategoryAdapter(getActivity(), csa);
            lv.setAdapter(adapter);
            Log.d("Volley", response);

            // on item click so that we can retreive the movies depending on their genres with their details
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    lv.setVisibility(View.INVISIBLE);
                    gv.setVisibility(View.VISIBLE);

                    urlGenresByMovie = urlGenresMov + csa.get(i).getGenre() + "/";

                    // API by Genres
                    CustomStringRequest customStringRequestgd = new CustomStringRequest(Request.Method.GET, urlGenresByMovie, key, onSuccess2, onFailed3);
                    VolleySingleton volleySingleton3 = null;
                    try {
                        volleySingleton3 = VolleySingleton.getInstance(getActivity().getApplicationContext());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    RequestQueue queue3 = volleySingleton3.getRequestInstance();
                    queue3.add(customStringRequestgd);

                    clickedGenre = csa.get(i).getGenre();
                    Log.d("MSG", "Clicked Genre: " + clickedGenre);
                }
            });

        }
    };

    // on failed response, show's volley error message
    Response.ErrorListener onFailed = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley", "");
        }
    };

    // This onSuccess returns the movies ID's.
    Response.Listener<String> onSuccess2 = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            try {

                JSONObject jsonObject = null;
                JSONArray results2 = null;
                jsonObject = new JSONObject(response);
                results2 = jsonObject.getJSONArray("results");
                Log.d("MSG", "RESULTS2: " + results2);

                for(int i = 0; i< results2.length(); i++){
                    Category_Skeleton cs = new Category_Skeleton();
                    movieId = (results2.getString(i));
                    JSONObject titleObject = new JSONObject(movieId);
                    movieIdText = titleObject.getString("imdb_id");
                    title = titleObject.getString("title");

                    //image url
                    imgURL = urlImage + movieIdText + "/";

                    // Movie details display API
                    CustomStringRequest csrImage = new CustomStringRequest(Request.Method.GET, imgURL, key, onSuccess3, onFailed3);
                    VolleySingleton volleySingleton = null;
                    volleySingleton = VolleySingleton.getInstance(getActivity().getApplicationContext());
                    RequestQueue queue2 = volleySingleton.getRequestInstance();
                    queue2.add(csrImage);

                    Log.d("MSG : ", "Movie ID : " + movieIdText);
                    Log.d("MSG : ", "Movie Title : " + title);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }};

    // on failed response, show's volley error message
    Response.ErrorListener onFailed2 = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley2", "");
        }
    };

    // Movie details display API onSuccess (Get and Set in arraylist)
    Response.Listener<String> onSuccess3 = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                Category_Skeleton cs = new Category_Skeleton();
                JSONObject jsonObject2 = null;
                JSONObject results2 = null;
                jsonObject2 = new JSONObject(response);
                results2 = jsonObject2.getJSONObject("results");
                image = (results2.getString("image_url") + "");
                releaseDate = (results2.getString("release" + ""));
                title = (results2.getString("title") + "");
                rating = ("" + results2.get("rating"));
                movieIdText = (results2.getString("imdb_id"));

                Log.d("MSG : ", "Movie ID : " + movieIdText);
                Log.d("MSG : ", "Movie Title : " + title);
                Log.d("MSG : ", "Rating : " + rating);
                Log.d("MSG : ", "Release Date : " + releaseDate);
                Log.d("MSG : ", "Image value : " + image);
                cs.setMovie_idCategory(movieIdText);
                cs.setMovie_titleCategory(title);
                cs.setMovie_releaseCategory(releaseDate);
                cs.setMovie_imageCategory(image);
                cs.setMovie_ratingCategory(rating);
                csaId.add(cs);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            CategoryDisplayAdapter gvAdapter = new CategoryDisplayAdapter(getActivity(), csaId);
            gv.setAdapter(gvAdapter);

            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Category_Skeleton cs2 = (Category_Skeleton) adapterView.getAdapter().getItem(i);
                    Intent it1 = new Intent(getActivity().getApplicationContext(), movie_details.class);
                    it1.putExtra("theID2", cs2.getMovie_idCategory());
                    startActivity(it1);
                }
            });
        }
    };

    // on failed response, show's volley error message
    Response.ErrorListener onFailed3 = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley3", "");
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_category_fragment, container, false);
        lv = (ListView)RootView.findViewById(R.id.listView_Category);
        gv = (GridView)RootView.findViewById(R.id.gridView_Category);
        return RootView;
    }

}