package com.seatstir.andy.eventlistlib;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.seatstir.andy.volleylib.MakeStringRequest;
import com.seatstir.andy.volleylib.VolleyResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fred6 on 4/22/2017.
 */

public class TixAdapter extends ArrayAdapter<TixData> {

    Context context;
    String resargJ; // holds the json sting that is passed to ptresarg.php.
    private Spinner spinnerTQ;
    // private String[] thisUserticpic = new String[20] ; //
    private int qSelected = 0;
    Button butAction;



    public TixAdapter(Context context, int resourceId,
                      List<TixData> items, int qlimit) {
        super(context, resourceId, items);
        this.context = context;
        Log.i("Construct ",  "context is set");
        Log.i("ticket limit ",  "limit " + qlimit);
        //  System.arraycopy( ticpic, 0, thisUserticpic, 0, qlimit-1 ); // add in check for =0
        //  for ( int i = 1; i <= qlimit; i = i + 1 ) {
        //  ticpic.add(Integer.toString(i)); }
        //ticpic.add("2");
        //ticpic.add("3");
      //  (Activity)context.finish();


    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtEvent;
        TextView txtVenue;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ArrayList<String> ticpic = new ArrayList<String>();

        //  private String[] ticpic;
        TixData tixData = getItem(position);  // we have the data for this array element
        LayoutInflater fasInflater = LayoutInflater.from(getContext());
        final View customView = fasInflater.inflate(R.layout.tix_layout2, parent, false); // xml for item
        //  NumberPicker PickTix;
        final int q;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final String dateString = tixData.getperfString();
        //  Date date = dt.parse(date_s);
        TextView perfDate = (TextView) customView.findViewById(R.id.textView12);
        final TextView perfq = (TextView) customView.findViewById(R.id.editTextTix);
        //  butAction = (Button) customView.findViewById(R.id.butReserveOrFull);
        ///////// do the calculation to figure out how high the spinner should go /////////////////////////////////////////////////////////
        int maxTixAllowedByPlan = tixData.getqlimit();
        int tixRemaining = tixData.getqavail();
        int maxTixYouCanPick = maxTixAllowedByPlan;
        String sTixAction = "reserve";
        if ((tixRemaining <= 0) || (maxTixAllowedByPlan == 0)) {
            maxTixYouCanPick = 0; // limited by tix available
            sTixAction = "full";
        } else if (tixRemaining < maxTixAllowedByPlan ) {
            maxTixYouCanPick = tixRemaining;
            sTixAction = ">>last<<<";
        }
        if (maxTixYouCanPick == 0) {
            ticpic.add("0"); }
        else {
            for (int i = 1; i <= maxTixYouCanPick; i = i + 1) {
                ticpic.add(Integer.toString(i));
            }
        }
        // move?
        spinnerTQ = (Spinner) customView.findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ticpic);
        //   ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ticpic);
        //   ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, thisUserticpic);
        // ArrayAdapter aax = new ArrayAdapter();
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //   butAction.setText("OkeyDokey");
        //Setting the ArrayAdapter data on the Spinner
        spinnerTQ.setAdapter(aa);
        //move?
        ///////////////////////////////////////////////////////////////////////

        spinnerTQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
                String item = parent.getItemAtPosition(position).toString();
                // this works beacuse "1" is at position 1
                //  qSelected = parent.getItemAtPosition(position);
                qSelected = position + 1;
                // this code is executed every time the spinner is displayed, as if the spinner
                // was selected. Whatever. But I guess we can ignore that, since what we really care about
                // is what the value is in the spinner when the reserve button is hit

                //  Toast.makeText(parent.getContext(), "spinner item selected..." + item, Toast.LENGTH_LONG).show();

