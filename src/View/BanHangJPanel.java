/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Color;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import javax.print.attribute.DocAttribute;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.HoaDonChiTietViewModel;
import model.HoaDonViewModel;
import model.Model_khachHang;
import model.NhanVien;
import model.NhanVien1;
import model.SanPhamChiTiet;
import model.SanPhamChiTietViewModel;
import responsitory.HoaDonChiTietRes;
import responsitory.HoaDonRes;
import responsitory.NhanVienRepo;
import responsitory.KhachHangRepository;
import responsitory.NhanVienRepo;
import responsitory.Repositories_Voucher;
import responsitory.SPCTRepo;
import responsitory.SanPhamRepo;
import service.HoaDonDao;
import service.HoaDonImpl;
import service.HoaDonService;
import view.BHChonKH;
import java.io.FileOutputStream;
import java.util.LinkedList;

/**
 *
 * @author admin
 */
public class BanHangJPanel extends javax.swing.JPanel {

    private KhachHangRepository rpKhachHang = new KhachHangRepository();
    private HoaDonRes hoadonres = new HoaDonRes();
    private SPCTRepo spctRepo = new SPCTRepo();
    private List<SanPhamChiTietViewModel> lstspctview = spctRepo.getAllSPCT();
    private List<HoaDon> listHD = hoadonres.getAllHoaDonCho();
    private List<HoaDonChiTietViewModel> listHDCT = new ArrayList<>();
    private List<NhanVien1> listNV = new NhanVienRepo().getAll();
    private NhanVien nhanvien = new NhanVien();
    private List<SanPhamChiTiet> listspct = new ArrayList<>();
    SanPhamRepo spres = new SanPhamRepo();
    DefaultTableModel tableModel = new DefaultTableModel();
    DefaultTableModel tableModel1 = new DefaultTableModel();
    DefaultTableModel tableModel2 = new DefaultTableModel();
    private Repositories_Voucher rp = new Repositories_Voucher();
    HoaDonDao hddao = new HoaDonDao();
    private HoaDonImpl hdservice;
    private NhanVienRepo nhanVienRepo = new NhanVienRepo();
    HoaDonChiTietRes hoaDonChiTietRes = new HoaDonChiTietRes();
    DecimalFormat currencyFormat = new DecimalFormat("#,##0");
    int vtriHD = -1;

    /**
     * Creates new form BanHangJPanel
     */
    public BanHangJPanel() {
        initComponents();
        hdservice = new HoaDonService();
        tableModel = (DefaultTableModel) tblHoaDon.getModel();
        tableModel1 = (DefaultTableModel) tblSanPhamCT.getModel();
        tableModel2 = (DefaultTableModel) tblGioHang.getModel();
        cboHTTT.removeAllItems();
        cboHTTT.addItem("--Chọn Phương Thức--");
        cboHTTT.addItem("--Chuyển Khoản--");
        cboHTTT.addItem("--Tiền Mặt--");
        initComboboxNhanVien();
        getListHoaDon();
        showDataSPCT(lstspctview);
    }

    private String getTrangThaiHD(int TrangThai) {
        if (TrangThai == 0) {
            return "chờ thanh Toán";
        }
        if (TrangThai == 1) {
            return "Đã thanh Toán";
        }

        return null;
    }
    public void clearGioHang(){
        tableModel2.setRowCount(0);
    }

    private String random() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public void updateCustomerInfo(int sdt, String hoten) {
        txtSoDienThoai.setText(String.valueOf(sdt));
        txttenkh.setText(hoten);
    }

    private void getListHoaDon() {
        tableModel.setRowCount(0);
        List<HoaDon> getList = hoadonres.getAllHoaDonCho();
        for (HoaDon hd : getList) {
            tableModel.addRow(new Object[]{
                hd.getMaHoaDon(),
                hd.getNgayTao(),
                hd.getTenNhanVien(),
                getTrangThaiHD(hd.getTrangThai())
            });
        }
    }

    private HoaDon randomHD() {
        HoaDon hd = new HoaDon();
        String Ma = "HD";
        Random random = new Random();
        hd.setMaHoaDon(Ma + random.nextInt());
        return hd;
    }

