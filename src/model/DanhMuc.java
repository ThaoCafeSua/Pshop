
package model;

/**
 *
 * @author Thanh Thao
 */
public class DanhMuc {
    private Integer id;
    private String tenDM;
    private Boolean trangThai;

    public DanhMuc() {
    }

    public DanhMuc(Integer id, String tenDM, Boolean trangThai) {
        this.id = id;
        this.tenDM = tenDM;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenDM;
    }

    
    

}
