package com.quick_example.IMDB_Application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Category_Skeleton> arList;

    public CategoryAdapter(@NonNull Context context, ArrayList<Category_Skeleton> arList) {
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

    //setting genres in list view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_category_listview, parent, false);
        }
        TextView genre = convertView.findViewById(R.id.listviewCategoryGenre);
        genre.setText(arList.get(position).getGenre());

        return convertView;
    }
}
