/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.view;


import com.source.dao.SanPhamDAO;

import com.source.model.SanPham;
import com.source.utils.Auth;
import com.source.utils.MsgBox;
import com.source.utils.XImages;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QLSanPhamJDialog extends javax.swing.JDialog {
    static int row = -1;
    SanPhamDAO dao = new SanPhamDAO();
   
    SanPham sp;
    public static String Masp = "";
    public static String TenSp="";
    public static double donGia ;

    /**
     * Creates new form QLSanPhamJDialog
     */
    public QLSanPhamJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        
    }
    public void init(){
        this.setLocationRelativeTo(null);
        this.setTitle("QUẢN LÝ SẢN PHẨM");
         setIconImage(XImages.getAppIcon());
        this.row = -1;
 
        this.fillTable(); // đổ dữ liệu nhân viên vào bảng
        this.updateStatus(); // cập nhật trạng thái form
    }
    
    public void setColor(JPanel panel){
        panel.setBackground(new java.awt.Color(150,150,150));
    }
     public void resetColor(JPanel panel){
        panel.setBackground(new java.awt.Color(255,255,255));
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
//                    nv.getSoLuong(),
                    nv.getHang(),
                    nv.getXuatXu(),
                    nv.getPhanLoai(),
                    nv.getMauSac(),
                  
                    nv.getHinh(),
                    nv.getGhiChu()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void insert() {
        SanPham nv = this.getForm();
            try {
                dao.insert(nv); // thêm mới
                this.fillTable(); // đỗ lại bảng
                this.clearForm(); // xóa trắng form
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
                System.out.println(e);
            }
    }

    void update() {
        SanPham nv = this.getForm();
            try {
                dao.update(nv); // cập nhật            
                this.fillTable(); // đổ lại bảng
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                System.out.println(e);
                MsgBox.alert(this, "Cập nhật thất bại!");
            }
    }
    void delete() {
            String masp = txtmasp.getText();
//            if (masp.equals(Auth.user.getUsername())) {
//                MsgBox.alert(this, "Bạn không được xóa chính bạn!");
//            } else 
                if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
                try {
                    dao.delete(masp);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại!");
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
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setForm(SanPham nv) {
        txtmasp.setText(nv.getMaSP());    
        txttensp.setText(nv.getTenSP());  
        txtdongia.setText(String.valueOf(nv.getDonGia()));
//        txtsoluong.setText(String.valueOf(nv.getSoLuong()));
        txthangsanxuat.setText(nv.getHang());
        txtxuatxu.setText(nv.getXuatXu());
        txtmausac.setText(nv.getMauSac());
        txtPhanLoai.setText(nv.getPhanLoai());
        if (nv.getHinh() != null) {
            lblHinh.setIcon(XImages.readHinhSP(nv.getHinh()));
            lblHinh.setToolTipText(nv.getHinh());
        }
        txtghichu.setText(nv.getGhiChu());
        Masp = txtmasp.getText();
        TenSp = txtdongia.getText();
        donGia = Double.parseDouble(txtdongia.getText());
        
    }
    

    SanPham getForm() {
        SanPham nv = new SanPham();
        nv.setMaSP(txtmasp.getText());
        nv.setTenSP(txttensp.getText());       
        nv.setDonGia(Double.valueOf(txtdongia.getText()));
//        nv.setSoLuong(Integer.valueOf(txtsoluong.getText()));
        nv.setHang(txtmausac.getText());
        nv.setXuatXu(txtmausac.getText());
        nv.setMauSac(txtmausac.getText());
       nv.setPhanLoai(txtPhanLoai.getText()); 
        nv.setHinh(lblHinh.getToolTipText());
        nv.setGhiChu(txtghichu.getText());  
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
        // Trạng thái form
//        txtmasp.setEnabled(!edit);
       // btnThem.setEnabled(!edit);
//        btnSua.setEnabled(edit);
//        btnXoa.setEnabled(edit);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
 void chonAnh() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile(); // lưu hình vào thư mục logo
            XImages.save(file);
            ImageIcon icon = XImages.read(file.getName()); // đọc hình từ logos
            lblHinh.setIcon(icon);
            lblHinh.setToolTipText(file.getName()); // giữ tên hình trong tooltip

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
        pnl3 = new javax.swing.JPanel();
        pnl4 = new javax.swing.JPanel();
        lblmasp = new javax.swing.JLabel();
        lbltensp = new javax.swing.JLabel();
        lbldongia = new javax.swing.JLabel();
        lblhangsanxuat = new javax.swing.JLabel();
        lblxuatxu = new javax.swing.JLabel();
        lblmausac = new javax.swing.JLabel();
        lblghichu = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtghichu = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        txtmasp = new javax.swing.JLabel();
        txtdongia = new javax.swing.JLabel();
        txttensp = new javax.swing.JLabel();
        txtmausac = new javax.swing.JLabel();
        txthangsanxuat = new javax.swing.JLabel();
        txtxuatxu = new javax.swing.JLabel();
        pnlgiohang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlthem = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblhangsanxuat1 = new javax.swing.JLabel();
        txtPhanLoai = new javax.swing.JLabel();
        pnl6 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        cbxSanPham = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setLayout(new java.awt.BorderLayout());

        pnl2.setBackground(new java.awt.Color(255, 255, 255));
        pnl2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        txttimkiem.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txttimkiem, org.jdesktop.beansbinding.ELProperty.create("Nhập mã sản phẩm bạn muốn tìm"), txttimkiem, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        tblsanpham.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Hãng", "Xuất xứ", "Phân loại", "Màu Sắc", "Hình", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsanpham.setRowHeight(25);
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
        }

        pnl3.setBackground(new java.awt.Color(255, 255, 255));
        pnl3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        pnl4.setBackground(new java.awt.Color(255, 255, 255));
        pnl4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblmasp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblmasp.setText("Mã sản phẩm");
        pnl4.add(lblmasp, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 0, -1, -1));

        lbltensp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbltensp.setText("Tên sản phẩm");
        pnl4.add(lbltensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 82, -1, -1));

        lbldongia.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbldongia.setText("Đơn giá");
        pnl4.add(lbldongia, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 164, -1, -1));

        lblhangsanxuat.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblhangsanxuat.setText("Phân loại");
        pnl4.add(lblhangsanxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1037, 0, -1, -1));

        lblxuatxu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblxuatxu.setText("Xuất xứ");
        pnl4.add(lblxuatxu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 82, -1, -1));

        lblmausac.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblmausac.setText("Màu sắc");
        pnl4.add(lblmausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 164, -1, -1));

        lblghichu.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblghichu.setText("Ghi chú");
        pnl4.add(lblghichu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 260, -1, -1));

        txtghichu.setEditable(false);
        txtghichu.setBorder(null);
        txtghichu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtghichu.setForeground(new java.awt.Color(204, 0, 0));
        jScrollPane2.setViewportView(txtghichu);

        pnl4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 321, 551, 110));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel2.setLayout(new java.awt.BorderLayout());

        lblHinh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/ImgSanPham/Annotation 2021-05-18 104326.jpg"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lblHinh, org.jdesktop.beansbinding.ELProperty.create("Click vào ảnh để chọn ảnh từ thư viện "), lblHinh, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        jPanel2.add(lblHinh, java.awt.BorderLayout.CENTER);

        pnl4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 390));

        txtmasp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtmasp.setForeground(new java.awt.Color(204, 0, 0));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtmasp, org.jdesktop.beansbinding.ELProperty.create("Nhập mã sp (SP001)"), txtmasp, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txtmasp, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 35, 270, 40));

        txtdongia.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtdongia.setForeground(new java.awt.Color(204, 0, 0));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txtdongia, org.jdesktop.beansbinding.ELProperty.create("đơn giá là số và trên 0 nha"), txtdongia, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txtdongia, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 210, 270, 40));

        txttensp.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txttensp.setForeground(new java.awt.Color(204, 0, 0));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, txttensp, org.jdesktop.beansbinding.ELProperty.create("Nhập tên sp"), txttensp, org.jdesktop.beansbinding.BeanProperty.create("toolTipText"));
        bindingGroup.addBinding(binding);

        pnl4.add(txttensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 117, 270, 40));

        txtmausac.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtmausac.setForeground(new java.awt.Color(204, 0, 0));
        pnl4.add(txtmausac, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 199, 270, 40));

        txthangsanxuat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txthangsanxuat.setForeground(new java.awt.Color(204, 0, 0));
        pnl4.add(txthangsanxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 35, 270, 40));

        txtxuatxu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtxuatxu.setForeground(new java.awt.Color(204, 0, 0));
        pnl4.add(txtxuatxu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 117, 270, 40));

        pnlgiohang.setBackground(new java.awt.Color(255, 255, 255));
        pnlgiohang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlgiohangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlgiohangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlgiohangMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Giỏ hàng");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlgiohangLayout = new javax.swing.GroupLayout(pnlgiohang);
        pnlgiohang.setLayout(pnlgiohangLayout);
        pnlgiohangLayout.setHorizontalGroup(
            pnlgiohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlgiohangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        pnlgiohangLayout.setVerticalGroup(
            pnlgiohangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlgiohangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl4.add(pnlgiohang, new org.netbeans.lib.awtextra.AbsoluteConstraints(988, 364, -1, -1));

        pnlthem.setBackground(new java.awt.Color(255, 255, 255));
        pnlthem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlthem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlthemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlthemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlthemMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thêm vào giỏ hàng");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlthemLayout = new javax.swing.GroupLayout(pnlthem);
        pnlthem.setLayout(pnlthemLayout);
        pnlthemLayout.setHorizontalGroup(
            pnlthemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlthemLayout.setVerticalGroup(
            pnlthemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlthemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnl4.add(pnlthem, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 225, -1));

        lblhangsanxuat1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblhangsanxuat1.setText("Hãng sản xuất");
        pnl4.add(lblhangsanxuat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, -1));

        txtPhanLoai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txtPhanLoai.setForeground(new java.awt.Color(204, 0, 0));
        pnl4.add(txtPhanLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1003, 35, 177, 40));

        pnl6.setLayout(new java.awt.GridLayout(1, 0));

        btnFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnFirst.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/First.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnl6.add(btnFirst);

        btnPrev.setBackground(new java.awt.Color(255, 255, 255));
        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Prev.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnl6.add(btnPrev);

        btnNext.setBackground(new java.awt.Color(255, 255, 255));
        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnl6.add(btnNext);

        btnLast.setBackground(new java.awt.Color(255, 255, 255));
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
            .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addComponent(pnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cbxSanPham.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        cbxSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Ô tô", "Phụ tùng" }));
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
            .addComponent(pnl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txttimkiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txttimkiem)
                    .addComponent(cbxSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnl1.add(pnl2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(pnl1);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tblsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblsanphamMouseClicked
        // TODO add your handling code here:
        try {
        if (evt.getClickCount() == 2) {
            this.row = tblsanpham.rowAtPoint(evt.getPoint());
            if (this.row >= 0) {
                this.edit();
//                tabs.setSelectedIndex(0);
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

    private void cbxSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSanPhamActionPerformed
        // TODO add your handling code here:
        //        fillComboBoxPhanLoai();
    }//GEN-LAST:event_cbxSanPhamActionPerformed

    private void pnlthemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlthemMouseClicked
        // TODO add your handling code here:
        new ChonSPJDialog(null, true).setVisible(true);
    }//GEN-LAST:event_pnlthemMouseClicked

    private void pnlthemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlthemMouseExited
        // TODO add your handling code here:
        resetColor(pnlthem);
    }//GEN-LAST:event_pnlthemMouseExited

    private void pnlthemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlthemMouseEntered
        // TODO add your handling code here:
        setColor(pnlthem);
    }//GEN-LAST:event_pnlthemMouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new ChonSPJDialog(null, true).setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        // TODO add your handling code here:
        resetColor(pnlthem);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
        setColor(pnlthem);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void pnlgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlgiohangMouseClicked
        // TODO add your handling code here:
        GioHangJFrame gh = new GioHangJFrame();
        gh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_pnlgiohangMouseClicked

    private void pnlgiohangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlgiohangMouseExited
        // TODO add your handling code here:
        resetColor(pnlgiohang);
    }//GEN-LAST:event_pnlgiohangMouseExited

    private void pnlgiohangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlgiohangMouseEntered
        // TODO add your handling code here:
        setColor(pnlgiohang);
    }//GEN-LAST:event_pnlgiohangMouseEntered

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        GioHangJFrame gh = new GioHangJFrame();
        gh.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        // TODO add your handling code here:
        setColor(pnlgiohang);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        // TODO add your handling code here:
        resetColor(pnlgiohang);
    }//GEN-LAST:event_jLabel2MouseExited

    private void cbxSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSanPhamKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cbxSanPhamKeyReleased

    private void cbxSanPhamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxSanPhamKeyPressed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_cbxSanPhamKeyPressed

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
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLSanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLSanPhamJDialog dialog = new QLSanPhamJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JComboBox<String> cbxSanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lbldongia;
    private javax.swing.JLabel lblghichu;
    private javax.swing.JLabel lblhangsanxuat;
    private javax.swing.JLabel lblhangsanxuat1;
    private javax.swing.JLabel lblmasp;
    private javax.swing.JLabel lblmausac;
    private javax.swing.JLabel lbltensp;
    private javax.swing.JLabel lblxuatxu;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnlgiohang;
    private javax.swing.JPanel pnlthem;
    private javax.swing.JTable tblsanpham;
    private javax.swing.JLabel txtPhanLoai;
    private javax.swing.JLabel txtdongia;
    private javax.swing.JTextPane txtghichu;
    private javax.swing.JLabel txthangsanxuat;
    private javax.swing.JLabel txtmasp;
    private javax.swing.JLabel txtmausac;
    private javax.swing.JLabel txttensp;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JLabel txtxuatxu;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    
     
}
