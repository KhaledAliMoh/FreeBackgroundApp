package com.example.khaled.FreeBackgroundApp;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Khaled on 8/16/2016.
 */
public class MySingletonForRequestQueue {

    final String Tag = "My Singleton For Request Queue ";
    private static MySingletonForRequestQueue myInstance;
    private RequestQueue myRequestQueue;
    private static Context myContext;

    private MySingletonForRequestQueue (Context myContext){
        this.myContext = myContext;
        this.myRequestQueue = getRequestQueue();

    }

    public static synchronized MySingletonForRequestQueue getInstance(Context context){
        if(myInstance == null){
            myInstance =new MySingletonForRequestQueue (context);
        }
        return myInstance;
    }
    public RequestQueue getRequestQueue (){
        if(myRequestQueue == null){
            myRequestQueue = Volley.newRequestQueue(myContext.getApplicationContext());
        }
        return myRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
