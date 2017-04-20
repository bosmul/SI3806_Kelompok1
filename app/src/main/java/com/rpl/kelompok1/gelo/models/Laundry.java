package com.rpl.kelompok1.gelo.models;

/**
 * Created by Lenovo on 08/04/2017.
 */

public class Laundry {
    private String id, name, email, password, alamat, telepon;

    public Laundry() {
    }

    public Laundry(String id, String name, String email, String alamat, String telepon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.alamat = alamat;
        this.telepon = telepon;
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
