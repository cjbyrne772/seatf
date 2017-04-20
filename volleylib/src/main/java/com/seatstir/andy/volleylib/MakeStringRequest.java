package com.seatstir.andy.volleylib;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
//import com.seatstir.andy.logind.LoginD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fred on 2/9/2016.
 */
public class MakeStringRequest {
    String tag_string_req = "string_req";
    String thisUser;
    String thisPass;
    String jstr;

  //  VolleySingleton.getInstance(context).setGs("inside MSR");
  //  Toast.makeText(context, VolleySingleton.getInstance(context).getGs(), Toast.LENGTH_SHORT).show();


    public void MakeCustomStringRequest(final Context context, String url,
                                         final VolleyResponseListener listener, String  injstr) {
        // if jstr holds login data ( for instance if the submit button was just hit) then grab that
        // data and save it. All subsequest string requests will have that data inserted into the json.
        // This does 2 things: it is how we implement a "session", and it does not require the various
        // activities that are calling us to keep track of login info.

        // If we save the login info in the volley singelton, how do we insert it into the
        // string request? Since here is the only place we build that data structure,
        // down in getparams(), try it here.

        // is login info in the jstr?

      //  LoginD mys = ((LoginD)getApplicationContext());
     //   LoginD mys = ((LoginD)context);
     //   String state = mys.getState();
     //   Toast.makeText(context, state, Toast.LENGTH_SHORT).show();

        try {
            JSONObject json=new JSONObject(injstr);
            if (json.has("login_password") && json.has("login_email")){
                //  Toast.makeText(MakeStringRequest.this, "got login data in MCSR", Toast.LENGTH_SHORT).show();
              //  Toast.makeText(context, "Login data in MCSR", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getContext(), "Login data in MCSR", Toast.LENGTH_SHORT).show();
               // Toast.makeText( getApplicationContext(), "Login data in MCSR", Toast.LENGTH_SHORT).show();
              //  Toast.makeText( MakeStringRequest.this, "Login data in MCSR", Toast.LENGTH_SHORT).show();
                // save the login info
                thisUser = json.getString("login_email");
                thisPass = json.getString("login_password");
                // ok now store it in Singleton
                 VolleySingleton.getInstance(context).setGloginEmail(thisUser);
                 VolleySingleton.getInstance(context).setGloginPass(thisPass);

                jstr = injstr;
                }
            else { // if it isn't in the json, put it there now
                thisUser = VolleySingleton.getInstance(context).getGloginEmail();
                thisPass = VolleySingleton.getInstance(context).getGloginPass();
                json.put("login_email", thisUser);
                json.put("login_password", thisPass);

                // then change the jstr
                jstr = json.toString();
            }
            //   myEvents.setAdapter(new ResactAdapter(this,testContents);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }


        StringRequest StrReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String ListenResponse) {
                listener.onResponse(ListenResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.toString());
            }
        })
        {// getparms here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // build the jstr that the
                // the POST parameters:
    //            VolleySingleton.getInstance(context).setGs("inside MCSR-getparams");
    //            Toast.makeText(context, VolleySingleton.getInstance(context).getGs(), Toast.LENGTH_SHORT).show();

                params.put("login_email", "info@fillaseatcleveland.com");
                params.put("login_password", "info");
                params.put("jstr", jstr); // gets parsed by the php
                return params;
            }
         };

        // Access the RequestQueue through singleton class.
        // testing the global

//        VolleySingleton.getInstance(context).setGs("inside MCSR");
  //      Toast.makeText(context, VolleySingleton.getInstance(context).getGs(), Toast.LENGTH_SHORT).show();

        VolleySingleton.getInstance(context).addToRequestQueue(StrReq, tag_string_req);
    }
    }
