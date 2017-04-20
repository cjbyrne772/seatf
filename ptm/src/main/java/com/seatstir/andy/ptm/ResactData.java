package com.seatstir.andy.ptm;

import java.util.Date;

/**
 * Created by fred on 1/23/2016.
 */
public class ResactData {
    private int ResID;
    private String resDate;
    private String sdesc;
    private String venue;
    private int q;


    public ResactData() {}
    public ResactData(int r, String d, String dt, int q) {
        this.ResID = r;
        this.sdesc = d;
        this.resDate = dt;
        this.q = q; }

    public int getResid()    { return this.ResID; }
    public int getResq()    { return this.q; }
    public String getResDate()  { return this.resDate; }
    public String getResDesc()  { return this.sdesc;   }
    public String getResVenue() { return this.venue;  }
    public void putResDest(String d) {this.sdesc = d; }
    public void putResQ(int v) {this.q = v; }
    public void putResDate(String d) {this.resDate = d; }
}

