
package model;

public class ChatLieu {
    private Integer id;
    private String tenCL;
    private Boolean trangThai;

    public ChatLieu() {
    }

    public ChatLieu(Integer id, String tenCL, Boolean trangThai) {
        this.id = id;
        this.tenCL = tenCL;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenCL;
    }

    
}