    public void initComboboxNhanVien() {
        DefaultComboBoxModel cboModel3 = new DefaultComboBoxModel();
        for (String nv : hddao.getallnv()) {
            cboModel3.addElement(nv);
        }
        cboNhanVien.setModel(cboModel3);
    }

    private void showDataSPCT(List<SanPhamChiTietViewModel> list) {
        tableModel1.setRowCount(0);
        List<SanPhamChiTietViewModel> getList = hoadonres.getAllSPCT();
        for (SanPhamChiTietViewModel spct : getList) {
            tableModel1.addRow(new Object[]{
                spct.getTenSP(),
                spct.getTenMS(),
                spct.getTenSize(),
                spct.getSoLuong(),
                spct.getGiaBan(),
                spct.getTrangThai() ? "Hoạt động" : "Ngưng hoạt động"
            });
        }
    }

    private void inHoaDon() {
        int x = tblHoaDon.getSelectedRow();
        Date currentDate = new Date();
        String IDHOADON = lblMaHoaDon.getText();
        String NGAYTAO = listHD.get(x).getNgayTao().toString();
        Date NGAYTHANHTOAN = currentDate;

        int soSP = listHDCT.size();
        String dsSP[] = new String[soSP];
        for (int i = 0; i < soSP; i++) {
            HoaDonChiTietViewModel hdct = listHDCT.get(i);
            dsSP[i] = hdct.getTenSanPham() + "        SL: " + hdct.getSoLuong() + "      TT: " + currencyFormat.format(hdct.getThanhTien());
        }
        String tongTien = lblTongTien.getText();
        String giamGia = lblTienGiam.getText();
        String thanhTien = lblThanhTien.getText();
        String sdtKhachHang = txtSoDienThoai.getText();
        System.out.println(IDHOADON);

        // Tạo hóa đơn PDF
        String pdfFileName = "HoaDon_" + IDHOADON + ".pdf";

        createInvoicePDF(pdfFileName, IDHOADON, NGAYTAO, NGAYTHANHTOAN,
                dsSP, tongTien, giamGia, thanhTien, sdtKhachHang);

        try {
            Desktop.getDesktop().open(new File(pdfFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createInvoicePDF(String fileName, String maHoaDon, String ngayTao, Date ngayThanhToan, String[] dsSP, String tongTien, String giamGia, String thanhTien, String sdtKhachHang) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Thêm các phần tử vào PDF
            Paragraph title = new Paragraph("HOA DON THANH TOAN");
            Paragraph light = new Paragraph("----------------------------------------------------------------------------------------------------------------");
            Paragraph Ngaytao = new Paragraph("NGAY TAO: " + ngayTao);
            Paragraph NgayThanhToan = new Paragraph("NGAY THANH TOAN: " + ngayThanhToan.toString());
            Paragraph light1 = new Paragraph("---------------------------------------------------------------------------------------------------------------");
            Paragraph MaHoaDon = new Paragraph("ID HOA DON: " + maHoaDon);
            Paragraph SDTKhachHang = new Paragraph("SDT KHACH HANG: " + sdtKhachHang);

            // Tạo danh sách sản phẩm
            StringBuilder TenSanPham = new StringBuilder();
            for (String sp : dsSP) {
                TenSanPham.append(sp).append("\n");
            }
            Paragraph SanPham = new Paragraph("SAN PHAM BAO GOM: \n" + TenSanPham.toString());

            Paragraph light3 = new Paragraph("==============================");
            Paragraph tongTien3 = new Paragraph("TONG TIEN:  " + tongTien);
            Paragraph giamgia = new Paragraph("GIAM GIA:  " + giamGia);
            Paragraph light4 = new Paragraph("==============================");
            Paragraph ThanhTien = new Paragraph("THANH TOAN:  " + thanhTien);
            Paragraph light5 = new Paragraph(" ");
            Paragraph light6 = new Paragraph("                                       CAM ON QUY KHACH DA MUA HANG");

            // Thêm các phần tử vào tài liệu
            document.add(title);
            document.add(light);
            document.add(Ngaytao);
            document.add(NgayThanhToan);
            document.add(light1);
            document.add(MaHoaDon);
            document.add(SDTKhachHang);
            document.add(SanPham);
            document.add(light3);
            document.add(tongTien3);
            document.add(giamgia);
            document.add(light4);
            document.add(ThanhTien);
            document.add(light5);
            document.add(light6);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void showDataGioHang(List<HoaDonChiTietViewModel> list) {
        tableModel2.setRowCount(0);
        float tongTien = 0;
        for (HoaDonChiTietViewModel hdct : list) {
            String formatDonGia = currencyFormat.format(hdct.getGiaTien());
            String formatThanhTien = currencyFormat.format(hdct.getThanhTien());
            tableModel2.addRow(new Object[]{
                hdct.getTenSanPham(),
                hdct.getTenMauSac(),
                hdct.getTenSize(),
                hdct.getSoLuong(),
                formatDonGia,
                formatThanhTien});
            tongTien = tongTien + hdct.getThanhTien();
        }
        tblGioHang.setModel(tableModel2);
        lblTongTien.setText(currencyFormat.format(tongTien) + "000 VND");
        lblTienGiam.setText("0");
        float TienGiam = 0;//suDungMaKhuyenMai();
        float thanhTien = (float) (tongTien - TienGiam);
        lblThanhTien.setText((String.valueOf(thanhTien)) + "00VND");
//
        if (lblMaHoaDon.getText() != null || !"".equals(lblMaHoaDon.getText())) {
            hoadonres.UpdateHoaDon(tongTien, TienGiam, tongTien - TienGiam, Integer.parseInt(lblMaHoaDon.getText()));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTienGiam = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnThanhToan = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txttenkh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboNhanVien = new javax.swing.JComboBox<>();
        txt_maVc = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btn_maVc = new javax.swing.JButton();
        cboHTTT = new javax.swing.JComboBox<>();
        btn_maVc1 = new javax.swing.JButton();
        btn_tienKhachDUa = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamCT = new javax.swing.JTable();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnHuyTK = new javax.swing.JButton();
        jpl125 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSanPham = new javax.swing.JButton();
        btnXoaTat = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Tạo Hóa Đơn"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setText("SDT");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jPanel7.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 24, 290, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 163, 370, 10));

        jLabel23.setText("Tổng Tiền");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel24.setText("Tiền Giảm");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel25.setText("Thành Tiền");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        lblTongTien.setText("0");
        jPanel7.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 192, -1));

        lblTienGiam.setText("0");
        jPanel7.add(lblTienGiam, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 185, -1));

        lblThanhTien.setText("0");
        jPanel7.add(lblThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 187, -1));

        jLabel11.setText("HT ThanhToán");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        jLabel12.setText("Tiền Khách Đưa");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jLabel14.setText("Tiền Thừa");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        lblTienThua.setText("0");
        jPanel7.add(lblTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 168, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 380, 10));

        btnThanhToan.setBackground(new java.awt.Color(0, 204, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/thanhtoan.png"))); // NOI18N
        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });
        jPanel7.add(btnThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 600, -1, 44));

