package com.ministudio.bungkhus.mobiledevtest_khusnan.model;

import java.util.Date;

/**
 * Created by bungkhus on 7/14/16.
 */
public class Event {

    private String nama;
    private Date tanggal;
    private int photo;

    public Event(String nama, Date tanggal, int photo) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.photo = photo;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
