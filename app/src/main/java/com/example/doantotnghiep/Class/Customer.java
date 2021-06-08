package com.example.doantotnghiep.Class;

import java.io.Serializable;

public class Customer implements Serializable {
    private String idtk, idkh;
    private String hoten;
    private String sdt;
    private String diachi;

    public Customer() {

    }
//    public Customer(int idtk, int idkh, String hoten, int sdt, String diachi) {
//        this.idtk = idtk;
//        this.idkh = idkh;
//        this.hoten = hoten;
//        this.sdt = sdt;
//        this.diachi = diachi;
//    }


    public String getIdtk() {
        return idtk;
    }

    public void setIdtk(String idtk) {
        this.idtk = idtk;
    }

    public String getIdkh() {
        return idkh;
    }

    public void setIdkh(String idkh) {
        this.idkh = idkh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public String toString() {
        return getHoten() + "" + getSdt() + "" + getDiachi();
    }

}