        btnTaoHoaDon.setBackground(new java.awt.Color(51, 51, 255));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnTaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/taohoadon.png"))); // NOI18N
        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });
        jPanel7.add(btnTaoHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 160, 47));

        jLabel26.setText("Tên KH");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel7.add(txtTienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 140, -1));

        jLabel29.setText("ID Hóa Đơn");
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel7.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 110, 23));

        jLabel15.setText("Mã KM");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jButton1.setText("Khách Hàng");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 100, -1));

        txttenkh.setEditable(false);
        txttenkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttenkhActionPerformed(evt);
            }
        });
        jPanel7.add(txttenkh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 180, 30));

        jLabel1.setText("Nhân viên");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jPanel7.add(cboNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 150, 30));

        txt_maVc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maVcActionPerformed(evt);
            }
        });
        jPanel7.add(txt_maVc, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 130, -1));

        jButton2.setText("Khách Hàng");
        jPanel7.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 100, -1));

        jButton3.setText("Khách Hàng");
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 100, -1));

        jButton4.setText("Khách Hàng");
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 100, -1));

        btn_maVc.setText("Áp Mã");
        btn_maVc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_maVcActionPerformed(evt);
            }
        });
        jPanel7.add(btn_maVc, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 100, -1));

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cboHTTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 140, -1));

        btn_maVc1.setText("Áp Mã");
        btn_maVc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_maVc1ActionPerformed(evt);
            }
        });
        jPanel7.add(btn_maVc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 100, -1));

        btn_tienKhachDUa.setText("Xác Nhận");
        btn_tienKhachDUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tienKhachDUaActionPerformed(evt);
            }
        });
        jPanel7.add(btn_tienKhachDUa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 90, -1));

        jPanel5.setBackground(new java.awt.Color(204, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Sản Phẩm"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPhamCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Màu sắc", "Size", "Số lượng", "Giá", "Trạng thái"
            }
        ));
        tblSanPhamCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamCTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamCT);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 850, 240));

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/timkiem.png"))); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel5.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 30));
        jPanel5.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 168, 30));

        btnHuyTK.setText("Hủy");
        btnHuyTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyTKActionPerformed(evt);
            }
        });
        jPanel5.add(btnHuyTK, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 93, 30));

        jpl125.setBackground(new java.awt.Color(255, 255, 204));
        jpl125.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));
        jpl125.setName(""); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Ngày Tạo", "Tên NV", "Trạng Thái"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jpl125Layout = new javax.swing.GroupLayout(jpl125);
        jpl125.setLayout(jpl125Layout);
        jpl125Layout.setHorizontalGroup(
            jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jpl125Layout.setVerticalGroup(
            jpl125Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpl125Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ Hàng"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Màu sắc", "Size", "Số lượng", "Đơn giá", "Thành Tiền"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblGioHang);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 160));

        btnXoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/xoa.png"))); // NOI18N
        btnXoaSanPham.setText("Xóa Sản Phẩm");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 170, 30));

        btnXoaTat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/xoa.png"))); // NOI18N
        btnXoaTat.setText("Xóa tất cả");
        jPanel6.add(btnXoaTat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 139, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpl125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 378, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpl125, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        if (txttenkh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa Chọn Khách Hàng");
            return;
        }

        int maHoaDon = Integer.parseInt(lblMaHoaDon.getText());
        float tongTien = hoadonres.tongTien(maHoaDon).get(hoadonres.tongTien(maHoaDon).size() - 1).getTongTien();
        float thanhTien = Float.parseFloat(lblThanhTien.getText().toString().substring(0, lblThanhTien.getText().toString().length() - 7));
        String tenNv = cboNhanVien.getSelectedItem().toString();
        int idNv = nhanVienRepo.idNhanVien(tenNv).get(nhanVienRepo.idNhanVien(tenNv).size() - 1).getId();

// Kiểm tra phương thức thanh toán
        String phuongThucThanhToan = cboHTTT.getSelectedItem().toString();
        if (phuongThucThanhToan.equals("--Chọn Phương Thức--")) {
            JOptionPane.showMessageDialog(this, "Chưa Chọn Hình Thức Thanh Toán");
            return;
        }

// Kiểm tra tiền khách đưa
        if (txtTienKhachDua.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa Nhập Tiền Khách Đưa!");
            txtTienKhachDua.requestFocus();
            return;
        }

        String tienKhachDua = txtTienKhachDua.getText();
        String regex = "^[1-9]\\d*$";
        if (!tienKhachDua.matches(regex)) {
            JOptionPane.showMessageDialog(this, "Tiền Khách Đưa Phải Là Số Nguyên Dương!");
            txtTienKhachDua.requestFocus();
            return;
        }
        float tienThua = Float.parseFloat(lblTienThua.getText().replace(" VND", ""));
        if (tienThua < 0) {
            JOptionPane.showMessageDialog(this, "Tiền Khách Đưa Không Đủ!");
            txtTienKhachDua.requestFocus();
            return;
        }

        String tenKh = txttenkh.getText();
        int idKh = rpKhachHang.timKiem(tenKh).get(rpKhachHang.timKiem(tenKh).size() - 1).getId();
        int pttt = phuongThucThanhToan.equals("--Chuyển Khoản--") ? 2 : 1;

        if (txt_maVc.getText().isEmpty()) {
            // Thanh toán không voucher
            if (hoadonres.thanhToanKovc(tongTien, thanhTien, idNv, idKh, pttt, maHoaDon) != -1) {
                JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
                return;
            }
        } else {
            // Thanh toán có voucher
            String maVC = txt_maVc.getText();
            int idVc = rp.apMa(maVC).get(rp.apMa(maVC).size() - 1).getId();

            if (hoadonres.thanhToan(tongTien, thanhTien, idNv, idKh, idVc, pttt, maHoaDon) != -1) {
                JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
                rp.suaSoLuong(maVC);
            } else {
                JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
                return;
            }
        }

// In hóa đơn và cập nhật danh sách hóa đơn
        inHoaDon();
        clearGioHang();
        xoaForm();
        getListHoaDon();

//        if (txttenkh.getText().toString().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Chưa Chọn Khách Hàng");
//            return;
//        }
//
//        int ma = Integer.parseInt(lblMaHoaDon.getText().toString());
//        float tongTien = hoadonres.tongTien(ma).getLast().getTongTien();
//        float thanhTien = Float.parseFloat((lblThanhTien.getText().toString()).substring(0, lblThanhTien.getText().toString().length() - 7));
//        String tenNv = cboNhanVien.getSelectedItem().toString();
//        int idNv = nhanVienRepo.idNhanVien(tenNv).getLast().getId();
//
//        if (txtTienKhachDua.getText().toString().isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Chưa Nhập Tiền Khách Đưa!");
//            txtTienKhachDua.requestFocus();
//            return;
//        }
//
//        String tienKhachDua = txtTienKhachDua.getText();
//        String regex = "^[1-9]\\d*$";
//        if (!tienKhachDua.matches(regex)) {
//            JOptionPane.showMessageDialog(this, "Tiền Khách Đưa Phải Là Số Nguyên Dương!");
//            txtTienKhachDua.requestFocus();
//            return;
//        }
//
//        float tienThua = Float.parseFloat(lblTienThua.getText().toString().substring(0, lblTienThua.getText().toString().length() - 4));
//        if (tienThua < 0) {
//            JOptionPane.showMessageDialog(this, "Tiền Khách Đưa Không Đủ!");
//            txtTienKhachDua.requestFocus();
//            return;
//        }
//
//        if (cboHTTT.getSelectedItem().toString().equalsIgnoreCase("--Chọn phương thức--")) {
//            JOptionPane.showMessageDialog(this, "Chưa Chọn Hình Thức Thanh Toán");
//            return;
//        } if (cboHTTT.getSelectedItem().toString().equalsIgnoreCase("--Chuyển Khoản--")) {
//            String tenKh = txttenkh.getText().toString();
//            int idKh = rpKhachHang.timKiem(tenKh).getLast().getId();
//            int pttt = 2;
//            if (txt_maVc.getText().toString().isEmpty()) {
//
//                if (txt_maVc.getText().toString().isEmpty()) {
//                    if (hoadonres.thanhToanKovc(tongTien, thanhTien, idNv, idKh, pttt, ma) != -1) {
//                        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//                        return;
//                    }
//                }
//            } else {
//                String maVC = txt_maVc.getText().toString();
//                int maVc = rp.apMa(maVC).getLast().getId();
//                int mahd = Integer.parseInt(lblMaHoaDon.getText().toString());
//                ;
//                if (hoadonres.thanhToan(tongTien, thanhTien, idNv, idKh, maVc, pttt, mahd) != -1) {
//                    JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
//
//                } else {
//                    JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//                    return;
//                }
//            }
//        } else if (cboHTTT.getSelectedItem().toString().equalsIgnoreCase("--Tiền Mặt--")) {
//            int pttt = 1;
//            String tenKh = txttenkh.getText().toString();
//            int idKh = rpKhachHang.timKiem(tenKh).getLast().getId();
//            if (txt_maVc.getText().toString().isEmpty()) {
//
//                if (txt_maVc.getText().toString().isEmpty()) {
//                    if (hoadonres.thanhToanKovc(tongTien, thanhTien, idNv, idKh, pttt, ma) != -1) {
//                        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
//
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//                        return;
//                    }
//                }
//            } else {
//                String maVC = txt_maVc.getText().toString();
//                int maVc = rp.apMa(maVC).getLast().getId();
//                int mahd = Integer.parseInt(lblMaHoaDon.getText().toString());
//                if (hoadonres.thanhToan(tongTien, thanhTien, idNv, idKh, maVc, pttt, mahd) != -1) {
//                    JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Thanh Toán Thất Bại");
//                    return;
//                }
//            }
//        }
//        inHoaDon();
//        xoaForm();
//        getListHoaDon();

    }//GEN-LAST:event_btnThanhToanActionPerformed
    private void xoaForm() {

        txttenkh.setText("");
        txtSoDienThoai.setText("");
        txtTienKhachDua.setText("");
        txt_maVc.setText("");
        cboHTTT.setSelectedIndex(0);
        cboNhanVien.setSelectedIndex(0);
        lblMaHoaDon.setText("");
        lblTongTien.setText("");
        lblTienGiam.setText("");
        lblThanhTien.setText("");
        lblTienThua.setText("");

    }
    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn thêm không?", "Xác nhận yêu cầu", JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            HoaDon hd1 = new HoaDon();
            if (txttenkh.getText().equals("")) {
                hd1.setMaHoaDon(random());
                hd1.setIdNhanVien(hddao.getidNV(cboNhanVien.getSelectedItem().toString()));
                hoadonres.InsertHoaDonKHnull(hd1);
                System.out.println("hd1 IF: " + hd1);
            } else {
                hd1.setMaHoaDon(random());
                Model_khachHang kh = new Model_khachHang();
                hd1.setIdkhachHang(hddao.getidKh(txttenkh.getText()));
                hd1.setIdNhanVien(hddao.getidNV(cboNhanVien.getSelectedItem().toString()));
                hoadonres.taoHoaDon(hd1);
                System.out.println("hd1 ELSE: " + hd1);
            }

            listHD = hoadonres.getAllHoaDonCho();
            System.out.println("list: " + listHD);
            if (listHD != null) {
                getListHoaDon();
            }
            HoaDon hd = listHD.get(0);
            lblMaHoaDon.setText(String.valueOf(hd.getId()));
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblSanPhamCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamCTMouseClicked
        int row = tblSanPhamCT.getSelectedRow();
        int a;
        try {
            a = Integer.parseInt(JOptionPane.showInputDialog("Mời nhập số lượng"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập 1 số");
            return;
        }
        if (a > 0) {
            try {
                SanPhamChiTietViewModel spct = lstspctview.get(row);
                int idctsp = spct.getId();
                int soLuongThuc = spct.getSoLuong();
                if (a > soLuongThuc) {
                    JOptionPane.showMessageDialog(this, "Không đủ số lượng");
                    return;
                }
                hoadonres.UpdateSoLuong(a, idctsp);
                vtriHD = tblHoaDon.getSelectedRow();
                if (vtriHD != -1) {
                    HoaDon hd = listHD.get(vtriHD);
                    int dem = 0;
//                    System.out.println("" + listHDCT);
                    for (HoaDonChiTietViewModel hdct : listHDCT) {
                        if (hdct.getIdCtsp() == idctsp) {
                            dem++;
                        }
                    }

                    if (dem == 0) {
                        hoaDonChiTietRes.InsertHoaDonChiTiet(hd.getId(), idctsp, a, 1);
                    } else {
                        hoaDonChiTietRes.UpdateHDCT_SoLuong(idctsp, a);
                    }
                    listHDCT = hoaDonChiTietRes.getAllHDCT2(hd.getId());
                    showDataGioHang(hoaDonChiTietRes.getAllHDCT2(hd.getId()));
                    lstspctview = spctRepo.getAllSPCT();
                    showDataSPCT(lstspctview);
                } else {
                    JOptionPane.showMessageDialog(this, "Phải tạo hóa đơn");
                }
            } catch (NumberFormatException e) {
                return;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
        }
    }//GEN-LAST:event_tblSanPhamCTMouseClicked

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnHuyTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyTKActionPerformed

    }//GEN-LAST:event_btnHuyTKActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        vtriHD = tblHoaDon.getSelectedRow();
        HoaDon hd = listHD.get(vtriHD);
        int IDHoaDon = hd.getId();
        String mahd = hd.getMaHoaDon();
        lblMaHoaDon.setText(String.valueOf(IDHoaDon));

        int MaKH = hd.getIdkhachHang();
        if (MaKH > 0) {
//            listKH = serviceKH.getAllKhachHangTheoMa(MaKH);
//            khachHang kh = listKH.get(0);
//            txtSoDienThoai.setTexJpnGiaoDientSODIENTHOAI());
//            txttenkh.setText(kh.getTENKHACHHANG());
            int manv = Integer.parseInt(tblHoaDon.getValueAt(vtriHD, 2).toString());
            cboNhanVien.setSelectedItem(hddao.getTenNV(manv));
        } else {
            txtSoDienThoai.setText("");
            txttenkh.setText("");
        }
        lblTienThua.setText("");
        txtTienKhachDua.setText("");
        listHDCT = hoaDonChiTietRes.getAllHDCT2(IDHoaDon);
        showDataGioHang(listHDCT);
    }//GEN-LAST:event_tblHoaDonMouseClicked


    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        int selectedRow = -1;
        selectedRow = tblGioHang.getSelectedRow();
        int solgnhap = -1;
        try {
            solgnhap = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng:"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải nhập vào 1 số !");
            return;
        }
        if (solgnhap > 0) {
            try {
                int idSPCT = listHDCT.get(selectedRow).getIdCtsp();
                int solg = Integer.parseInt(tblGioHang.getValueAt(selectedRow, 3).toString());
                SanPhamChiTietViewModel sp = spctRepo.getAllSanPhamid(idSPCT);
                System.out.println(sp.toString());
                int SoLuongThuc = -1;
                if (sp != null) {
                    SoLuongThuc = sp.getSoLuong();
                    if (solgnhap > SoLuongThuc + solg) {
                        JOptionPane.showMessageDialog(this, "Số lượng mua lớn hơn số lượng còn lại của sản phẩm");
                        return;
                    }
                    int x1;
                    x1 = solgnhap - solg;
                    hoadonres.UpdateSoLuong(x1, idSPCT);
                    hoaDonChiTietRes.UpdateHDCT_SoLuong(idSPCT, x1);
                }
                vtriHD = tblHoaDon.getSelectedRow();
                if (vtriHD != -1) {
                    HoaDon hd = listHD.get(vtriHD);
                    listHDCT = hoaDonChiTietRes.getAllHDCT2(hd.getId());
                    showDataGioHang(hoaDonChiTietRes.getAllHDCT2(hd.getId()));
                    lstspctview = spctRepo.getAllSPCT();
                    showDataSPCT(lstspctview);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sô phải lớn hơn 0");
        }
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        // TODO add your handling code here:
        if (tableModel2.getRowCount() != 0) {
            int choise = Integer.valueOf(JOptionPane.showInputDialog("nhập số "));
            int MaHoaDon = Integer.parseInt(lblMaHoaDon.getText());
            HoaDonChiTietViewModel sp = listHDCT.get(choise - 1);
            hoaDonChiTietRes.Delete1SPGioHang(MaHoaDon, sp.getIdCtsp());
            hoaDonChiTietRes.UpdateSoLuongVe(sp.getSoLuong(), sp.getIdCtsp());
            listHDCT = hoaDonChiTietRes.getAllHDCT2(MaHoaDon);
            showDataGioHang(listHDCT);
            lstspctview = spctRepo.getAllSPCT();
            showDataSPCT(lstspctview);
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void txt_maVcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maVcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maVcActionPerformed

    private void btn_maVcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_maVcActionPerformed
        //  TODO add your handling code here:
        String mavc = (txt_maVc.getText().toString());
        int maHd = Integer.parseInt(lblMaHoaDon.getText().toString());
        float tongTien = hoadonres.tongTien(maHd).get(hoadonres.tongTien(maHd).size() - 1).getTongTien();

        if (rp.apMa(mavc).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Sai Mã");
        } else {
            int lastIndex = rp.apMa(mavc).size() - 1;
            if (rp.apMa(mavc).get(lastIndex).getSoLuong() == 0) {
                JOptionPane.showMessageDialog(this, "Voucher Này Đã Hết");
                return;
            }
            JOptionPane.showMessageDialog(this, "Áp Mã Thành Công");

            String mucGiam = rp.apMa(mavc).get(lastIndex).getMucGiam();
            if (mucGiam.length() == 3 || mucGiam.length() == 4) {
                int phanTram = Integer.parseInt(mucGiam.substring(0, 2));
                float tienGiam = (tongTien * phanTram) / 100;
                lblTienGiam.setText(String.valueOf(tienGiam) + "00VND");
                float thanhTien = tongTien - tienGiam;
                lblThanhTien.setText(String.valueOf(thanhTien) + "00VND");
            } else if (mucGiam.length() > 4) {
                float tienMat = Float.parseFloat(mucGiam.substring(0, mucGiam.length() - 7));
                lblTienGiam.setText(mucGiam);
                float thanhTien = tongTien - tienMat;
                lblThanhTien.setText(String.valueOf(thanhTien) + "00VND");
                if (thanhTien < 0){
                    lblThanhTien.setForeground(Color.red);
                    JOptionPane.showMessageDialog(this, "Tiền Giảm Không Vượt Quá Tiền Đơn Hàng");
                    showDataGioHang(listHDCT); 
                    lblThanhTien.setForeground(Color.black);
                    lblTienGiam.setText(" ");
                    txt_maVc.setText("");
                    txt_maVc.requestFocus();
                    return;
                }
            }
        }

    }//GEN-LAST:event_btn_maVcActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txttenkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttenkhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttenkhActionPerformed

    private void btn_maVc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_maVc1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_maVc1ActionPerformed

    private void btn_tienKhachDUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tienKhachDUaActionPerformed
       int maHd = Integer.parseInt(lblMaHoaDon.getText().toString());

if (txt_maVc.getText().toString().isEmpty()) {
    // Access the last element in the tongTien list
    float thanhtien = hoadonres.tongTien(maHd).get(hoadonres.tongTien(maHd).size() - 1).getTongTien();
    float tienkhach = Float.parseFloat(txtTienKhachDua.getText());
    float tienThua = tienkhach - thanhtien;

    if (tienThua >= 0) {
        lblTienThua.setForeground(Color.darkGray);
        lblTienThua.setText(String.valueOf(tienThua) + "00 VND");
    } else {
        lblTienThua.setForeground(Color.red);
        lblTienThua.setText(String.valueOf(tienThua) + "00 VND");
    }
} else {
    float thanhtien = Float.parseFloat(lblThanhTien.getText().substring(0, lblThanhTien.getText().length() - 4));
    float tienkhach = Float.parseFloat(txtTienKhachDua.getText());
    float tienThua = tienkhach - thanhtien;

    if (tienThua >= 0) {
        lblTienThua.setForeground(Color.darkGray);
        lblTienThua.setText(String.valueOf(tienThua) + "00 VND");
    } else {
        lblTienThua.setForeground(Color.red);
        lblTienThua.setText(String.valueOf(tienThua) + "00 VND");
    }
}
    }//GEN-LAST:event_btn_tienKhachDUaActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked


    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        KhachHangRepository repository = new KhachHangRepository();
        ArrayList<Model_khachHang> customers = repository.getAll();
        BHChonKH dialog = new BHChonKH(this);
        dialog.updateTable(customers);
        dialog.setVisible(true);

    }//GEN-LAST:event_jButton1MousePressed
    public void updateCustomerInfo(String sdt, String hoten) {
        txtSoDienThoai.setText((sdt));
        txttenkh.setText(hoten);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyTK;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btnXoaTat;
    private javax.swing.JButton btn_maVc;
    private javax.swing.JButton btn_maVc1;
    private javax.swing.JButton btn_tienKhachDUa;
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel jpl125;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTienGiam;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPhamCT;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txt_maVc;
    private javax.swing.JTextField txttenkh;
    // End of variables declaration//GEN-END:variables
}
