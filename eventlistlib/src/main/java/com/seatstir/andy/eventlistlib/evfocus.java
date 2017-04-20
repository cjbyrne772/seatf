package com.seatstir.andy.eventlistlib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static java.security.AccessController.getContext;

//public class evfocus extends AppCompatActivity {
    public class evfocus extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evfocus);

        Button button = (Button) findViewById(R.id.buttonresreve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Toast.makeText(getContext(), "Button perf ", Toast.LENGTH_SHORT).show();
                AlertDialog alertDialog = new AlertDialog.Builder(evfocus.this).create();
                alertDialog.setTitle("Test Navigation");
                alertDialog.setMessage("Click Cancel to stay here at [RESERVE]. Click OK to go to Event List");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent i = new Intent(evfocus.this, com.seatstir.andy.eventlistlib.EventList.class);
                                  i.putExtra("jstr", "testing stuff" );
                                  evfocus.this.startActivity(i);
                                finish();
                            }
                        });

                alertDialog.show();
              //  Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();

        } //oc


        }); // OCL
    } // onCreate
} //class
