/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Model_khachHang;
import responsitory.KhachHangRepository;

/**
 *
 * @author admin
 */
public class KhachHang extends javax.swing.JPanel {
 private DefaultTableModel mol = new DefaultTableModel();
    private KhachHangRepository rp = new KhachHangRepository();
    private int i = 1;

    /**
     * Creates new form KhachHangJPanel
     */
    public KhachHang() {
        initComponents();
          tbl_bang.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_bang.getColumnModel().getColumn(0).setMaxWidth(0);
        cbo_trangThai.removeAllItems();
        cbo_trangThai.addItem("Hoạt Động");
        cbo_trangThai.addItem("Không Hoạt Động");
        fillTable(rp.getAll());

        cbo_locgt.removeAllItems();

        cbo_locgt.addItem("Nam");
        cbo_locgt.addItem("Nữ");
        cbo_loctt.removeAllItems();

        cbo_loctt.addItem("Hoạt Động");
        cbo_loctt.addItem("Không Hoạt Động");
        String ten = txt_ten.getText().trim();

    }
       public void fillTable(ArrayList<Model_khachHang> t) {
        mol = (DefaultTableModel) tbl_bang.getModel();
        mol.setRowCount(0);
        int stt=1;
        for (Model_khachHang k : t) {
            mol.addRow( new Object[]{k.getId(),stt++,k.getTen(),k.getNgaySinh(),k.isGioiTinh()?"Nam":"Nữ",k.getDiaChi(),k.getEmail(),k.getSdt(),k.getNgayTao(),k.getNgaySua(),k.isTrangThai()?"Hoạt Động":"Không Hoạt Động"});
        }

    }
     


    public Model_khachHang readForm() {
  

    String ten = txt_ten.getText().trim();
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên");
        txt_ten.requestFocus();
        return null;
    } else if (!chiChuaChuCai(ten)) {
        JOptionPane.showMessageDialog(this, "Họ Tên chỉ được chứa chữ cái và viết dấu");
        txt_ten.requestFocus();
        return null;
    }

