/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.dao.ShowRoomOtoDAO;
import com.source.dao.ShowRoomOtoDAO;
import com.source.model.GioHang;
import com.source.model.KhachHang;
import com.source.utils.Auth;
import com.source.utils.XDate;
import com.source.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teaung
 */
public class GioHangDAO extends ShowRoomOtoDAO<GioHang, String>{

    @Override
    public void insert(GioHang entity) {
          String sql="INSERT INTO GioHang (MaSP,SoLuong,DonGia ) VALUES (?, ?,?)";
        XJDBC.update(sql, 
               
                entity.getMaSP(),
                entity.getSoLuong(),
                entity.getDonGia()
      
        );
    }
    

    @Override
    public void update(GioHang entity) {
         String sql="UPDATE HoaDon SET  MaSP=?,SoLuong=?, DonGia=? WHERE MaHD=?";
        XJDBC.update(sql, 
                 entity.getMaGH(), 
                entity.getMaSP(),
                entity.getSoLuong(),
                entity.getDonGia()
          
        );
    }

    @Override
    public void delete(String id) {
          String sql="DELETE FROM GioHang WHERE MaGH=?";
        XJDBC.update(sql, id);
    }

    @Override
    public GioHang selectById(String mahd) {
        String sql="SELECT * FROM GioHang WHERE MaGH=?";
        List<GioHang> list = selectBySql(sql, mahd);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<GioHang> selectAll() {
        String sql="SELECT * FROM GioHang";
        return selectBySql(sql);
    }

    @Override
    protected List<GioHang> selectBySql(String sql, Object... args) {
        List<GioHang> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while(rs.next()){
             
                    GioHang entity = new GioHang();
                  
                    entity.setMaGH(rs.getInt("MaGH"));
                   entity.setMaSP(rs.getString("MaSP"));
                   entity.setSoLuong(rs.getInt("SoLuong"));
                   entity.setDonGia(rs.getDouble("DonGia"));

                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<GioHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM GioHang WHERE MaGH LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
         public static String DinhDangTien(double tien) {
        return NumberFormat.getNumberInstance().format(tien);
    }
             public static double ChuyenTien(String tien) {
        try {
            return NumberFormat.getNumberInstance().parse(tien).doubleValue();
        } catch (ParseException ex) {
        }
        return 0;
    }
}
