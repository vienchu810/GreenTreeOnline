package com.example.doantotnghiep.Class;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailOder implements Serializable {
    private String  giasp, soluong, sdt, tongtien, tamtinh, phiship;
    private String madonhang, tensp, tenkh, diachi, ngay, imgsp, trangthai, status;
    private List<DetailOder> listDetailOrder;
    private  List<Cart>cartList;
    public DetailOder() {

    }

    public DetailOder(String giasp, String soluong, String sdt, String tongtien, String tamtinh, String phiship, String madonhang, String tensp, String tenkh, String diachi, String ngay, String imgsp, String trangthai, String status, List<DetailOder> listDetailOrder, List<Cart> cartList) {
        this.giasp = giasp;
        this.soluong = soluong;
        this.sdt = sdt;
        this.tongtien = tongtien;
        this.tamtinh = tamtinh;
        this.phiship = phiship;
        this.madonhang = madonhang;
        this.tensp = tensp;
        this.tenkh = tenkh;
        this.diachi = diachi;
        this.ngay = ngay;
        this.imgsp = imgsp;
        this.trangthai = trangthai;
        this.status = status;
        this.listDetailOrder = listDetailOrder;
        this.cartList = cartList;
    }



    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getTamtinh() {
        return tamtinh;
    }

    public void setTamtinh(String tamtinh) {
        this.tamtinh = tamtinh;
    }

    public String getPhiship() {
        return phiship;
    }

    public void setPhiship(String phiship) {
        this.phiship = phiship;
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getImgsp() {
        return imgsp;
    }

    public void setImgsp(String imgsp) {
        this.imgsp = imgsp;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailOder> getListDetailOrder() {
        return listDetailOrder;
    }

    public void setListDetailOrder(List<DetailOder> listDetailOrder) {
        this.listDetailOrder = listDetailOrder;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public void addToListDetailOrder(DetailOder detailOrder){
        if (this.listDetailOrder == null){
            this.listDetailOrder = new ArrayList<>();
        }
        this.listDetailOrder.add(detailOrder);
    }
}