    String ngaySinh = txt_ngaySinh.getText().trim();
    if (ngaySinh.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sinh");
        txt_ngaySinh.requestFocus();
        return null;
    } else if (!isValidDate(ngaySinh)) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng yyyy-MM-dd");
        txt_ngaySinh.requestFocus();
        return null;
    }

    boolean gioiTinh = rdo_nam.isSelected();

    String diaChi = txt_dc.getText().trim();
    if (diaChi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ");
        txt_dc.requestFocus();
        return null;
    }

    String email = txt_email.getText().trim();
    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập email");
        txt_email.requestFocus();
        return null;
    }
    if (rp.checkEmail(email)) {
        JOptionPane.showMessageDialog(this, "Email đã tồn tại.");
        txt_email.requestFocus();
        return null;
    }

    String sdt = txt_sdt.getText().trim();
    if (sdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại");
        txt_sdt.requestFocus();
        return null;
    } else if (!isValidPhoneNumber(sdt)) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Số điện thoại chỉ được chứa 10-11 chữ số.");
        txt_sdt.requestFocus();
        return null;
    }
    if (rp.checkPhoneNumber(sdt)) {
        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại.");
        txt_sdt.requestFocus();
        return null;
    }

    String ngayTao = txt_ngayTao.getText().trim();
    if (ngayTao.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày tạo");
        txt_ngayTao.requestFocus();
        return null;
    } else if (!isValidDate(ngayTao)) {
        JOptionPane.showMessageDialog(this, "Ngày tạo không đúng định dạng yyyy-MM-dd");
        txt_ngayTao.requestFocus();
        return null;
    }

    String ngaySua = txt_ngaySua.getText().trim();
    if (ngaySua.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sửa");
        txt_ngaySua.requestFocus();
        return null;
    } else if (!isValidDate(ngaySua)) {
        JOptionPane.showMessageDialog(this, "Ngày sửa không đúng định dạng yyyy-MM-dd");
        txt_ngaySua.requestFocus();
        return null;
    }

    boolean trangThai = cbo_trangThai.getSelectedItem().toString().equalsIgnoreCase("Hoạt Động");
    return new Model_khachHang(0, ten, ngaySinh, gioiTinh, diaChi, email, sdt, ngayTao, ngaySua, trangThai);
}
    public Model_khachHang readFormTT() {
  

    String ten = txt_ten.getText().trim();
    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên");
        txt_ten.requestFocus();
        return null;
    } else if (!chiChuaChuCai(ten)) {
        JOptionPane.showMessageDialog(this, "Họ Tên chỉ được chứa chữ cái và viết dấu");
        txt_ten.requestFocus();
        return null;
    }

    String ngaySinh = txt_ngaySinh.getText().trim();
    if (ngaySinh.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sinh");
        txt_ngaySinh.requestFocus();
        return null;
    } else if (!isValidDate(ngaySinh)) {
        JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng yyyy-MM-dd");
        txt_ngaySinh.requestFocus();
        return null;
    }

    boolean gioiTinh = rdo_nam.isSelected();

    String diaChi = txt_dc.getText().trim();
    if (diaChi.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ");
        txt_dc.requestFocus();
        return null;
    }

    String email = txt_email.getText().trim();
    if (email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập email");
        txt_email.requestFocus();
        return null;
    }
   
    String sdt = txt_sdt.getText().trim();
    if (sdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại");
        txt_sdt.requestFocus();
        return null;
    } else if (!isValidPhoneNumber(sdt)) {
        JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Số điện thoại chỉ được chứa 10-11 chữ số.");
        txt_sdt.requestFocus();
        return null;
    }
   

    String ngayTao = txt_ngayTao.getText().trim();
    if (ngayTao.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày tạo");
        txt_ngayTao.requestFocus();
        return null;
    } else if (!isValidDate(ngayTao)) {
        JOptionPane.showMessageDialog(this, "Ngày tạo không đúng định dạng yyyy-MM-dd");
        txt_ngayTao.requestFocus();
        return null;
    }

    String ngaySua = txt_ngaySua.getText().trim();
    if (ngaySua.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập ngày sửa");
        txt_ngaySua.requestFocus();
        return null;
    } else if (!isValidDate(ngaySua)) {
        JOptionPane.showMessageDialog(this, "Ngày sửa không đúng định dạng yyyy-MM-dd");
        txt_ngaySua.requestFocus();
        return null;
    }

    boolean trangThai = cbo_trangThai.getSelectedItem().toString().equalsIgnoreCase("Hoạt Động");
    return new Model_khachHang(0, ten, ngaySinh, gioiTinh, diaChi, email, sdt, ngayTao, ngaySua, trangThai);
}
 


    

    public boolean chiChuaChuCai(String str) {
        return str.matches("[\\p{L} ]+");
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10,11}");
    }

    public boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public void xoaForm() {
        txt_ten.setText("");
        txt_ngaySinh.setText("");
        rdo_nam.setSelected(true);

        txt_dc.setText("");
        txt_email.setText("");
        txt_sdt.setText("");
        txt_ngayTao.setText("");
        txt_ngaySua.setText("");
        cbo_trangThai.setSelectedItem("Hoạt Động");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbo_locgt = new javax.swing.JComboBox<>();
        cbo_loctt = new javax.swing.JComboBox<>();
        btn_loc = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btn_lamMoi = new javax.swing.JButton();
        txt_timKiem = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdo_nu = new javax.swing.JRadioButton();
        rdo_nam = new javax.swing.JRadioButton();
        txt_diaChi = new javax.swing.JScrollPane();
        txt_dc = new javax.swing.JTextArea();
        txt_email = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_ngayTao = new javax.swing.JTextField();
        txt_ngaySua = new javax.swing.JTextField();
        txt_ten = new javax.swing.JTextField();
        txt_ngaySinh = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cbo_trangThai = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1265, 780));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách khách hang"));
        jPanel1.setName(""); // NOI18N
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel1KeyTyped(evt);
            }
        });

        tbl_bang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "STT", "Tên", "ngày sinh", "giới tính", "địa chỉ", "email", "Số điện thoại", "Ngày Tạo", "ngày Sủa", "Trạng thái"
            }
        ));
        tbl_bang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_bangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bang);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Lọc");

        jLabel11.setText("Giới Tính");

        cbo_locgt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbo_loctt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btn_loc.setText("Lọc");
        btn_loc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_locActionPerformed(evt);
            }
        });

        jLabel12.setText("Trạng Thái");

        btn_lamMoi.setBackground(new java.awt.Color(0, 255, 204));
        btn_lamMoi.setText("Làm Mới");
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbo_loctt, 0, 99, Short.MAX_VALUE)
                            .addComponent(cbo_locgt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btn_loc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_lamMoi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbo_locgt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbo_loctt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_lamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btn_loc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel13.setText("Tìm kiếm theo tên");

        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thêm thông tin khách hàng"));

        jLabel2.setText("Ngày SInh");

        jLabel3.setText("Tên");

        jLabel4.setText("Email");

        jLabel5.setText("Giới Tính");

        jLabel6.setText("Địa Chỉ");

        jLabel7.setText("Số Điện Thoại");

        jLabel8.setText("Ngày Tạo");

        jLabel9.setText("Ngày Sửa");

        buttonGroup1.add(rdo_nu);
        rdo_nu.setText("Nữ");

        buttonGroup1.add(rdo_nam);
        rdo_nam.setSelected(true);
        rdo_nam.setText("Nam");

        txt_dc.setColumns(20);
        txt_dc.setRows(5);
        txt_diaChi.setViewportView(txt_dc);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btn_them.setBackground(new java.awt.Color(51, 255, 204));
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(0, 255, 204));
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 255, 204));
        jButton1.setText("Làm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btn_them)
                .addGap(18, 18, 18)
                .addComponent(btn_sua)
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel10.setText("Trạng Thái");

        cbo_trangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel3)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(rdo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_nu)
                        .addGap(146, 146, 146)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(62, 62, 62)
                        .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(70, 70, 70)
                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(26, 26, 26)
                        .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jLabel6))
                            .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdo_nam)
                            .addComponent(rdo_nu)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(54, 54, 54))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_bangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangMouseClicked
        // TODO add your handling code here:
        i = tbl_bang.getSelectedRow();
        txt_ten.setText(tbl_bang.getValueAt(i, 2).toString());
        txt_ngaySinh.setText(tbl_bang.getValueAt(i, 3).toString());
        if (tbl_bang.getValueAt(i, 4).toString().equalsIgnoreCase("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }
        txt_dc.setText(tbl_bang.getValueAt(i, 5).toString());
        txt_email.setText(tbl_bang.getValueAt(i, 6).toString());
        txt_sdt.setText(tbl_bang.getValueAt(i, 7).toString());
        txt_ngayTao.setText(tbl_bang.getValueAt(i, 8).toString());
        txt_ngaySua.setText(tbl_bang.getValueAt(i, 9).toString());
        cbo_trangThai.setSelectedItem(tbl_bang.getValueAt(i, 10).toString());
    }//GEN-LAST:event_tbl_bangMouseClicked

    private void tbl_bangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_bangMouseEntered

    private void btn_locActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_locActionPerformed
        // TODO add your handling code here:
        String gioiTinh = (String) cbo_locgt.getSelectedItem();
        boolean gioiTinhValue;

        String trangThai = (String) cbo_loctt.getSelectedItem();
        boolean trangThaiValue;
        if (gioiTinh.equals("Nam")) {
            gioiTinhValue = true;

        } else {
            gioiTinhValue = false;
        }

        if (trangThai.equals("Hoạt Động")) {
            trangThaiValue = true;

        } else {
            trangThaiValue = false;
        }

        fillTable(rp.locAll(gioiTinhValue, trangThaiValue));

    }//GEN-LAST:event_btn_locActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
        fillTable(rp.getAll());
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String tim=txt_timKiem.getText().toString();
        if(!tim.isEmpty()){
            if(!rp.timKiem(tim).isEmpty()){
                JOptionPane.showMessageDialog(this,"tìm thấy");
                fillTable(rp.timKiem(tim));

            }
            else
            JOptionPane.showMessageDialog(this,"ko tìm thấy");
        }
        else
        JOptionPane.showMessageDialog(this,"ô tìm kiếm đang trống");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1KeyTyped

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
       if (readForm() != null) {
        if (rp.them(readForm()) > 0) {
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
            fillTable(rp.getAll());
        }
    }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
     int i = tbl_bang.getSelectedRow();
    if (i != -1) {
        int id = Integer.parseInt(tbl_bang.getValueAt(i, 0).toString());
        Model_khachHang khachHang = readFormTT();
        if (khachHang != null) {
 
            if (checkTrung(id)) {
        
                if (rp.sua(id, khachHang) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    fillTable(rp.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
            }
        }
    }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        xoaForm();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JButton btn_loc;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbo_locgt;
    private javax.swing.JComboBox<String> cbo_loctt;
    private javax.swing.JComboBox<String> cbo_trangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JTable tbl_bang;
    private javax.swing.JTextArea txt_dc;
    private javax.swing.JScrollPane txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txt_timKiem;
    // End of variables declaration//GEN-END:variables

 private boolean checkTrung(int boQuaId) {
    String email = txt_email.getText().trim();
    String sdt = txt_sdt.getText().trim();
    
    for (int i = 0; i < tbl_bang.getRowCount(); i++) {
        int idTable = Integer.parseInt(tbl_bang.getValueAt(i, 0).toString());
        if (idTable == boQuaId) continue;
        
        String emailTable = (String) tbl_bang.getValueAt(i, 6);
        String sdtTable = (String) tbl_bang.getValueAt(i, 7);

        if (email.equals(emailTable)) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại.");
            return false;
        }
        if (sdt.equals(sdtTable)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại.");
            return false;
        }
    }
    return true;
}}
