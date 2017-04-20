package com.seatstir.andy.eventlistlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

//import com.seatstir.andy.eventfocus.EventFocus;
import com.seatstir.andy.ptm.resact;
//import com.android.volley.RequestQueue;
//import com.seatstir.andy.volleylib.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//import com.seatstir.andy.aalib.aaclass;

//import com.seatstir.andy.logind.LoginD;



public class EventList extends Activity {
    String xtestJstr = "{\"login\":\"success\",\"user_id\":\"39293\",\"event\":[{\"eid\":\"4589\",\"pid\":\"14124\",\"display_date_start\":\"2015-07-18\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-22\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/X-at-Music-Box--Cleveland--July-22-o7wvc0cXgt.jpg\",\"shortDesc\":\"X at Music Box (Cleveland) July 22\",\"eventid\":\"4589\",\"qtysum\":\"12\",\"qty\":\"2\",\"allocated\":\"20\",\"qtyalloc\":\"120\"},{\"eid\":\"4583\",\"pid\":\"14112\",\"display_date_start\":\"2015-07-15\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-20\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/Waxahatchee-at-Beachland-Ballroom---July-20--Cleveland--DH4FXri0K3.jpg\",\"shortDesc\":\"Waxahatchee at Beachland Ballroom - July 20 (Cleveland)\",\"eventid\":\"4583\",\"qtysum\":null,\"qty\":null,\"allocated\":\"10\",\"qtyalloc\":\"10\"},{\"eid\":\"4532\",\"pid\":\"14022\",\"display_date_start\":\"2015-07-09\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-08-07\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/cellar-door-rendezvous-ft---seafair-and-the-querencia-orchestra-sxbcum8iiv.jpg\",\"shortDesc\":\"Cellar Door Rendezvous ft.. Seafair and the Querencia Orchestra\",\"eventid\":\"4532\",\"qtysum\":\"26\",\"qty\":\"2\",\"allocated\":\"50\",\"qtyalloc\":\"650\"},{\"eid\":\"4531\",\"pid\":\"14021\",\"display_date_start\":\"2015-07-09\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-30\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/blues-traveler-vvcnozapze.jpg\",\"shortDesc\":\"Blues Traveler\",\"eventid\":\"4531\",\"qtysum\":\"24\",\"qty\":\"2\",\"allocated\":\"20\",\"qtyalloc\":\"240\"},{\"eid\":\"4512\",\"pid\":\"13983\",\"display_date_start\":\"2015-07-07\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-22\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/Chris-Knight-Full-Band-Show----Radio-Birds-6MKdYVtJ7G.jpg\",\"shortDesc\":\"Chris Knight - Full Band Show! (Cleveland)\",\"eventid\":\"4512\",\"qtysum\":\"11\",\"qty\":\"2\",\"allocated\":\"10\",\"qtyalloc\":\"60\"},{\"eid\":\"4510\",\"pid\":\"13976\",\"display_date_start\":\"2015-07-06\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-26\",\"display_time_end\":\"11:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/sleeping-beauty---swan-lake--olmsted-falls--ypxp4ibejf.jpg\",\"shortDesc\":\"Sleeping Beauty \\/ Swan Lake (Olmsted Falls)\",\"eventid\":\"4510\",\"qtysum\":\"22\",\"qty\":\"2\",\"allocated\":\"50\",\"qtyalloc\":\"550\"},{\"eid\":\"4507\",\"pid\":\"14085\",\"display_date_start\":\"2015-07-15\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-23\",\"display_time_end\":\"16:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/camelot-at-mercury-theatre-company--s--euclid--acapyiviao.jpg\",\"shortDesc\":\"Camelot  (S. Euclid)\",\"eventid\":\"4507\",\"qtysum\":\"22\",\"qty\":\"2\",\"allocated\":\"30\",\"qtyalloc\":\"270\"},{\"eid\":\"4220\",\"pid\":\"13322\",\"display_date_start\":\"2015-06-29\",\"display_time_start\":\"00:00:00\",\"display_date_end\":\"2015-07-21\",\"display_time_end\":\"12:00:00\",\"isActive\":\"1\",\"city_id\":\"2\",\"image\":\"images\\/events\\/baseball---lake-county-captains-vs-fort-wayne---july-21--eastlake--kycbitiau3.jpg\",\"shortDesc\":\"Baseball - Lake County Captains vs Fort Wayne - July 21 (Eastlake)\",\"eventid\":\"4220\",\"qtysum\":\"56\",\"qty\":\"2\",\"allocated\":\"200\",\"qtyalloc\":\"5200\"}]}";
    String xxtestJstr = "{\"login\":\"success\",\"user_id\":\"39293\"}";
    String testJstr = "Patriots at Seahawks Super Bowl";
    EventData thisEventPicked;
    CustomAdapter eventAdapter;
    List<EventData> ListOfEvents;
    String strUserID; // The user id being passed in
    String jstrUserID; // the json string that carries the above user ID
    String sqlimit;
    String urlResString = "https://www.seatstir.com/ptapp/ptreslist.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        Button buttonMyRes = (Button) findViewById(R.id.buttonMyRes);
     //   Button buttonTest = (Button) findViewById(R.id.buttonMStest);
        Log.i("EventList ", "OnCreate");

// 4 below
     //   LoginD appState = ((LoginD)getApplicationContext());
     //   String state = appState.getState();

