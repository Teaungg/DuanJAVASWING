/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.model;

import java.util.Date;

/**
 *
 * @author MXGC
 */
public class GioHang {
    int maGH;
    String maSP;
    double  donGia , soLuong;

    public int getMaGH() {
        return maGH;
    }

    public GioHang() {
    }

    public GioHang(String maSP, double donGia, double soLuong) {
        this.maSP = maSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public void setMaGH(int maGH) {
        this.maGH = maGH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }
   

  

 

  
}
