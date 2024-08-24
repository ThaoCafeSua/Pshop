/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import JDBC.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;

/**
 *
 * @author admin
 */
public class HoaDonDao {

    public String getTen(String sql, int ma) {
        String x = null;
        try {
            ResultSet rs = null;
            try {
                rs = jdbc.query(sql, ma);
                while (rs.next()) {
                    x = rs.getString(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
            return x;
        } catch (SQLException ex) {
            return null;
        }
    }

    public String getTenNV(int id) {
        String sql = "select ten_nhan_vien FROM NhanVien where id = ?";
        String x = null;
        x = getTen(sql, id);
        return x;
    }
    
    public int getidNV(String ma) {
        String sql = "select id FROM NhanVien where ten_nhan_vien = ?";
        return getid(sql, ma);
    }

    public int getid(String sql, String ma) {
        int x = 0;
        try {
            ResultSet rs = null;
            try {
                rs = jdbc.query(sql, ma);
                while (rs.next()) {
                    x = rs.getInt(1);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
            return x;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int getidKh(String ma) {
        String sql = "select id FROM khach_hang where ten_khach_hang = ?";
        return getid(sql, ma);
    }
    public List<String> getallnv() {
        List<String> list = new ArrayList<>();
        String sql = "select ten_nhan_vien FROM NhanVien ";
        try {
            ResultSet rs = null;
            try {
                rs = jdbc.query(sql);
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    public static void main(String[] args) {
        System.out.println(new HoaDonDao().getidNV(""));
    }
}
