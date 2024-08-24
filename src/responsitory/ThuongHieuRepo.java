/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ThuongHieu;

/**
 *
 * @author Thanh Thao
 */
public class ThuongHieuRepo {

    JDBC.jdbc dbContext;

    public List<ThuongHieu> getAll() {
        List<ThuongHieu> lst = new ArrayList<>();
        String sql = "SELECT [id], [ten_thuong_hieu], [trang_thai] FROM [dbo].[ThuongHieu]";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenTH = rs.getString("ten_thuong_hieu");
                Boolean trangThai = rs.getBoolean("trang_thai");
                ThuongHieu th = new ThuongHieu(rs.getInt("id"), tenTH, trangThai);
                lst.add(th);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public Boolean add(ThuongHieu th) {
        String sql = "INSERT INTO ThuongHieu (ten_thuong_hieu, trang_thai) values (?, ?)";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, th.getTenTH());
            ps.setBoolean(2, th.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức kiểm tra sự tồn tại của tên thương hiệu
    public boolean TonTaiTenTH(String tenTH) {
        String sql = "SELECT COUNT(*) FROM ThuongHieu WHERE ten_thuong_hieu = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenTH);
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

        String sql = "DELETE FROM [dbo].[ThuongHieu]\n"
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
