package com.seatstir.andy.logind;

import android.app.Application;

/**
 * Created by fred on 2/22/2016.
 */
public class LoginD extends Application {
    private String myState;

    public LoginD(){
        myState = "fred";
    }

    public LoginD(String v) { myState = v;}

    public String getState(){
        return myState;
    }
    public void setState(String s){
        myState = s;
    }
}
