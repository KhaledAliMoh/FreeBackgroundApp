package com.example.khaled.FreeBackgroundApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Khaled on 9/4/2016.
 */

// This Database is local not the same as on the Server.
public class DatabaseHelper extends SQLiteOpenHelper {

    final static String TAG = "Database";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FreeBackground.db";

    // // Tables Names
    //public static final String TABLE_NAME_USER = "user";
    //public static final String TABLE_NAME_WALLPAPER = "wallpaper";
    public static final String TABLE_NAME_COMMENT_DETAILS = "comment_details";
    public static final String TABLE_NAME_FAVOURITES_WALLPAPER = "favourites_wallpaper";
    //public static final String TABLE_NAME_PROFILE_IMAGE_DETAILS = "profile_image_details";

    /*
    //  user columns names
    public static final String USER_ID_COLUMN = "user_id";
    public static final String USER_NAME_COLUMN = "user_name";
    public static final String USER_EMAIL_COLUMN = "user_email";
    public static final String USER_PASSWORD_COLUMN = "user_password";
    */

    /*
    //  wallpaper columns names
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String CATEGORY_COLUMN = "category";
    public static final String POSTED_TIME_COLUMN = "posted_time";
    public static final String WALLPAPER_ID_COLUMN = "wallpaper_id";
    public static final String WALLPAPER_URL_COLUMN = "wallpaper_url";
    public static final String WALLPAPER_SIZE_COLUMN = "wallpaper_size";
    */

    //  comment details columns names
    public static final String CONTENT_COLUMN = "content";
    public static final String COMMENT_ID_COLUMN = "comment_id";
    public static final String COMMENT_TIME_COLUMN = "comment_time";


    //  favourites wallpaper columns names
    public static final String WALLPAPER_ID_COLUMN = "favourite_time";
    public static final String FAVOURITE_TIME_COLUMN = "favourite_time";


    /*
    //  profile image details columns names
    public static final String IMAGE_PROFILE_ID_COLUMN = "image_profile_id";
    public static final String IMAGE_PROFILE_URL_COLUMN = "image_profile_url";
    public static final String IMAGE_PROFILE_SIZE_COLUMN = "image_profile_size";
    */


    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.d(TAG, "Before creation");
        //
        // Create User Table
        //db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + "( " + USER_ID_COLUMN + " INTEGER PRIMARY KEY AUTO INCREMENT , "
                //+ USER_NAME_COLUMN + " TEXT, " + USER_PASSWORD_COLUMN + " TEXT, " + USER_EMAIL_COLUMN + " TEXT );");
        //Log.d(TAG, "After creation");

        // Create Wallpaper Table
        //db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_WALLPAPER + "( " + WALLPAPER_ID_COLUMN + " INTEGER PRIMARY KEY AUTO INCREMENT , "
          //      + DESCRIPTION_COLUMN + " TEXT, " + CATEGORY_COLUMN + " TEXT, " + POSTED_TIME_COLUMN + " DATETIME , " + USER_ID_COLUMN + " INTEGER , "
            //    + WALLPAPER_URL_COLUMN + " TEXT, " + WALLPAPER_SIZE_COLUMN + " INTEGER, "
              //  + " FOREIGN KEY( " + USER_ID_COLUMN + ") REFERENCES " + TABLE_NAME_USER + " ( " + USER_ID_COLUMN + ") );");
        //Log.d(TAG, "After creation");

        // Create Comment Details Table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COMMENT_DETAILS + "( " + COMMENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTO INCREMENT , "
                + WALLPAPER_ID_COLUMN + " INTEGER, " +  CONTENT_COLUMN + " TEXT," + COMMENT_TIME_COLUMN + " DATETIME, "
                + " FOREIGN KEY( " + WALLPAPER_ID_COLUMN + ") REFERENCES " + TABLE_NAME_FAVOURITES_WALLPAPER + " ( " + WALLPAPER_ID_COLUMN + " )  "
                + " );");
        //Log.d(TAG, "After creation");

        // Create Favourites Table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_FAVOURITES_WALLPAPER + "( " + WALLPAPER_ID_COLUMN + " INTEGER PRIMARY KEY  , "
                + FAVOURITE_TIME_COLUMN + " DATETIME, "
                + " );");
                //Log.d(TAG, "After creation");

        // Create Profile Image Table
        //db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PROFILE_IMAGE_DETAILS + "( " + USER_ID_COLUMN + " INTEGER , "
          //      + IMAGE_PROFILE_ID_COLUMN + " INTEGER PRIMARY KEY AUTO INCREMENT , " + IMAGE_PROFILE_URL_COLUMN + " TEXT, " + IMAGE_PROFILE_SIZE_COLUMN + " INTEGER, "
            //    + " FOREIGN KEY( " + USER_ID_COLUMN + ") REFERENCES " + TABLE_NAME_USER + " ( " + USER_ID_COLUMN + " ) , "
              //  + " );");
        //Log.d(TAG, "After creation");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COMMENT_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_FAVOURITES_WALLPAPER);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
