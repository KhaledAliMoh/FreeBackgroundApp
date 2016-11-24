package com.example.khaled.FreeBackgroundApp;

import java.util.ArrayList;

/**
 * Created by Khaled on 11/23/2016.
 */
    class LikedWallpapers {
    static private ArrayList<Integer> listLikedWallpaper;
    static public boolean isLiked(int id){
        // Retrieve from database

        if(id == 0)
            return true;
        else
            return false;
    }
}
