/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.utils;

import com.source.model.Users;

/**
 *
 * @author DELL
 */
public class Auth {
    
    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
     */
    public static Users user = null;
    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }
    /**
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }
     /**                                                    
     * Kiểm tra xem có phải là trưởng phòng hay không
     */
    public static boolean isManager() {
        return Auth.isLogin() && user.getVaiTro();
    }
}
