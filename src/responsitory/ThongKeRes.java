/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import JDBC.jdbc;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.Model_khachHang;
import model.NhanVien;
import model.SanPham;
import model.SanPhamThongKe;

/**
 *
 * @author admin
 */
public class ThongKeRes {

    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon ORDER BY id ASC
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdkhachHang(rs.getInt(2));
                hd.setIdNhanVien(rs.getInt(3));
                hd.setIdPttt(rs.getInt(4));
                hd.setIdVoucher(rs.getInt(5));
                hd.setTongTien(rs.getFloat(6));
                hd.setThanhTien(rs.getFloat(7));
                hd.setNgayTao(rs.getDate(8));
                hd.setNgaySua(rs.getDate(9));
                hd.setNgayThanhToan(rs.getDate(10));
                hd.setTrangThai(rs.getInt(11));
                listhd.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDonSortDay(Date TimeBegin, Date TimeEnd) {
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon WHERE ngay_tao >=? and ngay_tao <=? ORDER BY ngay_tao desc
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, TimeBegin);
            ps.setObject(2, TimeEnd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                 hd.setIdkhachHang(rs.getInt(2));
                hd.setIdNhanVien(rs.getInt(3));
                hd.setIdPttt(rs.getInt(4));
                hd.setIdVoucher(rs.getInt(5));
                hd.setTongTien(rs.getFloat(6));
                hd.setThanhTien(rs.getFloat(7));
                hd.setNgayTao(rs.getDate(8));
                hd.setNgaySua(rs.getDate(9));
                hd.setNgayThanhToan(rs.getDate(10));
                hd.setTrangThai(rs.getInt(11));
                listhd.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDonSortThanhTien() {
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon ORDER BY thanh_tien desc
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdkhachHang(rs.getInt(2));
                hd.setIdNhanVien(rs.getInt(3));
                hd.setIdPttt(rs.getInt(4));
                hd.setIdVoucher(rs.getInt(5));
                hd.setTongTien(rs.getFloat(6));
                hd.setThanhTien(rs.getFloat(7));
                hd.setNgayTao(rs.getDate(8));
                hd.setNgaySua(rs.getDate(9));
                hd.setNgayThanhToan(rs.getDate(10));
                hd.setTrangThai(rs.getInt(11));
                listhd.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDonSortNgayTao() {
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon ORDER BY ngay_tao asc
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdkhachHang(rs.getInt(2));
                hd.setIdNhanVien(rs.getInt(3));
                hd.setIdPttt(rs.getInt(4));
                hd.setIdVoucher(rs.getInt(5));
                hd.setTongTien(rs.getFloat(6));
                hd.setThanhTien(rs.getFloat(7));
                hd.setNgayTao(rs.getDate(8));
                hd.setNgaySua(rs.getDate(9));
                hd.setNgayThanhToan(rs.getDate(10));
                hd.setTrangThai(rs.getInt(11));
                listhd.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<HoaDon> getAllHoaDonTrangThai(String trangThai) {
        List<HoaDon> listhd = new ArrayList<>();
        String sql = """
                     SELECT * FROM HoaDon WHERE trang_thai = ? ORDER BY ngay_tao =?;
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt(1));
                hd.setIdkhachHang(rs.getInt(2));
                hd.setIdNhanVien(rs.getInt(3));
                hd.setIdPttt(rs.getInt(4));
                hd.setIdVoucher(rs.getInt(5));
                hd.setTongTien(rs.getFloat(6));
                hd.setThanhTien(rs.getFloat(7));
                hd.setNgayTao(rs.getDate(8));
                hd.setNgaySua(rs.getDate(9));
                hd.setNgayThanhToan(rs.getDate(10));
                hd.setTrangThai(rs.getInt(11));
                listhd.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listhd;
    }

    public List<SanPhamThongKe> getAllSPTK() {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }

    public List<SanPhamThongKe> getAllSPTKSortSoLuong() {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     ORDER BY SanPhamChiTiet.so_luong DESC
                     
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }

    public List<SanPhamThongKe> getAllSPTKSortDonGia() {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     ORDER BY SanPhamChiTiet.gia_ban
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }

    public List<SanPhamThongKe> getAllSPTKByTrangThai(int trangThai) {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     WHERE SanPhamChiTiet.trang_thai =?
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }

    public List<SanPhamThongKe> getAllSPTKbyTrangThaiSortDonGia(int trangThai) {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     WHERE SanPhamChiTiet.trang_thai
                     ORDER BY SanPhamChiTiet.gia_ban DESC
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }

    public List<SanPhamThongKe> getAllSPTKbyTrangThaiSortSoLuong(int trangThai) {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     WHERE SanPhamChiTiet.trang_thai
                     ORDER BY SanPhamChiTiet.so_luong DESC
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }

    public List<SanPhamThongKe> getAllSPTKbyTenSanPham(String tenSanPham) {
        List<SanPhamThongKe> listsptk = new ArrayList<>();
        String sql = """
                     select SanPham.id,SanPhamChiTiet.id, SanPham.ten_san_pham, 
                     Size.ten_size,MauSac.ten_mau_sac,ChatLieu.ten_chat_lieu,
                     DanhMuc.ten_danh_muc,ThuongHieu.ten_thuong_hieu,
                     SanPhamChiTiet.so_luong,SanPhamChiTiet.gia_ban,SanPhamChiTiet.trang_thai
                     from  SanPhamChiTiet join SanPham on SanPhamChiTiet.id_san_pham = SanPham.id 
                     join Size on SanPhamChiTiet.id_size = Size.id 
                     join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                     join ChatLieu on SanPham.id_chat_lieu = ChatLieu.id
                     join DanhMuc on SanPham.id_danh_muc = DanhMuc.id
                     join ThuongHieu on SanPham.id_thuong_hieu = ThuongHieu.id
                     WHERE SanPham.ten_san_pham = ?
                     
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, tenSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamThongKe sptk = new SanPhamThongKe();
                sptk.setIdSanPham(rs.getInt(1));
                sptk.setIdCtsp(rs.getInt(2));
                sptk.setTenSanPham(rs.getString(3));
                sptk.setTenSize(rs.getString(4));
                sptk.setTenMauSac(rs.getString(5));
                sptk.setTenChatLieu(rs.getString(6));
                sptk.setTenDanhMuc(rs.getString(7));
                sptk.setTenThuongHieu(rs.getString(8));
                sptk.setSoLuong(rs.getInt(9));
                sptk.setGia(rs.getFloat(10));
                sptk.setTrangThai(rs.getBoolean(11));
                listsptk.add(sptk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsptk;
    }
 public float getDoanhThuTheoNgayHienTai() {
        float doanhThu = 0; 
            String sSQL = "SELECT SUM(thanh_tien) AS TongThanhTien\n"
                    + "FROM HoaDon\n"
                    + "WHERE trang_thai = 1 AND "
                    + "CONVERT(DATE, ngay_thanh_toan) = CONVERT(DATE, GETDATE());";
           try(Connection con = jdbc.getConnection();PreparedStatement ps = con.prepareStatement(sSQL)){
               ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }
public float getDoanhThuTheoThangHienTai() {
    float doanhThu  = 0;
       String sSQL = "SELECT SUM(thanh_tien) AS TongThanhTien\n"
                    + "FROM HoaDon\n"
                    + "WHERE trang_thai = 1 AND "
                    + "DATEPART(MONTH, ngay_thanh_toan) = DATEPART(MONTH, GETDATE());";
       try(Connection con = jdbc.getConnection();PreparedStatement ps = con.prepareStatement(sSQL)){
               ResultSet rs = ps.executeQuery();
               while(rs.next()){
                   doanhThu = rs.getFloat(1);
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }
    public float getDoanhThuTheoNamHienTai() {
        float doanhThu = 0;
            String sSQL = "SELECT SUM(thanh_tien) AS TongThanhTien\n"
                    + "FROM HoaDon\n"
                    + "WHERE trang_thai = 1";
           try(Connection con = jdbc.getConnection();PreparedStatement ps = con.prepareStatement(sSQL)){
               ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }
    public float getDoanhThuTheoNgay(Date timeBegin, Date TimeOver) {
        float doanhThu = 0;
        String sql = "SELECT SUM(thanh_tien) AS TongThanhTien\n"
                + "FROM HoaDon\n"
                + "WHERE trang_thai =1 AND ngay_thanh_toan >= ? AND ngay_thanh_toan <= ?";

        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, timeBegin);
            ps.setObject(2, TimeOver);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }

    public int getTongDon(Date timeBegin, Date TimeOver) {
        int tongSoDon = 0;
        String sql = """
                     SELECT COUNT(id) AS TongDon
                                               FROM HoaDon
                                               WHERE ngay_tao>=? AND ngay_tao<=?
                     """;
        try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, timeBegin);
            ps.setObject(2, TimeOver);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tongSoDon = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return tongSoDon;
    }
    public int getSoDonCho(Date timeBegin, Date TimeOver){
        int soDonCho = 0;
        String sql = """
                     SELECT COUNT(CASE WHEN trang_thai = 1 Then 0 END) AS soDonCho
                     FROM HoaDon
                     WHERE ngay_tao>=? AND ngay_tao <=?
                     """;
         try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, timeBegin);
            ps.setObject(2, TimeOver);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                soDonCho = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return soDonCho;
    }
    public int getSoDonDaThangToan(Date timeBegin, Date TimeOver){
        int soDonDaThanhToan = 0;
        String sql = """
                     SELECT COUNT(CASE WHEN trang_thai = 1 Then 0 END) AS soDonCho
                      FROM HoaDon
                      WHERE ngay_tao>=? AND ngay_tao <= ?
                     """;
         try (Connection con = jdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, timeBegin);
            ps.setObject(2, TimeOver);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                soDonDaThanhToan = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         return soDonDaThanhToan;
    }
    

}
