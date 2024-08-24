package responsitory;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
import JDBC.jdbc;
import java.sql.*;
import java.util.ArrayList;
import model.Model_khachHang;

public class KhachHangRepository {

  private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<Model_khachHang> getAll() {
        ArrayList<Model_khachHang> list = new ArrayList<>();
        sql = "select id,ten_khach_hang,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao,ngay_sua,trang_thai from khachhang";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String ten = rs.getString(2);
                String ngaySinh = rs.getString(3);
                boolean gt = rs.getBoolean(4);
                String diaChi = rs.getString(5);
                String email = rs.getString(6);
                String sdt = rs.getString(7);
                String ngayTao = rs.getString(8);
                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                Model_khachHang t = new Model_khachHang(id, ten, ngaySinh, gt, diaChi, email, sdt, ngayTao, ngaySua, trangThai);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 public ArrayList<Model_khachHang> chonKhachHang() {
        ArrayList<Model_khachHang> list = new ArrayList<>();
        sql = "select id,ten_khach_hang,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao,ngay_sua,trang_thai from khachhang";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String ten = rs.getString(2);
                String ngaySinh = rs.getString(3);
                boolean gt = rs.getBoolean(4);
                String diaChi = rs.getString(5);
                String email = rs.getString(6);
                String sdt = rs.getString(7);
                String ngayTao = rs.getString(8);
                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                Model_khachHang t = new Model_khachHang(id, ten, ngaySinh, gt, diaChi, email, sdt, ngayTao, ngaySua, trangThai);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int them(Model_khachHang t) {
        sql = "insert into khachHang(ten_khach_hang,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao,ngay_sua,trang_thai) values (?,?,?,?,?,?,?,?,?)";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, t.getTen());
            ps.setObject(2, t.getNgaySinh());
            ps.setObject(3, t.isGioiTinh());
            ps.setObject(4, t.getDiaChi());
            ps.setObject(5, t.getEmail());
            ps.setObject(6, t.getSdt());
            ps.setObject(7, t.getNgayTao());
            ps.setObject(8, t.getNgaySua());
            ps.setObject(9, t.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int xoa(int id) {

        try {
          
            String sql = "DELETE FROM HoaDonChiTiet WHERE id=?";
            Connection con = jdbc.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            ps.executeUpdate();

           
         
           
            sql = "DELETE FROM KhachHang WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); 
            return 0; 
        }

    }

    public ArrayList< Model_khachHang> locAll(boolean gioiTinh, boolean trangThai) {
        ArrayList<Model_khachHang> list = new ArrayList<>();
        sql = "select id,ten_khach_hang,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao,ngay_sua,trang_thai from khachhang where gioi_tinh=? and trang_thai=?";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gioiTinh);
            ps.setObject(2, trangThai);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten_khach_hang");
                String ngaySinh = rs.getString("ngay_sinh");
                boolean gioiTinhDb = rs.getBoolean("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("so_dien_thoai");
                String ngayTao = rs.getString("ngay_tao");
                String ngaySua = rs.getString("ngay_sua");
                boolean trangThaiDB = rs.getBoolean("trang_thai");
                Model_khachHang t = new Model_khachHang(id, ten, ngaySinh, gioiTinhDb, diaChi, email, soDienThoai, ngayTao, ngaySua, trangThaiDB);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Model_khachHang> timKiem(String tenKH) {
        ArrayList<Model_khachHang> list = new ArrayList<>();
        sql = "select id,ten_khach_hang,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao,ngay_sua,trang_thai from khachhang where ten_khach_hang like ?";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%"+tenKH+"%");
            rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt(1);
                String ten = rs.getString(2);
                String ngaySinh = rs.getString(3);
                boolean gt = rs.getBoolean(4);
                String diaChi = rs.getString(5);
                String email = rs.getString(6);
                String sdt = rs.getString(7);
                String ngayTao = rs.getString(8);
                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                Model_khachHang t = new Model_khachHang(id, ten, ngaySinh, gt, diaChi, email, sdt, ngayTao, ngaySua, trangThai);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int sua(int id, Model_khachHang t) {
        sql = "update khachhang set ten_khach_hang=?,ngay_sinh=?,gioi_tinh=?,dia_chi=?,email=?,so_dien_thoai=?,ngay_tao=?,ngay_sua=?,trang_thai=?  where id=?";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(10, id);
            ps.setObject(1, t.getTen());
            ps.setObject(2, t.getNgaySinh());
            ps.setObject(3, t.isGioiTinh());
            ps.setObject(4, t.getDiaChi());
            ps.setObject(5, t.getEmail());
            ps.setObject(6, t.getSdt());
            ps.setObject(7, t.getNgayTao());
            ps.setObject(8, t.getNgaySua());
            ps.setObject(9, t.isTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int suaTrangThai(int id, Model_khachHang t) {
        sql = "update khachhang set trang_thai=?  where id=?";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(2, id);
            ps.setObject(1, t.isTrangThai());
           
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
  
  

 public boolean checkEmail(String email) {
    sql = "SELECT COUNT(*) FROM KhachHang WHERE email = ?";
    try {
        con = jdbc.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, email);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


public boolean checkPhoneNumber(String phoneNumber) {
    sql = "SELECT COUNT(*) FROM KhachHang WHERE so_dien_thoai = ?";
    try {
        con = jdbc.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, phoneNumber);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public ArrayList<Model_khachHang> timKiemChon(String tenKH , String sđt) {
        ArrayList<Model_khachHang> list = new ArrayList<>();
        sql = "select id,ten_khach_hang,ngay_sinh,gioi_tinh,dia_chi,email,so_dien_thoai,ngay_tao,ngay_sua,trang_thai from khachhang where ten_khach_hang like ? OR so_dien_thoai like ?";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%"+tenKH+"%");
            ps.setObject(2, "%"+sđt+"%");
            rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt(1);
                String ten = rs.getString(2);
                String ngaySinh = rs.getString(3);
                boolean gt = rs.getBoolean(4);
                String diaChi = rs.getString(5);
                String email = rs.getString(6);
                String sdt = rs.getString(7);
                String ngayTao = rs.getString(8);
                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                Model_khachHang t = new Model_khachHang(id, ten, ngaySinh, gt, diaChi, email, sdt, ngayTao, ngaySua, trangThai);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
