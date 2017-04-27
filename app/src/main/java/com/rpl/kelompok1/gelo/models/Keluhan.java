package com.rpl.kelompok1.gelo.models;

/**
 * Created by Lenovo on 28/04/2017.
 */

public class Keluhan {
    public String idKeluhan, idUser, idLaundry, namaUser, namaLaundry, nomorUser, nomorLaundry, isi, feedback;

    public Keluhan() {
    }

    public Keluhan(String idKeluhan, String idUser, String idLaundry, String namaUser, String namaLaundry, String nomorUser, String nomorLaundry, String isi, String feedback) {
        this.idKeluhan = idKeluhan;
        this.idUser = idUser;
        this.idLaundry = idLaundry;
        this.namaUser = namaUser;
        this.namaLaundry = namaLaundry;
        this.nomorUser = nomorUser;
        this.nomorLaundry = nomorLaundry;
        this.isi = isi;
        this.feedback = feedback;
    }

    public String getIdKeluhan() {
        return idKeluhan;
    }

    public void setIdKeluhan(String idKeluhan) {
        this.idKeluhan = idKeluhan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public void setIdLaundry(String idLaundry) {
        this.idLaundry = idLaundry;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNamaLaundry() {
        return namaLaundry;
    }

    public void setNamaLaundry(String namaLaundry) {
        this.namaLaundry = namaLaundry;
    }

    public String getNomorUser() {
        return nomorUser;
    }

    public void setNomorUser(String nomorUser) {
        this.nomorUser = nomorUser;
    }

    public String getNomorLaundry() {
        return nomorLaundry;
    }

    public void setNomorLaundry(String nomorLaundry) {
        this.nomorLaundry = nomorLaundry;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