      //   LoginD myaa = new LoginD("info");
     //   myaa.setaa(55);
     //   Toast.makeText(getApplicationContext(), state, Toast.LENGTH_SHORT).show();
     //   appState.setState("whatever");

    //    LoginM login = ((LoginM)getApplicationContext());
      //  login.setState("fred");
      //  String state = ((LoginM) this.getApplication()).getState();
      //  Toast.makeText(getApplicationContext(), state, Toast.LENGTH_SHORT).show();
/////////////////////////////

        String jstr = new String(getIntent().getStringExtra("jstr"));
        //  String jstr = String(getIntent().getStringExtra("jstr"));
        // String jstr = getIntent().getStringExtra("jstr");
        // get the reservation list from the database


        ListOfEvents = new ArrayList<EventData>();
        // first, get the json data into a list container. Then an
        // adapter can fill the ListView with that list.
        try {
            // wtf parsing
         //   JSONObject             xxjstr = xxjobjTop.getString("user_id"); // should work
            JSONObject             xxjobjTop = new JSONObject(xxtestJstr);
            JSONObject xjobjuser, xxjobjuser;
            String     xjstr,     xxjstr;
            JSONObject xjobjTop = new JSONObject(xtestJstr);
            xxjstr = xxjobjTop.getString("user_id"); // should work
          //  sqlimit = xxjobjTop.getString("qlimit"); // should work

          //  JSONObject xjobjTop = new JSONObject(xtestJstr);
          //  JSONObject xjobjuser;
          //  xjstr = xjobjTop.getString("user_id"); // why fail?

            //we're really interested only in the user id and the event array
            // The user id is pulled out into a jstr that will eventually get sent
            // to a php file to get the reslist for this user
            JSONObject jobjTop = new JSONObject(jstr);
            JSONObject jobjuser;
           // xxjstr = jobjTop.getString("user_id");
            strUserID = jobjTop.getString("user_id"); // str going to php
            sqlimit = jobjTop.getString("qlimit"); // might as well keep it as a string
            if ( sqlimit == null ) {
                sqlimit = "0";
            }
            if (TextUtils.isEmpty(sqlimit)) {
                sqlimit = "0";
            }
            // as long as we're here inside the try, build the string. There
            // should be some way of substringing the json object we built,
            // just pulling out the
            // for now, we don't use bldj, but leave this code here in case we do
            JSONObject bldj = new JSONObject();
            bldj.put("user_id", strUserID);
            bldj.put("qlimit", sqlimit);
            jstrUserID = bldj.toString();

          //  jstrUserID = jobjTop.toString("user_id");
         //   jstrUserID = jobjTop.getString("user_id");
            JSONArray jsEvents = jobjTop.getJSONArray("event");

            int length = jsEvents.length();
            Toast.makeText(getApplicationContext(), length + " Events Available ", Toast.LENGTH_LONG).show();

            for (int i = 0; i < length; i++) {
                JSONObject c = jsEvents.getJSONObject(i);
                String d = c.getString("shortDesc");
                int eid = c.getInt("eid");
                int vid = c.getInt("vid");
                // only one pid gets returned - we neeed all pids. int pid = c.getInt("pid");
                EventData itemPlaceholder = new EventData(d, eid, vid);
                ListOfEvents.add(itemPlaceholder);
            }
            // add some filler events for testing TESTING ONLY
            for (int i = 0; i < 12; i++) {
                EventData itemPlaceholder = new EventData("Do not pick me", "   ph", 7878);
             //   ListOfEvents.add(itemPlaceholder);
            }

            //   myEvents.setAdapter(new CustomAdapter(this,testContents));
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        eventAdapter = new CustomAdapter(getApplicationContext(), R.layout.custom_event,
                ListOfEvents);
        ListView myEvents = (ListView) findViewById(R.id.EventListView);
        // set adapeter crashes rt. lets just test this out by making a new
        myEvents.setAdapter(eventAdapter);
        myEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // OK, we have an event picked, and we will send the event id to EventFocus. EventFocus
                // will then query the database to get the list of performances for this event.
                // This might be changed at a later time, when we might get all the event data and
                // performance data at the same time in one big json object. If so, then we would send the
                // performance part of the object from here.
                thisEventPicked = ListOfEvents.get(position);
                String sTheEvent = thisEventPicked.getShortdesc();
                int eventid = thisEventPicked.getEventid();
                int vid = thisEventPicked.getVenueid();
                Toast.makeText(getApplicationContext(), sTheEvent, Toast.LENGTH_SHORT).show();
               Intent i = new Intent(getApplicationContext(), evfocus.class);
                i.putExtra("focusstr", sTheEvent);
                i.putExtra("eid", eventid);
                i.putExtra("qlimit", Integer.parseInt(sqlimit));
                i.putExtra("vid", vid);
                startActivity(i);  // goes into login success
            }
        });
