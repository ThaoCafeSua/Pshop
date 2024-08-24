/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import JDBC.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien1;

/**
 *
 * @author admin
 */
public class NhanVienDao extends SysDAO<NhanVien1, Integer> {

    String SelectByTaiKhoan = """
                             select * from NhanVien
                             where tai_khoan = ?;
                             """;

//    @Override
//    public int insert(NhanVienEntity entity) {
//        String sql = """
//                    INSERT INTO [dbo].[NhanVien]
//                               ([id_chuc_vu]
//                               ,[ten_nhan_vien]
//                               ,[ngay_sinh]
//                               ,[gioi_tinh]
//                               ,[dia_chi]
//                               ,[email]
//                               ,[so_dien_thoai]
//                               ,[tai_khoan]
//                               ,[mat_khau]
//                               ,[ngay_tao]
//                               ,[trang_thai])
//                         VALUES
//                               (?,?,?,?,?,?,?,?,?,getdate(),?)
//                    """;
//
//        return jdbc.update(sql,
//                entity.getChucVu(),
//                entity.getTenNV(),
//                entity.getGioiTinh(),
//                entity.getEmailNV(),
//                entity.getNgaySinh(),
//                entity.getDiaChi(),
//                entity.getSoDT(),
//                entity.getTaiKhoan(),
//                entity.getMatKhau(),
//                entity.getNgayTao(),
//                entity.getTrangThai());
//    }
//
//    @Override
//    public int update(NhanVienEntity entity, Integer id) {
//        String sql = """
//             UPDATE [dbo].[NhanVien]
//                SET [id_chuc_vu] =?
//                   ,[ten_nhan_vien] = ?
//                   ,[ngay_sinh] = ?
//                   ,[gioi_tinh] = ?
//                   ,[dia_chi] = ?
//                   ,[email] = ?
//                   ,[so_dien_thoai] = ?
//                   ,[tai_khoan] = ?
//                   ,[mat_khau] = ?
//                   ,[ngay_tao] = ?
//                   ,[ngay_sua] = ?
//                   ,[trang_thai] = ?
//              WHERE id_nhan_vien like ?
//             """;
//        return jdbc.update(
//                sql,
//                entity.getTenNV(),
//                entity.getGioiTinh(),
//                entity.getEmailNV(),
//                entity.getNgaySinh(),
//                entity.getDiaChi(),
//                entity.getSoDT(),
//                entity.getTaiKhoan(),
//                entity.getMatKhau(),
//                entity.getChucVu(),
//                entity.getTrangThai(),
//                id
//        );
//    }
//
//    @Override
//    public int delete(Integer id) {
//        String sql = "DELETE FROM NhanVien where id_nhan_vien like ?";
//        return jdbc.update(sql, id);
//    }
//
    
   

    @Override
    protected List<NhanVien1> selectBySql(String sql, Object... args) {
        List<NhanVien1> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbc.query(sql, args);
                while (rs.next()) {
                    NhanVien1 e = new NhanVien1(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDate(4),
                            rs.getBoolean(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getDate(11),
                            rs.getDate(12),
                            rs.getBoolean(13)         
                    );
                    list.add(e);

                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception ex) {
                  ex.printStackTrace();
        }
        return list;
    }
 @Override
    public List<NhanVien1> selectAll() {
        String sql = """
                      SELECT [id]
                              ,[id_chuc_vu]
                              ,[ten_nhan_vien]
                              ,[ngay_sinh]
                              ,[gioi_tinh]
                              ,[dia_chi]
                              ,[email]
                              ,[so_dien_thoai]
                              ,[tai_khoan]
                              ,[mat_khau]
                              ,[ngay_tao]
                              ,[ngay_sua]
                              ,[trang_thai]
                          FROM [dbo].[NhanVien]
                      """;
        return selectBySql(sql);
    }
    @Override
    public NhanVien1 selectById(Integer id) {
        String sql = """
            SELECT [id]
                  ,[id_chuc_vu]
                  ,[ten_nhan_vien]
                  ,[ngay_sinh]
                  ,[gioi_tinh]
                  ,[dia_chi]
                  ,[email]
                  ,[so_dien_thoai]
                  ,[tai_khoan]
                  ,[mat_khau]
                  ,[ngay_tao]
                  ,[ngay_sua]
                  ,[trang_thai]
              FROM [dbo].[NhanVien] where id = ?
            """;
        List<NhanVien1> list = selectBySql(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    
    public NhanVien1 selectByTk(String TaiKhoan) {
        List<NhanVien1> list = this.selectBySql(SelectByTaiKhoan, TaiKhoan);
        if (list.isEmpty()){
            return null;
        } else {
            return list.get(0);
        }
    }

    public void updateMK(NhanVien1 nv) {
        String sql = "UPDATE NhanVien set mat_khau = ? where id = ?";
        jdbc.update(sql,
                nv.getMatKhau(),
                nv.getTenCV()
        );
    }
}
