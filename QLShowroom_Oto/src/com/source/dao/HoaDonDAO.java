/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.model.GioHang;
import com.source.model.KhachHang;
import com.source.model.HoaDon;
import com.source.model.SanPham;
import com.source.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teaung
 */
public class HoaDonDAO extends ShowRoomOtoDAO<HoaDon, Integer>{

    @Override
    public void insert(HoaDon entity) {
          String INSERT_SQL = "INSERT INTO HoaDon(TenKH,MaGH, MaNV, NgayLap, TongTien, QRCode)VALUES(?,?,?,?,?,?)";

        XJDBC.update(INSERT_SQL, 
                
                
                entity.getTenKH(),
                entity.getMaGH(),
                entity.getMaNV(),
                entity.getNgayLap(),
                entity.getTongTien(),
                entity.getQrCode()
               
               
        );
    }

    @Override
    public void update(HoaDon entity) {
         String UPDATE_SQL = "UPDATE HoaDon SET MaKH=?, MaGH= ?,MaNV=?, NgayLap=?, TongTien=? WHERE MaHD=?";

        XJDBC.update(UPDATE_SQL,
                  
              
                entity.getTenKH(),
                entity.getMaNV(),
                entity.getNgayLap(),
                entity.getTongTien()
               
        );
    }

    @Override
    public void delete(Integer id) {
         String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHD=?";

        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public HoaDon selectById(Integer id) {
          String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHD=?";
         List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
        return null;
        }
        return list.get(0);
        
    }

    @Override
    public List<HoaDon> selectAll() {
        String SELECT_ALL_SQL = "SELECT *  FROM HoaDon";

        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
         List<HoaDon> list = new ArrayList<HoaDon>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon();
                entity.setTenKH(rs.getString("TenKH"));
                entity.setMaGH(rs.getInt("MaGH"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayLap(rs.getDate("NgayLap"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setQrCode(rs.getString("QRCode"));
                list.add(entity);

            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }
    }
      public List<HoaDon> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HoaDon WHERE MaHD LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    
}
