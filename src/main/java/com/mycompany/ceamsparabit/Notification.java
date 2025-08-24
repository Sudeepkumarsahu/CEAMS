package com.mycompany.ceamsparabit;

import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Notification extends javax.swing.JFrame implements Runnable {

    DefaultTableModel tb1model;
    ParabitCeamsDb obdb1;
    Thread th;
    private volatile boolean isRunning = true;

    public Notification() {
        initComponents();
        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());

        th = new Thread(this);
        tableData();
    }

    public void tableData() {
        th.start();
        tb1model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //All cells are non-editable
            }
        };
        jTable1.setModel(tb1model);

        obdb1 = new ParabitCeamsDb();
        tb1model = (DefaultTableModel) jTable1.getModel();

        // Clear the table
        if (tb1model != null) {
            tb1model.setRowCount(0);
            tb1model.setColumnCount(0);
        }
        try {
            String s = "select LRID, NotificationText from landprofile where NotificationText is not null";
            System.out.println("Executing query: " + s);
            obdb1.rs = obdb1.stm.executeQuery(s);
            tb1model.addColumn("Land Registration ID");
            tb1model.addColumn("remark");
            while (obdb1.rs.next()) {
//                String msg = obdb1.rs.getString(1);
//                JOptionPane.showMessageDialog(null, msg);
                String tbData[] = {
                    obdb1.rs.getString(1), obdb1.rs.getString(2)
                };
                tb1model.addRow(tbData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LandInspector.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = jTable1.rowAtPoint(e.getPoint()); // Get row at mouse click point
                    int column = jTable1.columnAtPoint(e.getPoint()); // Get column at mouse click point

                    if (row != -1 && column != -1) { // Check if the click is within a valid cell

                        jTable1.setRowSelectionInterval(row, row); // Select the row at click point
                        jTable1.setColumnSelectionInterval(column, column); // Optional: select column
                        jPopupMenu1.show(jTable1, e.getX(), e.getY()); // Show popup menu at clicked location

                    }
                }

            }
        });
        isRunning = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (isRunning) {
                    tableData();
                    Thread.sleep(200);
                }
            } catch (Exception e) {
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("update details");
        jCheckBoxMenuItem1.setToolTipText("");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("send to approval");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jCheckBoxMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        Object lrid = null;
        if (jTable1.getSelectedRow() != -1) {
            int selectedRow = jTable1.getSelectedRow();
            lrid = jTable1.getValueAt(selectedRow, 0);
            this.setVisible(false);
//            NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1, jTable1, jCheckBoxMenuItem2);
                   NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1, jTable1);

            nlr.setVisible(true);
        }

    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        //        try {
            //            Object lrid;
            //
            //                int selectedRow = jTable1.getSelectedRow();
            //                lrid = jTable1.getValueAt(selectedRow, 0); // Replace 0 with the column index of LRID
            //                String s = "update landprofile set Status = 'Approved Land' where landprofile.LRID = " + lrid;
            //                System.out.println(s);
            //                obdb1.stm.executeUpdate(s);
            ////                JOptionPane.showMessageDialog(null, "Done");
            ////                String sp = "delete from notificationtext where LRID = " + lrid;
            ////                System.out.println(sp);
            ////                obdb1.stm.executeUpdate(sp);
            //
            //        } catch (Exception e) {
            //        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed
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
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
