/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Hà bet
 */
public class Model_Voucher {

    private int id;
    private String maVc;
    private String tenVc;  
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String mucGiam;
    private String hinhThuc;
    private int soLuong;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;

    public Model_Voucher() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVc() {
        return maVc;
    }

    public void setMaVc(String maVc) {
        this.maVc = maVc;
    }

    public String getTenVc() {
        return tenVc;
    }

    public void setTenVc(String tenVc) {
        this.tenVc = tenVc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMucGiam() {
        return mucGiam;
    }

    public void setMucGiam(String mucGiam) {
        this.mucGiam = mucGiam;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Model_Voucher(int id, String maVc, String tenVc, Date ngayBatDau, Date ngayKetThuc, String mucGiam, String hinhThuc, int soLuong, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.maVc = maVc;
        this.tenVc = tenVc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.mucGiam = mucGiam;
        this.hinhThuc = hinhThuc;
        this.soLuong = soLuong;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

   

 
    public Object[] toDaTaRow() {
        return new Object[]{this.getId(),this.getMaVc(),this.getTenVc(), this.getNgayBatDau(), this.getNgayKetThuc(), this.getMucGiam(), this.getHinhThuc(), this.getSoLuong(), this.getNgayTao(), this.getNgaySua(), this.isTrangThai() ? "Còn Hạn" : "Hết Hạn"};
    }
}
