/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChucVu;
import model.NhanVien1;
import responsitory.NhanVienRepo;

/**
 *
 * @author Legion 5
 */
public class NhanVien1JPanel extends javax.swing.JPanel {

    private NhanVienRepo nhanVienRepo = new NhanVienRepo();

    /**
     * Creates new form NhanVien1JPanel
     */
    public NhanVien1JPanel() {
        initComponents();
        tblNhanVien.getColumnModel().getColumn(0).setMinWidth(0);
        tblNhanVien.getColumnModel().getColumn(0).setMaxWidth(0);
        loadDt((ArrayList<NhanVien1>) nhanVienRepo.getAll());
        cboChucVuLoc.addItem("Quản Lý");
        cboChucVuLoc.addItem("Nhân Viên");
        cboChucVu2.removeAllItems();

        cboGioiTinhloc.removeAllItems();
        cboGioiTinhloc.addItem("Nam");
        cboGioiTinhloc.addItem("Nữ");

        ArrayList<NhanVien1> list = new ArrayList<>();
        ArrayList<ChucVu> listCV = new ArrayList<>();
        initcbo();
    }

    void loadDt(ArrayList<NhanVien1> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        defaultTableModel.setRowCount(0);
        int i = 1;
        for (NhanVien1 nv : list) {

            defaultTableModel.addRow(new Object[]{
                nv.getId(),
                i++,
                nv.getTenCV(),
                nv.getTenNV(),
                nv.getNgaySinh(),
                nv.getGioiTinh() ? "Nam" : "Nữ",
                nv.getDiaChi(),
                nv.getEmail(),
                nv.getSdt(),
                nv.getTaiKhoan(),
                nv.getMatKhau(),
                nv.getNgayTao(),
                nv.getNgaySua(),
                nv.getTrangThai() ? "hoạt động" : "nghỉ",});
        }
        tblNhanVien.setModel(defaultTableModel);
    }

    void initcbo() {
        ArrayList<ChucVu> listChucVu = nhanVienRepo.getListChucVu();
        loadComboBoxChucVu(nhanVienRepo.getListChucVu());
    }

    void loadComboBoxChucVu(ArrayList<ChucVu> list) {
        DefaultComboBoxModel<ChucVu> comboBoxModel = (DefaultComboBoxModel) cboChucVu2.getModel();
        for (ChucVu chucVu : list) {
            comboBoxModel.addElement(chucVu);
        }

    }

    ArrayList<ChucVu> getListChucVuFromRepo() {
        // Giả sử đây là phương thức lấy danh sách từ repository
        ArrayList<ChucVu> list = new ArrayList<>();
        list.add(new ChucVu(1, "Quản lý"));
        list.add(new ChucVu(2, "Nhân viên"));
        return list;
    }

    public NhanVien1 readForm() {
        NhanVien1 nv = new NhanVien1();

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            nv.setId(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID không hợp lệ. Vui lòng nhập một số nguyên.");
            txtId.requestFocus();
            return null;
        }

        ChucVu selectedChucVu = (ChucVu) cboChucVu2.getSelectedItem();
        String chucVu = selectedChucVu.toString();

        if (chucVu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập chức vụ.");
            cboChucVu2.requestFocus();
            return null;
        }
        nv.setTenCV(chucVu);

        String tenNV = txtTenVN.getText().trim();
        if (tenNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên nhân viên.");
            txtTenVN.requestFocus();
            return null;
        }
        nv.setTenNV(tenNV);

        Date ngaySinh;
        try {
            ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(txtNgaySinh.getText().trim());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Định dạng yêu cầu: yyyy-MM-dd.");
            txtNgaySinh.requestFocus();
            return null;
        }
        nv.setNgaySinh(ngaySinh);

        Boolean gioiTinh = rdoNam.isSelected();
        nv.setGioiTinh(gioiTinh);

        String diaChi = txtDiaChi.getText().trim();
        if (diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ.");
            txtDiaChi.requestFocus();
            return null;
        }
        nv.setDiaChi(diaChi);

        String email = txtEmail1.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập email.");
            txtEmail1.requestFocus();
            return null;
        }
        nv.setEmail(email);

