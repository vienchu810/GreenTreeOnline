package com.example.doantotnghiep.Class;

import java.io.Serializable;

public class Product implements Serializable {
    int idsp;
    String tensp;
    int giasp;
    String igmsp;
    String mota;

    public Product() {

    }

    public Product(int id, String tensp, int giasp, String igmsp, String mota) {
        this.idsp = id;
        this.tensp = tensp;
        this.giasp = giasp;
        this.igmsp = igmsp;
        this.mota = mota;

    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getIgmsp() {
        return igmsp;
    }

    public void setIgmsp(String igmsp) {
        this.igmsp = igmsp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}

