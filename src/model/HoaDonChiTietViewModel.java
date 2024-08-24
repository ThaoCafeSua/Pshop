/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class HoaDonChiTietViewModel {
      private int id;
    private int idHoaDon;
    private int idCtsp;
    private String tenMauSac;
    private String tenSize;
    private String tenSanPham;
    private int soLuong;
    private float giaTien;
    private float thanhTien;
    private String trangThai;

    public HoaDonChiTietViewModel() {
    }

    public HoaDonChiTietViewModel(int id, int idHoaDon, int idCtsp, String tenMauSac, String tenSize, String tenSanPham, int soLuong, float giaTien, float thanhTien, String trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idCtsp = idCtsp;
        this.tenMauSac = tenMauSac;
        this.tenSize = tenSize;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdCtsp() {
        return idCtsp;
    }

    public void setIdCtsp(int idCtsp) {
        this.idCtsp = idCtsp;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(float giaTien) {
        this.giaTien = giaTien;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "\n HoaDonChiTietViewModel{" + "id=" + id + ", idHoaDon=" + idHoaDon + ", idCtsp=" + idCtsp + ", tenMauSac=" + tenMauSac + ", tenSize=" + tenSize + ", tenSanPham=" + tenSanPham + ", soLuong=" + soLuong + ", giaTien=" + giaTien + ", thanhTien=" + thanhTien + ", trangThai=" + trangThai + '}';
    }

   
    
    
}
