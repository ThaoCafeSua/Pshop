/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class SanPhamViewModel {

    private Integer id;
    private String tenSP;
    private String tenDM;
    private String tenCL;
    private String tenTH;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(Integer id, String tenSP, String tenDM, String tenCL, String tenTH, Date ngayTao, Date ngaySua, Boolean trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.tenDM = tenDM;
        this.tenCL = tenCL;
        this.tenTH = tenTH;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
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

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamViewModel{" + "id=" + id + ", tenSP=" + tenSP + ", tenDM=" + tenDM + ", tenCL=" + tenCL + ", tenTH=" + tenTH + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + '}';
    }
    

}
