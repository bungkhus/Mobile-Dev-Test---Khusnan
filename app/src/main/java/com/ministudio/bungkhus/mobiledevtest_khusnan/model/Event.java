package com.ministudio.bungkhus.mobiledevtest_khusnan.model;

import java.util.Date;
import java.util.List;

/**
 * Created by bungkhus on 7/14/16.
 */
public class Event {

    private String nama;
    private String desc;
    private Date tanggal;
    private int photo;
    private List<String> tag;

    public Event(String nama, String desc, Date tanggal, int photo, List<String> tag) {
        this.nama = nama;
        this.desc = desc;
        this.tanggal = tanggal;
        this.photo = photo;
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
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
