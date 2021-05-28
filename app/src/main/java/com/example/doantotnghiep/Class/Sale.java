package com.example.doantotnghiep.Class;

public class Sale {
    private  String id;
    private  String tencay;
    private  int gia;
    private  int dirty;
    private  String mota;
    private  String igmcay;


    public Sale() {
    }

    public Sale(String id, String tencay, int gia, int dirty, String mota, String igmcay) {
        this.id = id;
        this.tencay = tencay;
        this.gia = gia;
        this.dirty = dirty;
        this.mota = mota;
        this.igmcay = igmcay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTencay() {
        return tencay;
    }

    public void setTencay(String tencay) {
        this.tencay = tencay;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getDirty() {
        return dirty;
    }

    public void setDirty(int drity) {
        this.dirty = drity;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getIgmcay() {
        return igmcay;
    }

    public void setIgmcay(String igmcay) {
        this.igmcay = igmcay;
    }
 public int getsale(){
        return gia-(gia *dirty)/100;
    }
}
