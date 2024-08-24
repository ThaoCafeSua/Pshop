
package model;

public class SanPhamChiTietViewModel {
    private Integer id;
    private String tenSP;
    private String tenMS;
    private String tenSize;
    private int soLuong;
    private float giaBan;
    private Boolean trangThai;

    public SanPhamChiTietViewModel() {
    }

    public SanPhamChiTietViewModel(Integer id, String tenSP, String tenMS, String tenSize, int soLuong, float giaBan, Boolean trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.tenMS = tenMS;
        this.tenSize = tenSize;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
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

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamChiTietViewModel{" + "id=" + id + ", tenSP=" + tenSP + ", tenMS=" + tenMS + ", tenSize=" + tenSize + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", trangThai=" + trangThai + '}';
    }

    
    
}
