/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.SanPhamThongKe;
import responsitory.ThongKeRes;

public class ThongKeJPanel extends javax.swing.JPanel {

    List<HoaDon> listHoadon = new ArrayList<>();
    List<SanPhamThongKe> listsptk = new ArrayList<>();
    ThongKeRes thongKeRes = new ThongKeRes();
    DefaultTableModel tableModel = new DefaultTableModel();
    DecimalFormat currencyFormat = new DecimalFormat("#,##0");

    public ThongKeJPanel() {
        initComponents();
//        tblSanPhamThongKe.getColumnModel().getColumn(0).setMinWidth(0);
//        tblSanPhamThongKe.getColumnModel().getColumn(0).setMinWidth(0);
//        tblSanPhamThongKe.getColumnModel().getColumn(1).setMinWidth(0);
//        tblSanPhamThongKe.getColumnModel().getColumn(1).setMinWidth(0);
        tableModel = (DefaultTableModel) tblSanPhamThongKe.getModel();
        listsptk = thongKeRes.getAllSPTK();

        showDataSanPhamThongKe(listsptk);
        fillDoanhThu();
    }

    public void fillDoanhThu() {
        double doanhThuNgay = thongKeRes.getDoanhThuTheoNgayHienTai();
        lblDoanhThuNgay.setText(currencyFormat.format(doanhThuNgay));
        double doanhThuThang = thongKeRes.getDoanhThuTheoThangHienTai();
        lblDoanhThuThang.setText(currencyFormat.format(doanhThuThang));
        double doanhThuNam = thongKeRes.getDoanhThuTheoNamHienTai();
        lblDoanhThuNam.setText(currencyFormat.format(doanhThuNam));
    }
    
