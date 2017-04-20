package com.seatstir.andy.ptm;

// import android.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.seatstir.andy.volleylib.MakeStringRequest;
import com.seatstir.andy.volleylib.NetworkStringLoader;
import com.seatstir.andy.volleylib.VolleyResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by fred on 1/23/2016.
 */

class ResactAdapter extends ArrayAdapter<ResactData>
        // implements FCom
{
    Context context;
    TextView resText;
    TextView resDate;
    TextView resQ;
    ResactAdapter sk;
    ResactData resData;
    List<ResactData> sitems;
    String resargJ; // holds the json sting that is passed to ptresarg.php. It can be
                    // update or cancel.

    public ResactAdapter(Context context, int resourceId,
                      List<ResactData> items) {
        super(context, resourceId, items);
        this.context = context;
        this.sitems = items;
    }
    // build the json that will do the reservation changes, put that string
    // into resargJ
    public void buildresargJ(String arg, int rid) {
        //
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("act", arg);
            jsonObj.put("res_id", rid );
         //   jsonObj.put("qty", 3);
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }
        resargJ = jsonObj.toString();
    }
    public void buildresargJ(String arg, int rid, int q) {
    //
    JSONObject jsonObj = new JSONObject();
    try {
        jsonObj.put("act", arg);
        jsonObj.put("res_id", rid );
        jsonObj.put("qty", q);
    }
    catch(JSONException ex) {
        ex.printStackTrace();
    }
    resargJ = jsonObj.toString();
}



    /*private view holder class*/
    private class ViewHolder {
        TextView txtRes;
        TextView txtDesc;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int iresID;
        ViewHolder holder = null;
        resData = getItem(position);

        LayoutInflater fasInflater = LayoutInflater.from(getContext());
        View customView = fasInflater.inflate(R.layout.custom_res, parent, false); //xml for item
        sk = this;


        // setup the destination for the data
        resText = (TextView) customView.findViewById(R.id.textView);
        resDate = (TextView) customView.findViewById(R.id.textdate);
        resQ    = (TextView) customView.findViewById(R.id.textQ);

        // txtRes = (TextView) customView.findViewById(R.id.textView);
        //get the data from resData
        resText.setText(resData.getResDesc());
        resDate.setText(resData.getResDate());
        final int iq = resData.getResq();
        resQ.setText(Integer.toString(iq));
        iresID = resData.getResid();
        final int ac = this.getCount();
        Button buttonUpdate = (Button) customView.findViewById(R.id.UpdateButton);
      //  buttonUpdate.setLongClickable();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            int newq = iq == 2 ?  1 : 2;
            int newp = position;
            @Override
                public void onClick(View view) {
                buildresargJ("update", iresID, newq);

                // send the update to the database
                VolleyResponseListener listener = new VolleyResponseListener() {
                    @Override
                    public void onError(String m) {Toast.makeText(getContext(), "update login error", Toast.LENGTH_SHORT).show();}

                    @Override
                    public void onResponse(String response) {
                        if (response.contains("success")){
                          //  Toast.makeText(getContext(), "did update " + iresID, Toast.LENGTH_SHORT).show();
                          // crashes RT  ((resact)context).TestCall("try parsing this");
                            // success - assume db changed, so change data on screen
                            // throwing up  confirm screen seems like overkill, so for
                            // now lets just change the data on the screen
                            resData = getItem(position);

                            resData.putResQ(newq);
                          //  resQ.setText(Integer.toString(newq));

                            sk.notifyDataSetChanged();
                        }
                        else
                            Toast.makeText(getContext(), "update Login failure, try again", Toast.LENGTH_SHORT).show();
                    }
                };
                // Get the list of reservations for this user
               // String urlString = "https://seatstir.com/ptapp/jsontest.php";
                String urlString = "https://www.seatstir.com/ptapp/ptresarg.php";
                MakeStringRequest custr = new MakeStringRequest();
                custr.MakeCustomStringRequest(getContext(), urlString, listener, resargJ);

                }
            }); //end of button update
        Button buttonCancel = (Button) customView.findViewById(R.id.CancelButton);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // before we cancel this res, confirm with the user
                buildresargJ("cancel", iresID);

                DLFrag myDiag=new DLFrag();
                Activity ac;
                // how do we show the fragment without the fragment manager?
             //   AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 ac = (Activity) context;
              //  ac = getA;
                myDiag.show(ac.getFragmentManager(),"Diag");

                // docancel(resData);
            }
            }); //end of button cancel

        return customView;
    }
 //   private void docancel(final ResactData itemdata){
        public void docancel(){
    // resargJ holds the string that executes the cancel command
   // int cancelresID = resData.getResid();
   // Toast.makeText(getContext(), "cancelling " + cancelresID, Toast.LENGTH_SHORT).show();

    ////////////////
    VolleyResponseListener listener = new VolleyResponseListener() {
        @Override
        public void onError(String m) {
            Toast.makeText(getContext(), "login error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(String response) {
            if (response.contains("success")) {
                // the reslist was updated. we need to get the new reslist
                // and display it. In the future we might get the updated res
                // list as json in the return from the update, but for now
                // we will make a separate request for the res list.
                // What should we do to redisplay the reservation list?
              //  resData.putResDest("cancelled");
                Log.i("Cancel ",  "got success ");

            } else
                Toast.makeText(getContext(), "Login failure, try again", Toast.LENGTH_SHORT).show();
        }
    };
    // Do the reservation changes for this user
            Log.i("Cancel ",  "before request ");

            String urlString = "https://www.seatstir.com/ptapp/ptresarg.php";
    MakeStringRequest custr = new MakeStringRequest();
    custr.MakeCustomStringRequest(getContext(), urlString, listener, resargJ);
            Log.i("Cancel ",  "after request ");

        }
    //   private void docancel(final ResactData itemdata){
    // this is only called if we decide to put a confirm screen up for the
    // quantity update
    public void doupdate(){
        // resargJ holds the string that executes the cancel command
        // int cancelresID = resData.getResid();
        // Toast.makeText(getContext(), "cancelling " + cancelresID, Toast.LENGTH_SHORT).show();

        ////////////////
        VolleyResponseListener listener = new VolleyResponseListener() {
            @Override
            public void onError(String m) {
                Toast.makeText(getContext(), "login error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                if (response.contains("success")) {
                    // What should we do to redisplay the reservation list?
                    // GetResData(); // jstr gets passed straight to php code

                } else
                    Toast.makeText(getContext(), "Login failure, try again", Toast.LENGTH_SHORT).show();
            }
        };
        // Do the reservation changes for this user
        String urlString = "https://www.seatstir.com/ptapp/ptresarg.php";
        MakeStringRequest custr = new MakeStringRequest();
        custr.MakeCustomStringRequest(getContext(), urlString, listener, resargJ);
    }

}





