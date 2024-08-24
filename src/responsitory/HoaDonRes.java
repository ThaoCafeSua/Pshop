/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import JDBC.jdbc;
import View.KhachHang;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.HoaDonViewModel;
import model.Model_HoaDon;
import model.HoaDon;
import model.Model_SanPhamCT;
import model.Model_khachHang;
import model.NhanVien;
import model.SanPhamChiTiet;
import model.SanPhamChiTietViewModel;
import org.apache.commons.compress.utils.Lists;

/**
 *
 * @author admin
 */
public class HoaDonRes {

    NhanVien idNhanVien = new NhanVien();

    public int taoHoaDon(HoaDon hd) {
        String sql = """
                     INSERT INTO HoaDon(ma_hoa_don,id_nhan_vien,ngay_tao,trang_thai)
                     VALUES(?,?,GETDATE(),0)
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getMaHoaDon());
            ps.setObject(2, hd.getIdNhanVien());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<HoaDon> getAllHoaDonCho() {
        List<HoaDon> lists = new ArrayList<>();
        String sql = """
                    Select HoaDon.id,HoaDon.ma_hoa_don,NhanVien.ten_nhan_vien,HoaDon.ngay_tao,HoaDon.trang_thai
                    from HoaDon join NhanVien on HoaDon.id_nhan_vien = NhanVien.id
                    Where HoaDon.trang_thai = 0 Order by HoaDon.id desc
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setMaHoaDon(rs.getString(2));
                hd.setTenNhanVien(rs.getString(3));
                hd.setNgayTao(rs.getDate(4));
                hd.setTrangThai(rs.getInt(5));
                lists.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;

    }

