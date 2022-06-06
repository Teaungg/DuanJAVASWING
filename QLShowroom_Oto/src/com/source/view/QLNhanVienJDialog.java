package com.source.view;

import com.source.dao.NhanVienDAO;
import com.source.model.NhanVien;
import com.source.utils.Auth;
import com.source.utils.MsgBox;
import com.source.utils.XImages;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class QLNhanVienJDialog extends javax.swing.JDialog {
 int row = 0;
NhanVienDAO dao = new NhanVienDAO();
    /**
     * Creates new form QLNhanVienJDialog
     */
    public QLNhanVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
     void init() {
        setLocationRelativeTo(null);
//        setIconImage(XImage.getAppIcon());
         setIconImage(XImages.getAppIcon());
        setTitle("QUẢN LÝ NHÂN VIÊN");
        this.row = -1;
        
        this.fillTable(); // đổ dữ liệu nhân viên vào bảng
        this.updateStatus(); // cập nhật trạng thái form
        this.tabs.setSelectedIndex(1);
    }
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblnhanvien.getModel();
        model.setRowCount(0);
        try {
//            List<NhanVien> list = dao.selectAll();
            String keyword = txtTimKiem.getText();
            List<NhanVien> list = dao.selectByKeyword(keyword);
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getHoTen(),
                    nv.getGioiTinh()? "Nam" : "Nữ" ,
                    nv.getSoDT(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.getLuong(),
                    nv.getThuongHH(),
                    nv.getGhiChu(),
                    nv.getHinh(),
                    
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void insert() {
        NhanVien nv = this.getForm();
            try {
                dao.insert(nv); // thêm mới
                this.fillTable();
                this.reset();
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
            }
    }

    void update() {
        NhanVien nv = this.getForm();
            try {
                dao.update(nv); // cập nhật
                this.fillTable(); // đổ lại bảng
                MsgBox.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại!");
            }
    }
    void delete() {
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền xóa nhân viên!");
        } else {
            String manv = txtmaNV.getText();
            if (manv.equals(Auth.user.getUsername())) {
                MsgBox.alert(this, "Bạn không được xóa chính bạn!");
            } else if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
                try {
                    dao.delete(manv);
                    this.fillTable();
                    this.reset();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại!");
                    System.out.println(e);
                }
            }
        }
    }

    void clearForm() {
        NhanVien nv = new NhanVien();
        this.setForm(nv);
        this.row = -1;
        this.updateStatus();
    }

    void edit() {
        try {
        String macd = (String) tblnhanvien.getValueAt(this.row, 0);
        NhanVien model = dao.selectById(macd);
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
    void setForm(NhanVien nv) {
        txtmaNV.setText(nv.getMaNV());
        txtHoTen.setText(nv.getHoTen());
        rdoNam.setSelected(nv.getGioiTinh());
        rdoNu.setSelected(!nv.getGioiTinh());
        
        txtSoDT.setText(nv.getSoDT());
        txtEmail.setText(nv.getEmail());
        txtDiaChi.setText(nv.getDiaChi());
        txtLuong.setText(String.valueOf(nv.getLuong()));
        txtThuongHH.setText(String.valueOf(nv.getThuongHH()));
        txtGhiChu.setText(nv.getGhiChu());
        if (nv.getHinh() != null) {
            lblHinh.setIcon(XImages.readHinhNV(nv.getHinh()));
            lblHinh.setToolTipText(nv.getHinh());
        }
        
        
    }
    NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtmaNV.getText());
        nv.setHoTen(txtHoTen.getText());
        nv.setGioiTinh(rdoNam.isSelected());
//        nv.setGioiTinh(rdoNu.isSelected());
        nv.setSoDT(txtSoDT.getText());
        nv.setEmail(txtEmail.getText());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setLuong(Double.valueOf(txtLuong.getText()));
        nv.setThuongHH(Double.valueOf(txtThuongHH.getText()));
        nv.setGhiChu(txtGhiChu.getText());   
        nv.setHinh(lblHinh.getToolTipText());
        return nv;
    }
    void reset(){
        txtmaNV.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtGhiChu.setText("");
        txtHoTen.setText("");
        txtLuong.setText("");
        txtSoDT.setText("");
        txtThuongHH.setText("");
        btnThem1.setEnabled(true);
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
        if (this.row < tblnhanvien.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    void last() {
        this.row = tblnhanvien.getRowCount() - 1;
        this.edit();
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblnhanvien.getRowCount() - 1);
        // Trạng thái form
        
//        txtmaNV.setEnabled(!edit);
        btnThem1.setEnabled(!edit);
        btnSua1.setEnabled(edit);
        btnXoa1.setEnabled(edit);

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
 /*
    void selectImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { //nếu người dùng đã chọn đc file
            File file = fileChooser.getSelectedFile();    //lấy file người dùng chọn
            if (shareHelper.saveLogo(file)) {  //sao chép file đã chọn thư mục logos
                // Hiển thị hình lên form
                lblHinh.setIcon(shareHelper.readLogo(file.getName())); //file.getName(); lấy tên của file
                //ImageIcon readLogo(String tenFile) đọc file trong thư mục logos theo tên file trả về ImageIcon
                //void setIcon(ImageIcon icon) set Icon cho lbl
                lblHinh.setToolTipText(file.getName());
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton3 = new javax.swing.JRadioButton();
        pnl1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        pnl2 = new javax.swing.JPanel();
        lblMaNhanVien = new javax.swing.JLabel();
        txtmaNV = new javax.swing.JTextField();
        pnl3 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        lblLuong = new javax.swing.JLabel();
        txtThuongHH = new javax.swing.JTextField();
        lblThuongHH = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblGhiChu = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextPane();
        pnl4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlbtn = new javax.swing.JPanel();
        btnThem1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnMoi1 = new javax.swing.JButton();
        btnXoa1 = new javax.swing.JButton();
        pnl5 = new javax.swing.JPanel();
        pnl7 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        pnl8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();

        jRadioButton3.setText("jRadioButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl1.setLayout(new java.awt.BorderLayout());

        tabs.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        pnl2.setBackground(new java.awt.Color(255, 255, 255));

        lblMaNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblMaNhanVien.setText("Mã nhân viên:");

        txtmaNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pnl3.setBackground(new java.awt.Color(255, 255, 255));
        pnl3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pnl3.setLayout(new java.awt.BorderLayout());

        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });
        pnl3.add(lblHinh, java.awt.BorderLayout.CENTER);

        lblSoDienThoai.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");

        txtSoDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblHoTen.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblHoTen.setText("Họ & tên:");

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblEmail.setText("Email:");

        lblGioiTinh.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblGioiTinh.setText("Giới tính:");

        lblDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtLuong.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblLuong.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblLuong.setText("Lương:");

        txtThuongHH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        lblThuongHH.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblThuongHH.setText("Thưởng:");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoNu.setText("Nữ");

        lblGhiChu.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblGhiChu.setText("Ghi chú:");

        txtGhiChu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(txtGhiChu);

        pnl4.setLayout(new java.awt.GridLayout(1, 0));

        btnFirst.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/First.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnl4.add(btnFirst);

        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Prev.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnl4.add(btnPrev);

        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnl4.add(btnNext);

        btnLast.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnl4.add(btnLast);

        pnlbtn.setBackground(new java.awt.Color(255, 255, 255));
        pnlbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThem1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnThem1.setText("Add New");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnSua1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSua1.setText("Updete");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnMoi1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnMoi1.setText("Reset");
        btnMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoi1ActionPerformed(evt);
            }
        });

        btnXoa1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnXoa1.setText("Delete");
        btnXoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlbtnLayout = new javax.swing.GroupLayout(pnlbtn);
        pnlbtn.setLayout(pnlbtnLayout);
        pnlbtnLayout.setHorizontalGroup(
            pnlbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlbtnLayout.setVerticalGroup(
            pnlbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlbtnLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGhiChu)
                    .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl2Layout.createSequentialGroup()
                            .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblLuong)
                                .addComponent(lblEmail)
                                .addComponent(lblMaNhanVien)
                                .addComponent(lblGioiTinh)
                                .addGroup(pnl2Layout.createSequentialGroup()
                                    .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addComponent(txtmaNV)
                                .addComponent(txtLuong))
                            .addGap(18, 18, 18)
                            .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblHoTen)
                                .addComponent(lblSoDienThoai)
                                .addComponent(lblDiaChi)
                                .addComponent(lblThuongHH)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                .addComponent(txtSoDT)
                                .addComponent(txtHoTen)
                                .addComponent(txtThuongHH)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(pnlbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addComponent(lblMaNhanVien)
                                .addGap(0, 0, 0)
                                .addComponent(txtmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addComponent(lblHoTen)
                                .addGap(0, 0, 0)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                                .addComponent(lblGioiTinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEmail)
                                .addGap(0, 0, 0)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblLuong)
                                .addGap(0, 0, 0)
                                .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                                .addComponent(lblSoDienThoai)
                                .addGap(0, 0, 0)
                                .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDiaChi)
                                .addGap(0, 0, 0)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblThuongHH)
                                .addGap(0, 0, 0)
                                .addComponent(txtThuongHH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGhiChu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnl3, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabs.addTab("                    Cập nhật                    ", pnl2);

        pnl5.setBackground(new java.awt.Color(255, 255, 255));
        pnl5.setLayout(new java.awt.BorderLayout());

        pnl7.setBackground(new java.awt.Color(255, 255, 255));
        pnl7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        txtTimKiem.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtTimKiem.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        pnl8.setBackground(new java.awt.Color(255, 255, 255));
        pnl8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách người dùng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblnhanvien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ & tên", "Giới tính", "Số ĐT", "Email", "Địa chỉ", "Lương", "Thưởng", "Ghi chú", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnhanvien.setGridColor(new java.awt.Color(255, 255, 255));
        tblnhanvien.setRowHeight(30);
        tblnhanvien.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhanvien);
        if (tblnhanvien.getColumnModel().getColumnCount() > 0) {
            tblnhanvien.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblnhanvien.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblnhanvien.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblnhanvien.getColumnModel().getColumn(9).setPreferredWidth(0);
        }

        javax.swing.GroupLayout pnl8Layout = new javax.swing.GroupLayout(pnl8);
        pnl8.setLayout(pnl8Layout);
        pnl8Layout.setHorizontalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
        );
        pnl8Layout.setVerticalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnl7Layout = new javax.swing.GroupLayout(pnl7);
        pnl7.setLayout(pnl7Layout);
        pnl7Layout.setHorizontalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem)
                .addContainerGap())
        );
        pnl7Layout.setVerticalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl5.add(pnl7, java.awt.BorderLayout.CENTER);

        tabs.addTab("                           Danh sách                           ", pnl5);

        pnl1.add(tabs, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMouseClicked
        // TODO add your handling code here:
        try {
            if (evt.getClickCount() == 2) {
                this.row = tblnhanvien.rowAtPoint(evt.getPoint());
                if (this.row >= 0) {
                    this.edit();
                    tabs.setSelectedIndex(0);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblnhanvienMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        try {
            if (evt.getClickCount() == 2) {
                this.chonAnh();
            }
            this.edit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        try {
            if(txtmaNV!=null){
                insert();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoi1ActionPerformed
        // TODO add your handling code here:
        txtmaNV.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtGhiChu.setText("");
        txtHoTen.setText("");
        txtLuong.setText("");
        txtSoDT.setText("");
        txtThuongHH.setText("");
        btnThem1.setEnabled(true);
    }//GEN-LAST:event_btnMoi1ActionPerformed

    private void btnXoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa1ActionPerformed
        // TODO add your handling code here:

        delete();
    }//GEN-LAST:event_btnXoa1ActionPerformed

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
            java.util.logging.Logger.getLogger(QLNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLNhanVienJDialog dialog = new QLNhanVienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnMoi1;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGhiChu;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblThuongHH;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl8;
    private javax.swing.JPanel pnlbtn;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblnhanvien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextPane txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtThuongHH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtmaNV;
    // End of variables declaration//GEN-END:variables
}
