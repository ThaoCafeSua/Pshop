/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Model_Voucher;

/**
 *
 * @author Hà bet
 */
public class Repositories_Voucher {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model_Voucher> getAll() {
        sql = "SELECT id,ma_voucher,ten_voucher,ngay_bat_dau,ngay_ket_thuc,muc_giam,hinh_thuc,so_luong,ngay_tao,ngay_sua,trang_thai FROM Voucher";
        ArrayList<Model_Voucher> list_Voucher = new ArrayList<>();
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String ma;
                String ten;
                Date nBD;
                Date nKT;
                String mg;
                String ht;
                int sl;
                Date nt;
                Date ns;
                boolean tt;

                id = rs.getInt(1);
                ma = rs.getString(2);
                ten = rs.getString(3);
                nBD = rs.getDate(4);
                nKT = rs.getDate(5);
                mg = rs.getString(6);
                ht = rs.getString(7);
                sl = rs.getInt(8);
                nt = rs.getDate(9);
                ns = rs.getDate(10);
                tt = rs.getBoolean(11);

                Model_Voucher m = new Model_Voucher(id, ma, ten, nBD, nKT, mg, ht, sl, nt, ns, tt);
                list_Voucher.add(m);
            }
            return list_Voucher;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public int them(Model_Voucher m) {
        sql = "INSERT INTO Voucher (ma_voucher,ten_voucher, ngay_bat_dau, ngay_ket_thuc, muc_giam, hinh_thuc, so_luong, ngay_tao, ngay_sua, trang_thai) \n"
                + "VALUES (?,?,?,?,?,?,?,GETDATE(),GETDATE(),?);";
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, m.getMaVc());
            ps.setObject(2, m.getTenVc());
            ps.setObject(3, m.getNgayBatDau());
            ps.setObject(4, m.getNgayKetThuc());
            ps.setObject(5, m.getMucGiam());
            ps.setObject(6, m.getHinhThuc());
            ps.setObject(7, m.getSoLuong());
            ps.setObject(8, m.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int themPhanTram(Model_Voucher m) {
        sql = "INSERT INTO Voucher (ma_voucher,ten_voucher, ngay_bat_dau, ngay_ket_thuc, muc_giam, hinh_thuc, so_luong, ngay_tao, ngay_sua, trang_thai) \n"
                + "VALUES (?,?,?,?,?,?,?,GETDATE(),GETDATE(),?);";
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, m.getMaVc());
            ps.setObject(2, m.getTenVc());
            ps.setObject(3, m.getNgayBatDau());
            ps.setObject(4, m.getNgayKetThuc());
            ps.setObject(5, m.getMucGiam() + "%");
            ps.setObject(6, m.getHinhThuc());
            ps.setObject(7, m.getSoLuong());
            ps.setObject(8, m.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public int xoa(int id) {
        sql = "DELETE FROM Voucher WHERE id = ?";
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int sua(int id, Model_Voucher m) {
        sql = "UPDATE Voucher SET ma_voucher=?, ten_voucher=?,muc_giam=?,hinh_thuc=?,so_luong=?,trang_thai = ?,ngay_bat_dau=?,ngay_ket_thuc=?,ngay_sua = GETDATE() WHERE id = ?";
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, m.getMaVc());
            ps.setObject(2, m.getTenVc());
            ps.setObject(3, m.getMucGiam());
            ps.setObject(4, m.getHinhThuc());
            ps.setObject(5, m.getSoLuong());
            ps.setObject(6, m.isTrangThai());
            ps.setObject(7, m.getNgayBatDau());
            ps.setObject(8, m.getNgayKetThuc());
            ps.setObject(9, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    public int suaSoLuong(String maVoucher) {
        sql = "UPDATE Voucher SET so_luong= so_luong -1 WHERE ma_voucher = ?";
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, maVoucher);                 
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    public int suaTrangThai(int id) {
        sql = "UPDATE Voucher SET trang_thai =0 WHERE id = ?";
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareCall(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public ArrayList<Model_Voucher> loc(String HT, boolean TT) {
        sql = "SELECT id,ma_voucher,ten_voucher,ngay_bat_dau,ngay_ket_thuc,muc_giam,hinh_thuc,so_luong,ngay_tao,ngay_sua,trang_thai FROM Voucher WHERE hinh_thuc =  ?  AND trang_thai = ?";
        ArrayList<Model_Voucher> list_Voucher = new ArrayList();
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, HT);
            ps.setObject(2, TT);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String ma;
                String ten;
                Date nBD;
                Date nKT;
                String mg;
                String ht;
                int sl;
                Date nt;
                Date ns;
                boolean tt;

                id = rs.getInt(1);
                ma = rs.getString(2);
                ten = rs.getString(3);
                nBD = rs.getDate(4);
                nKT = rs.getDate(5);
                mg = rs.getString(6);
                ht = rs.getString(7);
                sl = rs.getInt(8);
                nt = rs.getDate(9);
                ns = rs.getDate(10);
                tt = rs.getBoolean(11);
                Model_Voucher m = new Model_Voucher(id, ma, ten, nBD, nKT, mg, ht, sl, nt, ns, tt);
                list_Voucher.add(m);
            }
            return list_Voucher;
        } catch (Exception e) {
            e.printStackTrace();//đưa Ra Lỗi
            return null;
        }
    }

    public ArrayList<Model_Voucher> timKiem(String TEN) {
        sql = "SELECT id,ma_voucher,ten_voucher,ngay_bat_dau,ngay_ket_thuc,muc_giam,hinh_thuc,so_luong,ngay_tao,ngay_sua,trang_thai FROM Voucher WHERE ten_voucher LIKE ? OR ma_voucher LIKE ? OR muc_giam = ? OR muc_giam = ?";
        ArrayList<Model_Voucher> list_Voucher = new ArrayList();
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + TEN + '%');
            ps.setObject(2, '%' + TEN + '%');
            ps.setObject(3, TEN);
             ps.setObject(4, TEN +" VND");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String ma;
                String ten;
                Date nBD;
                Date nKT;
                String mg;
                String ht;
                int sl;
                Date nt;
                Date ns;
                boolean tt;

                id = rs.getInt(1);
                ma = rs.getString(2);
                ten = rs.getString(3);
                nBD = rs.getDate(4);
                nKT = rs.getDate(5);
                mg = rs.getString(6);
                ht = rs.getString(7);
                sl = rs.getInt(8);
                nt = rs.getDate(9);
                ns = rs.getDate(10);
                tt = rs.getBoolean(11);
                Model_Voucher m = new Model_Voucher(id, ma, ten, nBD, nKT, mg, ht, sl, nt, ns, tt);
                list_Voucher.add(m);
            }
            return list_Voucher;
        } catch (Exception e) {
            e.printStackTrace();//đưa Ra Lỗi
            return null;
        }

    }

