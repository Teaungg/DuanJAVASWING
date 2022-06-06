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
public class NhanVien {
    private String MaNV ;
    private String HoTen;
    private Boolean GioiTinh;
    private String SoDT;
    private String Email; 
    private String DiaChi;
    private Double Luong;
    private Double ThuongHH;
    private String GhiChu;
    private String Hinh;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String HoTen, Boolean GioiTinh, String SoDT, String Email, String DiaChi, Double Luong, Double ThuongHH, String GhiChu, String Hinh) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.SoDT = SoDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.Luong = Luong;
        this.ThuongHH = ThuongHH;
        this.GhiChu = GhiChu;
        this.Hinh = Hinh;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(Boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Double getLuong() {
        return Luong;
    }

    public void setLuong(Double Luong) {
        this.Luong = Luong;
    }

    public Double getThuongHH() {
        return ThuongHH;
    }

    public void setThuongHH(Double ThuongHH) {
        this.ThuongHH = ThuongHH;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
     @Override
    public String toString() {
        return this.HoTen;
    }


    
}
