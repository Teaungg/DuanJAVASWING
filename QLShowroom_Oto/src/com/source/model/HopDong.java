/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.model;

/**
 *
 * @author MXGC
 */
public class HopDong {
    String maHopDong;
    String tenHopDong;
    String maSP , maHD , maKH, maNV;

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getTenHopDong() {
        return tenHopDong;
    }

    public void setTenHopDong(String tenHopDong) {
        this.tenHopDong = tenHopDong;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public HopDong(String maHopDong, String tenHopDong, String maSP, String maHD, String maKH, String maNV) {
        this.maHopDong = maHopDong;
        this.tenHopDong = tenHopDong;
        this.maSP = maSP;
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
    }

    public HopDong() {
    }
    
}
