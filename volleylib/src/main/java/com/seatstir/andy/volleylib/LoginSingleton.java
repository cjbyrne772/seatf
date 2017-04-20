package com.seatstir.andy.volleylib;

/**
 * Created by fred on 2/19/2016.
 */
public class LoginSingleton {
    String user_email;
    String user_pass;


    private static LoginSingleton ourInstance = new LoginSingleton();

    public static LoginSingleton getInstance() { return ourInstance;
    }

    private LoginSingleton() {
    }
}
