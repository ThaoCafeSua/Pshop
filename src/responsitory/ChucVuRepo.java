package responsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ChucVu;

public class ChucVuRepo {
    JDBC.jdbc dbContext;

//    public List<ChucVu> getAll() {
//        List<ChucVu> lst = new ArrayList<>();
//        String sql = "SELECT id, ten_chuc_vu, trang_thai FROM CHUCVU";
//
//        try (Connection conn = dbContext.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                Integer id = rs.getInt(1);
//                String tenCV = rs.getString(2);
//                int trangThai = rs.getInt(3);
//                ChucVu cv = new ChucVu(id, tenCV, trangThai);
//                lst.add(cv);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return lst;
   }

