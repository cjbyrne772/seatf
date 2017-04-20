package com.seatstir.andy.volleylib;

/**
 * Created by fred on 2/9/2016.
 */

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String response);
    }


