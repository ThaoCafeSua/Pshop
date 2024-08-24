/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class HoaDonViewModel {
    private int id;
    private String maHoaDon;
    private String tenKhachHang;
    private NhanVien tenNhanVien;
    private String maVoucher;
    private String tenPttt;
    private float tongTien;
    private float thanhTien;
    private Date ngayTao;
    private Date ngaySua;
    private Date ngayThanhToan;
    private int trangThai;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(int id, String maHoaDon, String tenKhachHang, NhanVien tenNhanVien, String maVoucher, String tenPttt, float tongTien, float thanhTien, Date ngayTao, Date ngaySua, Date ngayThanhToan, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.maVoucher = maVoucher;
        this.tenPttt = tenPttt;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public NhanVien getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(NhanVien tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenPttt() {
        return tenPttt;
    }

    public void setTenPttt(String tenPttt) {
        this.tenPttt = tenPttt;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoaDonViewModel{" + "id=" + id + ", maHoaDon=" + maHoaDon + ", tenKhachHang=" + tenKhachHang + ", tenNhanVien=" + tenNhanVien + ", maVoucher=" + maVoucher + ", tenPttt=" + tenPttt + ", tongTien=" + tongTien + ", thanhTien=" + thanhTien + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", ngayThanhToan=" + ngayThanhToan + ", trangThai=" + trangThai + '}';
    }


   
    
}
