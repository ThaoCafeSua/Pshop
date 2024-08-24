package responsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ChucVu;
import model.NhanVien1;

public class NhanVienRepo {
    JDBC.jdbc dbContext;

    public ArrayList<NhanVien1> getAll() {
        String sql = "SELECT nv.id, cv.ten_chuc_vu, nv.ten_nhan_vien, nv.ngay_sinh, nv.gioi_tinh, nv.dia_chi, "
                   + "nv.email, nv.so_dien_thoai, nv.tai_khoan, nv.mat_khau, nv.ngay_tao, nv.ngay_sua, nv.trang_thai "
                   + "FROM dbo.NHANVIEN nv "
                   + "INNER JOIN dbo.CHUCVU cv ON nv.id_chuc_vu = cv.id";

        ArrayList<NhanVien1> list = new ArrayList<>();

        try (Connection conn = dbContext.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                 Integer id = rs.getInt(1);
                String tenCV = rs.getString(2);
                String tenNV = rs.getString(3);
                Date ngaySinh = rs.getDate(4);
                Boolean gioiTinh = rs.getBoolean(5);
                String diaChi = rs.getString(6);
                String email = rs.getString(7);
                String sdt = rs.getString(8);
                String taiKhoan = rs.getString(9);
                String matKhau = rs.getString(10);
                Date ngayTao = rs.getDate(11);
                Date ngaySua = rs.getDate(12);
                Boolean trangThai = rs.getBoolean(13);

                NhanVien1 nv = new NhanVien1(id, tenCV, tenNV, ngaySinh, gioiTinh, diaChi, email, sdt, taiKhoan, matKhau, ngayTao, ngaySua, trangThai);
                list.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
//
//    public ArrayList<ChucVu> getListChucVu() {
//        String sql = "SELECT id, ten_chuc_vu, trang_thai FROM CHUCVU";
//        ArrayList<ChucVu> list = new ArrayList<>();
//        try (Connection conn = dbContext.getConnection(); 
//             PreparedStatement ps = conn.prepareStatement(sql); 
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                Integer id = rs.getInt(1);
//                String ten = rs.getString(2);
//                int trangThai = rs.getInt(3);
//                ChucVu cv = new ChucVu(id, ten, trangThai);
//                list.add(cv);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
    
    
     public ArrayList<ChucVu> getListChucVu() {
        String sql = "SELECT * FROM ChucVu";
        ArrayList<ChucVu> list = new ArrayList<>();
        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String ten = rs.getString(2);
                boolean trangThai = rs.getBoolean(3);
                ChucVu cv = new ChucVu(id, ten);
                list.add(cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

public boolean add(NhanVien1 nv1) {
    String sql = "INSERT INTO NhanVien (id_chuc_vu, ten_nhan_vien, ngay_sinh, gioi_tinh, dia_chi, email, so_dien_thoai, tai_khoan, mat_khau, ngay_tao, ngay_sua, trang_thai) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = dbContext.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        int idChucVu = getIdChucVuByName(nv1.getTenCV());
        ps.setInt(1, idChucVu);
        ps.setString(2, nv1.getTenNV());
        ps.setDate(3, new java.sql.Date(nv1.getNgaySinh().getTime()));
        ps.setBoolean(4, nv1.getGioiTinh());
        ps.setString(5, nv1.getDiaChi());
        ps.setString(6, nv1.getEmail());
        ps.setString(7, nv1.getSdt());
        ps.setString(8, nv1.getTaiKhoan());
        ps.setString(9, nv1.getMatKhau());
        ps.setDate(10, new java.sql.Date(nv1.getNgayTao().getTime()));
        ps.setDate(11, new java.sql.Date(nv1.getNgaySua().getTime()));
        ps.setBoolean(12, nv1.getTrangThai());
        
        int rowsAffected = ps.executeUpdate();
        
        return rowsAffected > 0;
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

private int getIdChucVuByName(String tenCV) {
    String sql = "SELECT id FROM ChucVu WHERE ten_chuc_vu = ?";
    try (Connection conn = dbContext.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, tenCV);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trường hợp không tìm thấy chức vụ
}

    public ArrayList<NhanVien1> locAll(String chucVu, boolean gioiTinh) {
        ArrayList<NhanVien1> list = new ArrayList<>();
        String sql = "SELECT nv.id, cv.ten_chuc_vu, nv.ten_nhan_vien, nv.ngay_sinh, nv.gioi_tinh, "
                + "nv.dia_chi, nv.email, nv.so_dien_thoai, nv.tai_khoan, nv.mat_khau, nv.ngay_tao, "
                + "nv.ngay_sua, nv.trang_thai "
                + "FROM NhanVien nv "
                + "JOIN ChucVu cv ON nv.id_chuc_vu = cv.id "
                + "WHERE cv.ten_chuc_vu = ? AND nv.gioi_tinh = ?";

        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, chucVu);
            ps.setObject(2, gioiTinh);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tenChucVu = rs.getString("ten_chuc_vu");
                String tenNV = rs.getString("ten_nhan_vien");
                Date ngaySinh = rs.getDate("ngay_sinh");
                boolean gioiTinhDb = rs.getBoolean("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("so_dien_thoai");
                String taiKhoan = rs.getString("tai_khoan");
                String matKhau = rs.getString("mat_khau");
                Date ngayTao = rs.getDate("ngay_tao");
                Date ngaySua = rs.getDate("ngay_sua");
                boolean trangThaiDB = rs.getBoolean("trang_thai");

                NhanVien1 nv = new NhanVien1(id, tenChucVu, tenNV, ngaySinh, gioiTinhDb, diaChi, email, soDienThoai, taiKhoan, matKhau, ngayTao, ngaySua, trangThaiDB);
                list.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
  public ArrayList<NhanVien1> idNhanVien(String tenNV) {
        ArrayList<NhanVien1> list = new ArrayList<>();
        String sql = "SELECT nv.id, cv.ten_chuc_vu, nv.ten_nhan_vien, nv.ngay_sinh, nv.gioi_tinh, nv.dia_chi, "
                + "nv.email, nv.so_dien_thoai, nv.tai_khoan, nv.mat_khau, nv.ngay_tao, nv.ngay_sua, nv.trang_thai "
                + "FROM NhanVien nv "
                + "JOIN ChucVu cv ON nv.id_chuc_vu = cv.id "
                + "WHERE nv.ten_nhan_vien = ?";

        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tenChucVu = rs.getString("ten_chuc_vu");
                String tenNhanVien = rs.getString("ten_nhan_vien");
                Date ngaySinh = rs.getDate("ngay_sinh");
                boolean gioiTinh = rs.getBoolean("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("so_dien_thoai");
                String taiKhoan = rs.getString("tai_khoan");
                String matKhau = rs.getString("mat_khau");
                Date ngayTao = rs.getDate("ngay_tao");
                Date ngaySua = rs.getDate("ngay_sua");
                boolean trangThai = rs.getBoolean("trang_thai");

                NhanVien1 nv = new NhanVien1(id, tenChucVu, tenNhanVien, ngaySinh, gioiTinh, diaChi, email, soDienThoai, taiKhoan, matKhau, ngayTao, ngaySua, trangThai);
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<NhanVien1> timKiem(String tenNV) {
        ArrayList<NhanVien1> list = new ArrayList<>();
        String sql = "SELECT nv.id, cv.ten_chuc_vu, nv.ten_nhan_vien, nv.ngay_sinh, nv.gioi_tinh, nv.dia_chi, "
                + "nv.email, nv.so_dien_thoai, nv.tai_khoan, nv.mat_khau, nv.ngay_tao, nv.ngay_sua, nv.trang_thai "
                + "FROM NhanVien nv "
                + "JOIN ChucVu cv ON nv.id_chuc_vu = cv.id "
                + "WHERE nv.ten_nhan_vien LIKE ?";

        try (Connection conn = dbContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + tenNV + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tenChucVu = rs.getString("ten_chuc_vu");
                String tenNhanVien = rs.getString("ten_nhan_vien");
                Date ngaySinh = rs.getDate("ngay_sinh");
                boolean gioiTinh = rs.getBoolean("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("so_dien_thoai");
                String taiKhoan = rs.getString("tai_khoan");
                String matKhau = rs.getString("mat_khau");
                Date ngayTao = rs.getDate("ngay_tao");
                Date ngaySua = rs.getDate("ngay_sua");
                boolean trangThai = rs.getBoolean("trang_thai");

                NhanVien1 nv = new NhanVien1(id, tenChucVu, tenNhanVien, ngaySinh, gioiTinh, diaChi, email, soDienThoai, taiKhoan, matKhau, ngayTao, ngaySua, trangThai);
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int sua(int id, NhanVien1 t) {
    String sql = "UPDATE NhanVien SET id_chuc_vu = ?, ten_nhan_vien = ?, ngay_sinh = ?, gioi_tinh = ?, dia_chi = ?, email = ?, so_dien_thoai = ?, ngay_sua = ?, trang_thai = ? WHERE id = ?";
    try (Connection con = dbContext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        int idCV = getIdChucVuByName(t.getTenCV());
        ps.setInt(1, idCV); // Đặt id chức vụ thay vì id nhân viên
        ps.setString(2, t.getTenNV());
        ps.setDate(3, new java.sql.Date(t.getNgaySinh().getTime()));
        ps.setBoolean(4, t.getGioiTinh());
        ps.setString(5, t.getDiaChi());
        ps.setString(6, t.getEmail());
        ps.setString(7, t.getSdt());
        ps.setDate(8, new java.sql.Date(t.getNgaySua().getTime())); // Sửa vị trí cho ngày sửa
        ps.setBoolean(9, t.getTrangThai());
        ps.setInt(10, id);

        return ps.executeUpdate(); // Thực hiện câu lệnh update và trả về số hàng bị ảnh hưởng
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}
}
