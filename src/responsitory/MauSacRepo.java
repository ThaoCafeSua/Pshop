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
import model.MauSac;

/**
 *
 * @author Thanh Thao
 */
public class MauSacRepo {
 JDBC.jdbc dbContext;
 
    public List<MauSac> getAll() {
        List<MauSac> lst = new ArrayList<>();
        String sql = "SELECT [id], [ten_mau_sac], [trang_thai] FROM [dbo].[MauSac]";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tenMS = rs.getString("ten_mau_sac");
                Boolean trangThai = rs.getBoolean("trang_thai");
                MauSac ms = new MauSac(rs.getInt("id"), tenMS, trangThai);
                lst.add(ms);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }
     public Boolean add(MauSac ms) {
        String sql = "INSERT INTO MauSac (ten_mau_sac, trang_thai) values (?, ?)";

        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ms.getTenMS());
            ps.setBoolean(2, ms.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public boolean TonTaiTenMS(String tenMS) {
        String sql = "SELECT COUNT(*) FROM MauSac WHERE ten_mau_sac = ?";
        try ( Connection conn = dbContext.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenMS);
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

        String sql = "DELETE FROM [dbo].[MauSac]\n"
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
