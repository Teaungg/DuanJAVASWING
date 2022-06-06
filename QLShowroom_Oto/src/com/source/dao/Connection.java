/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.dao;

import java.sql.DriverManager;

/**
 *
 * @author Teaung
 */
public class Connection {
     protected java.sql.Connection con;
        
        public Connection(){
        try {
            String url = "jdbc:sqlserver://localhost:1433;database=QLShowRoomEternal";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url,"sa","123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
