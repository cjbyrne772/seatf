package com.seatstir.andy.eventlistlib;

/**
 * Created by fred6 on 4/22/2017.
 */

// public class TixData {
class TixData {
    private int qtyAllowed;
    private String  perfDate;
    private String  ainfo;  // Additional info, could be long, might be specific to this perf
    private int perfID;
    private int qlimit;  // yes it looks kludgy, since we are storing a ticket limit for each
    // performance. But this looks like the easiest way to implement.
    private int qavail;
    private int eid; // included so we can easily check for 2 reservations on the same event. There
                     // may be a way of building the SQL query so we don't need the eid handy, but
                     // I'm not that good at SQL. Maybe come back to this later.

    public TixData() {}
    public TixData(String d, int p, int q) {
        this.perfDate = d;
        this.perfID = p;
        this.qlimit = q;
    }
    public TixData(String d, int p, int q, int a) {
        this.perfDate = d;
        this.perfID = p;
        this.qlimit = q;
        this.qavail = a;
    }
    public TixData(String d, int p, int q, int a, int e) {
        this.perfDate = d;
        this.perfID = p;
        this.qlimit = q;
        this.qavail = a;
        this.eid = e;
    }
    public TixData(String d, String ai, int p, int q, int a, int e) {
        this.perfDate = d;
        this.ainfo = ai;
        this.perfID = p;
        this.qlimit = q;
        this.qavail = a;
        this.eid = e;
    }

    public int getperfID() { return this.perfID; }
    public int getqlimit() { return this.qlimit; }
    public int getqavail() { return this.qavail; }
    public int geteid() { return this.eid; }
    public String getaddlInfo() { return this.ainfo;}
    public String getperfString() { return this.perfDate;}

}
