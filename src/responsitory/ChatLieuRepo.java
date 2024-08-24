    package responsitory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ChatLieu;

/**
 *
 * @author Thanh Thao
 */
public class ChatLieuRepo {

    JDBC.jdbc dbContext;

    public List<ChatLieu> getAll() {
        List<ChatLieu> lst = new ArrayList<>();
        String sql = "SELECT [id], [ten_chat_lieu], [trang_thai] FROM [dbo].[ChatLieu]";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenCL = rs.getString("ten_chat_lieu");
                Boolean trangThai = rs.getBoolean("trang_thai");
                ChatLieu chatLieu = new ChatLieu(rs.getInt("id"), tenCL, trangThai);
                lst.add(chatLieu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public Boolean add(ChatLieu cl) {
        String sql = "INSERT INTO ChatLieu (ten_chat_lieu, trang_thai) values (?, ?)";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cl.getTenCL());
            ps.setBoolean(2, cl.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean TonTaiTenCL(String tenCL) {
        String sql = "SELECT COUNT(*) FROM ChatLieu WHERE ten_chat_lieu = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenCL);
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

        String sql = "DELETE FROM [dbo].[ChatLieu]\n"
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
