package com.rpl.kelompok1.gelo.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by lalit on 9/12/2016.
 */
public class User {

    private String id, name, email, password, alamat, telepon;
    private LatLng mLatLng;


    public User(String id, String name, String email, String alamat, String telepon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.alamat = alamat;
        this.telepon = telepon;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
