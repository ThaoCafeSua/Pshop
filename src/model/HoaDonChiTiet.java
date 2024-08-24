/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class HoaDonChiTiet {
    private int id;
    private int idHoaDon;
    private int idCtsp;
    private int soLuong;
    private float giaTien;
    private String trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, int idHoaDon, int idCtsp, int soLuong, float giaTien, String trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idCtsp = idCtsp;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
