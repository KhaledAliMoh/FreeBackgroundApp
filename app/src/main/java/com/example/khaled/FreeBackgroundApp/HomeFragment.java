package com.example.khaled.FreeBackgroundApp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Khaled on 11/22/2016.
 */
public class HomeFragment extends Fragment {
    final String Tag = "Home Fragment ";
    ImageButton imageButtonChooser;
    int PICK_IMAGE = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        imageButtonChooser = (ImageButton) rootView.findViewById(R.id.button_pick_image);
        imageButtonChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });

        ListView listViewHome = (ListView) rootView.findViewById(R.id.listView_home);
        listViewHome.setAdapter(new HomeAdapter(getActivity(), 3));
        return rootView;
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCode && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                Toast.makeText(getActivity(), "Some Thing Wrong !!!", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...

        }
    }
}
