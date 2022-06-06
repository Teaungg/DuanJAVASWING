/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.model.ChamCong;
import com.source.model.GioHang;
import com.source.utils.XJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Teaung
 */
public class ChamCongDAO extends ShowRoomOtoDAO<ChamCong, Integer>{

    @Override
    public void insert(ChamCong entity) {
           String sql="INSERT INTO ChamCong (HoTen,Ngay1,Ngay2,Ngay3,Ngay4,Ngay5,Ngay6,Ngay7,Ngay8,Ngay9,Ngay10,"
                   + "Ngay11,Ngay12,Ngay13,Ngay14,Ngay15,Ngay16,Ngay17,Ngay18,Ngay19,Ngay20,"
                   + "Ngay21,Ngay22,Ngay23,Ngay24,Ngay25,Ngay26,Ngay27,Ngay28,Ngay29,Ngay30,Ngay31 ) VALUES ( ?,?, ?,?,?, ?,?,?, ?,?,?"
                   + " ?, ?,?,?, ?,?,?, ?,?,?"
                   + " ?, ?,?,?, ?,?,?, ?,?,?,?";
        XJDBC.update(sql, 
                entity.getHoTen(),
                entity.getNgay1(),
                entity.getNgay2(),
                entity.getNgay3(),
                entity.getNgay4(),
                entity.getNgay5(),
                entity.getNgay6(),
                entity.getNgay7(),
                entity.getNgay8(),
                entity.getNgay9(),
                entity.getNgay10(),
                entity.getNgay11(),
                entity.getNgay12(),
                entity.getNgay13(),
                entity.getNgay14(),
                entity.getNgay15(),
                entity.getNgay16(),
                entity.getNgay17(),
                entity.getNgay18(),
                entity.getNgay19(),
                entity.getNgay20(),
                entity.getNgay21(),
                entity.getNgay22(),
                entity.getNgay23(),
                entity.getNgay24(),
                entity.getNgay25(),
                entity.getNgay26(),
                entity.getNgay27(),
                entity.getNgay28(),
                entity.getNgay29(),
                entity.getNgay30(),
                entity.getNgay31()

        );
    }

    @Override
    public void update(ChamCong entity) {
          String sql="UPDATE ChamCong SET  HoTen=?,Ngay1=? ,Ngay2=? ,Ngay3=? ,Ngay4=? ,Ngay5=? ,Ngay6=? ,Ngay7=? ,Ngay8=? ,Ngay9=? ,Ngay10=? ,"
                  + "Ngay11=? ,Ngay12=? ,Ngay13=? ,Ngay14=? ,Ngay15=? ,Ngay16=? ,Ngay17=? ,Ngay18=? ,Ngay19=? ,Ngay20=? ,"
                  + "Ngay21=? ,Ngay22=? ,Ngay23=? ,Ngay24=? ,Ngay25=? ,Ngay26=? ,Ngay27=? ,Ngay28=? ,Ngay29=? ,Ngay30=? ,Ngay31=? WHERE ID=?";
        XJDBC.update(sql, 
               entity.getHoTen(),
                entity.getNgay1(),
                entity.getNgay2(),
                entity.getNgay3(),
                entity.getNgay4(),
                entity.getNgay5(),
                entity.getNgay6(),
                entity.getNgay7(),
                entity.getNgay8(),
                entity.getNgay9(),
                entity.getNgay10(),
                entity.getNgay11(),
                entity.getNgay12(),
                entity.getNgay13(),
                entity.getNgay14(),
                entity.getNgay15(),
                entity.getNgay16(),
                entity.getNgay17(),
                entity.getNgay18(),
                entity.getNgay19(),
                entity.getNgay20(),
                entity.getNgay21(),
                entity.getNgay22(),
                entity.getNgay23(),
                entity.getNgay24(),
                entity.getNgay25(),
                entity.getNgay26(),
                entity.getNgay27(),
                entity.getNgay28(),
                entity.getNgay29(),
                entity.getNgay30(),
                entity.getNgay31()

          
        );
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChamCong selectById(Integer id) {
          String sql="SELECT * FROM ChamCong WHERE ID=?";
        List<ChamCong> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<ChamCong> selectAll() {
        String sql="SELECT * FROM ChamCong";
        return selectBySql(sql);
    }

    @Override
    protected List<ChamCong> selectBySql(String sql, Object... args) {
         List<ChamCong> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJDBC.query(sql, args);
                while(rs.next()){
             
                    ChamCong entity = new ChamCong();
                  
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setNgay1(rs.getBoolean("Ngay1"));
                    entity.setNgay2(rs.getBoolean("Ngay2"));
                    entity.setNgay3(rs.getBoolean("Ngay3"));
                    entity.setNgay4(rs.getBoolean("Ngay4"));
                    entity.setNgay5(rs.getBoolean("Ngay5"));
                    entity.setNgay6(rs.getBoolean("Ngay6"));
                    entity.setNgay7(rs.getBoolean("Ngay7"));
                    entity.setNgay8(rs.getBoolean("Ngay8"));
                    entity.setNgay9(rs.getBoolean("Ngay9"));
                    entity.setNgay10(rs.getBoolean("Ngay10"));
                    entity.setNgay11(rs.getBoolean("Ngay11"));
                    entity.setNgay12(rs.getBoolean("Ngay12"));
                    entity.setNgay13(rs.getBoolean("Ngay13"));
                    entity.setNgay14(rs.getBoolean("Ngay14"));
                    entity.setNgay15(rs.getBoolean("Ngay15"));
                    entity.setNgay16(rs.getBoolean("Ngay16"));
                    entity.setNgay17(rs.getBoolean("Ngay17"));
                    entity.setNgay18(rs.getBoolean("Ngay18"));
                    entity.setNgay19(rs.getBoolean("Ngay19"));
                    entity.setNgay20(rs.getBoolean("Ngay20"));
                    entity.setNgay21(rs.getBoolean("Ngay21"));
                    entity.setNgay22(rs.getBoolean("Ngay22"));
                    entity.setNgay23(rs.getBoolean("Ngay23"));
                    entity.setNgay24(rs.getBoolean("Ngay24"));
                    entity.setNgay25(rs.getBoolean("Ngay25"));
                    entity.setNgay26(rs.getBoolean("Ngay26"));
                    entity.setNgay27(rs.getBoolean("Ngay27"));
                    entity.setNgay28(rs.getBoolean("Ngay28"));
                    entity.setNgay29(rs.getBoolean("Ngay29"));
                    entity.setNgay30(rs.getBoolean("Ngay30"));
                    entity.setNgay31(rs.getBoolean("Ngay31"));
                   

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
    
}
