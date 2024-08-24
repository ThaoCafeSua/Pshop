/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package JDBC;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;
public class MsgBox {

     private static final Icon icon = new javax.swing.ImageIcon(MsgBox.class.getResource("/Icon/thongbao.png"));

    public static void alert(Component parent, String message) {

        //JOptionPane.showMessageDialog(parent, message, "ADAM STORE thông báo", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(parent, message, "PSHOP thong bao", JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static boolean confirm(Component perent, String mess) {
        int result = JOptionPane.showConfirmDialog(perent, mess, "PSHOP thong bao", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        return result == JOptionPane.YES_OPTION;
    }

    public static String promt(Component parent, String mess) {
        return JOptionPane.showInputDialog(parent, mess, "PSHOP thong bao", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
