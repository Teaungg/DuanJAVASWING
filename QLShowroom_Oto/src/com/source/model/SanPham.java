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
public class SanPham {
    private String MaSP;
    private String TenSP;
    private Double DonGia;
    private int SoLuong;
    private String Hang;
    private String XuatXu;
    private String MauSac;
    private String PhanLoai;
    private String Hinh;
    private String GhiChu;
    private String QRImages;

    public SanPham(String MaSP, String TenSP, Double DonGia, int SoLuong, String Hang, String XuatXu, String MauSac, String PhanLoai, String Hinh, String GhiChu, String QRImages) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.Hang = Hang;
        this.XuatXu = XuatXu;
        this.MauSac = MauSac;
        this.PhanLoai = PhanLoai;
        this.Hinh = Hinh;
        this.GhiChu = GhiChu;
        this.QRImages = QRImages;
    }

    public SanPham() {
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    public String getXuatXu() {
        return XuatXu;
    }

    public void setXuatXu(String XuatXu) {
        this.XuatXu = XuatXu;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public String getPhanLoai() {
        return PhanLoai;
    }

    public void setPhanLoai(String PhanLoai) {
        this.PhanLoai = PhanLoai;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public String getQRImages() {
        return QRImages;
    }

    public void setQRImages(String QRImages) {
        this.QRImages = QRImages;
    }

 
   
}
