
package model;

public class MauSac {
    private Integer id;
    private String tenMS;
    private Boolean trangThai;

    public MauSac() {
    }

    public MauSac(Integer id, String tenMS, Boolean trangThai) {
        this.id = id;
        this.tenMS = tenMS;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenMS;
    }

   
    
    
}
