/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.dao.ShowRoomOtoDAO;
import com.source.dao.ShowRoomOtoDAO;
import com.source.model.KhachHang;
import com.source.model.NhanVien;
import com.source.utils.XJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoàng Tú
 */
public class NhanVienDAO  extends ShowRoomOtoDAO<NhanVien, String> {
         
    String INSERT_SQL = "INSERT INTO NhanVien(MaNV, HoTen, GioiTinh, SoDT, Email, DiaChi, Luong, ThuongHH, GhiChu, Hinh)VALUES(?,?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET HoTen=?, GioiTinh=?, SoDT=?, Email=?, DiaChi=?, Luong=?, ThuongHH=?, GhiChu=?, Hinh=? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT *  FROM NhanVien";
  
    
    @Override
    public void insert(NhanVien entity) {
          XJDBC.update(INSERT_SQL, 
                  entity.getMaNV(), 
                  entity.getHoTen(), 
                  entity.getGioiTinh(), 
                  entity.getSoDT(),
                  entity.getEmail(),
                  entity.getDiaChi(),
                  entity.getLuong(),
                  entity.getThuongHH(),
                  entity.getGhiChu(),
                  entity.getHinh()
          ); 
    }

    @Override
    public void update(NhanVien entity) {
        XJDBC.update(UPDATE_SQL,
                  entity.getHoTen(), 
                  entity.getGioiTinh(), 
                  entity.getSoDT(),
                  entity.getEmail(),
                  entity.getDiaChi(),
                  entity.getLuong(),
                  entity.getThuongHH(),
                  entity.getGhiChu(),
                  entity.getHinh(),
                  entity.getMaNV()                
          ); 
    }

    @Override
    public void delete(String id) {
          XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public NhanVien selectById(String id) {
          String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV=?";
         
       List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id );
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<NhanVien> selectAll() {
         String SELECT_ALL_SQL = "SELECT *  FROM NhanVien";
  
   
         return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setSoDT(rs.getString("SoDT"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setDiaChi(rs.getString("DiaChi"));
                    entity.setLuong(rs.getDouble("Luong"));
                    entity.setThuongHH(rs.getDouble("ThuongHH"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setHinh(rs.getString("Hinh"));
                    list.add(entity);
                    
                }
                rs.getStatement().getConnection().close();
                return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }
    }
    public List<NhanVien> selectByKeyword(String keyword){
        String sql="SELECT * FROM NhanVien WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%"); 
    }
    public List<NhanVien> selectByNhanVien(String manv){
        String sql="SELECT MaNV FROM NhanVien WHERE HoTen =?";
        return this.selectBySql(sql, manv);
    }
//     public String findMaNV(String tennv){
//         String manv=null;
//         try {
//                    String sql = "Select NhanVien_ID from NhanVien where HoVaTen = ?";
//                   
//                    while(rs.next()){
//                        manv = rs.getString(1);
//                    }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return manv;
//     }   
}
