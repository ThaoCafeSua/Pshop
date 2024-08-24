
package model;

public class ThuongHieu {
    private Integer id;
    private String tenTH;
    private Boolean trangThai;

    public ThuongHieu() {
    }

    public ThuongHieu(Integer id, String tenTH, Boolean trangThai) {
        this.id = id;
        this.tenTH = tenTH;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenTH;
    }

    
    
}
