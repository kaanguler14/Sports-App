package com.example.demo;

import java.sql.Time;
import java.util.Date;

public class GameSearch {


    String name;
    Date Date;

    Time Time;

    String CountryNames;


    String Fname;

    String ChannelName;

    String Location;

    String Phone;

    public GameSearch(String name, java.sql.Date date, java.sql.Time time, String countryNames, String fname, String channelName, String location,String phone) {
        this.name = name;
        Date = date;
        Time = time;
        CountryNames = countryNames;
        Fname = fname;
        ChannelName = channelName;
        this.Location=location;
        this.Phone=phone;
    }

    public String getName() {
        return name;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public java.sql.Time getTime() {
        return Time;
    }

    public String getCountryNames() {
        return CountryNames;
    }

    public String getFname() {
        return Fname;
    }

    public String getChannelName() {
        return ChannelName;
    }


    public String getLocation() {
        return Location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public void setTime(java.sql.Time time) {
        Time = time;
    }

    public void setCountryNames(String countryNames) {
        CountryNames = countryNames;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }


    public void setLocation(String location) {
        Location = location;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