///////////////////////////////
        // show the list of reservations. This list is like a shopping cart.
        // users can stay there for a while, updating and cancelling reservations.
        // Each time the reservations change, the user will redraw the list
        // therefore we do not get data from the databse here. Instead, we
        // let the resact get the data. We do send the userid, which we
        // grabbed up above and is in the jstr
        buttonMyRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), resact.class);
                i.putExtra("jstr", jstrUserID );
                startActivity(i);
                }
             });
        // perform the test function. call the php file that undeletes the reservations
        // we just deleted
        /* remove the buttontest for now...
        buttonTest.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String urlString = "https://www.seatstir.com/ptapp/ptresarg.php";
                 MakeStringRequest custr = new MakeStringRequest();
                 JsonObject json=new JsonObject();
                 json.addProperty("act", "uncancel");
                 String jstr2 = json.toString();
                 VolleyResponseListener listener = new VolleyResponseListener() {
                     @Override
                     public void onError(String m) {Toast.makeText(getApplicationContext(), "test error", Toast.LENGTH_SHORT).show();}

                     @Override
                     public void onResponse(String response) {
                         if (response.contains("success")){
                             Toast.makeText(getApplicationContext(), "did uncancel ", Toast.LENGTH_SHORT).show();
                             // crashes RT  ((resact)context).TestCall("try parsing this");
                             // success - assume db changed, so change data on screen
                         }
                         else
                             Toast.makeText(getApplicationContext(), "Login failure, try again", Toast.LENGTH_SHORT).show();
                     }
                 };
                 custr.MakeCustomStringRequest(getApplicationContext(), urlString, listener, jstr2);

             }
        } );
        remove buttontest  */

    }
}
