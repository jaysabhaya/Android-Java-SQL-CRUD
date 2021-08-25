package com.flexion.sqldatabase.crud;


public class User {
    int userid; String name; String contact;


    public User(int userid, String name, String contact) {
        this.userid = userid;
        this.name = name;
        this.contact = contact;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
