/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import JDBC.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonChiTiet;
import model.HoaDonChiTietViewModel;

/**
 *
 * @author admin
 */
public class HoaDonChiTietRes {
    
    public List<HoaDonChiTietViewModel> getAllHDCT2(int MaHoaDon) {
        List<HoaDonChiTietViewModel> listHDCT = new ArrayList<>();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            String sSQL = """
                          SELECT SanPhamChiTiet.id, SanPham.ten_san_pham,MauSac.ten_mau_sac,Size.ten_size, HoaDonChiTiet.so_luong, SanPhamChiTiet.gia_ban, (HoaDonChiTiet.so_luong * SanPhamChiTiet.gia_ban) AS ThanhTien
                          FROM HoaDonChiTiet
                          JOIN SanPhamChiTiet ON HoaDonChiTiet.id_ctsp = SanPhamChiTiet.id
                          JOIN SanPham ON SanPhamChiTiet.id_san_pham = SanPham.id
                          join MauSac on SanPhamChiTiet.id_mau_sac = MauSac.id
                          join Size on SanPhamChiTiet.id_size = Size.id
                          WHERE HoaDonChiTiet.id_hoa_don = ?
                          """;
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, MaHoaDon);
            rs = sttm.executeQuery();
            while (rs.next()) {
                HoaDonChiTietViewModel hdct = new HoaDonChiTietViewModel(); 
                hdct.setIdCtsp(rs.getInt(1));
                hdct.setTenSanPham(rs.getString(2));
                hdct.setTenMauSac(rs.getString(3));
                hdct.setTenSize(rs.getString(4));
                hdct.setSoLuong(rs.getInt(5));
                hdct.setGiaTien(rs.getFloat(6));
                hdct.setThanhTien(rs.getFloat(7));
                listHDCT.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            rs.close();
            sttm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }
    
    
    
    
    
    public int InsertHoaDonChiTiet(int idHD,int idCTSP, int SoLuong,int trangthai) {
        HoaDonChiTiet hdct = null;
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "INSERT INTO HoaDonChiTiet (id_hoa_don,id_ctsp, so_luong, gia_tien,trang_thai) "
                    + "VALUES (?, ?, ?, (SELECT gia_ban FROM SanPhamChiTiet WHERE id = ?),?)";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, idHD);
            sttm.setInt(2, idCTSP);
            sttm.setInt(3, SoLuong);
            sttm.setInt(4, idCTSP);
            sttm.setInt(5, trangthai);
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
    public int UpdateHDCT_SoLuong( int idCTSP, int soLuong) {
        HoaDonChiTiet hdct = null;
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE HoaDonChiTiet SET so_luong = so_luong + ? WHERE id_ctsp=?;";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, soLuong);
            sttm.setInt(2,idCTSP);
            System.out.println("ok");
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
    public int Delete1SPGioHang(int idHoaDon, int idChiTietSP) {
        HoaDonChiTiet hdct = null;
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "DELETE HoaDonChiTiet  WHERE id_hoa_don=? AND id_ctsp=?;";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, idHoaDon);
            sttm.setInt(2, idChiTietSP);
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
      public int UpdateSoLuongVe(int soLuong, int idCTSP) {
        Connection conn = null;
        PreparedStatement sttm = null;
        try {
            String sSQL = "UPDATE SanPhamChiTiet SET so_luong=so_luong+? WHERE id=?";
            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sSQL);
            sttm.setInt(1, soLuong);
            sttm.setInt(2, idCTSP);
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
//    public static void main(String[] args) {
//        System.out.println(new HoaDonChiTietRes().getAllHDCT2(3).toString());
//    }
}