    public void showDataSanPhamThongKe(List<SanPhamThongKe> lists) {
        tableModel.setRowCount(0);
        for (SanPhamThongKe sp : lists) {
            String donGia = currencyFormat.format(sp.getGia());

            tableModel.addRow(new Object[]{
//                sp.getIdSanPham(),
//                sp.getIdCtsp(),
                sp.getTenSanPham(),
                sp.getTenSize(),
                sp.getTenMauSac(),
                sp.getTenChatLieu(),
                sp.getTenDanhMuc(),
                sp.getTenThuongHieu(),
                sp.getSoLuong(),
                sp.getGia(),
                sp.isTrangThai() == true ? "Còn Hàng" : "Hết Hàng"
            });
        }
    }
    
    
    public String Hienthi(){
        Date TimeBegin = dcsTimeBigin.getDate();
        Date TimeOver = dcsTimeOver.getDate();
        Float doanhThu = thongKeRes.getDoanhThuTheoNgay(TimeBegin, TimeOver);
        lblDoanhThuNgay.setText(currencyFormat.format(doanhThu));
        lblDoanhThuSearch.setText(currencyFormat.format(doanhThu));
        int soHoaDon = thongKeRes.getTongDon(TimeBegin, TimeOver);
        lblTongSoDonHang.setText(String.valueOf(soHoaDon));
        int soHoaDonCho = thongKeRes.getSoDonCho(TimeBegin, TimeOver);
        int soDonDaThanhToan = thongKeRes.getSoDonDaThangToan(TimeBegin, TimeOver);
        lblDonDaThanhToan.setText(String.valueOf(soDonDaThanhToan));
        return "abc";
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPhamThongKe = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        txtTenSPSearch = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        cboSapXepSP = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cboTrangThaiSP = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblTongSoDonHang = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblDonDaThanhToan = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblDoanhThuSearch = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblDoanhThuNgay = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblDoanhThuThang = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblDoanhThuNam = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        dcsTimeBigin = new com.toedter.calendar.JDateChooser();
        jLabel52 = new javax.swing.JLabel();
        dcsTimeOver = new com.toedter.calendar.JDateChooser();
        btnSearchAll = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1036, 780));

        jTabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102), 3));

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPhamThongKe.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblSanPhamThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Tên size", "Tên màu sắc", "Tên chất liệu", "Tên danh mục", "Tên thương hiệu", "Số lượng", "Giá", "Trạng thái"
            }
        ));
        jScrollPane4.setViewportView(tblSanPhamThongKe);

        jPanel8.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 99, 1230, 290));

        jPanel16.setBackground(new java.awt.Color(204, 204, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        txtTenSPSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPSearchActionPerformed(evt);
            }
        });

        jLabel27.setText("Lọc theo tên");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenSPSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenSPSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel8.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel17.setBackground(new java.awt.Color(204, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102), 2));

        cboSapXepSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----------", "Số lượng", "Đơn giá" }));

        jLabel28.setText("Sắp xếp theo");

        cboTrangThaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hết Hàng", "Còn Hàng" }));
        cboTrangThaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiSPActionPerformed(evt);
            }
        });

        jLabel29.setText("Trạng Thái");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboSapXepSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTrangThaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSapXepSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTrangThaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel8.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, 81));

        jTabbedPane.addTab("Sản Phẩm", jPanel8);

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 102), 3));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(0, 102, 102));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel19.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Tổng đơn hàng");
        jPanel19.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lblTongSoDonHang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTongSoDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lblTongSoDonHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongSoDonHang.setText("0");
        jPanel19.add(lblTongSoDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 6, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("đơn hàng");
        jPanel19.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 6, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Đã thanh toán");
        jPanel19.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        lblDonDaThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDonDaThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        lblDonDaThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDonDaThanhToan.setText("0");
        jPanel19.add(lblDonDaThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 41, 15));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Doanh thu:");
        jPanel19.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 76, -1));

        lblDoanhThuSearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDoanhThuSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuSearch.setText("0");
        jPanel19.add(lblDoanhThuSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("VNĐ");
        jPanel19.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 104, -1, -1));

        jPanel3.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 9, 290, -1));

        jPanel20.setBackground(new java.awt.Color(255, 102, 102));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel20.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Doanh thu trong ngày");
        jPanel20.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        lblDoanhThuNgay.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThuNgay.setText("0");
        jPanel20.add(lblDoanhThuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 97, -1));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("vnđ");
        jPanel20.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jPanel3.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 280, 120));

        jPanel21.setBackground(new java.awt.Color(153, 0, 153));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Doanh thu trong tháng");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("vnđ");

        lblDoanhThuThang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblDoanhThuThang.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThuThang.setText("0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuThang, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(71, 71, 71)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel47)
                .addContainerGap())
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel45)
                    .addContainerGap(110, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(lblDoanhThuThang, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel47)))
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel21Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jLabel45)
                    .addContainerGap(91, Short.MAX_VALUE)))
        );

        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 290, -1));

        jPanel22.setBackground(new java.awt.Color(0, 51, 153));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Doanh thu trong năm");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("vnđ");

        lblDoanhThuNam.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblDoanhThuNam.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThuNam.setText("0");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(lblDoanhThuNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(lblDoanhThuNam, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 280, 120));

        jLabel51.setText("Ngày bắt đầu");
        jPanel3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 94, 32));

        dcsTimeBigin.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(dcsTimeBigin, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 183, 32));

        jLabel52.setText("Ngày kết thúc");
        jPanel3.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 97, 30));

        dcsTimeOver.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(dcsTimeOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 199, 30));

        btnSearchAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timkiem.png"))); // NOI18N
        btnSearchAll.setText("Tìm kiếm");
        btnSearchAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAllActionPerformed(evt);
            }
        });
        jPanel3.add(btnSearchAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 140, -1, 29));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAllActionPerformed
        // TODO add your handling code here: Date TimeBeginAll = dcsTimeBigin.getDate();
        Hienthi();

    }//GEN-LAST:event_btnSearchAllActionPerformed

    private void cboTrangThaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiSPActionPerformed
        // TODO add your handling code here:
        txtTenSPSearch.setText("");
        int a = cboSapXepSP.getSelectedIndex();
        int b = cboTrangThaiSP.getSelectedIndex();
        if (b == 0) {
            if (a == 0) {
                listsptk = thongKeRes.getAllSPTK();
                showDataSanPhamThongKe(listsptk);
            } else if (a == 1) {
                listsptk = thongKeRes.getAllSPTKSortSoLuong();
                showDataSanPhamThongKe(listsptk);
            } else {
                listsptk = thongKeRes.getAllSPTKSortDonGia();
                showDataSanPhamThongKe(listsptk);
            }
        } else {
            if (a == 0) {
                listsptk = thongKeRes.getAllSPTKByTrangThai(b - 1);
                showDataSanPhamThongKe(listsptk);
            } else if (a == 1) {
                listsptk = thongKeRes.getAllSPTKbyTrangThaiSortSoLuong(b - 1);
                showDataSanPhamThongKe(listsptk);
            } else {
                listsptk = thongKeRes.getAllSPTKbyTrangThaiSortDonGia(b - 1);
                showDataSanPhamThongKe(listsptk);
            }
        }
    }//GEN-LAST:event_cboTrangThaiSPActionPerformed

    private void txtTenSPSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPSearchActionPerformed
        // TODO add your handling code here:
        String tenSPSearch = txtTenSPSearch.getText();
        listsptk = thongKeRes.getAllSPTKbyTenSanPham(tenSPSearch);
        showDataSanPhamThongKe(listsptk);
    }//GEN-LAST:event_txtTenSPSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearchAll;
    private javax.swing.JComboBox<String> cboSapXepSP;
    private javax.swing.JComboBox<String> cboTrangThaiSP;
    private com.toedter.calendar.JDateChooser dcsTimeBigin;
    private com.toedter.calendar.JDateChooser dcsTimeOver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel lblDoanhThuNam;
    private javax.swing.JLabel lblDoanhThuNgay;
    private javax.swing.JLabel lblDoanhThuSearch;
    private javax.swing.JLabel lblDoanhThuThang;
    private javax.swing.JLabel lblDonDaThanhToan;
    private javax.swing.JLabel lblTongSoDonHang;
    private javax.swing.JTable tblSanPhamThongKe;
    private javax.swing.JTextField txtTenSPSearch;
    // End of variables declaration//GEN-END:variables

}