    public int InsertHoaDonKHnull(HoaDon hd) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "INSERT INTO HoaDon(ma_hoa_don,id_nhan_vien,trang_thai,ngay_tao) "
                    + "VALUES(?,?,0,GETDATE())";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setString(1, hd.getMaHoaDon());
            sttm.setObject(2, hd.getIdNhanVien());
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Integer insertHoaDon(HoaDon hd, Integer idNV) {
        int result = 0;
        try {
            String sql = "insert into HoaDon (id_nhan_vien, ngay_tao , ngay_thanh_toan , trang_thai) values(? ,? ,? ,? )";
            Connection conn = jdbc.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, idNV);
            pr.setDate(2, (Date) hd.getNgayTao());
            pr.setDate(3, (Date) hd.getNgayThanhToan());
            pr.setInt(4, hd.getTrangThai());
            result = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    public List<SanPhamChiTietViewModel> getAllSPCT() {
        List<SanPhamChiTietViewModel> list = new ArrayList<>();
        String sql = """
                     select SanPham.ten_san_pham, MauSac.ten_mau_sac,Size.ten_size,SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id 
                     join Size on SanPhamChiTiet.id_size = Size.id
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTietViewModel spct = new SanPhamChiTietViewModel();
                spct.setTenSP(rs.getString(1));
                spct.setTenMS(rs.getString(2));
                spct.setTenSize(rs.getString(3));

                spct.setSoLuong(rs.getInt(4));
                spct.setGiaBan(rs.getFloat(5));
                spct.setTrangThai(rs.getBoolean(6));

                list.add(spct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int layphantucuoi() {
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        int ptcuoi = -1;
        try {
            String sSQL = "select id from HoaDon ORDER BY id;";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            rs = sttm.executeQuery();
            while (rs.next()) {
                ptcuoi = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ptcuoi;
    }

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public ArrayList<model.Model_HoaDon> getAll() {
        ArrayList<model.Model_HoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    COALESCE(SUM(hdct.so_luong * hdct.gia_tien), 0) AS thanh_tien,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "LEFT JOIN \n"
                + "    KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "LEFT JOIN \n"
                + "    NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "LEFT JOIN \n"
                + "    PhuongThucThanhToan ptt ON hd.id_pttt = ptt.id\n"
                + "LEFT JOIN \n"
                + "    Voucher v ON hd.id_voucher = v.id\n"
                + "LEFT JOIN \n"
                + "    HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don\n"
                + "GROUP BY \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "ORDER BY \n"
                + "    hd.ngay_thanh_toan DESC";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKhachHang = rs.getString(2);
                String tenNhanVien = rs.getString(3);
                String phuongThucTT = rs.getString(4);
                String maVoucher = rs.getString(5);
                double tongTien = rs.getDouble(6);
                String ngayTao = rs.getString(7);
                String ngayThanhToan = rs.getString(8);

                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                model.Model_HoaDon hd = new Model_HoaDon(id, tenKhachHang, tenNhanVien, phuongThucTT, maVoucher, tongTien, ngayTao, ngayThanhToan, ngaySua, trangThai);
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<model.Model_HoaDon> timKiem(String ten) {
        ArrayList<model.Model_HoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    COALESCE(SUM(hdct.so_luong * hdct.gia_tien), 0) AS thanh_tien,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "LEFT JOIN \n"
                + "    KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "LEFT JOIN \n"
                + "    NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "LEFT JOIN \n"
                + "    PhuongThucThanhToan ptt ON hd.id_pttt = ptt.id\n"
                + "LEFT JOIN \n"
                + "    Voucher v ON hd.id_voucher = v.id\n"
                + "LEFT JOIN \n"
                + "    HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don\n"
                + "WHERE \n"
                + "    kh.ten_khach_hang LIKE ?\n"
                + "GROUP BY \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "ORDER BY \n"
                + "    hd.ngay_thanh_toan DESC";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, "%" + ten + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKhachHang = rs.getString(2);
                String tenNhanVien = rs.getString(3);
                String phuongThucTT = rs.getString(4);
                String maVoucher = rs.getString(5);
                double tongTien = rs.getDouble(6);
                String ngayTao = rs.getString(7);
                String ngayThanhToan = rs.getString(8);

                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                model.Model_HoaDon hd = new Model_HoaDon(id, tenKhachHang, tenNhanVien, phuongThucTT, maVoucher, tongTien, ngayTao, ngayThanhToan, ngaySua, trangThai);
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int capNhat(int id, Model_HoaDon hd) {
        sql = "UPDATE HoaDon hd\n"
                + "JOIN KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "JOIN NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "JOIN PhuongThucThanhToan ptt ON hd.id_phuong_thuc_thanh_toan = ptt.id\n"
                + "LEFT JOIN Voucher v ON hd.id_voucher = v.id\n"
                + "SET hd.id = ?,"
                + "    hd.ten_khach_hang = ?,\n"
                + "    hd.ten_nhan_vien = ?,\n"
                + "    hd.phuong_thuc_thanh_toan = ?,\n"
                + "    hd.ma_voucher = ?,\n"
                + "    hd.tong_tien = ?,\n"
                + "    hd.ngay_tao = ?,\n"
                + "    hd.ngay_thanh_toan = ?,\n"
                + "    hd.ngay_sua = ?,\n"
                + "    hd.trang_thai = ?\n"
                + "WHERE hd.id = ?;";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, hd.getID());
            ps.setObject(2, hd.getTenKhachHang());
            ps.setObject(3, hd.getTenNhanVien());
            ps.setObject(4, hd.getPhuongThucTT());
            ps.setObject(5, hd.getMaVoucher());
            ps.setObject(6, hd.getTongTien());
            ps.setObject(7, hd.getNgayTao());
            ps.setObject(8, hd.getNgayThanhToan());
            ps.setObject(9, hd.getNgaySua());
            ps.setObject(10, hd.getTenKhachHang());
            ps.setObject(11, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }

    }

    public ArrayList<Model_HoaDon> loc(String ngayBD, String ngayKT) {
        ArrayList<Model_HoaDon> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    COALESCE(SUM(hdct.so_luong * hdct.gia_tien), 0) AS tong_thanh_tien,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "LEFT JOIN \n"
                + "    KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "LEFT JOIN \n"
                + "    NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "LEFT JOIN \n"
                + "    PhuongThucThanhToan ptt ON hd.id_pttt = ptt.id\n"
                + "LEFT JOIN \n"
                + "    Voucher v ON hd.id_voucher = v.id\n"
                + "LEFT JOIN \n"
                + "    HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don\n"
                + "WHERE \n"
                + "    hd.ngay_tao BETWEEN ? AND ?\n"
                + "GROUP BY \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "ORDER BY \n"
                + "    hd.ngay_thanh_toan DESC, \n"
                + "    hd.ngay_tao DESC";

        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ngayBD); // Thiết lập tham số 1
            ps.setString(2, ngayKT); // Thiết lập tham số 2
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKhachHang = rs.getString(2);
                String tenNhanVien = rs.getString(3);
                String phuongThucTT = rs.getString(4);
                String maVoucher = rs.getString(5);
                double tongTien = rs.getDouble(6);
                String ngayTao = rs.getString(7);
                String ngayThanhToan = rs.getString(8);
                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                Model_HoaDon hd = new Model_HoaDon(id, tenKhachHang, tenNhanVien, phuongThucTT, maVoucher, tongTien, ngayTao, ngayThanhToan, ngaySua, trangThai);
                list.add(hd);
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<model.Model_HoaDon> tongTienTang() {
        ArrayList<model.Model_HoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    COALESCE(SUM(hdct.so_luong * hdct.gia_tien), 0) AS tong_thanh_tien,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "LEFT JOIN \n"
                + "    KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "LEFT JOIN \n"
                + "    NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "LEFT JOIN \n"
                + "    PhuongThucThanhToan ptt ON hd.id_pttt = ptt.id\n"
                + "LEFT JOIN \n"
                + "    Voucher v ON hd.id_voucher = v.id\n"
                + "LEFT JOIN \n"
                + "    HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don\n"
                + "GROUP BY \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "ORDER BY \n"
                + "    tong_thanh_tien ASC";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKhachHang = rs.getString(2);
                String tenNhanVien = rs.getString(3);
                String phuongThucTT = rs.getString(4);
                String maVoucher = rs.getString(5);
                double tongTien = rs.getDouble(6);
                String ngayTao = rs.getString(7);
                String ngayThanhToan = rs.getString(8);

                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                model.Model_HoaDon hd = new Model_HoaDon(id, tenKhachHang, tenNhanVien, phuongThucTT, maVoucher, tongTien, ngayTao, ngayThanhToan, ngaySua, trangThai);
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<model.Model_HoaDon> tongTienGiam() {
        ArrayList<model.Model_HoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    COALESCE(SUM(hdct.so_luong * hdct.gia_tien), 0) AS tong_thanh_tien,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "LEFT JOIN \n"
                + "    KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "LEFT JOIN \n"
                + "    NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "LEFT JOIN \n"
                + "    PhuongThucThanhToan ptt ON hd.id_pttt = ptt.id\n"
                + "LEFT JOIN \n"
                + "    Voucher v ON hd.id_voucher = v.id\n"
                + "LEFT JOIN \n"
                + "    HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don\n"
                + "GROUP BY \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "ORDER BY \n"
                + "    tong_thanh_tien DESC";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKhachHang = rs.getString(2);
                String tenNhanVien = rs.getString(3);
                String phuongThucTT = rs.getString(4);
                String maVoucher = rs.getString(5);
                double tongTien = rs.getDouble(6);
                String ngayTao = rs.getString(7);
                String ngayThanhToan = rs.getString(8);

                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                model.Model_HoaDon hd = new Model_HoaDon(id, tenKhachHang, tenNhanVien, phuongThucTT, maVoucher, tongTien, ngayTao, ngayThanhToan, ngaySua, trangThai);
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<model.Model_SanPhamCT> getAllhDCT(int maHD) {
        ArrayList<model.Model_SanPhamCT> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hdt.id, \n"
                + "    sp.ten_san_pham, \n"
                + "    ms.ten_mau_sac, \n"
                + "    s.ten_size, \n"
                + "    hdt.so_luong, \n"
                + "    hdt.gia_tien, \n"
                + "    hdt.so_luong * hdt.gia_tien AS tong_tien \n"
                + "FROM \n"
                + "    HoaDonChiTiet hdt\n"
                + "JOIN \n"
                + "    SanPhamChiTiet ctsp ON hdt.id_ctsp = ctsp.id\n"
                + "JOIN \n"
                + "    SanPham sp ON ctsp.id_san_pham = sp.id\n"
                + "JOIN \n"
                + "    MauSac ms ON ctsp.id_mau_sac = ms.id\n"
                + "JOIN \n"
                + "    Size s ON ctsp.id_size = s.id\n"
                + "WHERE \n"
                + "    hdt.id_hoa_don = ?;";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String ten = rs.getString(2);
                String mauSac = rs.getString(3);
                String tenSize = rs.getString(4);
                int soLUong = rs.getInt(5);
                float giaTien = rs.getFloat(6);
                float tongTien = rs.getFloat(7);
                model.Model_SanPhamCT t = new Model_SanPhamCT(id, ten, mauSac, tenSize, soLUong, giaTien, tongTien);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<model.Model_HoaDon> locTrangThai(boolean trangThaii) {
        ArrayList<model.Model_HoaDon> list = new ArrayList<>();
        sql = "SELECT \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    COALESCE(SUM(hdct.so_luong * hdct.gia_tien), 0) AS tong_thanh_tien,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai\n"
                + "FROM \n"
                + "    HoaDon hd\n"
                + "LEFT JOIN \n"
                + "    KhachHang kh ON hd.id_khach_hang = kh.id\n"
                + "LEFT JOIN \n"
                + "    NhanVien nv ON hd.id_nhan_vien = nv.id\n"
                + "LEFT JOIN \n"
                + "    PhuongThucThanhToan ptt ON hd.id_pttt = ptt.id\n"
                + "LEFT JOIN \n"
                + "    Voucher v ON hd.id_voucher = v.id\n"
                + "LEFT JOIN \n"
                + "    HoaDonChiTiet hdct ON hd.id = hdct.id_hoa_don\n"
                + "WHERE \n"
                + "    hd.trang_thai = ?\n"
                + "GROUP BY \n"
                + "    hd.id,\n"
                + "    kh.ten_khach_hang,\n"
                + "    nv.ten_nhan_vien,\n"
                + "    ptt.ten_phuong_thuc_thanh_toan,\n"
                + "    v.ma_voucher,\n"
                + "    hd.ngay_thanh_toan,\n"
                + "    hd.ngay_tao,\n"
                + "    hd.ngay_sua,\n"
                + "    hd.trang_thai";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, trangThaii);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String tenKhachHang = rs.getString(2);
                String tenNhanVien = rs.getString(3);
                String phuongThucTT = rs.getString(4);
                String maVoucher = rs.getString(5);
                double tongTien = rs.getDouble(6);
                String ngayTao = rs.getString(7);
                String ngayThanhToan = rs.getString(8);

                String ngaySua = rs.getString(9);
                boolean trangThai = rs.getBoolean(10);
                model.Model_HoaDon hd = new Model_HoaDon(id, tenKhachHang, tenNhanVien, phuongThucTT, maVoucher, tongTien, ngayTao, ngayThanhToan, ngaySua, trangThai);
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int UpdateSoLuong(int soLuong, int idctsp) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE SanPhamChiTiet SET so_luong=so_luong-? WHERE id=?";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, soLuong);
            sttm.setInt(2, idctsp);
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int UpdateHoaDon(float TongTien, float GiamGia, float ThanhTien, int MaHoaDon) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = """
                          UPDATE HoaDon SET tong_tien=?,thanh_tien=? 
                          WHERE id=?""";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setDouble(1, TongTien);
            sttm.setDouble(2, ThanhTien);
            sttm.setInt(3, MaHoaDon);
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int thanhToan(double TongTien,double ThanhTien,int idNv,int khachHang, int voucher,int pttt, int MaHoaDon) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE HoaDon SET id_khach_hang=?,id_nhan_vien=?,id_voucher=?,id_pttt=?,tong_tien = ?,"
                    + "thanh_tien=?,ngay_sua=GETDATE(),ngay_thanh_toan = GETDATE(), trang_thai = 1 WHERE id= ?";

            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setObject(1, khachHang);
            sttm.setObject(2, idNv);
            sttm.setObject(3, voucher);
            sttm.setObject(4, pttt);
            sttm.setObject(5, TongTien);
            sttm.setObject(6, ThanhTien);
            sttm.setInt(7,MaHoaDon);
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
  public int thanhToanKovc(double TongTien,double ThanhTien,int idNv, int khachHang, int pttt, int MaHoaDon) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE HoaDon SET id_khach_hang=?,id_nhan_vien=?,id_pttt=?,tong_tien = ?,"
                    + "thanh_tien=?,ngay_sua=GETDATE(),ngay_thanh_toan = GETDATE(), trang_thai = 1 WHERE id = ?";

            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setObject(1, khachHang);
             sttm.setObject(2, idNv);
            sttm.setObject(3, pttt);
            sttm.setObject(4, TongTien);
            sttm.setObject(5, ThanhTien);
            sttm.setInt(6, MaHoaDon);
            return sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public ArrayList<HoaDon> tongTien(int ma) {
        ArrayList<model.HoaDon> list = new ArrayList<>();
        sql = "SELECT id,ma_hoa_don,id_khach_hang,id_nhan_vien,id_voucher,id_pttt,"
                + "tong_tien,thanh_tien,ngay_tao,ngay_sua,ngay_thanh_toan,trang_thai FROM HoaDon  where id = ? ";
        try {
            con = jdbc.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String maHoaDon = rs.getString(2);
                int idKhachHang = rs.getInt(3);
                int idNhanhVien = rs.getInt(4);
                int idVouCher = rs.getInt(5);
                int idPhươngttt = rs.getInt(6);
                float tongTien = rs.getFloat(7);
                float thanhTien = rs.getFloat(8);
                Date ngayTao = rs.getDate(9);
                 Date ngaySua = rs.getDate(10);
                Date ngayThanhToan = rs.getDate(11);             
                int trangThai = rs.getByte(12);
                HoaDon hd = new HoaDon(id, maHoaDon, idNhanhVien, idKhachHang, idVouCher, id, tongTien, thanhTien, ngayTao, ngaySua, ngayThanhToan, trangThai);
                list.add(hd);              
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(new HoaDonRes().getAllHoaDonCho().toString());
    }
}
