package model;

public class SanPhamChiTiet {

    private Integer id;
    private Integer idSP;
    private Integer idMS;
    private Integer idSize;
    private int soLuong;
    private float giaBan;
    private Boolean trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(Integer id, Integer idSP, Integer idMS, Integer idSize, int soLuong, float giaBan, Boolean trangThai) {
        this.id = id;
        this.idSP = idSP;
        this.idMS = idMS;
        this.idSize = idSize;
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

    public Integer getIdSP() {
        return idSP;
    }

    public void setIdSP(Integer idSP) {
        this.idSP = idSP;
    }

    public Integer getIdMS() {
        return idMS;
    }

    public void setIdMS(Integer idMS) {
        this.idMS = idMS;
    }

    public Integer getIdSize() {
        return idSize;
    }

    public void setIdSize(Integer idSize) {
        this.idSize = idSize;
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
        return "SanPhamChiTiet{" + "id=" + id + ", idSP=" + idSP + ", idMS=" + idMS + ", idSize=" + idSize + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", trangThai=" + trangThai + '}';
    }

   
}
