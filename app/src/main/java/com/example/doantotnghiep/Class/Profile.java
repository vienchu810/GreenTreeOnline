package com.example.doantotnghiep.Class;

import java.io.Serializable;

public class Profile implements Serializable {
    private int id, sdt;
    private String taikhoan, gmail;
    private String dated,matkhau;


    public Profile(int id, int sdt, String taikhoan, String gmail, String dated, String matkhau) {
        this.id = id;
        this.sdt = sdt;
        this.taikhoan = taikhoan;
        this.gmail = gmail;
        this.dated = dated;
        this.matkhau = matkhau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}