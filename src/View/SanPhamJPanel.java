/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChatLieu;
import model.DanhMuc;
import model.MauSac;
import model.SanPham;
import model.SanPhamChiTiet;
import model.SanPhamChiTietViewModel;
import model.SanPhamViewModel;
import model.Size;
import model.ThuongHieu;
import org.jfree.data.Value;
import responsitory.ChatLieuRepo;
import responsitory.DanhMucRepo;
import responsitory.MauSacRepo;
import responsitory.SPCTRepo;
import responsitory.SanPhamRepo;
import responsitory.SizeRepo;
import responsitory.ThuongHieuRepo;

/**
 *
 * @author admin
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    private SanPhamRepo sanPhamRepo = new SanPhamRepo();
    private SPCTRepo spctRepo = new SPCTRepo();
    private List<SanPham> lstsanpham = sanPhamRepo.getAllSP();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private List<SanPhamViewModel> listsp = new ArrayList<>();
    private List<SanPhamChiTietViewModel> lstspct = spctRepo.getAllSPCT();
    private List<SanPhamChiTietViewModel> lstspctview = new ArrayList<>();

    /**
     * Creates new form SanPhamNewJPanel
     */
    public SanPhamJPanel() {
        initComponents();
        tableModel = (DefaultTableModel) tblSP.getModel();
        loadData((ArrayList<SanPhamViewModel>) sanPhamRepo.getAll());
        loadData1((ArrayList<SanPhamChiTietViewModel>) spctRepo.getAll1());
        initCbo();
    }

    void initCbo() {

        loadComboBoxSanPham(spctRepo.getListSanPham());
        loadComboBoxMauSac(spctRepo.getListMauSac());
        loadComboBoxSize(spctRepo.getListSize());
        loadComboBox(sanPhamRepo.getListDanhMuc());
        loadComboBoxThuongHieu(sanPhamRepo.getListThuongHieu());
        loadComboBoxChatLieu(sanPhamRepo.getListChatLieu());
        loadComboBoxLocDM(sanPhamRepo.getListDanhMuc());
        loadComboBoxLocTH(sanPhamRepo.getListThuongHieu());
        loadComboBoxLocMS(spctRepo.getListMauSac());
        loadComboBoxLocSZ(spctRepo.getListSize());

        // Thêm sự kiện lắng nghe
        cboLocDanhMuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DanhMuc selectedDanhMuc = (DanhMuc) cboLocDanhMuc.getSelectedItem();
                filterDataByDanhMuc(selectedDanhMuc);
            }
        });
        cbolocTH1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThuongHieu th = (ThuongHieu) cbolocTH1.getSelectedItem();
                filterDataByThuongHieu(th);
            }
        });
        cbolocMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MauSac ms = (MauSac) cbolocMS.getSelectedItem();
                filterDataByMauSac(ms);
            }
        });
        cbolocSz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Size sz = (Size) cbolocSz.getSelectedItem();
                filterDataBySize(sz);
            }
        });
    }

    private void filterDataByDanhMuc(DanhMuc selectedDanhMuc) {
        ArrayList<SanPhamViewModel> filteredList = new ArrayList<>();
        for (SanPhamViewModel spvm : sanPhamRepo.getAll()) {
            if (selectedDanhMuc == null || spvm.getTenDM().equals(selectedDanhMuc.getTenDM())) {
                filteredList.add(spvm);
            }
        }
        loadData(filteredList);
    }

    private void filterDataByThuongHieu(ThuongHieu th) {
        ArrayList<SanPhamViewModel> filteredList = new ArrayList<>();
        for (SanPhamViewModel spvm : sanPhamRepo.getAll()) {
            if (th == null || spvm.getTenTH().equals(th.getTenTH())) {
                filteredList.add(spvm);
            }
        }
        loadData(filteredList);
    }

    private void filterDataByMauSac(MauSac ms) {
        ArrayList<SanPhamChiTietViewModel> filteredList = new ArrayList<>();
        for (SanPhamChiTietViewModel spctvm : spctRepo.getAll1()) {
            if (ms == null || spctvm.getTenMS().equals(ms.getTenMS())) {
                filteredList.add(spctvm);
            }
        }
        loadData1(filteredList);
    }

    private void filterDataBySize(Size sz) {
        ArrayList<SanPhamChiTietViewModel> filteredList = new ArrayList<>();
        for (SanPhamChiTietViewModel spctvm : spctRepo.getAll1()) {
            if (sz == null || spctvm.getTenSize().equals(sz.getTenSize())) {
                filteredList.add(spctvm);
            }
        }
        loadData1(filteredList);
    }

    void loadComboBoxSanPham(ArrayList<SanPham> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboSPCT.getModel();
        comboBoxModel.removeAllElements();
        for (SanPham sp : list) {
            comboBoxModel.addElement(sp);
        }
        loadData((ArrayList<SanPhamViewModel>) sanPhamRepo.getAll());
    }

    void loadComboBoxMauSac(ArrayList<MauSac> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboMS1.getModel();
        comboBoxModel.removeAllElements();
        for (MauSac ms : list) {
            comboBoxModel.addElement(ms);
        }

    }

    void loadComboBoxSize(ArrayList<Size> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboSize.getModel();
        comboBoxModel.removeAllElements();
        for (Size sz : list) {
            comboBoxModel.addElement(sz);
        }
    }

    void loadComboBox(ArrayList<DanhMuc> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboDM.getModel();
        comboBoxModel.removeAllElements();
        for (DanhMuc danhMuc : list) {
            comboBoxModel.addElement(danhMuc);
        }

    }

    void loadComboBoxThuongHieu(ArrayList<ThuongHieu> list) {
        DefaultComboBoxModel<ThuongHieu> comboBoxModel = (DefaultComboBoxModel) cboTH.getModel();
        comboBoxModel.removeAllElements();
        for (ThuongHieu thuongHieu : list) {
            comboBoxModel.addElement(thuongHieu);
        }
//        

    }

    void loadComboBoxChatLieu(ArrayList<ChatLieu> list) {
        DefaultComboBoxModel<ChatLieu> comboBoxModel = (DefaultComboBoxModel) cboCL.getModel();
        comboBoxModel.removeAllElements();
        for (ChatLieu chatLieu : list) {
            comboBoxModel.addElement(chatLieu);
        }

    }

    void loadComboBoxLocDM(ArrayList<DanhMuc> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cboLocDanhMuc.getModel();
        comboBoxModel.addElement(null);
        for (DanhMuc danhMuc : list) {
            comboBoxModel.addElement(danhMuc);
        }
    }
    //loc cbo

    void loadComboBoxLocTH(ArrayList<ThuongHieu> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbolocTH1.getModel();
        comboBoxModel.addElement(null);
        for (ThuongHieu th : list) {
            comboBoxModel.addElement(th);
        }
    }

    void loadComboBoxLocMS(ArrayList<MauSac> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbolocMS.getModel();
        comboBoxModel.addElement(null);
        for (MauSac ms : list) {
            comboBoxModel.addElement(ms);
        }
    }

    void loadComboBoxLocSZ(ArrayList<Size> list) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) cbolocSz.getModel();
        comboBoxModel.addElement(null);
        for (Size sz : list) {
            comboBoxModel.addElement(sz);
        }
    }

    void loadData(ArrayList<SanPhamViewModel> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblSP.getModel();
        defaultTableModel.setRowCount(0);
        for (SanPhamViewModel sanPhamViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                sanPhamViewModel.getTenSP(),
                sanPhamViewModel.getTenTH(),
                sanPhamViewModel.getTenCL(),
                sanPhamViewModel.getTenDM(),
                sanPhamViewModel.getNgayTao(),
                sanPhamViewModel.getNgaySua(),
                sanPhamViewModel.getTrangThai() == true ? "Hoạt Động" : "Không Hoạt Động"
            });
        }
    }

    void loadData1(ArrayList<SanPhamChiTietViewModel> list) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblSanPham.getModel();
        defaultTableModel.setRowCount(0);
        for (SanPhamChiTietViewModel spctViewModel : list) {
            defaultTableModel.addRow(new Object[]{
                spctViewModel.getTenSP(),
                spctViewModel.getTenMS(),
                spctViewModel.getTenSize(),
                spctViewModel.getSoLuong(),
                spctViewModel.getGiaBan(),
                spctViewModel.getTrangThai() == true ? "Hoạt Động" : "Không Hoạt Động"
            });
        }
    }

    private void clear() {
        txtTenSP.setText("");
        cboDM.setSelectedIndex(0);
        cboTH.setSelectedIndex(0);
        cboCL.setSelectedIndex(0);
        rdoSP.setSelected(true);
    }

    private void clearLocSP() {
        cboLocDanhMuc.setSelectedIndex(0);
        cbolocTH1.setSelectedIndex(0);

    }

    private void clearLocSPCT() {
        cbolocMS.setSelectedIndex(0);
        cbolocSz.setSelectedIndex(0);

    }

    private void clearSPCT() {
        cboDM.setSelectedIndex(0);
        cboTH.setSelectedIndex(0);
        cboCL.setSelectedIndex(0);
        txtSoLuong.setText("");
        txtGiaBan.setText("");
        rdoSPCT.setSelected(true);
    }

    public SanPham getFormSP() {
        DanhMuc danhMuc = (DanhMuc) cboDM.getSelectedItem();
        ThuongHieu th = (ThuongHieu) cboTH.getSelectedItem();
        ChatLieu cl = (ChatLieu) cboCL.getSelectedItem();
        SanPham sp = new SanPham();
        String ten = txtTenSP.getText();
        if (ten.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Sản Phẩm");
            return null;
        }
        sp.setTenSP(txtTenSP.getText());
        sp.setIdTH(th.getId());
        sp.setIdCL(cl.getId());
        sp.setIdDM(danhMuc.getId());
        if (rdoSP.isSelected()) {
            sp.setTrangThai(true); // Hoạt động
        } else {
            sp.setTrangThai(false); // Không hoạt động
        }
        return sp;

    }

    public SanPhamViewModel getFormSP1() {
        DanhMuc danhMuc = (DanhMuc) cboDM.getSelectedItem();
        ThuongHieu th = (ThuongHieu) cboTH.getSelectedItem();
        ChatLieu cl = (ChatLieu) cboCL.getSelectedItem();
        SanPhamViewModel sp = new SanPhamViewModel();
        String ten = txtTenSP.getText();
        if (ten.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Sản Phẩm");
            return null;
        }
        sp.setTenSP(txtTenSP.getText());
        sp.setTenTH(th.getTenTH());
        sp.setTenCL(cl.getTenCL());
        sp.setTenDM(danhMuc.getTenDM());
        if (rdoSP.isSelected()) {
            sp.setTrangThai(true); // Hoạt động
        } else {
            sp.setTrangThai(false); // Không hoạt động
        }
        return sp;

    }

    public SanPhamChiTiet getFormSPCT() {
        SanPham sp = (SanPham) cboSPCT.getSelectedItem();
        MauSac ms = (MauSac) cboMS1.getSelectedItem();
        Size sz = (Size) cboSize.getSelectedItem();
        SanPhamChiTiet spct = new SanPhamChiTiet();

        // Gán giá trị cho spct
        spct.setIdSP(sp.getId());
        spct.setIdMS(ms.getId());
        spct.setIdSize(sz.getId());

        int soLuong = Integer.parseInt(txtSoLuong.getText());
        spct.setSoLuong(soLuong);

        float giaBan = Float.parseFloat(txtGiaBan.getText());
        spct.setGiaBan(giaBan);

        // Kiểm tra trạng thái sản phẩm
        if (rdoSPCT.isSelected()) {
            spct.setTrangThai(true); // Hoạt động
        } else {
            spct.setTrangThai(false); // Không hoạt động
        }

        return spct;
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        tabSP = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        cboCL = new javax.swing.JComboBox<>();
        cboDM = new javax.swing.JComboBox<>();
        cboTH = new javax.swing.JComboBox<>();
        rdoSP = new javax.swing.JRadioButton();
        rdoSP1 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        btnTimKiemSP = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnClearSP = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbolocTH1 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        cboLocDanhMuc = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        clearLocSP = new javax.swing.JButton();
        btnSPCT = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        cboSize = new javax.swing.JComboBox<>();
        cboSPCT = new javax.swing.JComboBox<>();
        cboMS1 = new javax.swing.JComboBox<>();
        rdoSPCT = new javax.swing.JRadioButton();
        rdoSPCT1 = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnQLTT = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnTimKiemSPCT = new javax.swing.JButton();
        txtTimKiemSPCT = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cbolocSz = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cbolocMS = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        tabSP.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản Lý Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 102, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel1.setText("Tên SP");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText("Thương Hiệu");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText("Trạng Thái");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel4.setText("Chất Liệu");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel5.setText("Danh Mục");

        cboDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDMActionPerformed(evt);
            }
        });

        cboTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTHActionPerformed(evt);
            }
        });

        rdoSP.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSP);
        rdoSP.setSelected(true);
        rdoSP.setText("Hoạt Động");

        rdoSP1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSP1);
        rdoSP1.setText("Không Hoạt Động");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenSP)
                            .addComponent(cboTH, 0, 192, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboDM, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(rdoSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoSP1))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 147, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoSP)
                            .addComponent(jLabel3)
                            .addComponent(rdoSP1))))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cboCL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(102, 153, 255))); // NOI18N

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "Thương Hiệu", "Chất Liệu", "Danh Mục", "Ngày Tạo", "Ngày Sửa", "Trạng Thái"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        btnTimKiemSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timkiem.png"))); // NOI18N
        btnTimKiemSP.setText("Tìm Kiếm");
        btnTimKiemSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(652, 652, 652)
                        .addComponent(btnTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemSP)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThemSP.setBackground(new java.awt.Color(204, 255, 255));
        btnThemSP.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnThemSP.setForeground(new java.awt.Color(51, 102, 255));
        btnThemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btnThemSP.setText("Thêm ");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setBackground(new java.awt.Color(204, 255, 255));
        btnSuaSP.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnSuaSP.setForeground(new java.awt.Color(51, 102, 255));
        btnSuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnSuaSP.setText("Sửa");
        btnSuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSPActionPerformed(evt);
            }
        });

        btnClearSP.setBackground(new java.awt.Color(204, 255, 255));
        btnClearSP.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnClearSP.setForeground(new java.awt.Color(51, 102, 255));
        btnClearSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btnClearSP.setText("Clear");
        btnClearSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearSPActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 153, 255))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loc.png"))); // NOI18N
        jLabel11.setText("Thương Hiệu");

        cbolocTH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbolocTH1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cbolocTH1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(172, 172, 172))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbolocTH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loc.png"))); // NOI18N
        jLabel16.setText("Danh Mục");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(cboLocDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel16)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        clearLocSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearLocSP.setForeground(new java.awt.Color(51, 102, 255));
        clearLocSP.setText("Làm Mới");
        clearLocSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLocSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearLocSP)
                .addGap(460, 460, 460))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(clearLocSP))
        );

        btnSPCT.setBackground(new java.awt.Color(204, 255, 255));
        btnSPCT.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnSPCT.setForeground(new java.awt.Color(51, 102, 255));
        btnSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/capnhat_1.png"))); // NOI18N
        btnSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearSP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClearSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabSP.addTab("Sản Phẩm", jPanel2);

        jPanel5.setPreferredSize(new java.awt.Dimension(1037, 691));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm Chi Tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 153, 255))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));

        jLabel7.setText("Tên SP");

        jLabel8.setText("Màu Sắc");

        jLabel10.setText("Size");

        jLabel12.setText("Giá Bán");

        jLabel13.setText("Số Lượng");

        jLabel14.setText("Trạng Thái");

        cboSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSPCTActionPerformed(evt);
            }
        });

        rdoSPCT.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoSPCT);
        rdoSPCT.setSelected(true);
        rdoSPCT.setText("Hoạt Động");
        rdoSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSPCTActionPerformed(evt);
            }
        });

        rdoSPCT1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoSPCT1);
        rdoSPCT1.setText("Không Hoạt Động");
        rdoSPCT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSPCT1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cboMS1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(cboSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(36, 36, 36)))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdoSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoSPCT1)
                                .addGap(42, 42, 42))))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboMS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoSPCT)
                    .addComponent(rdoSPCT1))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnThem.setBackground(new java.awt.Color(204, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setForeground(new java.awt.Color(102, 153, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 255, 255));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setForeground(new java.awt.Color(51, 153, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/update.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 255, 255));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(51, 153, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/lammoi.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnQLTT.setBackground(new java.awt.Color(204, 255, 204));
        btnQLTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnQLTT.setForeground(new java.awt.Color(0, 204, 51));
        btnQLTT.setText("Quản lý thuộc tính");
        btnQLTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLTTActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 153, 255))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "Màu Sắc", "Size", "Số Lượng", "Giá Bán", "Trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        btnTimKiemSPCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiemSPCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timkiem.png"))); // NOI18N
        btnTimKiemSPCT.setText("Tìm Kiếm");
        btnTimKiemSPCT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTimKiemSPCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemSPCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTimKiemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiemSPCT)
                    .addComponent(txtTimKiemSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(51, 153, 255))); // NOI18N

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loc.png"))); // NOI18N
        jLabel22.setText("Size");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cbolocSz, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(167, 167, 167))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbolocSz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loc.png"))); // NOI18N
        jLabel23.setText("Màu Săc");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbolocMS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(206, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(198, 198, 198))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbolocMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 255));
        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(406, 406, 406))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQLTT, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnQLTT, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabSP.addTab("Sản Phẩm Chi Tiết", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tabSP)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabSP, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDMActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int row = tblSP.getSelectedRow();

        txtTenSP.setText((String) tblSP.getValueAt(row, 0));
        String thuongHieu = tblSP.getValueAt(row, 1).toString();
        int thCount = cboTH.getItemCount();
        for (int i = 0; i < thCount; i++) {
            Object l = cboTH.getItemAt(i);
            if (l.toString().equals(thuongHieu.toString())) {
                cboTH.setSelectedIndex(i);
                break;
            }
        }
        String chatLieu = tblSP.getValueAt(row, 2).toString();
        int clCount = cboCL.getItemCount();
        for (int i = 0; i < clCount; i++) {
            Object l = cboCL.getItemAt(i);
            if (l.toString().equals(chatLieu.toString())) {
                cboCL.setSelectedIndex(i);
                break;
            }
        }
        String danhMuc = tblSP.getValueAt(row, 3).toString();
        int dmCount = cboDM.getItemCount();
        for (int i = 0; i < dmCount; i++) {
            Object l = cboDM.getItemAt(i);
            if (l.toString().equals(danhMuc.toString())) {
                cboDM.setSelectedIndex(i);
                break;
            }
        }
        if (tblSP.getValueAt(row, 6).toString().equalsIgnoreCase("Hoạt Động")) {
            rdoSP.setSelected(true);
        } else {
            rdoSP1.setSelected(true);
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        DanhMuc danhMuc = (DanhMuc) cboDM.getSelectedItem();
        ThuongHieu th = (ThuongHieu) cboTH.getSelectedItem();
        ChatLieu cl = (ChatLieu) cboCL.getSelectedItem();
        SanPham sp = new SanPham();
        String ten = txtTenSP.getText();
        if (ten.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Tên Sản Phẩm");
            return;
        }
        sp.setTenSP(txtTenSP.getText());
        sp.setIdTH(th.getId());
        sp.setIdCL(cl.getId());
        sp.setIdDM(danhMuc.getId());
        if (rdoSP.isSelected()) {
            sp.setTrangThai(true); // Hoạt động
        } else {
            sp.setTrangThai(false); // Không hoạt động
        }

        if (sanPhamRepo.add(sp) == true) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại ");
        }
        loadData((ArrayList<SanPhamViewModel>) sanPhamRepo.getAll());
        initCbo();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnSuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSPActionPerformed
