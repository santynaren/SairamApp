package com.nagajothy.smazee.sairamapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SANTHOSH on 12/25/2016.
 */

public class MySingleton {


    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private  static Context context;
    private MySingleton(Context ctx)
    {
        context =ctx;
        requestQueue = getRequestQueue();
    }


    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new MySingleton(context);

        }
        return mInstance;
    }
    public <T>void addToRequestque(Request<T> request)
    {
        requestQueue.add(request);
    }




}
