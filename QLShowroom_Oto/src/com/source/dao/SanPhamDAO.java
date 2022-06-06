/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.model.SanPham;
import com.source.utils.XJDBC;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SanPhamDAO extends ShowRoomOtoDAO<SanPham, String> {

    String INSERT_SQL = "INSERT INTO SanPham(MaSP, TenSP, DonGia, SoLuong, Hang, XuatXu, MauSac, PhanLoai, Hinh, GhiChu,QRImages)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE SanPham SET TenSP=?, DonGia=?, SoLuong=?, Hang=?, XuatXu=?, MauSac=?, MaTL=?, Hinh=?, GhiChu=?,QRImages=? WHERE MaSP=?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE MaSP=?";
    String SELECT_ALL_SQL = "SELECT *  FROM SanPham";
   
    
    @Override
    public void insert(SanPham entity) {
        XJDBC.update(INSERT_SQL , 
                entity.getMaSP(),
                entity.getTenSP(),
                entity.getDonGia(), 
                entity.getSoLuong(), 
                entity.getHang(),
                entity.getXuatXu(),
                entity.getMauSac(),
                entity.getPhanLoai(),
                entity.getHinh(),
                entity.getGhiChu(),
                entity.getQRImages()
        );
    }

    @Override
    public void update(SanPham entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getTenSP(),
                entity.getDonGia(), 
                entity.getSoLuong(), 
                entity.getHang(),
                entity.getXuatXu(),
                entity.getMauSac(),
                entity.getPhanLoai(),
                entity.getHinh(),
                entity.getGhiChu(),
                entity.getMaSP(),
                 entity.getQRImages()
       
        );
    }

    @Override
    public void delete(String id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public SanPham selectById(String id) {
         String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSP=?";
        List<SanPham> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
        return null;
        }
        return list.get(0);
    }
     

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<SanPham>();
        try {
             ResultSet rs = XJDBC.query(sql, args);
                while (rs.next()) {
                    SanPham entity = new SanPham();
                    entity.setMaSP(rs.getString("MaSP"));
                    entity.setTenSP(rs.getString("TenSP"));
                    entity.setDonGia(rs.getDouble("DonGia"));
                    entity.setSoLuong(rs.getInt("SoLuong"));
                    entity.setHang(rs.getString("Hang"));
                    entity.setXuatXu(rs.getString("XuatXu"));
                    entity.setMauSac(rs.getString("MauSac"));
                    entity.setPhanLoai(rs.getString("PhanLoai"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setQRImages(rs.getString("QRImages"));
                    list.add(entity);
                }
                rs.getStatement().getConnection().close();
                return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }
    }
//    public List<SanPham> selectByPhanLoai(String PhanLoai) {
//        String sql = "SELECT * FROM  SanPham WHERE PhanLoai =? ";
//      //  return this.selectBySql(sql, PhanLoai);
//         return  this.selectByPhanLoai(sql);
//    }
        
    public List<SanPham> selectByKeyword(String keyword){
        String sql="SELECT * FROM SanPham WHERE MaSP LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%"); 
    }
     public List<SanPham> selectPhanLoai(int phanloai){
        String sql="SELECT * FROM SanPham WHERE PhanLoai LIKE ?";
        return this.selectBySql(sql, "%"+phanloai+"%"); 
    }
////    
////     public int find_IDLoai(String theloai){
////            String sql = "select TheLoai_ID  from TheLoai where Ten = ?";
////           return this.find_IDLoai(sql);
////    }
//       private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";  
//    private static String url="jdbc:sqlserver://localhost:1433;database=QLShowRoomEternal";
//    private static String username="sa";
//    private static String password="123";
//     public ArrayList<SanPham> findSPTheoTheLoaiID(String id){
//        ArrayList<SanPham> list = new ArrayList<SanPham>();
//        try {
//               java.sql.Connection connection = DriverManager.getConnection(url, username, password);
////            String url = "jdbc:sqlserver://DESKTOP-J5QL42J\\SQLEXPRESS:1433;database=QUANLYSACH";
////            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////          
//            
//            String sql ="select * from SanPham where MaTL = ?";
//            PreparedStatement pt = connection.prepareStatement(sql);
//            pt.setString(1, id);
//            ResultSet rs = pt.executeQuery();
//            while(rs.next()){
//                SanPham entity = new SanPham();
//                    entity.setMaSP(rs.getString("MaSP"));
//                    entity.setTenSP(rs.getString("TenSP"));
//                    entity.setDonGia(rs.getDouble("DonGia"));
//                    entity.setSoLuong(rs.getInt("SoLuong"));
//                    entity.setHang(rs.getString("Hang"));
//                    entity.setXuatXu(rs.getString("XuatXu"));
//                    entity.setMauSac(rs.getString("MauSac"));
//                    entity.setPhanLoai(rs.getInt("MaTL"));
//                    entity.setHinh(rs.getString("Hinh"));
//                    entity.setGhiChu(rs.getString("GhiChu"));
//                    entity.setQRImages(rs.getString("QRImages"));
//                    list.add(entity);
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }   
//      public String find_IDLoai(String theloai){
//           String id =null;  
//        try {
//              java.sql.Connection connection = DriverManager.getConnection(url, username, password);
////            String url = "jdbc:sqlserver://DESKTOP-J5QL42J\\SQLEXPRESS:1433;database=QUANLYSACH";
////            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////          
//            
//              String sql = "select MaTL  from TheLoai where Ten = ?";
//            PreparedStatement pt = connection.prepareStatement(sql);
//         
//           
//           pt.setString(1, theloai);
//            ResultSet rs = pt.executeQuery();
//            while(rs.next()){
//                id = rs.getString(1);
//            }
//        } catch (Exception e) {
//        }
//        return theloai;
//    }
}