//        SanPham sp = getFormSP();
        int row = tblSP.getSelectedRow();
        if (row != -1) {
            if (getFormSP() != null) {
                Integer id = lstsanpham.get(row).getId();

                if (sanPhamRepo.update(id, getFormSP())) {
                    JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Thành Công !!");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Sản Phẩm Thất Bại!!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "vui lòng chọn dòng để sửa");
        }
        loadData((ArrayList<SanPhamViewModel>) sanPhamRepo.getAll());
    }//GEN-LAST:event_btnSuaSPActionPerformed

    private void btnClearSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearSPActionPerformed
        clear();
    }//GEN-LAST:event_btnClearSPActionPerformed

    private void btnSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPCTActionPerformed
        initCbo();
    }//GEN-LAST:event_btnSPCTActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();

        String sp = tblSanPham.getValueAt(row, 0).toString();
        int spCount = cboSPCT.getItemCount();
        for (int i = 0; i < spCount; i++) {
            Object l = cboSPCT.getItemAt(i);
            if (l.toString().equals(sp.toString())) {
                cboSPCT.setSelectedIndex(i);
                break;
            }
        }
        String ms = tblSanPham.getValueAt(row, 1).toString();
        int msCount = cboMS1.getItemCount();
        for (int i = 0; i < msCount; i++) {
            Object l = cboMS1.getItemAt(i);
            if (l.toString().equals(ms.toString())) {
                cboCL.setSelectedIndex(i);
                break;
            }
        }
        String sz = tblSanPham.getValueAt(row, 2).toString();
        int szCount = cboSize.getItemCount();
        for (int i = 0; i < szCount; i++) {
            Object l = cboSize.getItemAt(i);
            if (l.toString().equals(sz.toString())) {
                cboSize.setSelectedIndex(i);
                break;
            }
        }
        String sl = tblSanPham.getValueAt(row, 3).toString();
        int soLuong = Integer.parseInt(sl);
        txtSoLuong.setText(String.valueOf(soLuong));

        String gb = tblSanPham.getValueAt(row, 4).toString();
        float giaBan = Float.parseFloat(gb);
        txtGiaBan.setText(String.valueOf(giaBan));

        if (tblSanPham.getValueAt(row, 5).toString().equalsIgnoreCase("Hoạt động")) {
            rdoSPCT.setSelected(true);
        } else {
            rdoSPCT1.setSelected(true);
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnQLTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLTTActionPerformed
        ThuocTinhView thuocTinhView = new ThuocTinhView();
        thuocTinhView.setVisible(true);

        initCbo();
    }//GEN-LAST:event_btnQLTTActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearSPCT();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
