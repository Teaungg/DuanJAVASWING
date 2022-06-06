/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.view;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.source.dao.SanPhamDAO;

import com.source.model.SanPham;
import com.source.utils.Auth;
import com.source.utils.MsgBox;
import com.source.utils.XImages;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class ThongTinSanPhamJDialog extends javax.swing.JDialog {
    static int row = -1;
    SanPhamDAO dao = new SanPhamDAO();
 
    SanPham sp;
    public static String Masp = "";
    public static String TenSp="";
    public static Double donGia ;

    /**
     * Creates new form QLSanPhamJDialog
     */
    public ThongTinSanPhamJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        
    }
    public void init(){
        this.setLocationRelativeTo(null);
        setIconImage(XImages.getAppIcon());
        this.setTitle("THÃ”NG TIN Sáº¢N PHáº¨M");
        this.row = -1;

        this.fillTable(); // Ä‘á»• dá»¯ liá»‡u nhÃ¢n viÃªn vÃ o báº£ng
        this.updateStatus(); // cáº­p nháº­t tráº¡ng thÃ¡i form
    }
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblsanpham.getModel();
        model.setRowCount(0);
        try {
            String keyword = txttimkiem.getText();
            List<SanPham> list = dao.selectByKeyword(keyword);
//            List<SanPham> list = dao.selectAll();
            for (SanPham nv : list) {
                Object[] row = {
                    nv.getMaSP(),
                    nv.getTenSP(),
                    nv.getDonGia(),
                    nv.getSoLuong(),
                    nv.getHang(),
                    nv.getXuatXu(),
                    nv.getMauSac(),
                    nv.getPhanLoai(),
                    nv.getHinh(),
                    nv.getGhiChu(),
                    nv.getQRImages()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lá»—i truy váº¥n dá»¯ liá»‡u!");
        }
    }
     void fillTablePhanLoai() {
        DefaultTableModel model = (DefaultTableModel) tblsanpham.getModel();
        model.setRowCount(0);
        try {
            int phanloai = Integer.parseInt(cbxSanPham.getSelectedItem().toString());
            
           
            List<SanPham> list = dao.selectPhanLoai(phanloai);
//            List<SanPham> list = dao.selectAll();
            for (SanPham nv : list) {
                Object[] row = {
                    nv.getMaSP(),
                    nv.getTenSP(),
                    nv.getDonGia(),
                    nv.getSoLuong(),
                    nv.getHang(),
                    nv.getXuatXu(),
                    nv.getMauSac(),
                    nv.getPhanLoai(),
                    nv.getHinh(),
                    nv.getGhiChu(),
                    nv.getQRImages()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lá»—i truy váº¥n dá»¯ liá»‡u!");
        }
    }

    void insert() {
        SanPham nv = this.getForm();
            try {
                dao.insert(nv); // thÃªm má»›i
                this.fillTable(); // Ä‘á»— láº¡i báº£ng
                this.clearForm(); // xÃ³a tráº¯ng form
                MsgBox.alert(this, "ThÃªm má»›i thÃ nh cÃ´ng!");
            } catch (Exception e) {
                MsgBox.alert(this, "ThÃªm má»›i tháº¥t báº¡i!");
                MsgBox.alert(this, "Lá»—i nháº­p dá»¯ liá»‡u");
                System.out.println(e);
            }
    }

    void update() {
        SanPham nv = this.getForm();
            try {
                dao.update(nv); // cáº­p nháº­t            
                this.fillTable(); // Ä‘á»• láº¡i báº£ng
                MsgBox.alert(this, "Cáº­p nháº­t thÃ nh cÃ´ng!");
            } catch (Exception e) {
                System.out.println(e);
                MsgBox.alert(this, "Cáº­p nháº­t tháº¥t báº¡i!");
            }
    }
    void delete() {
            String masp = txtmasp.getText();
                if (MsgBox.confirm(this, "Báº¡n thá»±c sá»± muá»‘n xÃ³a sáº£n pháº©m nÃ y?")) {
                try {
                    dao.delete(masp);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "XÃ³a thÃ nh cÃ´ng!");
                } catch (Exception e) {
                    MsgBox.alert(this, "XÃ³a tháº¥t báº¡i!");
                }
            }
        }

    void clearForm() {
        SanPham nv = new SanPham();
        this.setForm(nv);
        this.row = -1;
        this.updateStatus();
    }

    void edit() {
    try {
        String macd = (String) tblsanpham.getValueAt(this.row, 0);
        SanPham model = dao.selectById(macd);
        if (model != null) {
                this.setForm(model);
//                tabs.setSelectedIndex(0);
                this.updateStatus();
            }
        } catch (Exception e) {
            System.out.println(e);
            MsgBox.alert(this, "Lá»—i truy váº¥n dá»¯ liá»‡u!");
        }
    }

    void setForm(SanPham nv) {
        txtmasp.setText(nv.getMaSP());    
        txttensp.setText(nv.getTenSP());  
        txtdongia.setText(String.valueOf(nv.getDonGia()));
        txtsoluong.setText(String.valueOf(nv.getSoLuong()));
        txthangsanxuat.setText(nv.getHang());
        txtxuatxu.setText(nv.getXuatXu());
        txtmausac.setText(nv.getMauSac());
        txtghichu.setText(nv.getGhiChu());
        if (nv.getHinh() != null) {
            lblHinh.setIcon(XImages.readHinhSP(nv.getHinh()));
            lblHinh.setToolTipText(nv.getHinh());
        }
        txtghichu.setText(nv.getGhiChu());
        if (nv.getQRImages()!= null) {
            lblMaQR.setIcon(XImages.readQRSanPham(nv.getQRImages()));
            lblMaQR.setToolTipText(nv.getQRImages());
        }
       txtphanloai.setText(nv.getPhanLoai());
    }
    

    SanPham getForm() {
        SanPham nv = new SanPham();
        nv.setMaSP(txtmasp.getText());
        nv.setTenSP(txttensp.getText());       
        nv.setDonGia(Double.valueOf(txtdongia.getText()));
        nv.setSoLuong(Integer.valueOf(txtsoluong.getText()));
        nv.setHang(txthangsanxuat.getText());
        nv.setXuatXu(txtxuatxu.getText());
        nv.setMauSac(txtmausac.getText());
        nv.setPhanLoai(txtphanloai.getText()); 
        nv.setHinh(lblHinh.getToolTipText());
        nv.setGhiChu(txtghichu.getText());  
        nv.setQRImages(f.getName());
//         hd.setQrCode(f.getName());
        return nv;
    }

        void first() {
        this.row = 0;
        this.edit();
    }

    void prev() {
        if (this.row > 0) {
            this.row--;
            this.edit();
        }
    }

    void next() {
        if (this.row < tblsanpham.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    void last() {
        this.row = tblsanpham.getRowCount() - 1;
        this.edit();
    }
    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblsanpham.getRowCount() - 1);
        // Tráº¡ng thÃ¡i form
        txtmasp.setEnabled(!edit);
       // btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        // Tráº¡ng thÃ¡i Ä‘iá»�u hÆ°á»›ng
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
 void chonAnh() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile(); // lÆ°u hÃ¬nh vÃ o thÆ° má»¥c logo
            XImages.savehinhSP(file);
            ImageIcon icon = XImages.readHinhSP(file.getName()); // Ä‘á»�c hÃ¬nh tá»« logos
            lblHinh.setIcon(icon);
            System.out.println(icon);
            lblHinh.setToolTipText(file.getName()); // giá»¯ tÃªn hÃ¬nh trong tooltip

        }
    }
 void chonQRCode() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile(); // lÆ°u hÃ¬nh vÃ o thÆ° má»¥c logo
            XImages.saveQRSanPham(file);
            ImageIcon icon = XImages.readQRSanPham(file.getName()); // Ä‘á»�c hÃ¬nh tá»« logos
            lblMaQR.setIcon(icon);
            System.out.println(icon);
            lblMaQR.setToolTipText(file.getName()); // giá»¯ tÃªn hÃ¬nh trong tooltip

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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pnl1 = new javax.swing.JPanel();
        pnl2 = new javax.swing.JPanel();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsanpham = new javax.swing.JTable();
        cbxSanPham = new javax.swing.JComboBox<>();
        pnl3 = new javax.swing.JPanel();
        pnl4 = new javax.swing.JPanel();
        lblmasp = new javax.swing.JLabel();
        txtmasp = new javax.swing.JTextField();
        txttensp = new javax.swing.JTextField();
        lbltensp = new javax.swing.JLabel();
        lbldongia = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        txtsoluong = new javax.swing.JTextField();
        lblsoluong = new javax.swing.JLabel();
        txthangsanxuat = new javax.swing.JTextField();
        lblhangsanxuat = new javax.swing.JLabel();
        txtxuatxu = new javax.swing.JTextField();
        lblxuatxu = new javax.swing.JLabel();
        lblmausac = new javax.swing.JLabel();
        txtmausac = new javax.swing.JTextField();
        txtghichu = new javax.swing.JTextField();
        lblphanloai = new javax.swing.JLabel();
        lblghichu = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        txtphanloai = new javax.swing.JTextField();
        lblMaQR = new javax.swing.JLabel();
        pnl6 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl1.setLayout(new java.awt.BorderLayout());

        pnl2.setBackground(new java.awt.Color(255, 255, 255));
        pnl2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Danh sÃ¡ch sáº£n pháº©m", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
        pnl2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txttimkiem.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txttimkiem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        tblsanpham.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ£ SP", "TÃªn SP", "Ä�Æ¡n giÃ¡", "Sá»‘ lÆ°á»£ng", "HÃ£ng", "Xuáº¥t xá»©", "MÃ u sáº¯c", "PhÃ¢n loáº¡i", "HÃ¬nh", "Ghi chÃº", "QR Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsanpham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblsanpham.setGridColor(new java.awt.Color(255, 255, 255));
        tblsanpham.setRowHeight(30);
        tblsanpham.setRowMargin(2);
        tblsanpham.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblsanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblsanpham);
        if (tblsanpham.getColumnModel().getColumnCount() > 0) {
            tblsanpham.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblsanpham.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblsanpham.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblsanpham.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblsanpham.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblsanpham.getColumnModel().getColumn(7).setPreferredWidth(0);
        }

        cbxSanPham.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cbxSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Táº¥t cáº£", "Ã” tÃ´", "Phá»¥ tÃ¹ng", " " }));
        cbxSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSanPhamItemStateChanged(evt);
            }
        });
        cbxSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSanPhamActionPerformed(evt);
            }
        });
        cbxSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbxSanPhamKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbxSanPhamKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttimkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(cbxSanPham))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl1.add(pnl2, java.awt.BorderLayout.CENTER);

        pnl3.setBackground(new java.awt.Color(255, 255, 255));
        pnl3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        pnl4.setBackground(new java.awt.Color(255, 255, 255));
        pnl4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblmasp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblmasp.setText("MÃ£ sáº£n pháº©m");
        pnl4.add(lblmasp, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 28, -1, -1));

        txtmasp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmasp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtmasp, org.jdesktop.beansbinding.ELProperty.create("Nháº­p mÃ£ sp (SP001)"), txtmasp, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txtmasp, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 50, 230, 40));

        txttensp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txttensp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txttensp, org.jdesktop.beansbinding.ELProperty.create("nháº­p tÃªn sáº£n pháº©m"), txttensp, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txttensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 119, 230, 40));

        lbltensp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbltensp.setText("TÃªn sáº£n pháº©m");
        pnl4.add(lbltensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 97, -1, -1));

        lbldongia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbldongia.setText("Ä�Æ¡n giÃ¡");
        pnl4.add(lbldongia, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 159, -1, -1));

        txtdongia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtdongia.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtdongia, org.jdesktop.beansbinding.ELProperty.create("Ä‘Æ¡n giÃ¡ lÃ  sá»‘ vÃ  pháº£i lá»›n hÆ¡n 0"), txtdongia, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txtdongia, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 181, 230, 40));

        txtsoluong.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtsoluong.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtsoluong, org.jdesktop.beansbinding.ELProperty.create("sá»‘ lÆ°á»£ng pháº£i lÃ  sá»‘ vÃ  lá»›n hÆ¡n 0"), txtsoluong, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txtsoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 250, 230, 40));

        lblsoluong.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblsoluong.setText("Sá»‘ lÆ°á»£ng");
        pnl4.add(lblsoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 228, -1, -1));

        txthangsanxuat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txthangsanxuat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnl4.add(txthangsanxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 50, 220, 30));

        lblhangsanxuat.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblhangsanxuat.setText("HÃ£ng sáº£n xuáº¥t");
        pnl4.add(lblhangsanxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, -1));

        txtxuatxu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtxuatxu.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnl4.add(txtxuatxu, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 220, 30));

        lblxuatxu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblxuatxu.setText("Xuáº¥t xá»©");
        pnl4.add(lblxuatxu, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 80, -1, -1));

        lblmausac.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblmausac.setText("MÃ u sáº¯c");
        pnl4.add(lblmausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, -1, -1));

        txtmausac.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmausac.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnl4.add(txtmausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 160, 220, 30));

        txtghichu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtghichu.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnl4.add(txtghichu, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 220, 30));

        lblphanloai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblphanloai.setText("PhÃ¢n loáº¡i");
        pnl4.add(lblphanloai, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, -1, -1));

        lblghichu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblghichu.setText("Ghi chÃº");
        pnl4.add(lblghichu, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, -1, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnThem.setText("Add New");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSua.setText("Update");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnMoi.setText("Reset");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnPrint.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnexit.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnl4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 327, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new java.awt.BorderLayout());

        lblHinh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/ImgSanPham/Annotation 2021-05-18 104326.jpg"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lblHinh, org.jdesktop.beansbinding.ELProperty.create("click vÃ o hÃ¬nh Ä‘á»ƒ set hÃ¬nh tá»« thÆ° viá»‡n"), lblHinh, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        jPanel2.add(lblHinh, java.awt.BorderLayout.CENTER);

        pnl4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 7, 426, 307));

        txtphanloai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtphanloai.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pnl4.add(txtphanloai, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 220, 30));

        lblMaQR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblMaQR.setOpaque(true);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lblMaQR, org.jdesktop.beansbinding.ELProperty.create("Ä�Ã¢y lÃ  mÃ£ qr cá»§a sáº£n pháº©m , láº¥y Ä‘iá»‡n thoáº¡i ra vÃ  quÃ©t mÃ£ sáº½ hiá»‡n thÃ´ng tin sáº£n pháº©m"), lblMaQR, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        lblMaQR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMaQRMouseClicked(evt);
            }
        });
        pnl4.add(lblMaQR, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 396, 360));

        pnl6.setBackground(new java.awt.Color(204, 204, 204));
        pnl6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        pnl6.setLayout(new java.awt.GridLayout(1, 0));

        btnFirst.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/First.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnl6.add(btnFirst);

        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Prev.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnl6.add(btnPrev);

        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnl6.add(btnNext);

        btnLast.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnl6.add(btnLast);

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, 1376, Short.MAX_VALUE)
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addComponent(pnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl1.add(pnl3, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pnl1);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked
        // TODO add your handling code here:
        try {
        if (evt.getClickCount() == 2) {
            this.row = tblsanpham.rowAtPoint(evt.getPoint());
            if (this.row >= 0) {
                this.edit();
            }
        }
            } catch (Exception e) {
                System.out.println(e);
            }
    }//GEN-LAST:event_tblsanphamMouseClicked

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            delete();
        } catch (Exception e) {
            MsgBox.alert(this, "Báº¡n hiá»‡n khÃ´ng thá»ƒ xÃ³a sáº£n pháº©m nÃ y");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        try {
            this.chonAnh();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            if(txtmasp==null){
            }else{
                QRcode();
                insert();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.last();
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet spreadsheet = workbook.createSheet("SanPham");
//
//            XSSFRow row = null;
//            Cell cell = null;
//            
//            
//            row=spreadsheet.createRow(2);
//            cell=row.createCell(0, CellType.STRING);
//            row = spreadsheet.createRow((short) 2);
//            row.setHeight((short) 500);
            
//            cell = row.createCell(0, CellType.STRING);
////            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("STT");
////
//            row = spreadsheet.createRow((short) 3);
//            row.setHeight((short) 500);
//            cell = row.createCell(0, CellType.STRING);
//            cell.setCellValue("STT");
//            cell = row.createCell(1, CellType.STRING);
//            cell.setCellValue("Há»� vÃ  tÃªn");
//            cell = row.createCell(2, CellType.STRING);
//            cell.setCellValue("NgÃ y sinh");
//            cell = row.createCell(3, CellType.STRING);
//            cell.setCellValue("Giá»›i tÃ­nh");
//            cell = row.createCell(4, CellType.STRING);
//            cell.setCellValue("Sá»‘ Ä‘iá»‡n thoáº¡i");
//            cell = row.createCell(5, CellType.STRING);
//            cell.setCellValue("Ä�á»‹a chá»‰");
//
//            HocVienService hocVienService = new HocVienServiceImpl();
//
//            List<HocVien> listItem = hocVienService.getList();
//
//            for (int i = 0; i < listItem.size(); i++) {
//                HocVien hocVien = listItem.get(i);
//                row = spreadsheet.createRow((short) 4 + i);
//                row.setHeight((short) 400);
//                row.createCell(0).setCellValue(i + 1);
//                row.createCell(1).setCellValue(hocVien.getHo_ten());
//                row.createCell(2).setCellValue(hocVien.getNgay_sinh().toString());
//                row.createCell(3).setCellValue(hocVien.isGioi_tinh() ? "Nam" : "Ná»¯");
//                row.createCell(4).setCellValue(hocVien.getSo_dien_thoai());
//                row.createCell(5).setCellValue(hocVien.getDia_chi());
//            }
//
//            FileOutputStream out = new FileOutputStream(new File("D:/hv.xlsx"));
//            workbook.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        if(MsgBox.confirm(this, "Báº¡n cháº¯c cháº¯n muá»‘n thoÃ¡t!")){
        this.dispose();
        }
    }//GEN-LAST:event_btnexitActionPerformed

    private void cbxSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSanPhamActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_cbxSanPhamActionPerformed

    private void lblMaQRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaQRMouseClicked
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_lblMaQRMouseClicked

    private void cbxSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSanPhamKeyReleased
        // TODO add your handling code here:
        //fillTablePhanLoai();
    }//GEN-LAST:event_cbxSanPhamKeyReleased

    private void cbxSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSanPhamKeyPressed
        // TODO add your handling code here:
        //fillTablePhanLoai();
    }//GEN-LAST:event_cbxSanPhamKeyPressed

    private void cbxSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSanPhamItemStateChanged
        // TODO add your handling code here:
     //   fillTablePhanLoai();
    }//GEN-LAST:event_cbxSanPhamItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongTinSanPhamJDialog dialog = new ThongTinSanPhamJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnexit;
    private javax.swing.JComboBox<String> cbxSanPham;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblMaQR;
    private javax.swing.JLabel lbldongia;
    private javax.swing.JLabel lblghichu;
    private javax.swing.JLabel lblhangsanxuat;
    private javax.swing.JLabel lblmasp;
    private javax.swing.JLabel lblmausac;
    private javax.swing.JLabel lblphanloai;
    private javax.swing.JLabel lblsoluong;
    private javax.swing.JLabel lbltensp;
    private javax.swing.JLabel lblxuatxu;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl6;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtghichu;
    private javax.swing.JTextField txthangsanxuat;
    private javax.swing.JTextField txtmasp;
    private javax.swing.JTextField txtmausac;
    private javax.swing.JTextField txtphanloai;
    private javax.swing.JTextField txtsoluong;
    private javax.swing.JTextField txttensp;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txtxuatxu;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

      File f;
    OutputStream out;
     QRCodeWriter QRcode() {
        String tiengviet = "UTF-8";
        String content = "ThÃ´ng tin sáº£n pháº©m\n"
                + "   TÃªn sáº£n pháº©m : " + txttensp.getText()+ "\n"
                + "  file:///C:/Study_Fpoly_PS17792/HK4/DUAN1/QLShowRoomOto/QLShowroom_Oto/help/index.html " + "\n";
                
               

        QRCodeWriter qrcodeWriter = new QRCodeWriter();

        try {
            Hashtable hint = new Hashtable();
            hint.put(EncodeHintType.CHARACTER_SET,"UTF-8" );
            BitMatrix bitmatrix = qrcodeWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hint);
//            BitMatrix matrix = new MultiFormatWriter().encode(
//            new String(content.getBytes(tiengviet), tiengviet),
//            BarcodeFormat.QR_CODE, 100, 100);
            StringBuilder tenanhQR = new StringBuilder("");
            int random = 0;
            for (int i = 0; i < 10; i++) {
                random = (int) Math.round(Math.random() * 9);
                tenanhQR.append(random);
                System.out.println(random);
            }
            f = new File("src\\com\\source\\qrcode\\" + tenanhQR + ".jpg");
            out = new FileOutputStream(f);
            System.out.println(f); 
            MatrixToImageWriter.writeToStream(bitmatrix, "jpg", out);
       
        } catch (Exception e) {
        }
        return qrcodeWriter;
    }

//     void timKiemSPtheoLoai(){
//        DefaultTableModel model = (DefaultTableModel)tblsanpham.getModel();
//        model.setRowCount(0);
//        String text;
//        if(cbxSanPham.getSelectedIndex()== 0){
//            text = null;
//            this.fillTable();
//        }else{
//         text = (String) cbxSanPham.getSelectedItem();
//        ArrayList<SanPham> list = dao.selectById(dao.find_IDLoai(text));
//        ArrayList<SanPham> list = dao.findSPTheoTheLoaiID(String.valueOf(dao.find_IDLoai(text)));
//         for(Sach sach : list){
//            Object [] o = {
//                sach.getId(),
//                sach.getTensach(),
//                sach.getDongia(),
//                sach.getSoluong(),            
//            };
//            model.addRow(o);
//        }              
//        }       
//   }
 
     
}
