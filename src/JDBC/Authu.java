/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JDBC;

//import model.NhanVienEntity;

import model.NhanVien1;


/**
 *
 * @author duck1
 */
public class Authu {

     public static NhanVien1 user = null;

    public static void clear() {
        Authu.user = null;
    }

    public static Boolean isLogin() {
        return Authu.user != null;
    }
     public static boolean isManager() {
        return Authu.isLogin();
    }
}
