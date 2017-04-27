package com.rpl.kelompok1.gelo.models;

/**
 * Created by Lenovo on 25/04/2017.
 */

public class Order {
    public String idOrder, idLaundry, idUser, namaUser, namaLaundry, alamatLaundry, alamatUser, nomorUser, nomorLaundry, tipe, berat, harga, status, parfurm;

    public Order() {
    }

    public Order(String idOrder, String idLaundry, String idUser, String namaUser, String namaLaundry,
                 String alamatLaundry, String alamatUser, String nomorUser, String nomorLaundry, String tipe, String berat, String harga, String status,
                 String parfurm) {
        this.idOrder = idOrder;
        this.idLaundry = idLaundry;
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.namaLaundry = namaLaundry;
        this.alamatLaundry = alamatLaundry;
        this.alamatUser = alamatUser;
        this.nomorUser = nomorUser;
        this.nomorLaundry = nomorLaundry;
        this.tipe = tipe;
        this.berat = berat;
        this.harga = harga;
        this.status = status;
        this.parfurm = parfurm;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public void setIdLaundry(String idLaundry) {
        this.idLaundry = idLaundry;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    public String getAlamatLaundry() {
        return alamatLaundry;
    }

    public void setAlamatLaundry(String alamatLaundry) {
        this.alamatLaundry = alamatLaundry;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setAlamatUser(String alamatUser) {
        this.alamatUser = alamatUser;
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

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParfurm() {
        return parfurm;
    }

    public void setParfurm(String parfurm) {
        this.parfurm = parfurm;
    }
}
