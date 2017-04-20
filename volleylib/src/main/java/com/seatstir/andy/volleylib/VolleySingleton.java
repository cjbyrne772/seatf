package com.seatstir.andy.volleylib;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by fred on 1/11/2016.
 */

public class VolleySingleton {

    public static final String TAG = VolleySingleton.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static Context mCtx;
    // can we store a global here???
    private static String gloginEmail = new String("cjbyrne@gmail.com");
    private static String gloginPass = new String("this shouldbeoverwritten");


    private static VolleySingleton mInstance;


    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static void setGloginPass(String gloginPass) {
        VolleySingleton.gloginPass = gloginPass;
    }

    public static String getGloginPass() {
        return gloginPass;
    }

    public static String getGloginEmail() {
        return gloginEmail;
    }

    public static void setGloginEmail(String gloginEmail) {
        VolleySingleton.gloginEmail = gloginEmail;
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
