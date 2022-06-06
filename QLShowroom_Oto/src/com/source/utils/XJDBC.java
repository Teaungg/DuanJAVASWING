/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class XJDBC {
     private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    private static String url="jdbc:sqlserver://localhost:1433;database=QLShowRoomEternal";
    private static String username="sa";
    private static String password="123";
    
    /*
     * Nạp driver
     */
    static{
        try {            
            Class.forName(driver);
        } 
        catch (ClassNotFoundException ex) {
            System.out.println(ex);
            System.out.println("lỗi nek");
            throw new RuntimeException(ex);
        }
    }
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }
        else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    public static void update(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJDBC.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } 
            finally{
                stmt.getConnection().close();
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = XJDBC.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    
    public static Object value(String sql, Object...args) {
        try {
            ResultSet rs = XJDBC.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
