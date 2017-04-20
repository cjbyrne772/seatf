package com.seatstir.andy.ptm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.seatstir.andy.volleylib.MakeStringRequest;
import com.seatstir.andy.volleylib.NetworkStringLoader;
import com.seatstir.andy.volleylib.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// The 1st time we get here it is from the user clicking the
// "My Reservations" button on the event list page. We are sent
// a string containing user id. Use that to display a list of reservations.
// The display adapter will handle reservation cancel/update.
//public class resact extends AppCompatActivity {
    public class resact extends AppCompatActivity
        implements FCom
        //   implements DLFrag.OnFragmentInteractionListener
{
    ResactAdapter   resactAdapter;
    ResactData      resPicked;
    List<ResactData> ListOfRes;
    ListView possibleRes;
    String jstr;

    @Override
    public void Frag(String dpick) {
     //   Toast.makeText(resact.this, "frag comm " + dpick, Toast.LENGTH_SHORT).show();
        if (dpick.equals("Yes")) {
            // ok, how do we display the data?
            // how about getting a pointer to the adapter?
          //  resactAdapter.remove();
            resactAdapter.clear(); // try clearing the adapter
            resactAdapter.docancel();

            try {
              //  Thread.sleep(1000);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            GetResData(); // jstr gets passed straight to php code
        }

    }



    // This activity starts up when the php file that gets reservation lists
    // returns with a valid response. Place the json ino the ListOfRes, where
    // it gets displayed in a ListView by the ResactAdapter.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resact); // holds listview
        TextView tCurrentRes;
        ListOfRes = new ArrayList<ResactData>();
        resactAdapter = new ResactAdapter(this, R.layout.custom_res, ListOfRes);  // goes to onCreate
        possibleRes = (ListView) findViewById(R.id.listView); // goes to onCreate
        possibleRes.setAdapter(resactAdapter);

        // String jstr = getIntent().getStringExtra("jstr");
      //  jstr = getIntent().getStringExtra("jstr");

        // GetResData(); // jstr gets passed straight to php code

    }

    @Override
    protected void onStart() {
        super.onStart();

        // String jstr = getIntent().getStringExtra("jstr");
        Log.i("OnStart ", "resact");
        jstr = getIntent().getStringExtra("jstr");
        if (jstr == null)   Log.i("OnStart ", "was null");
           else {
            Log.i("OnStart ", jstr);

            GetResData(); // jstr gets passed straight to php code
        }

    }

    public void GetResData(){
        //////////////////////////////////////////
        //get the data for the list of res
        VolleyResponseListener listener = new VolleyResponseListener() {
            @Override
            public void onError(String m) {Toast.makeText(resact.this, "error getting reservations", Toast.LENGTH_SHORT).show();}

            @Override
            public void onResponse(String response) {
                if (response.contains("success")){
                 //   Log.i("GetResList", response);
                    ParseAndFill(response);
                    Log.i("GetResList", "filled data");

                    //  possibleRes.setAdapter(resactAdapter);
                    // Toast.makeText(resact.this, "got res,redisplay", Toast.LENGTH_SHORT).show();
                    resactAdapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(resact.this, "Login failure, try again", Toast.LENGTH_SHORT).show();
            }
        };
        // Get the list of reservations for this user
        Log.i("GetResList ",  "before request ");

        String urlString = "https://www.seatstir.com/ptapp/ptreslist.php";
        MakeStringRequest custr = new MakeStringRequest();
        custr.MakeCustomStringRequest(resact.this, urlString, listener, jstr);
        Log.i("GetResList ",  "after request ");
    }
    public void ParseAndFill(String response) {
        // First, get the data out of response and put it into the global
        // List Of Res.
     // seems ok here   resactAdapter = new ResactAdapter(this, R.layout.custom_res, ListOfRes);  // goes to onCreate
        try {
            JSONObject jobjTop = new JSONObject(response);
            //we're really interested only in the event array
            JSONArray jsResList = jobjTop.getJSONArray("upcoming");
            ListOfRes.clear();  // just in case some old list elements were hanging around

            int length = jsResList.length();
            Log.i("parse and fill", "rcount" + length);
            for (int ic = 0; ic < length; ic++) {
                JSONObject c = jsResList.getJSONObject(ic);
                int rid = c.getInt("rid");
                String d = c.getString("shortDesc");
                String pdate = c.getString("sdate");
                String ptime = c.getString("stime");
                int q = c.getInt("qty");
                ResactData itemPlaceholder = new ResactData(rid, d, pdate + " " + ptime,  q); // add a json constructor later new Resactdata(c)
                ListOfRes.add(itemPlaceholder);
            }
            //   myEvents.setAdapter(new ResactAdapter(this,testContents);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        //////////////////////////////////// end of new res filler
        //ok data is now in ListOfRes.
      // these 3 lines now get moved out, so we can repopulate List Of Res without
        // getting a new adapter

      //  ListView possibleRes = (ListView) findViewById(R.id.listView); // goes to onCreate
      //  resactAdapter = new ResactAdapter(this, R.layout.custom_res, ListOfRes);  // goes to onCreate
      //  possibleRes.setAdapter(resactAdapter);  // is called after Parse and Fill is called


    }
    public void doPositiveClick(String p) {
        Log.i("FragmentAlertDialog", "Positive click!" + p);
    }
    public void doNegativeClick(String n) {
        Log.i("FragmentAlertDialog", "Negative click!" + n);
    }
}
