package responsitory;

import JDBC.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.SanPhamChiTietViewModel;
import model.Size;

/**
 *
 * @author Thanh Thao
 */
public class SPCTRepo {

    JDBC.jdbc dbContext;

    public List<SanPhamChiTietViewModel> getAll1() {
        List<SanPhamChiTietViewModel> lst = new ArrayList<>();
        String sql = "SELECT sp.ten_san_pham, ms.ten_mau_sac, sz.ten_size, "
                + "spct.so_luong, spct.gia_ban, spct.trang_thai "
                + "FROM SanPhamChiTiet spct "
                + "INNER JOIN SanPham sp ON spct.id_san_pham = sp.id "
                + "INNER JOIN MauSac ms ON spct.id_mau_sac = ms.id "
                + "INNER JOIN Size sz ON spct.id_size = sz.id";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenSP = rs.getString(1);
                String tenMS = rs.getString(2);
                String tenSize = rs.getString(3);
                int soLuong = rs.getInt(4);
                float giaBan = rs.getFloat(5);
                Boolean trangThai = rs.getBoolean(6);
                SanPhamChiTietViewModel spctViewModel = new SanPhamChiTietViewModel(soLuong, tenSP, tenMS, tenSize, soLuong, giaBan, trangThai);
                lst.add(spctViewModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public List<SanPhamChiTietViewModel> getAllSPCT() {
        List<SanPhamChiTietViewModel> lst = new ArrayList<>();
        String sql = "SELECT spct.id,sp.ten_san_pham, ms.ten_mau_sac, sz.ten_size,spct.so_luong, spct.gia_ban, spct.trang_thai \n"
                + "FROM SanPhamChiTiet spct \n"
                + "INNER JOIN SanPham sp ON spct.id_san_pham = sp.id \n"
                + "INNER JOIN MauSac ms ON spct.id_mau_sac = ms.id \n"
                + "INNER JOIN Size sz ON spct.id_size = sz.id ";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SanPhamChiTietViewModel spct = new SanPhamChiTietViewModel();
                spct.setId(rs.getInt(1));
                spct.setTenSP(rs.getString(2));
                spct.setTenMS(rs.getString(3));
                spct.setTenSize(rs.getString(4));
                spct.setSoLuong(rs.getInt(5));
                spct.setGiaBan(rs.getFloat(6));
                Boolean trangThai = rs.getBoolean(7);
                lst.add(spct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public ArrayList<SanPham> getListSanPham() {
        // Tao cau sql
        String sql = "Select * from SanPham";
        ArrayList<SanPham> list = new ArrayList<>();
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String tenSP = rs.getString(2);
                Integer idDM = rs.getInt(3);
                Integer idCL = rs.getInt(4);
                Integer idTH = rs.getInt(5);
                Date ngayTao = rs.getDate(6);
                Date ngaySua = rs.getDate(7);
                Boolean trangThai = rs.getBoolean(8);
                SanPham sp = new SanPham(id, tenSP, id, id, id, ngayTao, ngaySua, trangThai);
                list.add(sp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<MauSac> getListMauSac() {
        // Tao cau sql
        String sql = "Select * from MauSac";
        ArrayList<MauSac> list = new ArrayList<>();
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String ten = rs.getString(2);
                Boolean trangThai = rs.getBoolean(3);
                MauSac ms = new MauSac(id, ten, trangThai);
                list.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Size> getListSize() {
        // Tao cau sql
        String sql = "Select * from Size";
        ArrayList<Size> list = new ArrayList<>();
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt(1);
                String ten = rs.getString(2);
                Boolean trangThai = rs.getBoolean(3);
                Size sz = new Size(id, ten, trangThai);
                list.add(sz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Boolean addSPCT(SanPhamChiTiet sp) {
        String sql = "INSERT into SanPhamChiTiet (id_mau_sac, id_size,id_san_pham, "
                + "so_luong,gia_ban, trang_thai)"
                + " values (?,?,?,?,?,?)";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, sp.getIdMS());
            ps.setObject(2, sp.getIdSize());
            ps.setObject(3, sp.getIdSP());
            ps.setObject(4, sp.getSoLuong());
            ps.setObject(5, sp.getGiaBan());
            ps.setObject(6, sp.getTrangThai());
            int ketQua = ps.executeUpdate();
            if (ketQua > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateSPCT(Integer id, SanPhamChiTiet spct) {
        String sql = """
         UPDATE [dbo].[SanPhamChiTiet]
                  SET [id_mau_sac] = ?,
                      [id_size] = ?,
                      [id_san_pham] = ?,
                      [so_luong] = ?,
                      [gia_ban] = ?,
                      [trang_thai] = ?
          WHERE id = ? 
         """;
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, spct.getIdMS());
            ps.setInt(2, spct.getIdSize());
            ps.setInt(3, spct.getIdSP());
            ps.setInt(4, spct.getSoLuong());
            ps.setFloat(5, spct.getGiaBan());
            ps.setBoolean(6, spct.getTrangThai());
            ps.setInt(7, id);

            int ketQua = ps.executeUpdate();
            return ketQua > 0;
        } catch (Exception e) {
            e.printStackTrace(); // Thay bằng ghi nhật ký lỗi nếu cần
        }
        return false;
    }

    public List<SanPhamChiTietViewModel> TimKiem(String tenSanPham) {
        List<SanPhamChiTietViewModel> lst = new ArrayList<>();
        String sql = "SELECT sp.ten_san_pham, ms.ten_mau_sac, sz.ten_size, "
                + "spct.so_luong, spct.gia_ban, spct.trang_thai "
                + "FROM SanPhamChiTiet spct "
                + "INNER JOIN SanPham sp ON spct.id_san_pham = sp.id "
                + "INNER JOIN MauSac ms ON spct.id_mau_sac = ms.id "
                + "INNER JOIN Size sz ON spct.id_size = sz.id "
                + "WHERE sp.ten_san_pham LIKE ?";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + tenSanPham + "%");

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    String tenSP = rs.getString(1);
                    String tenMS = rs.getString(2);
                    String tenSize = rs.getString(3);
                    int soLuong = rs.getInt(4);
                    float giaBan = rs.getFloat(5);
                    Boolean trangThai = rs.getBoolean(6);
                    SanPhamChiTietViewModel spctViewModel = new SanPhamChiTietViewModel(soLuong, tenSP, tenMS, tenSize, soLuong, giaBan, trangThai);
                    lst.add(spctViewModel);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public SanPhamChiTietViewModel getAllSanPhamid(int x) {
        SanPhamChiTietViewModel sp = new SanPhamChiTietViewModel();
        Connection conn = null;
        PreparedStatement sttm = null;
        ResultSet rs = null;
        try {
            //"Ten San Pham", "Do Toa Huong", "Luu HUong", "Xuat Xu", "Nong Do", "Nhom Huong", "So Luong", "Gia"
            String sql = """
                         SELECT sp.ten_san_pham, ms.ten_mau_sac, sz.ten_size,spct.so_luong, spct.gia_ban, spct.trang_thai 
                         FROM SanPhamChiTiet spct 
                         INNER JOIN SanPham sp ON spct.id_san_pham = sp.id 
                         INNER JOIN MauSac ms ON spct.id_mau_sac = ms.id 
                         INNER JOIN Size sz ON spct.id_size = sz.id 
                         WHERE spct.trang_thai = 1 and spct.id = ?
                         """;

            conn = jdbc.getConnection();
            sttm = conn.prepareStatement(sql);
            sttm.setObject(1, x);
            rs = sttm.executeQuery();

            while (rs.next()) {

                sp.setTenSP(rs.getString(1));
                sp.setTenMS(rs.getString(2));
                sp.setTenSize(rs.getString(3));
                sp.setSoLuong(rs.getInt(4));
                sp.setGiaBan(rs.getFloat(5));
                sp.setTrangThai(rs.getBoolean(6));

            }
        } catch (SQLException e) {
            return null;
        }
        try {
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

}
