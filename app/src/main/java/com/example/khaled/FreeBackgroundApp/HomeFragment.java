package com.example.khaled.FreeBackgroundApp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Khaled on 11/22/2016.
 */
public class HomeFragment extends Fragment {
    final String Tag = "Home Fragment ";
    //ImageButton imageButtonChooser;
    ListView listViewHome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        listViewHome = (ListView) rootView.findViewById(R.id.listView_home);
        listViewHome.setAdapter(new HomeAdapter(getActivity(), 3));

        return rootView;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.change_avatar_item){

        }
        else if(item.getItemId() == R.id.change_username_item){

        }
        else if(item.getItemId() == R.id.change_password_item){

        }
        else if(item.getItemId() == R.id.logout_item){

        }
        return true;
    }
}
