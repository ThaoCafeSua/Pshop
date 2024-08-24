package model;

import java.util.Date;

public class SanPham {

    private Integer id;
    private String tenSP;
    private Integer idDM;
    private Integer idCL;
    private Integer idTH;
    private Date ngayTao;
    private Date ngaySua;
    private Boolean trangThai;

    public SanPham() {
    }

    public SanPham(Integer id, String tenSP, Integer idDM, Integer idCL, Integer idTH, Date ngayTao, Date ngaySua, Boolean trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.idDM = idDM;
        this.idCL = idCL;
        this.idTH = idTH;
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

    public Integer getIdDM() {
        return idDM;
    }

    public void setIdDM(Integer idDM) {
        this.idDM = idDM;
    }

    public Integer getIdCL() {
        return idCL;
    }

    public void setIdCL(Integer idCL) {
        this.idCL = idCL;
    }

    public Integer getIdTH() {
        return idTH;
    }

    public void setIdTH(Integer idTH) {
        this.idTH = idTH;
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
        return tenSP;
    }

    
    

}
