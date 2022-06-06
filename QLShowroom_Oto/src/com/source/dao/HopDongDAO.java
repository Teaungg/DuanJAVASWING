/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.dao.ShowRoomOtoDAO;
import com.source.model.GioHang;
import com.source.model.HopDong;
import com.source.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teaung
 */
public class HopDongDAO extends ShowRoomOtoDAO<HopDong, String>{

    @Override
    public void insert(HopDong entity) {
             String sql="INSERT INTO NguoiHoc (MaHopDong, TenHopDong, MaSP, MaHD,  MaKH ,MaNV) VALUES (?, ?, ?, ?, ?,?)";
        XJDBC.update(sql, 
                entity.getMaHopDong(), 
                entity.getTenHopDong(), 
                entity.getMaSP(), 
                entity.getMaHD(), 
                entity.getMaKH(),
                entity.getMaNV()

        );
    }

    @Override
    public void update(HopDong entity) {
          String sql="UPDATE HopDong SET TenHopDong=?, MaSP=?, MaHD=?, MaKH=?, MaNV=? WHERE MaHopDong=?";
        XJDBC.update(sql, 
                entity.getTenHopDong(), 
                entity.getMaSP(), 
                entity.getMaHD(), 
                entity.getMaKH(),
                entity.getMaNV()   
        );
    }

    @Override
    public void delete(String id) {
           String sql="DELETE FROM HopDong WHERE MaHopDong=?";
        XJDBC.update(sql, id);
    }

    @Override
    public HopDong selectById(String mahopdong) {
          String sql="SELECT * FROM HopDong WHERE MaHopDong=?";
        List<HopDong> list = selectBySql(sql, mahopdong);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HopDong> selectAll() {
         String sql="SELECT * FROM HoaDon";
        return selectBySql(sql);
    }

    @Override
    protected List<HopDong> selectBySql(String sql, Object... args) {
          List<HopDong> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while(rs.next()){
                   
                    HopDong entity = new HopDong();
                    entity.setMaHopDong(rs.getString("MaHopDong"));
                    entity.setTenHopDong(rs.getString("TenHopDong"));
                 
                    entity.setMaSP(rs.getString("MaSP"));
                     
                    entity.setMaHD(rs.getString("MaHD"));
                     entity.setMaKH(rs.getString("MaKH"));
                     entity.setMaNV(rs.getString("MaNV"));
              
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
    public List<HopDong> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM HopDong WHERE TenHopDong LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
}
