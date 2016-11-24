package com.example.khaled.FreeBackgroundApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Khaled on 9/1/2016.
 */
public class HomeAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> list;
    int cc;

    public HomeAdapter(Context context, int c) {
        this.context = context;
        this.list = list;
        cc = c;
    }

    @Override
    public int getCount() {
        /*if (list == null)
            return 0;
        return list.size();*/
        return cc;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.list_view_home_item, parent, false);
        ImageButton imageButtonComment = (ImageButton) row.findViewById(R.id.comment_button);
        imageButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CommentActivity.class));
            }
        });

        final ImageButton imageButtonFavourite = (ImageButton) row.findViewById(R.id.favourite_button);
        imageButtonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LikedWallpapers.isLiked(position)){ // it should to be id not position
                    // change image and delete from database
                    imageButtonFavourite.setImageResource(R.drawable.unliked_image);
                }
                else
                    imageButtonFavourite.setImageResource(R.drawable.liked_image);

            }
        });

        if(LikedWallpapers.isLiked(position))
            imageButtonFavourite.setImageResource(R.drawable.liked_image);


        return row;
    }
}


