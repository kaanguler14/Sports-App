package com.example.demo;

public class TeamSearchNation {

    Integer Number;
    Integer Age;

    String Country;

    String Fname;

    String Minit;

    String Lname;






    public TeamSearchNation(Integer number, Integer age, String country, String fname, String minit, String lname) {
        Number = number;
        Age = age;
        Fname = fname;
        Minit = minit;
        Lname = lname;
        Country=country;

    }

    public Integer getNumber() {
        return Number;
    }

    public Integer getAge() {
        return Age;
    }



    public String getFname() {
        return Fname;
    }
    public String getCountry() {
        return Country;
    }
    public String getMinit() {
        return Minit;
    }

    public String getLname() {
        return Lname;
    }




    public void setNumber(Integer number) {
        Number = number;
    }

    public void setAge(Integer age) {
        Age = age;
    }
    public void setCountry(String country) {
        Country = country;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setMinit(String minit) {
        Minit = minit;
    }

    public void setLname(String lname) {
        Lname = lname;
    }


}
