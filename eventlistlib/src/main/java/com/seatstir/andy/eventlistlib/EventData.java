package com.seatstir.andy.eventlistlib;

/**
 * Created by fred on 8/15/2015.
 */

public class EventData {
    private String shortdesc;
    private String venue;
    private int eventid;
    private int perfid;
    private int venueid;

    public EventData() {
    }
    // The constructor is called as the json data is parsed
    public EventData(String shortdesc, String venue) {
        this.shortdesc = shortdesc;
        this.venue = venue;
    }
    // The constructor is called as the json data is parsed
    public EventData(String shortdesc, String venue, int eid) {
        this.shortdesc = shortdesc;
        this.venue = venue;
        this.eventid = eid;
    }
    // The constructor is called as the json data is parsed
    public EventData(String shortdesc, String venue, int eid, int pid) {
        this.shortdesc = shortdesc;
        this.venue = venue;
        this.eventid = eid;
        this.perfid = pid;
    }
    // The constructor is called as the json data is parsed
    public EventData(String shortdesc, int eid, int vid) {
        this.shortdesc = shortdesc;
        this.eventid = eid;
        this.venueid = vid;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getVenue() {
        return venue;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getEventid() { return eventid;}

    public void setEventid(int eid) { this.eventid = eid;}

    public int getPerfid() { return perfid;}

    public void setPerfid(int pid) { this.perfid = pid;}

    public int getVenueid() { return venueid; }
}
