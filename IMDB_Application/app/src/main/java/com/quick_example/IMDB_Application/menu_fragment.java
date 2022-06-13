package com.quick_example.IMDB_Application;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * Use the {@link menu_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class menu_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //variables
    ArrayList<menu_skeleton> ar = new ArrayList<>();
    String image;
    String rating;
    String title;
    String releaseDate;
    String movieId;
    ListView lv;

    // API Links
    String url = "https://data-imdb1.p.rapidapi.com/movie/order/upcoming/";
    String urlImage = "https://data-imdb1.p.rapidapi.com/movie/id/";
    String imgURL;

    //API Token
    String key = "8988800409msh25b5be8fcfdd627p182fc9jsnc17c7a1cd2b1";


    public menu_fragment() {
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
    public static menu_fragment newInstance(String param1, String param2) {
        menu_fragment fragment = new menu_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // get upcoming movies
    @Override
    public void onStart() {
        super.onStart();
        Log.d("MSG : ", "In view created");
        ar = new ArrayList<menu_skeleton>();
        CustomStringRequest customStringRequest = new CustomStringRequest(Request.Method.GET, url, key, onSuccees, onFailed);

        try {
            VolleySingleton volleySingleton = null;
            volleySingleton = VolleySingleton.getInstance(getActivity().getApplicationContext());
            RequestQueue queue = volleySingleton.getRequestInstance();
            queue.add(customStringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // onSuccess for API
    Response.Listener<String> onSuccees = new Response.Listener<String>() {
        @Override
        public synchronized void onResponse(String response) {
            JSONObject jsonObject = null;
            JSONArray results = null;

            try {
                jsonObject = new JSONObject(response);
                results=jsonObject.getJSONArray("results");
                for(int i = 0; i < results.length(); i++){
                    JSONObject jso = results.getJSONObject(i);
                    movieId = jso.getString("imdb_id");
                    imgURL = urlImage + movieId + "/";

                    // Image API
                    CustomStringRequest csrImage = new CustomStringRequest(Request.Method.GET, imgURL, key, onSuccess2, onFailed2);
                    VolleySingleton volleySingleton = null;
                    volleySingleton = VolleySingleton.getInstance(getActivity().getApplicationContext());
                    RequestQueue queue2 = volleySingleton.getRequestInstance();
                    queue2.add(csrImage);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //  }
            Log.d("Volley", response);
        }
    };

    // on failed response volley 1
    Response.ErrorListener onFailed = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley", "");
        }
    };

    // on failed response volley 2
    Response.ErrorListener onFailed2 = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Volley2", "");
        }
    };

    // Movie details display API onSuccess (Get and Set in arraylist)
    Response.Listener<String> onSuccess2 = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                menu_skeleton ms = new menu_skeleton();
                JSONObject jsonObject2 = null;
                JSONObject results2 = null;
                jsonObject2 = new JSONObject(response);
                results2 = jsonObject2.getJSONObject("results");
                image = (results2.getString("image_url") + "");
                releaseDate =(results2.getString("release" + ""));
                title = (results2.getString("title" ) + "");
                rating = ("" + results2.get("rating"));
                movieId = (results2.getString("imdb_id") + "");

                Log.d("MSG : ", "Movie ID : " + movieId);
                Log.d("MSG : ", "Movie Title : " + title);
                Log.d("MSG : ", "Rating : " + rating);
                Log.d("MSG : ", "Release Date : " + releaseDate);
                Log.d("MSG : ", "Image value : " + image);
                ms.setMovie_id(movieId);
                ms.setMovie_title(title);
                ms.setMovie_release(releaseDate);
                ms.setMovie_image(image);
                ms.setMovie_rating(rating);
                ar.add(ms);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            MenuAdapter adapter = new MenuAdapter(getActivity(), ar);
            lv.setAdapter(adapter);

            //on click item from listview takes us to movie details
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    menu_skeleton ms2 = (menu_skeleton) adapterView.getAdapter().getItem(i);
                    Intent it1 = new Intent(getActivity().getApplicationContext(), movie_details.class);
                    it1.putExtra("theID", ms2.getMovie_id());
                    startActivity(it1);
                }
            });

        }};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_menu_fragment, container, false);
        lv = (ListView)RootView.findViewById(R.id.listView_Menu);
        return RootView;
    }


    }