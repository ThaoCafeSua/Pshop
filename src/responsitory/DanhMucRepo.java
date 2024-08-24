package responsitory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DanhMuc;

/**
 *
 * @author Thanh Thao
 */
public class DanhMucRepo {

    JDBC.jdbc dbContext;

    public List<DanhMuc> getAll() {
        List<DanhMuc> lst = new ArrayList<>();
        String sql = "SELECT [id], [ten_danh_muc], [trang_thai] FROM [dbo].[DanhMuc]";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenDM = rs.getString("ten_danh_muc");
                Boolean trangThai = rs.getBoolean("trang_thai");
                DanhMuc dm = new DanhMuc(rs.getInt("id"), tenDM, trangThai);
                lst.add(dm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }
     public Boolean add(DanhMuc dm) {
        String sql = "INSERT INTO DanhMuc (ten_danh_muc, trang_thai) values (?, ?)";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dm.getTenDM());
            ps.setBoolean(2, dm.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public boolean TonTaiTenDM(String tenDM) {
        String sql = "SELECT COUNT(*) FROM DanhMuc WHERE ten_danh_muc = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public Boolean Xoa(Integer id) {

        String sql = "DELETE FROM [dbo].[DanhMuc]\n"
                + "      WHERE id=?";
        try ( Connection conn = dbContext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
