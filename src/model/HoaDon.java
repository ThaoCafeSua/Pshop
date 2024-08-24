/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import View.KhachHang;
import java.util.Date;

/**
 *
 * @author admin
 */
public class HoaDon {

    private int id;
    private String maHoaDon;
    private String tenkhachHang;
    private int idNhanVien;
    private int idkhachHang;
    private String tenNhanVien;
    private int idVoucher;
    private int idPttt;
    private float tongTien;
    private float thanhTien;
    private Date ngayTao;
    private Date ngaySua;
    private Date ngayThanhToan;
    private int trangThai;

    public HoaDon() {
    }

    public HoaDon(int id, String maHoaDon, int idNhanVien, int idkhachHang, int idVoucher, int idPttt, float tongTien, float thanhTien, Date ngayTao, Date ngaySua, Date ngayThanhToan, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.idNhanVien = idNhanVien;
        this.idkhachHang = idkhachHang;
        this.idVoucher = idVoucher;
        this.idPttt = idPttt;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }
    

    public HoaDon(int id, String maHoaDon, String tenkhachHang, String tenNhanVien, int idVoucher, int idPttt, float tongTien, float thanhTien, Date ngayTao, Date ngaySua, Date ngayThanhToan, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenkhachHang = tenkhachHang;
        this.tenNhanVien = tenNhanVien;
        this.idVoucher = idVoucher;
        this.idPttt = idPttt;
        this.tongTien = tongTien;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
    }
    

    public HoaDon(int id, String maHoaDon, String tenkhachHang, int idNhanVien, int idkhachHang, String tenNhanVien, int idVoucher, int idPttt, float tongTien, float thanhTien, Date ngayTao, Date ngaySua, Date ngayThanhToan, int trangThai) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenkhachHang = tenkhachHang;
        this.idNhanVien = idNhanVien;
        this.idkhachHang = idkhachHang;
        this.tenNhanVien = tenNhanVien;
        this.idVoucher = idVoucher;
        this.idPttt = idPttt;
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

    public String getTenkhachHang() {
        return tenkhachHang;
    }

    public void setTenkhachHang(String tenkhachHang) {
        this.tenkhachHang = tenkhachHang;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdkhachHang() {
        return idkhachHang;
    }

    public void setIdkhachHang(int idkhachHang) {
        this.idkhachHang = idkhachHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public int getIdPttt() {
        return idPttt;
    }

    public void setIdPttt(int idPttt) {
        this.idPttt = idPttt;
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
        return " HoaDon{" +"\n"+ "id=" + id + ", maHoaDon=" + maHoaDon + ", tenkhachHang=" + tenkhachHang + ", idNhanVien=" + idNhanVien + ", idkhachHang=" + idkhachHang + ", tenNhanVien=" + tenNhanVien + ", idVoucher=" + idVoucher + ", idPttt=" + idPttt + ", tongTien=" + tongTien + ", thanhTien=" + thanhTien + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", ngayThanhToan=" + ngayThanhToan + ", trangThai=" + trangThai + '}';
    }

    
   
}
