package com.seatstir.andy.seat99;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import com.seatstir.andy.eventlistlib.EventList;
import com.android.volley.RequestQueue;
import com.seatstir.andy.volleylib.MakeStringRequest;
import com.seatstir.andy.volleylib.VolleyResponseListener;
import com.seatstir.andy.volleylib.VolleySingleton;

public class MainActivity extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    */

 //   public class MainActivity extends Activity {

        TextView tv;
        String jstr = "testtest";
        // use a StringBuffer to hold the json coming back. This gives us some flexibility, but we
        // may want to change this back to just a String for release.
        StringBuffer jsb = new StringBuffer(4000);
        // JSONObject jobj = new JSONObject();
        //  String urlString = "https://seatstir.com/ptapp/jsontest.php";
        String urlString = "https://www.seatstir.com/ptapp/ptlogin.php";
        // String urlString = "https://seatstir.com/ptapp/Seat98bTest.php";
        // String urlString = "https://httpbin.org/post";
        //  String urlString = "http://api.androidhive.info/volley/person_object.json";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ///////// global data
            //   LoginData login = ((LoginData)getApplicationContext());
            //   login.setState("fred");
            //   String state = login.getState();
            //   Toast.makeText(MainActivity.this, state, Toast.LENGTH_SHORT).show();
            ///////// done global data

            Log.i("MainAct ", "OnCreate");

         //   tv = (TextView) findViewById(R.id.textView2);
            Button button = (Button) findViewById(R.id.buttonLogin);

            // Get a RequestQueue. If the VolleySingleton has not been created, this will
// new it and return a pointer to the instance
            RequestQueue queue = VolleySingleton.getInstance(this.getApplicationContext()).
                    getRequestQueue();
            // We don't need to have the queue here. Just get a string from the network
            // with networkStringLoader
            //Toast.makeText(MainActivity.this, "starting", Toast.LENGTH_SHORT).show();

            final EditText etUser = (EditText) findViewById(R.id.editTextUser);
            final EditText etPass = (EditText) findViewById(R.id.editTextPassword);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String kbuser = etUser.getText().toString();
                    String kbpass = etPass.getText().toString();
                //    String kbuser = "cjbyrne@gmail.com";
                //    String kbpass = "ffft";
                    //    int i = Integer.parseInt(editText.getText().toString());
                    VolleySingleton.getInstance(MainActivity.this).setGloginEmail(kbuser);
                    VolleySingleton.getInstance(MainActivity.this).setGloginPass(kbpass);

                    // Each time the submit button is hit we create a new String Loader. This
                    // is so we can log in as different users from the same phone
                    // OK the new volley front end!
                    VolleyResponseListener listener = new VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(MainActivity.this, "login error" + message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response) {
                            if (response.contains("success")){
                             //   Toast.makeText(MainActivity.this, "good", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), EventList.class);
                                i.putExtra("jstr", response);  // send the entire response, EventList will parse
                                startActivity(i);
                            }
                            else
                                Toast.makeText(MainActivity.this, "Login failure, try again", Toast.LENGTH_SHORT).show();
                        }
                    };
                    MakeStringRequest custr = new MakeStringRequest();
                    String jstr2;
                    JSONObject json=new JSONObject();
                    try {
                    //   json.addProperty("act", "update");
                    //   json.addProperty("login_password", "info");
                    //   json.addProperty("login_email", "info@fillaseatcleveland.com");
                    json.put("login_password", kbpass);
                    json.put("login_email", kbuser);
                  //  jstr2 = json.toString();
                    //   myEvents.setAdapter(new ResactAdapter(this,testContents);
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error parsing data " + e.toString());
                }
                //    custr.MakeCustomStringRequest(MainActivity.this, urlString, listener, jstr2);
                    custr.MakeCustomStringRequest(MainActivity.this, urlString, listener, json.toString());


                }
            });


        }
    }

