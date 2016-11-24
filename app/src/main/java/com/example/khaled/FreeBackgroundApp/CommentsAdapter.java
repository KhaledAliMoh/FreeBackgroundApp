package com.example.khaled.FreeBackgroundApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Khaled on 11/23/2016.
 */
public class CommentsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> list;
    int cc;

    public CommentsAdapter(Context context, int c) {
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
        row = inflater.inflate(R.layout.list_view_comment_item, parent, false);


        return row;
    }
}
