package com.quick_example.IMDB_Application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryDisplayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Category_Skeleton> arList;

    public CategoryDisplayAdapter(@NonNull Context context, ArrayList<Category_Skeleton> arList) {
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

    //setting movie details in the grid view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_category_display, parent, false);
        }
        TextView title = convertView.findViewById(R.id.listviewCategoryTitle);
        TextView rating = convertView.findViewById(R.id.listviewCategoryRating);
        TextView releaseDate = convertView.findViewById(R.id.listviewCategoryReleaseDate);
        ImageView img = convertView.findViewById(R.id.listviewCategoryImg);

        title.setText(arList.get(position).getMovie_title());
        rating.setText(arList.get(position).getMovie_ratingCategory());
        releaseDate.setText(arList.get(position).getMovie_releaseCategory());
        Picasso.get().load(  arList.get(position).getMovie_imageCategory()).into(img);

        return convertView;
    }
}
