/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import JDBC.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import model.HoaDonViewModel;
import model.NhanVien;
import responsitory.HoaDonRes;

/**
 *
 * @author admin
 */
public class HoaDonService implements HoaDonImpl {

    HoaDonRes hdres = new HoaDonRes();

    @Override
    public Integer saveHD(HoaDon hoaDon, int idNV) {
        HoaDon hd = new HoaDon();
        hd.setId(hoaDon.getId());
        hd.setNgayTao(hoaDon.getNgayTao());
        hd.setTrangThai(0);

        Integer isInsert = hdres.insertHoaDon(hd, idNV);
        return isInsert;
    }

    @Override
    public List<HoaDonViewModel> getListHD(int TrangThai) {
        List<HoaDonViewModel> getListGD = new ArrayList<>();
        try {
            String sql = "SELECT HoaDon.id , HoaDon.ngay_tao , NhanVien.ten_nhan_vien , HoaDon.trang_thai FROM HoaDon  JOIN NhanVien  ON HoaDon.id_nhan_vien = NhanVien.id WHERE HoaDon.trang_thai = ?";
            Connection conn = jdbc.getConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, TrangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();

                hd.setId(rs.getInt(1));
                hd.setNgayTao(rs.getDate(2));
                NhanVien uesr = new NhanVien();
                uesr.setTenNV(rs.getString(3));
                hd.setTenNhanVien(uesr);
                hd.setTrangThai(rs.getInt(4));
                getListGD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getListGD;
    }

}
