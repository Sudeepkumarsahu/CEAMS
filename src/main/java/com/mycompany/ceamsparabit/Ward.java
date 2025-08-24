package com.mycompany.ceamsparabit;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ward extends javax.swing.JDialog {

    ParabitCeamsDb obdb1;
    DefaultTableModel tbmodel;
    String s, ss;
    String wardName, selectedWardName;

    public Ward(java.awt.Frame parent, boolean modal, String s) {
        super(parent, modal);
        initComponents();

        this.s = s;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
//        this.setVisible(true);
        obdb1 = new ParabitCeamsDb();
        table(jTable1);
    }

    public void table(JTable table) {

        try {
            //this is for getting all ward in zone
            String wn = "SELECT DISTINCT WardName FROM nnemprecords WHERE ZoneCode = " + ParabitLogin.srs.getInt(5) + " AND WorkPost != '" + ParabitLogin.ss + "'";
            System.out.println(wn);
            obdb1.rs = obdb1.stm.executeQuery(wn);
            while (obdb1.rs.next()) {
                wardName = obdb1.rs.getString("WardName");
                jComboBox1.addItem(wardName);
            }
            // Add an ActionListener to the JComboBox
            jComboBox1.addActionListener(e -> {
                // Get the selected WardName
                selectedWardName = (String) jComboBox1.getSelectedItem();

                // Open a new frame based on the selected WardName
                if (selectedWardName != null) {
//                    Ward(selectedWardName);
                    this.setVisible(false);
                    try {
                        ss = "SELECT NNEID,WardCode,WardNumber,WardName"
                                + ",EmpName,WorkPost,OffMobNo,PerMobNo,Status"
                                + ",DeviceNumber,DeviceStatus FROM nnemprecords "
                                + "WHERE ZoneCode = " + ParabitLogin.srs.getInt(5) + " and WardName = '" + selectedWardName
                                + "' ORDER BY 'nnemprecords,NNEID' ASC";
                        System.out.println(ss);
                    } catch (Exception ee) {
                        System.out.println(ee);
                    }
                    Ward w = new Ward(null, false, ss);
                    w.setVisible(true);
                }
            });

            obdb1.rs = obdb1.stm.executeQuery(s);
            System.out.println(s);
            // Clear existing table columns and rows
            DefaultTableModel tb1model = (DefaultTableModel) table.getModel();
            tb1model.setRowCount(0);
            tb1model.setColumnCount(0);

            // Manually add column names
            tb1model.addColumn("Nagar Nigam Employee ID");
            tb1model.addColumn("Ward Code");
            tb1model.addColumn("Ward Number");
            tb1model.addColumn("Ward Name");
            tb1model.addColumn("Employee Name");
            tb1model.addColumn("Work Post");
            tb1model.addColumn("Office Mobile Number");
            tb1model.addColumn("Personal Mobile Number");
            tb1model.addColumn("Status");
            tb1model.addColumn("Device Number");
            tb1model.addColumn("Device Status");

            // Get column count based on manually added columns
            int columnCount = tb1model.getColumnCount();

            // Add rows to the table model
            while (obdb1.rs.next()) {
                String[] tbdata = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    tbdata[i - 1] = obdb1.rs.getString(i);
                }
                tb1model.addRow(tbdata);
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Ward Name");

        jRadioButton1.setText("All");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Land Inspector");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Ward Municiple Officer");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Assistant Project Officer");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1811, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(94, 94, 94)
                        .addComponent(jRadioButton2)
                        .addGap(110, 110, 110)
                        .addComponent(jRadioButton3)
                        .addGap(146, 146, 146)
                        .addComponent(jRadioButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addGap(0, 84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        this.setVisible(false);
        try {
            s = "SELECT NNEID,WardCode,WardNumber,WardName"
                    + ",EmpName,WorkPost,OffMobNo,PerMobNo,Status"
                    + ",DeviceNumber,DeviceStatus FROM nnemprecords "
                    + "WHERE ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and WorkPost = 'Ward Municipal Officer' ORDER BY 'nnemprecords,NNEID' ASC";
        } catch (Exception e) {
            System.out.println(e);
        }
        Ward wd = new Ward(null, false, s);
        wd.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        this.setVisible(false);
        try {
            s = "SELECT NNEID,WardCode,WardNumber,WardName"
                    + ",EmpName,WorkPost,OffMobNo,PerMobNo,Status"
                    + ",DeviceNumber,DeviceStatus FROM nnemprecords "
                    + "WHERE ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and WorkPost != 'Zone Municipal Officer' ORDER BY 'nnemprecords,NNEID' ASC";
        } catch (Exception e) {
            System.out.println(e);
        }
        Ward wd = new Ward(null, false, s);
        wd.setVisible(true);

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        this.setVisible(false);
        try {
            s = "SELECT NNEID,WardCode,WardNumber,WardName"
                    + ",EmpName,WorkPost,OffMobNo,PerMobNo,Status"
                    + ",DeviceNumber,DeviceStatus FROM nnemprecords "
                    + "WHERE ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and WorkPost = 'Land Inspector' ORDER BY 'nnemprecords,NNEID' ASC";
        } catch (Exception e) {
            System.out.println(e);
        }
        Ward wd = new Ward(null, false, s);
        wd.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
this.setVisible(false);
        try {
            s = "SELECT NNEID,WardCode,WardNumber,WardName"
                    + ",EmpName,WorkPost,OffMobNo,PerMobNo,Status"
                    + ",DeviceNumber,DeviceStatus FROM nnemprecords "
                    + "WHERE ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and WorkPost = 'Assistant project Officer' ORDER BY 'nnemprecords,NNEID' ASC";
        } catch (Exception e) {
            System.out.println(e);
        }
        Ward wd = new Ward(null, false, s);
        wd.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ward dialog = new Ward(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
