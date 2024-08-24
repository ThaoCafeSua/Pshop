/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class SanPhamThongKe {
    private int idSanPham;
    private int idCtsp;
    private String tenSanPham;
    private String tenSize;
    private String tenChatLieu;
    private String tenDanhMuc;
    private String tenMauSac;
    private String tenThuongHieu;
    private int soLuong;
    private float gia;
    private boolean trangThai;

    public SanPhamThongKe() {
    }

    public SanPhamThongKe(int idSanPham, int idCtsp, String tenSanPham, String tenSize, String tenChatLieu, String tenDanhMuc, String tenMauSac, String tenThuongHieu, int soLuong, float gia, boolean trangThai) {
        this.idSanPham = idSanPham;
        this.idCtsp = idCtsp;
        this.tenSanPham = tenSanPham;
        this.tenSize = tenSize;
        this.tenChatLieu = tenChatLieu;
        this.tenDanhMuc = tenDanhMuc;
        this.tenMauSac = tenMauSac;
        this.tenThuongHieu = tenThuongHieu;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdCtsp() {
        return idCtsp;
    }

    public void setIdCtsp(int idCtsp) {
        this.idCtsp = idCtsp;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamThongKe{" + "idSanPham=" + idSanPham + ", idCtsp=" + idCtsp + ", tenSanPham=" + tenSanPham + ", tenSize=" + tenSize + ", tenChatLieu=" + tenChatLieu + ", tenDanhMuc=" + tenDanhMuc + ", tenMauSac=" + tenMauSac + ", tenThuongHieu=" + tenThuongHieu + ", soLuong=" + soLuong + ", gia=" + gia + ", trangThai=" + trangThai + '}';
    }

   
    
}
