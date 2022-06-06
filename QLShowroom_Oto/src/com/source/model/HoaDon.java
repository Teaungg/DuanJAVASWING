/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.model;

import java.util.Date;

/**
 *
 * @author Teaung
 */
public class HoaDon {
   
    int maHD;
    int maGH;
    String tenKH;
    String maNV;
    Date NgayLap;
    double tongTien;
    String qrCode;

    public HoaDon(int maGH, String tenKH, String maNV, Date NgayLap, double tongTien, String qrCode) {
        this.maGH = maGH;
        this.tenKH = tenKH;
        this.maNV = maNV;
        this.NgayLap = NgayLap;
        this.tongTien = tongTien;
        this.qrCode = qrCode;
    }

   
    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaGH() {
        return maGH;
    }

    public void setMaGH(int maGH) {
        this.maGH = maGH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date NgayLap) {
        this.NgayLap = NgayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public HoaDon() {
    }


    
   
   

  
    
}