//        SanPhamChiTiet spct = getFormSPCT();
        int row = tblSanPham.getSelectedRow();
        if (row != -1) {
            if (getFormSPCT() != null) {
                Integer id = lstspct.get(row).getId();

                if (spctRepo.updateSPCT(id, getFormSPCT())) {
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm chi tiết thành công!!");

                } else {
                    JOptionPane.showMessageDialog(this, "Sửa sản phẩm chi tiết thất bại!!");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "vui lòng chọn dòng để sửa");
        }
        loadData1((ArrayList<SanPhamChiTietViewModel>) spctRepo.getAll1());
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        MauSac ms = (MauSac) cboMS1.getSelectedItem();
        Size sz = (Size) cboSize.getSelectedItem();
        SanPham sp = (SanPham) cboSPCT.getSelectedItem();
        SanPhamChiTiet spct = new SanPhamChiTiet();

        spct.setIdMS(ms.getId());
        spct.setIdSize(sz.getId());
        spct.setIdSP(sp.getId());
        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        spct.setSoLuong(soLuong);

        // Chuyển đổi giá bán từ String sang float
        float giaBan = Float.parseFloat(txtGiaBan.getText().trim());
        spct.setGiaBan(giaBan);

        if (rdoSPCT.isSelected()) {
            spct.setTrangThai(true); // Hoạt động
        } else {
            spct.setTrangThai(false); // Không hoạt động
        }

        if (spctRepo.addSPCT(spct) == true) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại ");
        }
        loadData1((ArrayList<SanPhamChiTietViewModel>) spctRepo.getAll1());
        initCbo();
    }//GEN-LAST:event_btnThemActionPerformed

    private void rdoSPCT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSPCT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoSPCT1ActionPerformed

    private void rdoSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSPCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoSPCTActionPerformed

    private void cboSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSPCTActionPerformed
        // TODO add your handling code here:
        loadData((ArrayList<SanPhamViewModel>) sanPhamRepo.getAll());
    }//GEN-LAST:event_cboSPCTActionPerformed

    private void btnTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPActionPerformed
        String tim = txtTimKiem.getText().toString();
        if (!tim.isEmpty()) {
            if (!sanPhamRepo.TimKiem(tim).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tìm thấy");
                loadData((ArrayList<SanPhamViewModel>) sanPhamRepo.TimKiem(tim));
            } else {
                JOptionPane.showMessageDialog(this, "ko tìm thấy");
            }
        } else {
            JOptionPane.showMessageDialog(this, "ô tìm kiếm đang trống");
        }

    }//GEN-LAST:event_btnTimKiemSPActionPerformed

    private void clearLocSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLocSPActionPerformed
        clearLocSP();
    }//GEN-LAST:event_clearLocSPActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        initCbo();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnTimKiemSPCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemSPCTActionPerformed
        String tim = txtTimKiemSPCT.getText().toString();
        if (!tim.isEmpty()) {
            if (!spctRepo.TimKiem(tim).isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Tìm thấy");
                loadData1((ArrayList<SanPhamChiTietViewModel>) spctRepo.TimKiem(tim));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy tên SP");
            }
        } else {
            JOptionPane.showMessageDialog(this, "ô tìm kiếm đang trống");
        }
    }//GEN-LAST:event_btnTimKiemSPCTActionPerformed

    private void cbolocTH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbolocTH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbolocTH1ActionPerformed

    private void cboTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearSP;
    private javax.swing.JButton btnQLTT;
    private javax.swing.JButton btnSPCT;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnTimKiemSP;
    private javax.swing.JButton btnTimKiemSPCT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboCL;
    private javax.swing.JComboBox<String> cboDM;
    private javax.swing.JComboBox<String> cboLocDanhMuc;
    private javax.swing.JComboBox<String> cboMS1;
    private javax.swing.JComboBox<String> cboSPCT;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JComboBox<String> cboTH;
    private javax.swing.JComboBox<String> cbolocMS;
    private javax.swing.JComboBox<String> cbolocSz;
    private javax.swing.JComboBox<String> cbolocTH1;
    private javax.swing.JButton clearLocSP;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoSP;
    private javax.swing.JRadioButton rdoSP1;
    private javax.swing.JRadioButton rdoSPCT;
    private javax.swing.JRadioButton rdoSPCT1;
    private javax.swing.JTabbedPane tabSP;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemSPCT;
    // End of variables declaration//GEN-END:variables

}
