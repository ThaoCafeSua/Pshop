
package responsitory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Size;


public class SizeRepo {
 JDBC.jdbc dbContext;
 
    public List<Size> getAll() {
        List<Size> lst = new ArrayList<>();
        String sql = "SELECT [id], [ten_size], [trang_thai] FROM [dbo].[Size]";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenSize = rs.getString("ten_size");
                Boolean trangThai = rs.getBoolean("trang_thai");
                Size sz = new Size(rs.getInt("id"), tenSize, trangThai);
                lst.add(sz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }
     public Boolean add(Size sz) {
        String sql = "INSERT INTO Size (ten_size, trang_thai) values (?, ?)";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sz.getTenSize());
            ps.setBoolean(2, sz.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean TonTaiTenSZ(String tenSZ) {
        String sql = "SELECT COUNT(*) FROM Size WHERE ten_size = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenSZ);
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

        String sql = "DELETE FROM [dbo].[Size]\n"
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
