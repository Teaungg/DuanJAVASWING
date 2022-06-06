/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.source.view;

import com.source.dao.ChamCongDAO;
import com.source.dao.NhanVienDAO;
import com.source.dao.NhanVienDAO;
import com.source.model.ChamCong;
import com.source.model.KhachHang;
import com.source.model.NhanVien;
import com.source.utils.MsgBox;
import com.source.utils.XDate;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Teaung
 */
public class ChamCOng extends javax.swing.JFrame {
    ChamCongDAO ccdao = new ChamCongDAO();
    NhanVienDAO nvdao = new NhanVienDAO();

    /**
     * Creates new form ChamCOng
     */
    public ChamCOng() {
        initComponents();
            this.fillCboNhanVien();
        this.setLocationRelativeTo(null);
    }
    
void setForm(ChamCong cc){ 
        rdoDilam1.setSelected(cc.getNgay1());
        rdoNghi1.setSelected(!cc.getNgay1());
        
        rdoDilam2.setSelected(cc.getNgay2());
        rdoNghi2.setSelected(!cc.getNgay2());
        
        rdoDilam3.setSelected(cc.getNgay3());
        rdoNghi3.setSelected(!cc.getNgay3());
        
       rdoDilam4.setSelected(cc.getNgay4());
        rdoNghi4.setSelected(!cc.getNgay4());
        
       rdoDilam5.setSelected(cc.getNgay5());
        rdoNghi5.setSelected(!cc.getNgay5());
        
       rdoDilam6.setSelected(cc.getNgay6());
        rdoNghi6.setSelected(!cc.getNgay6());
        
        rdoDilam7.setSelected(cc.getNgay7());
        rdoNghi7.setSelected(!cc.getNgay7());
        
       rdoDilam8.setSelected(cc.getNgay8());
        rdoNghi8.setSelected(!cc.getNgay8());
        
       rdoDilam9.setSelected(cc.getNgay9());
        rdoNghi9.setSelected(!cc.getNgay9());
        
        rdoDilam10.setSelected(cc.getNgay10());
        rdoNghi10.setSelected(!cc.getNgay10());
        
       rdoDilam11.setSelected(cc.getNgay11());
        rdoNghi11.setSelected(!cc.getNgay11());
        
       rdoDilam12.setSelected(cc.getNgay12());
        rdoNghi12.setSelected(!cc.getNgay12());
        
        rdoDilam13.setSelected(cc.getNgay13());
        rdoNghi13.setSelected(!cc.getNgay13());
        
       rdoDilam14.setSelected(cc.getNgay14());
        rdoNghi14.setSelected(!cc.getNgay14());
        
       rdoDilam15.setSelected(cc.getNgay15());
        rdoNghi15.setSelected(!cc.getNgay15());
        
        rdoDilam16.setSelected(cc.getNgay16());
        rdoNghi16.setSelected(!cc.getNgay16());
        
       rdoDilam17.setSelected(cc.getNgay17());
        rdoNghi17.setSelected(!cc.getNgay17());
        
       rdoDilam18.setSelected(cc.getNgay18());
        rdoNghi18.setSelected(!cc.getNgay18());
        
        rdoDilam19.setSelected(cc.getNgay19());
        rdoNghi19.setSelected(!cc.getNgay19());
        
       rdoDilam20.setSelected(cc.getNgay20());
        rdoNghi20.setSelected(!cc.getNgay20());
        
       rdoDilam21.setSelected(cc.getNgay21());
        rdoNghi21.setSelected(!cc.getNgay21());
        
           rdoDilam22.setSelected(cc.getNgay22());
        rdoNghi22.setSelected(!cc.getNgay22());
        
       rdoDilam23.setSelected(cc.getNgay23());
        rdoNghi23.setSelected(!cc.getNgay23());
        
        rdoDilam24.setSelected(cc.getNgay24());
        rdoNghi24.setSelected(!cc.getNgay24());
        
       rdoDilam25.setSelected(cc.getNgay25());
        rdoNghi25.setSelected(!cc.getNgay25());
        
       rdoDilam26.setSelected(cc.getNgay26());
        rdoNghi26.setSelected(!cc.getNgay26());
        
          rdoNghi27.setSelected(!cc.getNgay27());
        rdoDilam27.setSelected(cc.getNgay27());
        
        rdoNghi28.setSelected(!cc.getNgay28());
       rdoDilam28.setSelected(cc.getNgay28());
       
        rdoNghi29.setSelected(!cc.getNgay29());
       rdoDilam29.setSelected(cc.getNgay29());
       
        rdoNghi30.setSelected(!cc.getNgay30());
        rdoDilam30.setSelected(cc.getNgay30());
         rdoNghi31.setSelected(!cc.getNgay31());
        rdoDilam31.setSelected(cc.getNgay31());
        
        
        
        
        
      
         
}
 ChamCong getForm() { // tạo NhanVien từ form
        ChamCong cc = new ChamCong();

       
     cc.setHoTen(cbxHoTen.getSelectedItem().toString());
     cc.setNgay1(Boolean.valueOf(rdoDilam1.isSelected() ? "" : ""));
     cc.setNgay2(Boolean.valueOf(rdoDilam2.isSelected() ? "" : ""));
     cc.setNgay3(Boolean.valueOf(rdoDilam3.isSelected() ? "" : ""));
     cc.setNgay4(Boolean.valueOf(rdoDilam4.isSelected() ? "" : ""));
     cc.setNgay5(Boolean.valueOf(rdoDilam5.isSelected() ? "" : ""));
     cc.setNgay6(Boolean.valueOf(rdoDilam6.isSelected() ? "" : ""));
     cc.setNgay7(Boolean.valueOf(rdoDilam7.isSelected() ? "" : ""));
     cc.setNgay8(Boolean.valueOf(rdoDilam8.isSelected() ? "" : ""));
     cc.setNgay9(Boolean.valueOf(rdoDilam9.isSelected() ? "" : ""));
     cc.setNgay10(Boolean.valueOf(rdoDilam10.isSelected() ? "" : ""));
     cc.setNgay11(Boolean.valueOf(rdoDilam11.isSelected() ? "" : ""));
     cc.setNgay12(Boolean.valueOf(rdoDilam12.isSelected() ? "" : ""));
     cc.setNgay13(Boolean.valueOf(rdoDilam13.isSelected() ? "" : ""));
     cc.setNgay14(Boolean.valueOf(rdoDilam14.isSelected() ? "" : ""));
     cc.setNgay15(Boolean.valueOf(rdoDilam15.isSelected() ? "" : ""));
     cc.setNgay16(Boolean.valueOf(rdoDilam16.isSelected() ? "" : ""));
     cc.setNgay17(Boolean.valueOf(rdoDilam17.isSelected() ? "" : ""));
     cc.setNgay18(Boolean.valueOf(rdoDilam18.isSelected() ? "" : ""));
     cc.setNgay19(Boolean.valueOf(rdoDilam19.isSelected() ? "" : ""));
     cc.setNgay20(Boolean.valueOf(rdoDilam20.isSelected() ? "" : ""));
     cc.setNgay21(Boolean.valueOf(rdoDilam21.isSelected() ? "" : ""));
     cc.setNgay22(Boolean.valueOf(rdoDilam22.isSelected() ? "" : ""));
     cc.setNgay23(Boolean.valueOf(rdoDilam23.isSelected() ? "" : ""));
     cc.setNgay24(Boolean.valueOf(rdoDilam24.isSelected() ? "" : ""));
     cc.setNgay25(Boolean.valueOf(rdoDilam25.isSelected() ? "" : ""));
     cc.setNgay26(Boolean.valueOf(rdoDilam26.isSelected() ? "" : ""));
     cc.setNgay27(Boolean.valueOf(rdoDilam27.isSelected() ? "" : ""));
     cc.setNgay28(Boolean.valueOf(rdoDilam28.isSelected() ? "" : ""));
     cc.setNgay29(Boolean.valueOf(rdoDilam29.isSelected() ? "" : ""));
     cc.setNgay30(Boolean.valueOf(rdoDilam30.isSelected() ? "" : ""));
     cc.setNgay31(Boolean.valueOf(rdoDilam31.isSelected() ? "" : ""));
   


             
       

        return cc;
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
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
        buttonGroup13 = new javax.swing.ButtonGroup();
        buttonGroup14 = new javax.swing.ButtonGroup();
        buttonGroup15 = new javax.swing.ButtonGroup();
        buttonGroup16 = new javax.swing.ButtonGroup();
        buttonGroup17 = new javax.swing.ButtonGroup();
        buttonGroup18 = new javax.swing.ButtonGroup();
        buttonGroup19 = new javax.swing.ButtonGroup();
        buttonGroup20 = new javax.swing.ButtonGroup();
        buttonGroup21 = new javax.swing.ButtonGroup();
        buttonGroup22 = new javax.swing.ButtonGroup();
        buttonGroup23 = new javax.swing.ButtonGroup();
        buttonGroup24 = new javax.swing.ButtonGroup();
        buttonGroup25 = new javax.swing.ButtonGroup();
        buttonGroup26 = new javax.swing.ButtonGroup();
        buttonGroup27 = new javax.swing.ButtonGroup();
        buttonGroup28 = new javax.swing.ButtonGroup();
        buttonGroup29 = new javax.swing.ButtonGroup();
        buttonGroup30 = new javax.swing.ButtonGroup();
        buttonGroup31 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxHoTen = new javax.swing.JComboBox<>();
        cboThang = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jButton76 = new javax.swing.JButton();
        jButton77 = new javax.swing.JButton();
        jButton78 = new javax.swing.JButton();
        jButton79 = new javax.swing.JButton();
        jButton80 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        rdoDilam1 = new javax.swing.JRadioButton();
        rdoDilam2 = new javax.swing.JRadioButton();
        rdoDilam3 = new javax.swing.JRadioButton();
        rdoDilam4 = new javax.swing.JRadioButton();
        rdoDilam5 = new javax.swing.JRadioButton();
        rdoDilam6 = new javax.swing.JRadioButton();
        rdoDilam7 = new javax.swing.JRadioButton();
        rdoDilam8 = new javax.swing.JRadioButton();
        rdoDilam9 = new javax.swing.JRadioButton();
        rdoDilam10 = new javax.swing.JRadioButton();
        rdoDilam11 = new javax.swing.JRadioButton();
        rdoDilam12 = new javax.swing.JRadioButton();
        rdoDilam13 = new javax.swing.JRadioButton();
        rdoDilam14 = new javax.swing.JRadioButton();
        rdoDilam15 = new javax.swing.JRadioButton();
        rdoDilam16 = new javax.swing.JRadioButton();
        rdoDilam17 = new javax.swing.JRadioButton();
        rdoDilam18 = new javax.swing.JRadioButton();
        rdoDilam19 = new javax.swing.JRadioButton();
        rdoDilam20 = new javax.swing.JRadioButton();
        rdoDilam21 = new javax.swing.JRadioButton();
        rdoDilam22 = new javax.swing.JRadioButton();
        rdoDilam23 = new javax.swing.JRadioButton();
        rdoDilam24 = new javax.swing.JRadioButton();
        rdoDilam25 = new javax.swing.JRadioButton();
        rdoDilam26 = new javax.swing.JRadioButton();
        rdoDilam27 = new javax.swing.JRadioButton();
        rdoDilam28 = new javax.swing.JRadioButton();
        rdoDilam29 = new javax.swing.JRadioButton();
        rdoDilam30 = new javax.swing.JRadioButton();
        rdoDilam31 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        rdoNghi1 = new javax.swing.JRadioButton();
        rdoNghi2 = new javax.swing.JRadioButton();
        rdoNghi3 = new javax.swing.JRadioButton();
        rdoNghi4 = new javax.swing.JRadioButton();
        rdoNghi5 = new javax.swing.JRadioButton();
        rdoNghi6 = new javax.swing.JRadioButton();
        rdoNghi7 = new javax.swing.JRadioButton();
        rdoNghi8 = new javax.swing.JRadioButton();
        rdoNghi9 = new javax.swing.JRadioButton();
        rdoNghi10 = new javax.swing.JRadioButton();
        rdoNghi11 = new javax.swing.JRadioButton();
        rdoNghi12 = new javax.swing.JRadioButton();
        rdoNghi13 = new javax.swing.JRadioButton();
        rdoNghi14 = new javax.swing.JRadioButton();
        rdoNghi15 = new javax.swing.JRadioButton();
        rdoNghi16 = new javax.swing.JRadioButton();
        rdoNghi17 = new javax.swing.JRadioButton();
        rdoNghi18 = new javax.swing.JRadioButton();
        rdoNghi19 = new javax.swing.JRadioButton();
        rdoNghi20 = new javax.swing.JRadioButton();
        rdoNghi21 = new javax.swing.JRadioButton();
        rdoNghi22 = new javax.swing.JRadioButton();
        rdoNghi23 = new javax.swing.JRadioButton();
        rdoNghi24 = new javax.swing.JRadioButton();
        rdoNghi25 = new javax.swing.JRadioButton();
        rdoNghi26 = new javax.swing.JRadioButton();
        rdoNghi27 = new javax.swing.JRadioButton();
        rdoNghi28 = new javax.swing.JRadioButton();
        rdoNghi29 = new javax.swing.JRadioButton();
        rdoNghi30 = new javax.swing.JRadioButton();
        rdoNghi31 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Chấm Công Nhân Viên");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 242, 37));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tháng");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 70, 63, 24));

        getContentPane().add(cbxHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 263, 30));

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cboThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangActionPerformed(evt);
            }
        });
        getContentPane().add(cboThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 310, 35));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Họ tên NV:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 71, 103, 24));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBox1.setText("jCheckBox1");
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 220, 20, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 255, 153));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 72, 1560, 10));

        jPanel4.setLayout(new java.awt.GridLayout(1, 31));

        jButton50.setText("1");
        jPanel4.add(jButton50);

        jButton52.setText("2");
        jPanel4.add(jButton52);

        jButton53.setText("3");
        jPanel4.add(jButton53);

        jButton54.setText("4");
        jPanel4.add(jButton54);

        jButton55.setText("5");
        jPanel4.add(jButton55);

        jButton56.setText("6");
        jPanel4.add(jButton56);

        jButton57.setText("7");
        jPanel4.add(jButton57);

        jButton58.setText("8");
        jPanel4.add(jButton58);

        jButton59.setText("9");
        jPanel4.add(jButton59);

        jButton60.setText("10");
        jPanel4.add(jButton60);

        jButton61.setText("11");
        jPanel4.add(jButton61);

        jButton62.setText("12");
        jPanel4.add(jButton62);

        jButton63.setText("13");
        jPanel4.add(jButton63);

        jButton64.setText("14");
        jPanel4.add(jButton64);

        jButton65.setText("15");
        jPanel4.add(jButton65);

        jButton66.setText("16");
        jPanel4.add(jButton66);

        jButton67.setText("17");
        jPanel4.add(jButton67);

        jButton51.setText("18");
        jPanel4.add(jButton51);

        jButton68.setText("19");
        jPanel4.add(jButton68);

        jButton69.setText("20");
        jPanel4.add(jButton69);

        jButton70.setText("21");
        jPanel4.add(jButton70);

        jButton71.setText("22");
        jPanel4.add(jButton71);

        jButton72.setText("23");
        jPanel4.add(jButton72);

        jButton73.setText("24");
        jPanel4.add(jButton73);

        jButton74.setText("25");
        jPanel4.add(jButton74);

        jButton75.setText("26");
        jPanel4.add(jButton75);

        jButton76.setText("27");
        jPanel4.add(jButton76);

        jButton77.setText("28");
        jPanel4.add(jButton77);

        jButton78.setText("29");
        jPanel4.add(jButton78);

        jButton79.setText("30");
        jPanel4.add(jButton79);

        jButton80.setText("31");
        jPanel4.add(jButton80);

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 1440, 40));

        jLabel4.setText("Nghỉ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 60, -1));

        jLabel5.setText("Đi Làm");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, -1));

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));
        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, -1, -1));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroup1.add(rdoDilam1);
        jPanel7.add(rdoDilam1);

        buttonGroup2.add(rdoDilam2);
        jPanel7.add(rdoDilam2);

        buttonGroup3.add(rdoDilam3);
        jPanel7.add(rdoDilam3);

        buttonGroup4.add(rdoDilam4);
        jPanel7.add(rdoDilam4);

        buttonGroup5.add(rdoDilam5);
        jPanel7.add(rdoDilam5);

        buttonGroup6.add(rdoDilam6);
        jPanel7.add(rdoDilam6);

        buttonGroup7.add(rdoDilam7);
        jPanel7.add(rdoDilam7);

        buttonGroup8.add(rdoDilam8);
        jPanel7.add(rdoDilam8);

        buttonGroup9.add(rdoDilam9);
        jPanel7.add(rdoDilam9);

        buttonGroup10.add(rdoDilam10);
        jPanel7.add(rdoDilam10);

        buttonGroup11.add(rdoDilam11);
        jPanel7.add(rdoDilam11);

        buttonGroup12.add(rdoDilam12);
        jPanel7.add(rdoDilam12);

        buttonGroup13.add(rdoDilam13);
        jPanel7.add(rdoDilam13);

        buttonGroup14.add(rdoDilam14);
        jPanel7.add(rdoDilam14);

        buttonGroup15.add(rdoDilam15);
        jPanel7.add(rdoDilam15);

        buttonGroup16.add(rdoDilam16);
        jPanel7.add(rdoDilam16);

        buttonGroup17.add(rdoDilam17);
        jPanel7.add(rdoDilam17);

        buttonGroup18.add(rdoDilam18);
        jPanel7.add(rdoDilam18);

        buttonGroup19.add(rdoDilam19);
        jPanel7.add(rdoDilam19);

        buttonGroup20.add(rdoDilam20);
        jPanel7.add(rdoDilam20);

        buttonGroup21.add(rdoDilam21);
        jPanel7.add(rdoDilam21);

        buttonGroup22.add(rdoDilam22);
        jPanel7.add(rdoDilam22);

        buttonGroup23.add(rdoDilam23);
        jPanel7.add(rdoDilam23);

        buttonGroup24.add(rdoDilam24);
        jPanel7.add(rdoDilam24);

        buttonGroup25.add(rdoDilam25);
        jPanel7.add(rdoDilam25);

        buttonGroup26.add(rdoDilam26);
        jPanel7.add(rdoDilam26);

        buttonGroup27.add(rdoDilam27);
        jPanel7.add(rdoDilam27);

        buttonGroup28.add(rdoDilam28);
        jPanel7.add(rdoDilam28);

        buttonGroup29.add(rdoDilam29);
        jPanel7.add(rdoDilam29);

        buttonGroup30.add(rdoDilam30);
        jPanel7.add(rdoDilam30);

        buttonGroup31.add(rdoDilam31);
        jPanel7.add(rdoDilam31);

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 1440, 30));

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroup1.add(rdoNghi1);
        rdoNghi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNghi1ActionPerformed(evt);
            }
        });
        jPanel8.add(rdoNghi1);

        buttonGroup2.add(rdoNghi2);
        jPanel8.add(rdoNghi2);

        buttonGroup3.add(rdoNghi3);
        jPanel8.add(rdoNghi3);

        buttonGroup4.add(rdoNghi4);
        jPanel8.add(rdoNghi4);

        buttonGroup5.add(rdoNghi5);
        jPanel8.add(rdoNghi5);

        buttonGroup6.add(rdoNghi6);
        jPanel8.add(rdoNghi6);

        buttonGroup7.add(rdoNghi7);
        jPanel8.add(rdoNghi7);

        buttonGroup8.add(rdoNghi8);
        jPanel8.add(rdoNghi8);

        buttonGroup9.add(rdoNghi9);
        jPanel8.add(rdoNghi9);

        buttonGroup10.add(rdoNghi10);
        jPanel8.add(rdoNghi10);

        buttonGroup11.add(rdoNghi11);
        jPanel8.add(rdoNghi11);

        buttonGroup12.add(rdoNghi12);
        jPanel8.add(rdoNghi12);

        buttonGroup13.add(rdoNghi13);
        jPanel8.add(rdoNghi13);

        buttonGroup14.add(rdoNghi14);
        jPanel8.add(rdoNghi14);

        buttonGroup15.add(rdoNghi15);
        jPanel8.add(rdoNghi15);

        buttonGroup16.add(rdoNghi16);
        jPanel8.add(rdoNghi16);

        buttonGroup17.add(rdoNghi17);
        jPanel8.add(rdoNghi17);

        buttonGroup18.add(rdoNghi18);
        jPanel8.add(rdoNghi18);

        buttonGroup19.add(rdoNghi19);
        jPanel8.add(rdoNghi19);

        buttonGroup20.add(rdoNghi20);
        jPanel8.add(rdoNghi20);

        buttonGroup21.add(rdoNghi21);
        jPanel8.add(rdoNghi21);

        buttonGroup22.add(rdoNghi22);
        jPanel8.add(rdoNghi22);

        buttonGroup23.add(rdoNghi23);
        jPanel8.add(rdoNghi23);

        buttonGroup24.add(rdoNghi24);
        jPanel8.add(rdoNghi24);

        buttonGroup25.add(rdoNghi25);
        jPanel8.add(rdoNghi25);

        buttonGroup26.add(rdoNghi26);
        jPanel8.add(rdoNghi26);

        buttonGroup27.add(rdoNghi27);
        jPanel8.add(rdoNghi27);

        buttonGroup28.add(rdoNghi28);
        jPanel8.add(rdoNghi28);

        buttonGroup29.add(rdoNghi29);
        jPanel8.add(rdoNghi29);

        buttonGroup30.add(rdoNghi30);
        jPanel8.add(rdoNghi30);

        buttonGroup31.add(rdoNghi31);
        jPanel8.add(rdoNghi31);

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 1440, 30));

        jLabel6.setText("Ngày");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 1580, 170));

        btnLuu.setText("Lưu Chấm Công");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        getContentPane().add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 70, 180, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNghi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNghi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNghi1ActionPerformed

    private void cboThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboThangActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_btnLuuActionPerformed
    void fillCboNhanVien() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxHoTen.getModel();
        model.removeAllElements();
        
            List<NhanVien> list = nvdao.selectAll(); 
        
            for (NhanVien nhanVien : list) {
             model.addElement(nhanVien);
              
                 cbxHoTen.setToolTipText(nhanVien.getHoTen());
                 cbxHoTen.setSelectedItem(nvdao.selectById(nhanVien.getHoTen()));
        }
    }
   void insert() {
       

        ChamCong cc = getForm();

        try {
            ccdao.insert(cc);
          
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
             e.printStackTrace();
        }

    
    }

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
            java.util.logging.Logger.getLogger(ChamCOng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChamCOng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChamCOng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChamCOng.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChamCOng().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup13;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.ButtonGroup buttonGroup15;
    private javax.swing.ButtonGroup buttonGroup16;
    private javax.swing.ButtonGroup buttonGroup17;
    private javax.swing.ButtonGroup buttonGroup18;
    private javax.swing.ButtonGroup buttonGroup19;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup20;
    private javax.swing.ButtonGroup buttonGroup21;
    private javax.swing.ButtonGroup buttonGroup22;
    private javax.swing.ButtonGroup buttonGroup23;
    private javax.swing.ButtonGroup buttonGroup24;
    private javax.swing.ButtonGroup buttonGroup25;
    private javax.swing.ButtonGroup buttonGroup26;
    private javax.swing.ButtonGroup buttonGroup27;
    private javax.swing.ButtonGroup buttonGroup28;
    private javax.swing.ButtonGroup buttonGroup29;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup30;
    private javax.swing.ButtonGroup buttonGroup31;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JComboBox<String> cbxHoTen;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton80;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rdoDilam1;
    private javax.swing.JRadioButton rdoDilam10;
    private javax.swing.JRadioButton rdoDilam11;
    private javax.swing.JRadioButton rdoDilam12;
    private javax.swing.JRadioButton rdoDilam13;
    private javax.swing.JRadioButton rdoDilam14;
    private javax.swing.JRadioButton rdoDilam15;
    private javax.swing.JRadioButton rdoDilam16;
    private javax.swing.JRadioButton rdoDilam17;
    private javax.swing.JRadioButton rdoDilam18;
    private javax.swing.JRadioButton rdoDilam19;
    private javax.swing.JRadioButton rdoDilam2;
    private javax.swing.JRadioButton rdoDilam20;
    private javax.swing.JRadioButton rdoDilam21;
    private javax.swing.JRadioButton rdoDilam22;
    private javax.swing.JRadioButton rdoDilam23;
    private javax.swing.JRadioButton rdoDilam24;
    private javax.swing.JRadioButton rdoDilam25;
    private javax.swing.JRadioButton rdoDilam26;
    private javax.swing.JRadioButton rdoDilam27;
    private javax.swing.JRadioButton rdoDilam28;
    private javax.swing.JRadioButton rdoDilam29;
    private javax.swing.JRadioButton rdoDilam3;
    private javax.swing.JRadioButton rdoDilam30;
    private javax.swing.JRadioButton rdoDilam31;
    private javax.swing.JRadioButton rdoDilam4;
    private javax.swing.JRadioButton rdoDilam5;
    private javax.swing.JRadioButton rdoDilam6;
    private javax.swing.JRadioButton rdoDilam7;
    private javax.swing.JRadioButton rdoDilam8;
    private javax.swing.JRadioButton rdoDilam9;
    private javax.swing.JRadioButton rdoNghi1;
    private javax.swing.JRadioButton rdoNghi10;
    private javax.swing.JRadioButton rdoNghi11;
    private javax.swing.JRadioButton rdoNghi12;
    private javax.swing.JRadioButton rdoNghi13;
    private javax.swing.JRadioButton rdoNghi14;
    private javax.swing.JRadioButton rdoNghi15;
    private javax.swing.JRadioButton rdoNghi16;
    private javax.swing.JRadioButton rdoNghi17;
    private javax.swing.JRadioButton rdoNghi18;
    private javax.swing.JRadioButton rdoNghi19;
    private javax.swing.JRadioButton rdoNghi2;
    private javax.swing.JRadioButton rdoNghi20;
    private javax.swing.JRadioButton rdoNghi21;
    private javax.swing.JRadioButton rdoNghi22;
    private javax.swing.JRadioButton rdoNghi23;
    private javax.swing.JRadioButton rdoNghi24;
    private javax.swing.JRadioButton rdoNghi25;
    private javax.swing.JRadioButton rdoNghi26;
    private javax.swing.JRadioButton rdoNghi27;
    private javax.swing.JRadioButton rdoNghi28;
    private javax.swing.JRadioButton rdoNghi29;
    private javax.swing.JRadioButton rdoNghi3;
    private javax.swing.JRadioButton rdoNghi30;
    private javax.swing.JRadioButton rdoNghi31;
    private javax.swing.JRadioButton rdoNghi4;
    private javax.swing.JRadioButton rdoNghi5;
    private javax.swing.JRadioButton rdoNghi6;
    private javax.swing.JRadioButton rdoNghi7;
    private javax.swing.JRadioButton rdoNghi8;
    private javax.swing.JRadioButton rdoNghi9;
    // End of variables declaration//GEN-END:variables

    

    
}
