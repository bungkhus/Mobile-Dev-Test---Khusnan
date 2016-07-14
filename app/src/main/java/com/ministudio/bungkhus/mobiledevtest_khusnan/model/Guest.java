package com.ministudio.bungkhus.mobiledevtest_khusnan.model;

/**
 * Created by bungkhus on 7/14/16.
 */
public class Guest {

    private String id, nama, birthdate;
    private int photo;

    public Guest() {
    }

    public Guest(String id, String nama, String birthdate, int photo) {
        this.id = id;
        this.nama = nama;
        this.birthdate = birthdate;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
