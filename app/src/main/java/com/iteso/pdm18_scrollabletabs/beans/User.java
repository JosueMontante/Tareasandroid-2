package com.iteso.pdm18_scrollabletabs.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Viraloch on 12/3/2018.
 */

public class User implements Parcelable {

    String name, psw;
    boolean isLogged;

    public User(String name, String psw, boolean isLogged) {
        this.name = name;
        this.psw = psw;
        this.isLogged = isLogged;
    }

    public User (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.psw);
        dest.writeByte(this.isLogged ? (byte) 1 : (byte) 0);

    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.psw = in.readString();
        this.isLogged = in.readByte() != 0;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}


