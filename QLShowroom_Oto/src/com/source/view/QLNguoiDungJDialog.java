/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.view;

import com.source.dao.UsersDAO;
import com.source.model.Users;
import com.source.utils.Auth;
import com.source.utils.MsgBox;
import com.source.utils.XImages;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QLNguoiDungJDialog extends javax.swing.JDialog {

    int row = -1;
    UsersDAO dao = new UsersDAO();
    /**
     * Creates new form QuanLyNguoiDungJDialog
     */
    public QLNguoiDungJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    void init() {
        setLocationRelativeTo(null);
        setIconImage(XImages.getAppIcon());
        setTitle("QUẢN LÝ NGƯỜI DÙNG");
        
        this.fillTable(); // đổ dữ liệu nhân viên vào bảng
        this.updateStatus(); // cập nhật trạng thái form
        this.tabs.setSelectedIndex(1);
    }
    
    public void setColor(JPanel panel){
        panel.setBackground(new java.awt.Color(150,150,150));
    }
     public void resetColor(JPanel panel){
        panel.setBackground(new java.awt.Color(255,255,255));
    }
//    ArrayList<Users> dsus;
//    public void loadData_ToTable(){
//      // dsus.clear();
//        UsersDAO usdao= new UsersDAO();
//        Users user = new Users();
//        user.setUsername(txtTimKiem.getText());
//        user.setHoTen(txtTimKiem.getText());
//        user.setMatKhau(txtTimKiem.getText());
//        user.setEmail(txtTimKiem.getText());
//        user.setSodt(txtTimKiem.getText());
////        user.setVaiTro(false);
//        //// su ly kieu du lieu la so. cho rol vi rol la kieu so.
////        if (txtTimKiem.getText().isEmpty())
////            user.setVaiTro(false);
////        else
////           if(txtTimKiem.getText().chars().allMatch(Character::isDigit))//khi nhap chuoi thi khong nhan, so o txttim moi nhan.
////               user.setVaiTro(Boolean.parseBoolean(txtTimKiem.getText())); 
////        ////
//        dsus=usdao.tim(user);
//        DefaultTableModel df = (DefaultTableModel) tblnguoidung.getModel();
//        df.setRowCount(0);
//        for(int i=0; i<dsus.size(); i++)
//        {
//            String usernsme=dsus.get(i).getUsername();
//            String hoten=dsus.get(i).getHoTen();
//            String matkhau=dsus.get(i).getMatKhau();
//            String email=dsus.get(i).getEmail();
//            String sodt=dsus.get(i).getSodt();
////            boolean role=dsus.get(i).getVaiTro();
//            Object[] row= new Object[]{usernsme,hoten,matkhau,email,sodt};//usernsme,hoten,matkhau,email,sodt,role
//            df.addRow(row);
//        }
//    }
    
    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblnguoidung.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<Users> list = dao.selectByKeyword(keyword);
//            List<Users> list = dao.selectAll();
            for (Users nv : list) {
                Object[] row = {
                    nv.getUsername(),
                    nv.getHoTen(),
                    nv.getMaNV(),
                    nv.getMatKhau(),
                    nv.getEmail(),
                    nv.getSodt(),
                    nv.getVaiTro() ?  "Trưởng phòng" : "Nhân viên" ,
                    nv.getHinh()
                        
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void insert() {
        Users nv = this.getForm();
            try {
                dao.insert(nv); // thêm mới
                this.fillTable(); // đỗ lại bảng
                this.clearForm(); // xóa trắng form
                MsgBox.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Thêm mới thất bại!");
            }
    }

    void update() {
        Users nv = this.getForm();
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
        if (!Auth.isManager()) {
            MsgBox.alert(this, "Bạn không có quyền xóa nhân viên!");
        } else {
            String manv = txthoten.getText();
            if (manv.equals(Auth.user.getUsername())) {
                MsgBox.alert(this, "Bạn không được xóa chính bạn!");
            } else if (MsgBox.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
                try {
                    dao.delete(manv);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    MsgBox.alert(this, "Xóa thất bại!");
                }
            }
        }
    }

    void clearForm() {
        Users nv = new Users();
        this.setForm(nv);
        this.row = -1;
        this.updateStatus();
    }

    void edit() {
        String manv = (String) tblnguoidung.getValueAt(this.row, 0);
        Users nv = dao.selectById(manv);
        this.setForm(nv);
        tabs.setSelectedIndex(0);
        this.updateStatus();
    }

    void setForm(Users nv) {
        txtusername.setText(nv.getUsername());    
        txthoten.setText(nv.getHoTen());  
        txtMaNV.setText(nv.getMaNV());
        txtpassword.setText(nv.getMatKhau());
        txtEmail.setText(nv.getEmail());
        txtSDT.setText(nv.getSodt());
        rdoquanly.setSelected(nv.getVaiTro());
        rdonhanvien.setSelected(!nv.getVaiTro());
       if (nv.getHinh() != null) {
            lblHinh.setIcon(XImages.readHinhNV(nv.getHinh()));
            lblHinh.setToolTipText(nv.getHinh());
        }
        
    }

    Users getForm() {
        Users nv = new Users();
        nv.setUsername(txtusername.getText());
        nv.setHoTen(txthoten.getText());      
        nv.setMaNV(txtMaNV.getText());
        nv.setMatKhau(new String(txtpassword.getPassword()));
        nv.setEmail(txtEmail.getText());
        nv.setSodt(txtSDT.getText());
        nv.setVaiTro(rdoquanly.isSelected());
        nv.setHinh(lblHinh.getToolTipText());
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
        if (this.row < tblnguoidung.getRowCount() - 1) {
            this.row++;
            this.edit();
        }
    }

    void last() {
        this.row = tblnguoidung.getRowCount() - 1;
        this.edit();
    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblnguoidung.getRowCount() - 1);
        // Trạng thái form
        txtusername.setEnabled(!edit);
        btnThem.setEnabled(!edit);
        btnSua.setEnabled(edit);
        btnXoa.setEnabled(edit);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        brgvaitro = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        pnl1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        pnl2 = new javax.swing.JPanel();
        pnl3 = new javax.swing.JPanel();
        lblhoten = new javax.swing.JLabel();
        lbluesrname = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        lblsodt = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        lblvaitro = new javax.swing.JLabel();
        rdoquanly = new javax.swing.JRadioButton();
        rdonhanvien = new javax.swing.JRadioButton();
        txtusername = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        pnl10 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lbluesrname1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        pnlbtn = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lblHinh = new javax.swing.JLabel();
        pnl5 = new javax.swing.JPanel();
        pnl6 = new javax.swing.JPanel();
        pnl7 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        pnl8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnguoidung = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabs.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        pnl2.setLayout(new java.awt.BorderLayout());

        pnl3.setBackground(new java.awt.Color(255, 255, 255));

        lblhoten.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblhoten.setText("Họ & tên:");

        lbluesrname.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lbluesrname.setText("Username:");

        lblpassword.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblpassword.setText("Password:");

        lblsodt.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblsodt.setText("Số điện thoại:");

        lblemail.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblemail.setText("Email:");

        lblvaitro.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblvaitro.setText("Vai trò:");

        rdoquanly.setBackground(new java.awt.Color(255, 255, 255));
        brgvaitro.add(rdoquanly);
        rdoquanly.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdoquanly.setText("Quản lý");

        rdonhanvien.setBackground(new java.awt.Color(255, 255, 255));
        brgvaitro.add(rdonhanvien);
        rdonhanvien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rdonhanvien.setSelected(true);
        rdonhanvien.setText("Nhân viên");

        txtusername.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txthoten.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        txtpassword.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        pnl10.setLayout(new java.awt.GridLayout(1, 0));

        btnFirst.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/First.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnl10.add(btnFirst);

        btnPrev.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Prev.png"))); // NOI18N
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        pnl10.add(btnPrev);

        btnNext.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Next.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnl10.add(btnNext);

        btnLast.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/source/Images/Last.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnl10.add(btnLast);

        lbluesrname1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lbluesrname1.setText("Mã NV: ");

        txtMaNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        pnlbtn.setBackground(new java.awt.Color(255, 255, 255));
        pnlbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnThem.setText("Add New");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnSua.setText("Updete");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnMoi.setText("Reset");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        btnXoa.setText("Delete");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlbtnLayout = new javax.swing.GroupLayout(pnlbtn);
        pnlbtn.setLayout(pnlbtnLayout);
        pnlbtnLayout.setHorizontalGroup(
            pnlbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlbtnLayout.setVerticalGroup(
            pnlbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlbtnLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lbluesrname, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214)
                .addComponent(lbluesrname1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblhoten)
                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpassword)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblemail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblsodt, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblvaitro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addComponent(rdoquanly)
                        .addGap(4, 4, 4)
                        .addComponent(rdonhanvien))))
            .addComponent(pnlbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnl10, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbluesrname)
                    .addComponent(lbluesrname1))
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addComponent(lblhoten)
                        .addGap(1, 1, 1)
                        .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblpassword)
                        .addGap(0, 0, 0)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblemail)
                        .addGap(0, 0, 0)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblsodt)
                        .addGap(12, 12, 12)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addComponent(lblvaitro)
                        .addGap(3, 3, 3)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoquanly)
                            .addComponent(rdonhanvien))))
                .addGap(38, 38, 38)
                .addComponent(pnlbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(pnl10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl2.add(pnl3, java.awt.BorderLayout.CENTER);

        tabs.addTab("                Cập nhật                ", pnl2);

        pnl6.setBackground(new java.awt.Color(255, 255, 255));
        pnl6.setLayout(new java.awt.BorderLayout());

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

        tblnguoidung.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblnguoidung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Họ tên", "MaNV", "Password", "Email", "Số ĐT", "Vai Trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblnguoidung.setGridColor(new java.awt.Color(255, 255, 255));
        tblnguoidung.setRowHeight(25);
        tblnguoidung.setSelectionBackground(new java.awt.Color(153, 153, 153));
        tblnguoidung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnguoidungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnguoidung);
        if (tblnguoidung.getColumnModel().getColumnCount() > 0) {
            tblnguoidung.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblnguoidung.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblnguoidung.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblnguoidung.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblnguoidung.getColumnModel().getColumn(6).setPreferredWidth(0);
        }

        javax.swing.GroupLayout pnl8Layout = new javax.swing.GroupLayout(pnl8);
        pnl8.setLayout(pnl8Layout);
        pnl8Layout.setHorizontalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );
        pnl8Layout.setVerticalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnl7Layout = new javax.swing.GroupLayout(pnl7);
        pnl7.setLayout(pnl7Layout);
        pnl7Layout.setHorizontalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem)
                .addContainerGap())
            .addComponent(pnl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl7Layout.setVerticalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl6.add(pnl7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnl5Layout = new javax.swing.GroupLayout(pnl5);
        pnl5.setLayout(pnl5Layout);
        pnl5Layout.setHorizontalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl5Layout.setVerticalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabs.addTab("             Danh sách người dùng            ", pnl5);

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addComponent(tabs)
                .addGap(0, 0, 0))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnl1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblnguoidungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnguoidungMouseClicked
        // TODO add your handling code here:
        try {
            if (evt.getClickCount() == 2) {
            this.row = tblnguoidung.getSelectedRow();
            this.edit();
        }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblnguoidungMouseClicked

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
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        fillTable();
//loadData_ToTable();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

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
            java.util.logging.Logger.getLogger(QLNguoiDungJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNguoiDungJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNguoiDungJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNguoiDungJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLNguoiDungJDialog dialog = new QLNguoiDungJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup brgvaitro;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblhoten;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblsodt;
    private javax.swing.JLabel lbluesrname;
    private javax.swing.JLabel lbluesrname1;
    private javax.swing.JLabel lblvaitro;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl10;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl8;
    private javax.swing.JPanel pnlbtn;
    private javax.swing.JRadioButton rdonhanvien;
    private javax.swing.JRadioButton rdoquanly;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblnguoidung;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txthoten;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