        String sdt = txtSdt.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại.");
            txtSdt.requestFocus();
            return null;
        }
        nv.setSdt(sdt);

        String taiKhoan = txtTaiKhoan.getText().trim();
        if (taiKhoan.isEmpty()) {
            taiKhoan = "defaultUsername";
        }
        nv.setTaiKhoan(taiKhoan);

        String matKhau = txtMatKhau.getText().trim();
        if (matKhau.isEmpty()) {
            matKhau = "defaultPassword";
        }

        nv.setMatKhau(matKhau);
        nv.setNgayTao(new Date());
        nv.setNgaySua(new Date());
        String trangThaiText = txtTrangThai.getText().trim();
        Boolean trangThai;
        if (trangThaiText.equalsIgnoreCase("Hoạt Động")) {
            trangThai = true;
        } else if (trangThaiText.equalsIgnoreCase("Không Hoạt Động")) {
            trangThai = false;
        } else {
            JOptionPane.showMessageDialog(this, "Trạng thái không hợp lệ. Vui lòng nhập 'Hoạt Động' hoặc 'Không Hoạt Động'.");
            txtTrangThai.requestFocus();
            return null;
        }
        nv.setTrangThai(trangThai);

        return nv;
    }

    public void xoaForm() {
        txtId.setText("");
        cboChucVu2.setSelectedItem("Hoạt Động");
        txtTenVN.setText("");
        txtNgaySinh.setText("");
        rdoNam.setSelected(true);
        txtDiaChi.setText("");
        txtEmail1.setText("");
        txtSdt.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        txtNgayTao.setText("");
        txtNgaySua.setText("");
        txtTrangThai.setText("");

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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtNgayTao = new javax.swing.JTextPane();
        lbl2 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtEmail1 = new javax.swing.JTextPane();
        lbl1GioiTinh = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        lblTenNV = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtNgaySinh = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtTaiKhoan = new javax.swing.JTextPane();
        rdoNu = new javax.swing.JRadioButton();
        lblDiaChi = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        lblIdCv = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtId = new javax.swing.JTextPane();
        lblTaiKhoan = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTrangThai = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtTenVN = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtSdt = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblChucVu = new javax.swing.JLabel();
        cboChucVuLoc = new javax.swing.JComboBox<>();
        lblGioiTinh = new javax.swing.JLabel();
        cboGioiTinhloc = new javax.swing.JComboBox<>();
        tbnLoc = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cboChucVu2 = new javax.swing.JComboBox<>();
        lblMatKhau1 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtMatKhau = new javax.swing.JTextPane();
        lblMatKhau2 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtNgaySua = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân vien", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        jScrollPane10.setViewportView(txtNgayTao);

        lbl2.setText("NGAY SINH");

        lblEmail.setText("EMAIL");

        lblId.setText("ID");

        jScrollPane17.setViewportView(txtEmail1);

        lbl1GioiTinh.setText("GIỚI TINH");

        lblMatKhau.setText("Ngày Sửa");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("NAM ");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        lblTenNV.setText("TÊN NV");

        jScrollPane6.setViewportView(txtNgaySinh);

        jScrollPane9.setViewportView(txtTaiKhoan);

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        lblDiaChi.setText("ĐỊA CHỈ");

        lblSdt.setText("SĐT");

        lblIdCv.setText("TEN CV");

        lblTrangThai.setText("TRẠNG THÁI ");

        jScrollPane16.setViewportView(txtDiaChi);

        jScrollPane2.setViewportView(txtId);

        lblTaiKhoan.setText("TÀI KHOẢN");

        jScrollPane3.setViewportView(txtTrangThai);

        jScrollPane7.setViewportView(txtTenVN);

        jScrollPane12.setViewportView(txtSdt);

        btnSua.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 0, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 255));
        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnSua1.setForeground(new java.awt.Color(0, 0, 255));
        btnSua1.setText("Clear");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnSua1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnSua1)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnThem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LỌC NHÂN VIÊN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Emoji", 0, 12), new java.awt.Color(102, 102, 255))); // NOI18N

        lblChucVu.setText("Chức vu");

        cboChucVuLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuLocActionPerformed(evt);
            }
        });

        lblGioiTinh.setText("Giới Tính");

        tbnLoc.setText("LỌC");
        tbnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnLocActionPerformed(evt);
            }
        });

        jButton3.setText("LÀM MỚI");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblChucVu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboChucVuLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tbnLoc))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblGioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboGioiTinhloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChucVu)
                            .addComponent(cboChucVuLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tbnLoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh)
                    .addComponent(cboGioiTinhloc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        cboChucVu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVu2ActionPerformed(evt);
            }
        });

        lblMatKhau1.setText("MẬT KHẨU ");

        jScrollPane11.setViewportView(txtMatKhau);

        lblMatKhau2.setText("Ngày Tạo");

        jScrollPane13.setViewportView(txtNgaySua);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(403, 403, 403)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbl1GioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdoNu)
                                .addGap(41, 41, 41))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbl2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblIdCv, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cboChucVu2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(13, 13, 13)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMatKhau2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(lblTrangThai)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 8, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(233, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1065, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl1GioiTinh))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(lblMatKhau1))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(lblTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                    .addComponent(cboChucVu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblIdCv)
                                                                    .addComponent(lblSdt))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblTenNV))))
                                                        .addGap(28, 28, 28)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(lbl2)
                                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(31, 31, 31))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(103, 103, 103)
                                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblMatKhau2)
                                                .addComponent(rdoNam)
                                                .addComponent(rdoNu)))))
                                .addGap(12, 12, 12))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMatKhau)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addComponent(lblTrangThai)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDiaChi)
                                .addGap(60, 60, 60))))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(lblId)
                    .addContainerGap(329, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Nhân Viên");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N

        tblNhanVien.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "STT", "CHỨC VỤ", "TÊN NV", "NGÀY SINH", "GIỚI TÍNH", "ĐỊA CHỈ", "EMAIL", "SDT", "TÀI KHOẢN", "MẬT KHẨU", "NGÀY TẠO", "NGÀY SỬA", "TRẠNG THÁI"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        btnTimKiem.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnTimKiem.setForeground(new java.awt.Color(102, 102, 255));
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(454, 454, 454)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        // Lấy chỉ số hàng được chọn trong bảng
        int i = tblNhanVien.getSelectedRow();

        // Kiểm tra nếu người dùng đã chọn một hàng
        if (i != -1) {
            // Đọc dữ liệu từ form và tạo đối tượng NhanVien1
            NhanVien1 nv = readForm();

            if (nv != null) {
                // Lấy ID của nhân viên từ bảng
                int id = Integer.parseInt(tblNhanVien.getValueAt(i, 0).toString());

                // Gọi phương thức sua (sửa) của nhanVienRepo để cập nhật thông tin nhân viên
                if (nhanVienRepo.sua(id, nv) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    // Tải lại dữ liệu vào bảng
                    loadDt(nhanVienRepo.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        NhanVien1 nv = readForm();
        if (nv != null) {
            System.out.println("NhanVien object created successfully: " + nv.toString());
            if (nhanVienRepo.add(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                loadDt(nhanVienRepo.getAll()); // Đổi thành loadData nếu đây là tên đúng của phương thức
            } else {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        xoaForm();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void cboChucVuLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuLocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChucVuLocActionPerformed

    private void tbnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnLocActionPerformed
        //        String gioiTinh = (String) cboGioiTinhloc.getSelectedItem();
        //        boolean gioiTinhValue;
        //
        //        if (gioiTinh.equals("Nam")) {
        //            gioiTinhValue = true;
        //        } else {
        //            gioiTinhValue = false;
        //        }
        //
        //        String chucVuValue = (String) cboChucVuLoc.getSelectedItem();
        String chucVu = (String) cboChucVuLoc.getSelectedItem();
        String chucVuValue; // Assuming you need to use chucVuValue for something else

        String gioiTinh = (String) cboGioiTinhloc.getSelectedItem();
        Boolean gioiTinhValue;
        if (chucVu.equalsIgnoreCase("Quản Lý")) {
            chucVuValue = "Quản Lý";
        } else {
            chucVuValue = "Nhân Viên";
        }

        if (gioiTinh.equalsIgnoreCase("Nam")) {
            gioiTinhValue = true;
        } else {
            gioiTinhValue = false;

        }
        // Chuyển đổi giá trị từ JComboBox thành String
        // Gọi phương thức locAll với giá trị chuyển đổi
        // Chuyển đổi giá trị từ JComboBox thành String
        // Gọi phương thức locAll với giá trị chuyển đổi
        loadDt(nhanVienRepo.locAll(chucVuValue, gioiTinhValue));
    }//GEN-LAST:event_tbnLocActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        loadDt(nhanVienRepo.getAll());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cboChucVu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChucVu2ActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
           int i = tblNhanVien.getSelectedRow();
    if (i != -1) {
        txtId.setText(tblNhanVien.getValueAt(i, 0).toString());

        // Tìm đối tượng ChucVu tương ứng
        String chucVuName = tblNhanVien.getValueAt(i, 2).toString();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChucVu2.getModel();
        ChucVu selectedChucVu = null;
        for (int j = 0; j < model.getSize(); j++) {
            ChucVu cv = (ChucVu) model.getElementAt(j);
            if (cv.getTenCV().equalsIgnoreCase(chucVuName)) {
                selectedChucVu = cv;
                break;
            }
        }
        if (selectedChucVu != null) {
            cboChucVu2.setSelectedItem(selectedChucVu);
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng
            System.out.println("Chức vụ không tìm thấy: " + chucVuName);
        }
            txtTenVN.setText(tblNhanVien.getValueAt(i, 3).toString());
            txtNgaySinh.setText(tblNhanVien.getValueAt(i, 4).toString());
            if (tblNhanVien.getValueAt(i, 5).toString().equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(tblNhanVien.getValueAt(i, 6).toString());
            txtEmail1.setText(tblNhanVien.getValueAt(i, 7).toString());
            txtSdt.setText(tblNhanVien.getValueAt(i, 8).toString());
            txtTaiKhoan.setText(tblNhanVien.getValueAt(i, 9).toString());
            txtMatKhau.setText(tblNhanVien.getValueAt(i, 10).toString());
            txtNgayTao.setText(tblNhanVien.getValueAt(i, 11).toString());
            txtNgaySua.setText(tblNhanVien.getValueAt(i, 12).toString());
            txtTrangThai.setText(tblNhanVien.getValueAt(i, 13).toString());
        }
//
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked

    }//GEN-LAST:event_btnTimKiemMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String tim = txtTimKiem.getText().trim();
        if (!tim.isEmpty()) {
            ArrayList<NhanVien1> resultList = nhanVienRepo.timKiem(tim);
            if (resultList != null && !resultList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tìm thấy");
                loadDt(resultList); // Truyền kết quả vào phương thức loadData
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ô tìm kiếm đang trống");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChucVu2;
    private javax.swing.JComboBox<String> cboChucVuLoc;
    private javax.swing.JComboBox<String> cboGioiTinhloc;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lbl1GioiTinh;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdCv;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblMatKhau1;
    private javax.swing.JLabel lblMatKhau2;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JButton tbnLoc;
    private javax.swing.JTextPane txtDiaChi;
    private javax.swing.JTextPane txtEmail1;
    private javax.swing.JTextPane txtId;
    private javax.swing.JTextPane txtMatKhau;
    private javax.swing.JTextPane txtNgaySinh;
    private javax.swing.JTextPane txtNgaySua;
    private javax.swing.JTextPane txtNgayTao;
    private javax.swing.JTextPane txtSdt;
    private javax.swing.JTextPane txtTaiKhoan;
    private javax.swing.JTextPane txtTenVN;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextPane txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
