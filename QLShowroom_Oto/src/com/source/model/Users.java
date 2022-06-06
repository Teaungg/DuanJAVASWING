/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.model;

/**
 *
 * @author DELL
 */
public class Users {
    public String Username;
    String MaNV;
    private String hoTen;
    private String MatKhau;
    private String Email;
    private String Sodt;
    private Boolean vaiTro = false;
    public String hinh;

    
    public Users(String Username, String MaNV, String hoTen, String MatKhau, String Email, String Sodt, String hinh) {
        this.Username = Username;
        this.MaNV = MaNV;
        this.hoTen = hoTen;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.Sodt = Sodt;
        this.hinh = hinh;
    }

    public Users() {
        this.Username = "";
        this.MatKhau ="";   
        this.hinh = "";
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSodt() {
        return Sodt;
    }

    public void setSodt(String Sodt) {
        this.Sodt = Sodt;
    }

    public Boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(Boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
    

  
    
}
