package com.seatstir.andy.volleylib;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
//import com.seatstir.andy.eventlistlib.EventList;
//import com.seatstir.andy.ptm.resact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by fred on 1/11/2016.
 */



public class NetworkStringLoader {
    Context context;
    TextView display;
    //    StringBuffer networkdata;
    private static String user_email;
    private static String user_password;
    String myjstr;
    String response;
    int rcount;
    Intent gi;
    // String jstr = "{\"login\":\"success\",\"user_id\":\"39293\",\"event\":[{\"eid\":\"4589\",\"pid\":\"14124\",\"display_date_start\":\"2015-07-18\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-22\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/X-at-Music-Box--Cleveland--July-22-o7wvc0cXgt.jpg\",\"shortDesc\":\"X at Music Box (Cleveland) July 22\",\"eventid\":\"4589\",\"qtysum\":\"12\",\"qty\":\"2\",\"allocated\":\"20\",\"qtyalloc\":\"120\"},{\"eid\":\"4583\",\"pid\":\"14112\",\"display_date_start\":\"2015-07-15\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-20\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/Waxahatchee-at-Beachland-Ballroom---July-20--Cleveland--DH4FXri0K3.jpg\",\"shortDesc\":\"Waxahatchee at Beachland Ballroom - July 20 (Cleveland)\",\"eventid\":\"4583\",\"qtysum\":null,\"qty\":null,\"allocated\":\"10\",\"qtyalloc\":\"10\"},{\"eid\":\"4532\",\"pid\":\"14022\",\"display_date_start\":\"2015-07-09\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-08-07\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/cellar-door-rendezvous-ft---seafair-and-the-querencia-orchestra-sxbcum8iiv.jpg\",\"shortDesc\":\"Cellar Door Rendezvous ft.. Seafair and the Querencia Orchestra\",\"eventid\":\"4532\",\"qtysum\":\"26\",\"qty\":\"2\",\"allocated\":\"50\",\"qtyalloc\":\"650\"},{\"eid\":\"4531\",\"pid\":\"14021\",\"display_date_start\":\"2015-07-09\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-30\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/blues-traveler-vvcnozapze.jpg\",\"shortDesc\":\"Blues Traveler\",\"eventid\":\"4531\",\"qtysum\":\"24\",\"qty\":\"2\",\"allocated\":\"20\",\"qtyalloc\":\"240\"},{\"eid\":\"4512\",\"pid\":\"13983\",\"display_date_start\":\"2015-07-07\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-22\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/Chris-Knight-Full-Band-Show----Radio-Birds-6MKdYVtJ7G.jpg\",\"shortDesc\":\"Chris Knight - Full Band Show! (Cleveland)\",\"eventid\":\"4512\",\"qtysum\":\"11\",\"qty\":\"2\",\"allocated\":\"10\",\"qtyalloc\":\"60\"},{\"eid\":\"4510\",\"pid\":\"13976\",\"display_date_start\":\"2015-07-06\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-26\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/sleeping-beauty---swan-lake--olmsted-falls--ypxp4ibejf.jpg\",\"shortDesc\":\"Sleeping Beauty \\/ Swan Lake (Olmsted Falls)\",\"eventid\":\"4510\",\"qtysum\":\"22\",\"qty\":\"2\",\"allocated\":\"50\",\"qtyalloc\":\"550\"},{\"eid\":\"4507\",\"pid\":\"14085\",\"display_date_start\":\"2015-07-15\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-23\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/camelot-at-mercury-theatre-company--s--euclid--acapyiviao.jpg\",\"shortDesc\":\"Camelot  (S. Euclid)\",\"eventid\":\"4507\",\"qtysum\":\"22\",\"qty\":\"2\",\"allocated\":\"30\",\"qtyalloc\":\"270\"},{\"eid\":\"4220\",\"pid\":\"13322\",\"display_date_start\":\"2015-06-29\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-21\",\"display_time_end\":\"12:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/baseball---lake-county-captains-vs-fort-wayne---july-21--eastlake--kycbitiau3.jpg\",\"shortDesc\":\"Baseball - Lake County Captains vs Fort Wayne - July 21 (Eastlake)\",\"eventid\":\"4220\",\"qtysum\":\"56\",\"qty\":\"2\",\"allocated\":\"200\",\"qtyalloc\":\"5200\"}]}";
    String xtestJstr = "{\"login\":\"success\",\"user_id\":\"39293\",\"event\":[{\"eid\":\"4589\",\"pid\":\"14124\",\"display_date_start\":\"2015-07-18\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-22\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/X-at-Music-Box--Cleveland--July-22-o7wvc0cXgt.jpg\",\"shortDesc\":\"X at Music Box (Cleveland) July 22\",\"eventid\":\"4589\",\"qtysum\":\"12\",\"qty\":\"2\",\"allocated\":\"20\",\"qtyalloc\":\"120\"},{\"eid\":\"4583\",\"pid\":\"14112\",\"display_date_start\":\"2015-07-15\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-20\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/Waxahatchee-at-Beachland-Ballroom---July-20--Cleveland--DH4FXri0K3.jpg\",\"shortDesc\":\"Waxahatchee at Beachland Ballroom - July 20 (Cleveland)\",\"eventid\":\"4583\",\"qtysum\":null,\"qty\":null,\"allocated\":\"10\",\"qtyalloc\":\"10\"},{\"eid\":\"4532\",\"pid\":\"14022\",\"display_date_start\":\"2015-07-09\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-08-07\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/cellar-door-rendezvous-ft---seafair-and-the-querencia-orchestra-sxbcum8iiv.jpg\",\"shortDesc\":\"Cellar Door Rendezvous ft.. Seafair and the Querencia Orchestra\",\"eventid\":\"4532\",\"qtysum\":\"26\",\"qty\":\"2\",\"allocated\":\"50\",\"qtyalloc\":\"650\"},{\"eid\":\"4531\",\"pid\":\"14021\",\"display_date_start\":\"2015-07-09\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-30\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/blues-traveler-vvcnozapze.jpg\",\"shortDesc\":\"Blues Traveler\",\"eventid\":\"4531\",\"qtysum\":\"24\",\"qty\":\"2\",\"allocated\":\"20\",\"qtyalloc\":\"240\"},{\"eid\":\"4512\",\"pid\":\"13983\",\"display_date_start\":\"2015-07-07\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-22\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/Chris-Knight-Full-Band-Show----Radio-Birds-6MKdYVtJ7G.jpg\",\"shortDesc\":\"Chris Knight - Full Band Show! (Cleveland)\",\"eventid\":\"4512\",\"qtysum\":\"11\",\"qty\":\"2\",\"allocated\":\"10\",\"qtyalloc\":\"60\"},{\"eid\":\"4510\",\"pid\":\"13976\",\"display_date_start\":\"2015-07-06\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-26\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/sleeping-beauty---swan-lake--olmsted-falls--ypxp4ibejf.jpg\",\"shortDesc\":\"Sleeping Beauty \\/ Swan Lake (Olmsted Falls)\",\"eventid\":\"4510\",\"qtysum\":\"22\",\"qty\":\"2\",\"allocated\":\"50\",\"qtyalloc\":\"550\"},{\"eid\":\"4507\",\"pid\":\"14085\",\"display_date_start\":\"2015-07-15\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-23\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/camelot-at-mercury-theatre-company--s--euclid--acapyiviao.jpg\",\"shortDesc\":\"Camelot  (S. Euclid)\",\"eventid\":\"4507\",\"qtysum\":\"22\",\"qty\":\"2\",\"allocated\":\"30\",\"qtyalloc\":\"270\"},{\"eid\":\"4220\",\"pid\":\"13322\",\"display_date_start\":\"2015-06-29\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-21\",\"display_time_end\":\"12:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/baseball---lake-county-captains-vs-fort-wayne---july-21--eastlake--kycbitiau3.jpg\",\"shortDesc\":\"Baseball - Lake County Captains vs Fort Wayne - July 21 (Eastlake)\",\"eventid\":\"4220\",\"qtysum\":\"56\",\"qty\":\"2\",\"allocated\":\"200\",\"qtyalloc\":\"5200\"}]}";