    public ArrayList<Model_Voucher> apMa(String maVoucher) {
        sql = "SELECT id,ma_voucher,ten_voucher,ngay_bat_dau,ngay_ket_thuc,muc_giam,hinh_thuc,so_luong,ngay_tao,ngay_sua,trang_thai FROM Voucher WHERE ma_voucher = ?";
        ArrayList<Model_Voucher> list_Voucher = new ArrayList();
        try {
            con = JDBC.jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maVoucher);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id;
                String ma;
                String ten;
                Date nBD;
                Date nKT;
                String mg;
                String ht;
                int sl;
                Date nt;
                Date ns;
                boolean tt;

                id = rs.getInt(1);
                ma = rs.getString(2);
                ten = rs.getString(3);
                nBD = rs.getDate(4);
                nKT = rs.getDate(5);
                mg = rs.getString(6);
                ht = rs.getString(7);
                sl = rs.getInt(8);
                nt = rs.getDate(9);
                ns = rs.getDate(10);
                tt = rs.getBoolean(11);
                Model_Voucher m = new Model_Voucher(id, ma, ten, nBD, nKT, mg, ht, sl, nt, ns, tt);
                list_Voucher.add(m);
            }
            return list_Voucher;
        } catch (Exception e) {
            e.printStackTrace();//đưa Ra Lỗi
            return null;
        }

    }
}
