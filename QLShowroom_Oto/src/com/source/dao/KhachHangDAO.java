/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.model.KhachHang;
import com.source.model.Users;
import com.source.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MXGC
 */
public class KhachHangDAO extends ShowRoomOtoDAO<KhachHang, String> {

    @Override
    public void insert(KhachHang entity) {
        String INSERT_SQL = "INSERT INTO KhachHang(MaKH, HoTen, GioiTinh, SoDT, NgaySinh, DiaChi, Email, GhiChu)VALUES(?,?,?,?,?,?,?,?)";

        XJDBC.update(INSERT_SQL, 
                entity.getMaKH(),
                entity.getHoTen(),
                entity.getGioiTinh(),
                entity.getSoDT(),
                entity.getNgaySinh(),
                entity.getDiaChi(),
                entity.getEmail(),
                entity.getGhiChu()
        );
    }

    @Override
    public void update(KhachHang entity) {
        String UPDATE_SQL = "UPDATE KhachHang SET HoTen=?, GioiTinh=?, SoDT=?, NgaySinh=?, DiaChi=?,Email=? ,GhiChu=? WHERE MaKH=?";

        XJDBC.update(UPDATE_SQL,
                entity.getMaKH(),
                entity.getHoTen(),
                entity.getGioiTinh(),
                entity.getSoDT(),
                entity.getNgaySinh(),
                entity.getDiaChi(),
                entity.getEmail(),
                entity.getGhiChu()
        );
    }

    @Override
    public void delete(String id) {
        String DELETE_SQL = "DELETE FROM KhachHang WHERE MaKH=?";

        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public KhachHang selectById(String id) {
     
        String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang WHERE MaKH=?";

       List<KhachHang> list = selectBySql(SELECT_BY_ID_SQL, id );
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhachHang> selectAll() {
        String SELECT_ALL_SQL = "SELECT *  FROM KhachHang";

        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<KhachHang>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
            while (rs.next()) {
                KhachHang entity = new KhachHang();
                entity.setMaKH(rs.getString("MaKH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setSoDT(rs.getString("SoDT"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);

            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException(ex);
        }
    }

    public List<KhachHang> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM KhachHang WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

//    public List<KhachHang> selectNotInCourse(int makh, String keyword) {
//        String sql="SELECT * FROM NguoiHoc "
//                + " WHERE HoTen LIKE ? AND "
//                + " MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
//        return this.selectBySql(sql, "%"+keyword+"%", makh);
//    }
      public List<KhachHang> selectByKhachHang(String hoten){
       // String sql="SELECT * FROM KhachHang WHERE HoTen Like ?";
         String sql = "select  MaKH from KhachHang where TenKH = ?";
        return this.selectBySql(sql, hoten);
    }
}
