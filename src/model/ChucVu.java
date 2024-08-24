/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Legion 5
 */
public class ChucVu {
    private int idCV;
    private String tenCV;
    private Boolean trangThai;

    public ChucVu(int idCV, String tenCV) {
        this.idCV = idCV;
        this.tenCV = tenCV;
        this.trangThai = trangThai;
    }

    public ChucVu() {
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getId() {
return new NhanVien1().getId();
    }

    @Override
    public String toString() {
        return this.tenCV;
    }
    
}
