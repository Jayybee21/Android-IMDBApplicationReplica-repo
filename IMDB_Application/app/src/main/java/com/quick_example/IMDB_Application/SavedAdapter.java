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

public class SavedAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<menu_skeleton> arList;

    public SavedAdapter(@NonNull Context context, ArrayList<menu_skeleton> arList) {
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


    // setting saved items in list view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_saved_listview, parent, false);
        }
        TextView title = convertView.findViewById(R.id.listviewSavedTitle);
        ImageView img = convertView.findViewById(R.id.listviewSavedImg);

        title.setText(arList.get(position).getMovie_title());
        Picasso.get().load( arList.get(position).getMovie_image()).into(img);

        return convertView;
    }
}
