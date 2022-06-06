/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import com.source.model.Users;
import com.source.utils.XJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class UsersDAO extends ShowRoomOtoDAO<Users, String>{
    
    String INSERT_SQL = "INSERT INTO Userr(Username, HoTen, MaNV, MatKhau, Email, Sodt, VaiTro,Hinh)VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE Userr SET HoTen=?, MaNV=? ,MatKhau=?, Email=?, Sodt=?, VaiTro=? ,Hinh= ?WHERE Username=?";
    String DELETE_SQL = "DELETE FROM Userr WHERE Username=?";
    String SELECT_ALL_SQL = "SELECT *  FROM Userr";
    String SELECT_BY_ID_SQL = "SELECT * FROM Userr WHERE Username=?";

    @Override
    public void insert(Users entity) {
        XJDBC.update(INSERT_SQL , 
                entity.getUsername(),
                entity.getHoTen(),
                entity.getMaNV(),
                entity.getMatKhau(), 
                entity.getEmail(), 
                entity.getSodt(),
                entity.getVaiTro(),
                entity.getHinh());
    }

    @Override
    public void update(Users entity) {
        XJDBC.update(UPDATE_SQL,
                entity.getHoTen(),
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getEmail(), 
                entity.getSodt(),
                entity.getVaiTro(),
                entity.getUsername(),
                entity.getHinh());
    }

    @Override
    public void delete(String id) {
        XJDBC.update(DELETE_SQL, id);
    }

    @Override
    public Users selectById(String id) {
        List<Users> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
        return null;
        }
        return list.get(0);
    }

    @Override
    public List<Users> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<Users> selectBySql(String sql, Object... args) {
        List<Users> list = new ArrayList<Users>();
        try {
            ResultSet rs = XJDBC.query(sql, args);
                while (rs.next()) {
                    Users entity = new Users();
                    entity.setUsername(rs.getString("Username"));
                    entity.setHoTen(rs.getString("HoTen"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setSodt(rs.getString("Sodt"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
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
    
    //////////////////////////////////////////////
    //////////////////////////////////////////////
//    Connection con=null;
//    public Vector<Users> docusername()
//    { Vector<Users> vec = new Vector<Users>();
//        try
//        {
//       
//        String sql="select * from User";
//        Statement sm=con.createStatement();
//        ResultSet rs=sm.executeQuery(sql);
//        while(rs.next())
//        {
//            Users us = new Users();
//            us.setUsername(rs.getString(1));
//                  us.setHoTen(rs.getString(2));
//                  us.setMatKhau(rs.getString(3));
//                  us.setEmail(rs.getString(4));
//                  us.setSodt(rs.getString(5));
////                  us.setVaiTro(rs.getBoolean(6));
//            vec.add(us);
//        }
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return vec;
//    }
//        public ArrayList<Users> tim(Users user){
//            ArrayList<Users> ustim = new ArrayList<>();
//            try
//            {
//           String sql="select * from Userr where Username like N'%" + user.getUsername() + "%' or HoTen like N'%" + user.getHoTen()+ "%' or MatKhau like '" 
//                   + user.getMatKhau()+"' or Email like '%" + user.getEmail()+"%' or Sodt like '" + user.getSodt()+"'";
//                    //or VaiTro=" + user.getVaiTro(); //N'%" + user.getUsername() + "%' 
//                System.out.println(sql);                                //or HoTen like N'%" + user.getHoTen()+ "%' 
//            Statement st=con.createStatement();                          //or MatKhau like '" + user.getMatKhau()+"' 
//              ResultSet rs= st.executeQuery(sql);                       //or Email like '" + user.getEmail()+"'
//              while(rs.next())                                          //or Sodt like '" + user.getSodt()+"'
//              {
//                  Users us = new Users();
//                  us.setUsername(rs.getString(1));
//                  us.setHoTen(rs.getString(2));
//                  us.setMatKhau(rs.getString(3));
//                  us.setEmail(rs.getString(4));
//                  us.setSodt(rs.getString(5));
////                  us.setVaiTro(rs.getBoolean(6));
//                ustim.add(us);
//               }
//            }
//            catch(Exception ex)
//            {
//                ex.printStackTrace();
//            }
//            return ustim;
//        }
        //////////////////
        ////////////////////
public List<Users> selectByKeyword(String keyword){
        String sql="SELECT * FROM Userr WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%"); 
    }

    
}
