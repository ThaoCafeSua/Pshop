
package model;

public class Size {
   private Integer id;
   private String tenSize;
   private Boolean trangThai;  

    public Size() {
    }

    public Size(Integer id, String tenSize, Boolean trangThai) {
        this.id = id;
        this.tenSize = tenSize;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenSize;
    }

   
}