    public NetworkStringLoader(Context con, String user, String pass) {
        context = con;
        user_email = user;
        user_password = pass;
        rcount = 0;
    }

    public NetworkStringLoader(Context con) {
        context = con;
        rcount = 0;
    }

    public void requestString(String url) {
        // Tag used to cancel the request
        String tag_string_req = "string_req";
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Listenresponse) {
                        rcount++;
                        if (BuildConfig.DEBUG) {
                            int len = Listenresponse.length();
                            Log.d("DEBUG", "INONR before req strlen ==" + len);
                            if (len > 0) { Log.d("DEBUG", "INONR before req str==" + Listenresponse.substring(0,12)); }
                        }
                        pDialog.hide();
                        if (Listenresponse.indexOf("success") != -1) {
                            Toast.makeText(context, "Login OK", Toast.LENGTH_SHORT).show();
                         //   Intent i = new Intent(context, EventList.class); i.putExtra("jstr", Listenresponse);context.startActivity(i);
                        }
                        else {
                            Toast.makeText(context, "Login Failed Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display.setText(error.toString());
                pDialog.hide();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("login_email", user_email);
                params.put("login_password", user_password);
                return params;
            }
        };

        // Adding request to request queue
        //   VolleySingleton.getInstance().addToRequestQueue(strReq, tag_string_req);
        VolleySingleton.getInstance(context).addToRequestQueue(strReq, tag_string_req);
        // return tag_string_req;
        //  return "rstr";
        //  return jstr;
    } //Request String

