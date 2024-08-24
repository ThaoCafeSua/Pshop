/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duck1
 */
public class jdbc {

    private static final String USERNAME = "haha";
    private static final String PASSWORD = "1";
    private static final String SERVER = "localhost";
    private static final String PORT = "1433";
    private static final String DATABASE_NAME = "DUAN1_PRO1041_NHOM7_P5";
    private static final boolean USING_SSL = true;

    private static String CONNECT_STRING;

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            StringBuilder connectStringBuilder = new StringBuilder();
            connectStringBuilder.append("jdbc:sqlserver://")
                    .append(SERVER).append(":").append(PORT).append(";")
                    .append("databaseName=").append(DATABASE_NAME).append(";")
                    .append("user=").append(USERNAME).append(";")
                    .append("password=").append(PASSWORD).append(";");
            if (USING_SSL) {
                connectStringBuilder.append("encrypt=true;trustServerCertificate=true;");
            }
            CONNECT_STRING = connectStringBuilder.toString();
//            System.out.println("Connect String có dạng: " + CONNECT_STRING);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNECT_STRING);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {           //goi thủ tục lưu trữ
            pstmt = connection.prepareCall(sql);  //(store procedure)
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]); // ps.setString(1, hv.gẹtHoTen());
        }
        return pstmt;
    }

    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = jdbc.getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            return null;
        }

    }

    public static int update(String sql, Object... args) { //insert, update, delete
        try {
            PreparedStatement stmt = jdbc.getStmt(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = jdbc.query(sql, args);
            if (rs.next()) {
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            getConnection();
            System.out.println("Thanh Cong Roi !");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean checkEmailExists(String email) {
        Connection conn = getConnection();
        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE EMAIL = ?";
        try {
            PreparedStatement preSt = conn.prepareCall(sql);
            preSt.setString(1, email);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException ex) {
            // Xử lý ngoại lệ
        }
        return false;
    }

}
