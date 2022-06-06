/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author MXGC
 */
public class XImages {
    public static Image getAppIcon(){
        String file = "/com/source/Icon/img.png";
        return new ImageIcon(XImages.class.getResource(file)).getImage();
    }
    public static Image getAppIconTren(){
        String file = "/com/source/Icon/imgg.jpg";
        return new ImageIcon(XImages.class.getResource(file)).getImage();
    }
//     hàm lưu ảnh và lấy ảnh nhân viên
    public static void savehinhNV(File src){ 
        File dst = new File("src/com/source/ImgNhanVien",src.getName());
        if(!dst.getParentFile().exists()){ 
            dst.getParentFile().mkdirs();
        } 
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon readHinhNV(String fileName){
        File path = new File("src/com/source/ImgNhanVien", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
//--------------------------------------
    // hàm lưu và lấy ảnh sản phẩm
        public static void savehinhSP(File src){ 
        File dst = new File("src/com/source/ImgSanPham",src.getName());
        if(!dst.getParentFile().exists()){ 
            dst.getParentFile().mkdirs();
        } 
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon readHinhSP(String fileName){
        File path = new File("src/com/source/ImgSanPham", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    //--------------------------------------
    // hàm lưu và lấy ảnh QR code sp
        public static void saveQRSanPham(File src){ 
        File dst = new File("src/com/source/qrcode",src.getName());
        if(!dst.getParentFile().exists()){ 
            dst.getParentFile().mkdirs();
        } 
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon readQRSanPham(String fileName){
        File path = new File("src/com/source/qrcode", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
//-----------------------------------------
    
     // hàm lưu và lấy ảnh QR code sp
        public static void saveQRHoaDon(File src){ 
        File dst = new File("src/com/source/qrcodeHoaDon",src.getName());
        if(!dst.getParentFile().exists()){ 
            dst.getParentFile().mkdirs();
        } 
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon readQRHoaDon(String fileName){
        File path = new File("src/com/source/qrcodeHoaDOn", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
//-----------------------------------------
    public static void save(File src){ 
        File dst = new File("/src/com/source/ImgNhanVien",src.getName());
        if(!dst.getParentFile().exists()){ 
            dst.getParentFile().mkdirs();
        } 
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    public static ImageIcon read(String fileName){
        File path = new File("Images", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