    // Perform a login to the server and obtain event info. Save the user and password
    // so that you can use them for future requests (What are my current reservations,
    // for instance.
    public void requestStringAct(String url, Intent i, String user, String pass) {
        // Tag used to cancel the request
        String tag_string_req = "string_req";
        gi = i;
        user_email = user;    // save it
        user_password = pass; // save it
        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Listenresponse) {
                        rcount++;
                        if (BuildConfig.DEBUG) {
                            int len = Listenresponse.length();
                            Log.d("DEBUG", "INONR before req strlen ==" + len);
                            if (len > 0) { Log.d("DEBUG", "INONR before req str==" + Listenresponse.substring(0,12)); }
                        }
                        pDialog.hide();
                        if (Listenresponse.indexOf("success") != -1) {
                            Toast.makeText(context, "Login OK", Toast.LENGTH_SHORT).show();
                            gi.putExtra("jstr", Listenresponse);
                            context.startActivity(gi);
                        }
                        else {
                            Toast.makeText(context, "Login Failed Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display.setText(error.toString());
                pDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("login_email", user_email);
                params.put("login_password", user_password);
                return params;
            }
        };
        // Adding request to request queue
        VolleySingleton.getInstance(context).addToRequestQueue(strReq, tag_string_req);
    } //Request StringAct

    public void getResList(String url, final String jstr ) {
        // Tag used to cancel the request
        String tag_string_req = "string_req";
        // Get the parameters from jstr. There might be some magic
        // way to do this in one line of code, but for now we'll just
        // take the parameters out one at a time and put them into
        // the params array.

        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Listenresponse) {
                        rcount++;
                        response = new String(Listenresponse);
                        if (BuildConfig.DEBUG) {
                            int len = Listenresponse.length();
                            Log.d("DEBUG", "INONR before req strlen ==" + len);
                            if (len > 0) { Log.d("DEBUG", "INONR before req str==" + Listenresponse.substring(0,12)); }
                        }
                    //    pDialog.hide();
                        rcount++;
                        if (Listenresponse.indexOf("success") != -1) {
                            rcount++;
                        //    Intent i = new Intent(context, resact.class);
                        //    i.putExtra("jstr", Listenresponse);
                        //    context.startActivity(i);
                        }
                        else {
                            Toast.makeText(context, "Login Failed Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display.setText(error.toString());
                //pDialog.hide();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("login_email", user_email);
                params.put("login_password", user_password);
                params.put("jstr", jstr);
                return params;
            }
        };

        // Adding request to request queue
        //   VolleySingleton.getInstance().addToRequestQueue(strReq, tag_string_req);
        VolleySingleton.getInstance(context).addToRequestQueue(strReq, tag_string_req);
        // return tag_string_req;
        //  return "rstr";
        //  return jstr;
        // return response;
       // return "this is a sample stringxxxxxxxxxxxxxx";
    } //Request String
    //public void getResListAct(String url, Intent i, String user, String pass ) {
    // Someone clicked on a button to get here (should be the [MY RESERVATIONS] button
    // and has sent us a url string pointing to some php code. If we want to change
    // the behavior of the php code, then go change the php code directly. That's
    // easier than adding run-time parameters here, or having extra "test" buttons
    public void getResListAct(String url, Intent i ) {
        // Tag used to cancel the request
        String tag_string_req = "string_req";
        // final String response;
        gi = i;

        final ProgressDialog pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Listenresponse) {
                        rcount++;
                        response = new String(Listenresponse);
                        if (BuildConfig.DEBUG) {
                            int len = Listenresponse.length();
                            Log.d("DEBUG", "INONR before req strlen ==" + len);
                            if (len > 0) { Log.d("DEBUG", "INONR before req str==" + Listenresponse.substring(0,12)); }
                        }
                        pDialog.hide();
                        if (Listenresponse.indexOf("success") != -1) {
                                gi.putExtra("jstr", Listenresponse);
                                context.startActivity(gi);
                        }
                        else {
                            Toast.makeText(context, "Login Failed Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                display.setText(error.toString());
                pDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("login_email", user_email);
                params.put("login_password", user_password);
                return params;
            }
        };
        // Adding request to request queue. strReq is the structure we
        // just built here,
        VolleySingleton.getInstance(context).addToRequestQueue(strReq, tag_string_req);
    } //Request StringR

}
