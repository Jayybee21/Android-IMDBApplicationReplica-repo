package com.quick_example.IMDB_Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<menu_skeleton> arList;

    public MenuAdapter(@NonNull Context context, ArrayList<menu_skeleton> arList) {
        this.context = context;
        this.arList = arList;
    }

    @Override
    public int getCount() {
        return arList.size();
    }

    @Override
    public Object getItem(int i) {
        return arList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //setting movie details in list view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // if --> for the first item in list view (BIG)
        if (position == 0) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_menu_listview, parent, false);
            TextView title = convertView.findViewById(R.id.listviewMenuTitle);
            ImageView img = convertView.findViewById(R.id.listviewMenuImg);
            title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50);
            title.setGravity(Gravity.CENTER_VERTICAL);
            title.setText(arList.get(position).getMovie_title());
            Picasso.get().load(arList.get(position).getMovie_image()).into(img);
        }
        else {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_menu_listview, parent, false);
            TextView title = convertView.findViewById(R.id.listviewMenuTitle);
            ImageView img = convertView.findViewById(R.id.listviewMenuImg);
            TextView rating = convertView.findViewById(R.id.listviewMenuRating);
            TextView genre = convertView.findViewById(R.id.listviewMenuGenre);
            TextView releaseDate = convertView.findViewById(R.id.listviewMenuReleaseDate);
            title.setText(arList.get(position).getMovie_title());
            rating.setText(arList.get(position).getMovie_rating());
            genre.setText(arList.get(position).getMovie_genre());
            releaseDate.setText(arList.get(position).getMovie_release());
            //    keywords.setText(arList.get(position).getMovie_keywords());
            Picasso.get().load(arList.get(position).getMovie_image()).into(img);
        }
        return convertView;
    }
}