                //  perfq.setText(qSelected);
                perfq.setText(item);
            }
        });
        //   PickTix = (NumberPicker) customView.findViewById(R.id.numberPicker);
        //   PickTix.setMaxValue(4);
        // get the ticket
        perfDate.setText(dateString);
        Button button = (Button) customView.findViewById(R.id.butReserveOrFull);
        final int iperfID = tixData.getperfID();
        final int iEid = tixData.geteid();
        // for debug purposes, print the number of tickets this user is allowed to reserve
        //


        button.setText("rf " + iperfID + " " + tixData.getqavail() + " " + sTixAction + " " + maxTixYouCanPick);
        if (maxTixYouCanPick != 0) { //can you make a reservation??

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    android.support.v7.app.AlertDialog.Builder mBuilder =
                            new android.support.v7.app.AlertDialog.Builder(context);
                    mBuilder.setTitle("Ticket Reservation");
                    String sTix = "tickets";
                    if (qSelected == 1) {
                        sTix = "ticket";
                    }
                    mBuilder.setMessage(dateString + "..." + iperfID + " " + qSelected + " " + sTix);
                    mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            makeres(iperfID, qSelected, iEid );
                            dialog.dismiss();
                        }
                    });
                    mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    //mBuilder.setNeutralButton("Test", new DialogInterface.OnClickListener() {
                    //    @Override
                    //    public void onClick(DialogInterface dialog, int which) {
                    //        testalert(999999, 33);
                    //        dialog.dismiss();
                    //    }
                    //});
                    android.support.v7.app.AlertDialog alertDialog = mBuilder.create();
                    alertDialog.show();


                    Toast.makeText(getContext(), "Button perf " + iperfID, Toast.LENGTH_SHORT).show();
                }
            });
        } // you are allowed to make a reservation

        return customView;
    }
    //////////////////////////////////////
    public void buildtixargJ( int pid, int q, int eid ) {
        //
        JSONObject jsonObj = new JSONObject();
        String appVersion;
        int myversionCode = 99;

        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            myversionCode = pinfo.versionCode;
            String versionName = pinfo.versionName;

            appVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();}

        try {
            jsonObj.put("act", "createnew");
            jsonObj.put("pid", pid );
            jsonObj.put("qty", q);
            jsonObj.put("eid", eid);
  //          jsonObj.put("ver", appVersion);
            jsonObj.put("ver", myversionCode );
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
        resargJ = jsonObj.toString();
    }

    // put makeres here
    public void makeres(int pid, int q, int eid){

        ////////////////
        VolleyResponseListener listener = new VolleyResponseListener() {
            @Override
            public void onError(String m) {
                Toast.makeText(getContext(), "login error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
              try {
                  String strResult, msg;

                  JSONObject jobjTop = new JSONObject(response);
                  JSONObject jobjuser;
                  // xxjstr = jobjTop.getString("user_id");
                  strResult = jobjTop.getString("CreateResult"); // str going to php
                  msg = jobjTop.getString("msg"); // str going to php

                  if (strResult.equals("success")) {
                      Toast.makeText(getContext(), "success on res create", Toast.LENGTH_SHORT).show();

                      // the reslist was updated. we need to get the new reslist
                      // and display it. In the future we might get the updated res
                      // list as json in the return from the update, but for now
                      // we will make a separate request for the res list.
                      // What should we do to redisplay the reservation list?
                      //  resData.putResDest("cancelled");
                      Log.i("Cancel ", "got success ");
                      goodres("reservation success");


                  } else {
                      Toast.makeText(getContext(), "Failure res create, previous", Toast.LENGTH_SHORT).show();
                      badres (msg);
                  }
              } catch (JSONException e) {
                  Log.e("JSON Parser", "Error parsing return from create " + e.toString());
              }
            }  // on response
        };  //VolleyResponse listener
        // Do the reservation changes for this user
        Log.i("Create ",  "before request ");

        buildtixargJ(pid, q, eid);  // create a new reservation for q tickets
        String urlString = "https://www.seatstir.com/ptapp/ptresarg.php";
        MakeStringRequest custr = new MakeStringRequest();
        custr.MakeCustomStringRequest(getContext(), urlString, listener, resargJ);
        Log.i("Create ",  "after request ");

    } //makeres
    public void goodres(String m) {
        android.support.v7.app.AlertDialog.Builder mBuilder =
                new android.support.v7.app.AlertDialog.Builder(context);
        mBuilder.setTitle("You have made the reservation");

        mBuilder.setMessage(m);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((Activity)context).finish();
            }
        });

        android.support.v7.app.AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
        // Toast.makeText(getContext(), m, Toast.LENGTH_SHORT).show();

    }
    public void badres(String m) {
        android.support.v7.app.AlertDialog.Builder mBuilder =
                new android.support.v7.app.AlertDialog.Builder(context);
        mBuilder.setTitle("Reservation Not Made");

        mBuilder.setMessage(m);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((Activity)context).finish();
            }
        });

        android.support.v7.app.AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
        // Toast.makeText(getContext(), m, Toast.LENGTH_SHORT).show();

    }

    ////  testalert will simulate a duplicate reservation. It acts just as if the
    /// user hit [CREATE]. For testing, we send a create to resarg. resarg will return "failure".
    /// Then we give the user the option to return to My Reservations.
    /// For the 1st try, just go back to My Reservations
    public void testalert(int pid, int q){
        VolleyResponseListener listener = new VolleyResponseListener() {
            @Override
            public void onError(String m) {
                Toast.makeText(getContext(), "login error", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(String response) {
                if (response.contains("success")) {
                    displayDialogTest();
                    Log.i("Cancel ",  "got success ");
                } else
                    Toast.makeText(getContext(), "Login failure, try again", Toast.LENGTH_SHORT).show();
            }
        };
        // 1st - just go to My Reservations.
        // Intent i = new Intent(getApplicationContext        Intent i = new Intent(getApplicationContext(), resact.class);

        // i have no idea why i cant get this to work
        //  com.seatstir.andy.
        //  Intent i = new Intent(this.context, eventlist.class);
        //  i.putExtra("jstr", "testing stuff" );
        //  this.context.startActivity(i);
        // Do the reservation changes for this user
        buildtixargJ(1234, 56, 789);  // create a new reservation for q tickets
        String urlString = "https://www.seatstir.com/ptapp/ptresarg.php";
        MakeStringRequest custr = new MakeStringRequest();
        custr.MakeCustomStringRequest(getContext(), urlString, listener, resargJ);
        Log.i("test ",  "after request ");

    }
    void displayDialogTest() {
        android.support.v7.app.AlertDialog.Builder mBuilder =
                new android.support.v7.app.AlertDialog.Builder(context);
        mBuilder.setTitle("Duplicate");

        mBuilder.setMessage("Bad idea");
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }); mBuilder.setNeutralButton("Test", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        android.support.v7.app.AlertDialog alertDialog = mBuilder.create();
        alertDialog.show();
    }

}
