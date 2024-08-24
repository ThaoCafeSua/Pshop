package responsitory;

import JDBC.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ChatLieu;
import model.DanhMuc;
import model.SanPham;
import model.SanPhamChiTietViewModel;
import model.SanPhamViewModel;
import model.ThuongHieu;

public class SanPhamRepo {

    JDBC.jdbc dbContext;

    public List<SanPhamViewModel> getAll() {
        List<SanPhamViewModel> lst = new ArrayList<>();
        String sql = "SELECT sp.id, sp.ten_san_pham, th.ten_thuong_hieu, cl.ten_chat_lieu, dm.ten_danh_muc, "
                + "sp.ngay_tao, sp.ngay_sua, sp.trang_thai "
                + "FROM SanPham sp "
                + "INNER JOIN DanhMuc dm ON sp.id_danh_muc = dm.id "
                + "INNER JOIN ThuongHieu th ON sp.id_thuong_hieu = th.id "
                + "INNER JOIN ChatLieu cl ON sp.id_chat_lieu = cl.id ";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt(1);
                String tenSP = rs.getString(2);
                String tenTH = rs.getString(3);
                String tenCL = rs.getString(4);
                String tenDM = rs.getString(5);
                Date ngayTao = rs.getDate(6);
                Date ngaySua = rs.getDate(7);
                Boolean trangThai = rs.getBoolean(8);
                SanPhamViewModel sanPhamViewModel = new SanPhamViewModel(id, tenSP, tenDM, tenCL, tenTH, ngayTao, ngaySua, trangThai);
                lst.add(sanPhamViewModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }



    public ArrayList<DanhMuc> getListDanhMuc() {
        // Tao cau sql
        String sql = "Select * from DanhMuc";
        ArrayList<DanhMuc> list = new ArrayList<>();
        // ket noi co so du lieu va thuc thi truy van
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            // thuc thi truy van
            ResultSet rs = ps.executeQuery();
            // doc tung ban ghi vaf convert sang doi tuong hoa don
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String ten = rs.getString(2);
                Boolean trangThai = rs.getBoolean(3);
                DanhMuc danhMuc = new DanhMuc(id, ten, Boolean.TRUE);
                list.add(danhMuc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<SanPham> getAllSP() {
        List<SanPham> lst = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[ten_san_pham]\n"
                + "      ,[id_thuong_hieu]\n"
                + "      ,[id_chat_lieu]\n"
                + "      ,[id_danh_muc]\n"
                + "      ,[ngay_tao]\n"
                + "      ,[ngay_sua]\n"
                + "      ,[trang_thai]\n"
                + "  FROM [dbo].[SanPham] ";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt(1);
                String tenSP = rs.getString(2);
                Integer id_thuong_hieu = rs.getInt(3);
                Integer id_chat_lieu = rs.getInt(4);
                Integer id_danh_muc = rs.getInt(5);
                Date ngayTao = rs.getDate(6);
                Date ngaySua = rs.getDate(7);
                Boolean trangThai = rs.getBoolean(8);
                SanPham sanPham = new SanPham(id, tenSP, id, id, id, ngayTao, ngaySua, trangThai);
                lst.add(sanPham);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public ArrayList<ChatLieu> getListChatLieu() {
        String sql = "Select * from ChatLieu";
        ArrayList<ChatLieu> list = new ArrayList<>();
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            // thuc thi truy van
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String ten = rs.getString(2);
                Boolean trangThai = rs.getBoolean(3);
                ChatLieu cl = new ChatLieu(id, ten, trangThai);
                list.add(cl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<ThuongHieu> getListThuongHieu() {
        String sql = "Select * from ThuongHieu";
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String ten = rs.getString(2);
                Boolean trangThai = rs.getBoolean(3);
                ThuongHieu th = new ThuongHieu(id, ten, trangThai);
                list.add(th);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Boolean add(SanPham sp) {
        String sql = "INSERT into SanPham (ten_san_pham,id_thuong_hieu, id_chat_lieu, id_danh_muc, "
                + "ngay_tao,ngay_sua, trang_thai)"
                + " values (?,?,?,?,?,?,?)";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sp.getTenSP());
            ps.setObject(2, sp.getIdTH());
            ps.setObject(3, sp.getIdCL());
            ps.setObject(4, sp.getIdDM());
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis())); // Thêm giá trị cho ngay_tao
            ps.setDate(6, new java.sql.Date(System.currentTimeMillis())); // Thêm giá trị cho ngay_tao
            ps.setObject(7, sp.getTrangThai());

            // thuc thi = executeUpdate
            int ketQua = ps.executeUpdate();
            if (ketQua > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

public Boolean update(Integer id, SanPham sp) {
String sql = """
             UPDATE [dbo].[SanPham]
                SET [ten_san_pham] = ?
                   ,[id_thuong_hieu] = ?
                   ,[id_chat_lieu] = ?
                   ,[id_danh_muc] = ?
                   ,[ngay_tao] = ?
                   ,[ngay_sua] = ?
                   ,[trang_thai] = ?
              WHERE id = ? 
             """;
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSP());
            ps.setInt(2, sp.getIdTH());
            ps.setInt(3, sp.getIdCL());
            ps.setInt(4, sp.getIdDM());
            ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            ps.setDate(6, new java.sql.Date(System.currentTimeMillis()));
            ps.setBoolean(7, sp.getTrangThai());
            ps.setInt(8, id);

            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 public List<SanPhamViewModel> TimKiem(String tenSanPham) {
        List<SanPhamViewModel> lst = new ArrayList<>();
        String sql = "SELECT sp.id, sp.ten_san_pham, th.ten_thuong_hieu, cl.ten_chat_lieu, dm.ten_danh_muc, "
                + "sp.ngay_tao, sp.ngay_sua, sp.trang_thai "
                + "FROM SanPham sp "
                + "INNER JOIN DanhMuc dm ON sp.id_danh_muc = dm.id "
                + "INNER JOIN ThuongHieu th ON sp.id_thuong_hieu = th.id "
                + "INNER JOIN ChatLieu cl ON sp.id_chat_lieu = cl.id "
                + "WHERE sp.ten_san_pham LIKE ?";

        try (Connection conn = dbContext.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, "%" + tenSanPham + "%");
           

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt(1);
                    String tenSP = rs.getString(2);
                    String tenTH = rs.getString(3);
                    String tenCL = rs.getString(4);
                    String tenDM = rs.getString(5);
                    Date ngayTao = rs.getDate(6);
                    Date ngaySua = rs.getDate(7);
                    Boolean trangThaiResult = rs.getBoolean(8);
                    SanPhamViewModel sanPhamViewModel = new SanPhamViewModel(id, tenSP, tenDM, tenCL, tenTH, ngayTao, ngaySua, trangThaiResult);
                    lst.add(sanPhamViewModel);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }
 

}
