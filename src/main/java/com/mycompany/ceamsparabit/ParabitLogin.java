package com.mycompany.ceamsparabit;

import java.awt.Color;
import java.awt.Cursor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComponent;

public class ParabitLogin extends javax.swing.JDialog {

    static ResultSet srs;
    ParabitCeamsDb obdb;
    static String ss;

    public ParabitLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        obdb = new ParabitCeamsDb();  //for connect with DB
        getContentPane().setBackground(new Color(47, 117, 193));
         JComponent[] components = {b1,b2,b3};
        for (JComponent comp : components) {
            comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        tf1 = new javax.swing.JTextField();
        tf3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        tf2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tf1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf1ActionPerformed(evt);
            }
        });

        tf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf3ActionPerformed(evt);
            }
        });

        jLabel1.setText("LoginID");

        jLabel2.setText("Password");

        jLabel3.setText("OTP");

        b1.setText("Resend OTP");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setText("Forgot Password");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setText("Login");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(tf2)
                            .addComponent(tf3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b2)
                        .addGap(18, 18, 18)
                        .addComponent(b1)
                        .addGap(38, 38, 38)
                        .addComponent(b3)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tf2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tf3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b2)
                    .addComponent(b1)
                    .addComponent(b3))
                .addGap(43, 43, 43))
        );

        setSize(new java.awt.Dimension(470, 332));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void tf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf1ActionPerformed
    }//GEN-LAST:event_tf1ActionPerformed
    private void tf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf3ActionPerformed
    }//GEN-LAST:event_tf3ActionPerformed
    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
    }//GEN-LAST:event_b2ActionPerformed
    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed

        String s1 = tf1.getText();
        String s2 = tf2.getText();
        String s3 = tf3.getText();

        // SQL query with placeholders to prevent SQL injection
        String query = "SELECT * FROM nnemprecords WHERE loginid = ? AND LoginPSW = ? AND status = 1";

        try {
            // Prepare the statement
            PreparedStatement pstmt = obdb.con.prepareStatement(query);

            // Set parameters
            pstmt.setString(1, s1);
            pstmt.setString(2, s2);
            System.out.println(query);
            // Execute the query
            obdb.rs = pstmt.executeQuery();
            srs = obdb.rs;
            if (obdb.rs.next()) {
                System.out.println("Record found");
                ss = obdb.rs.getString(13); // column no 13
                System.out.println(ss);

                // Navigate based on the role
                switch (ss) {
                    case "Land Inspector":
                        this.setVisible(false);
                        LandInspector li = new LandInspector();
                        li.setVisible(true);
                        break;
                    case "Zone Municipal Officer":
                        this.setVisible(false);
                        ZoneInspector zc = new ZoneInspector();
                        zc.setVisible(true);
                        break;
                    case "Ward Municipal Officer":
                        this.setVisible(false);
                        WardMO wmo = new WardMO();
                        wmo.setVisible(true);
                        break;
                    case "Municipal Commissioner":
                        this.setVisible(false);
                        MunicipalCommissioner mc = new MunicipalCommissioner();
                        mc.setVisible(true);
                        break;
                    default:
                        System.out.println("Unknown role");
                }
            } else {
                System.out.println("Record not found");
            }
        } catch (Exception e) {
            System.out.print(e);
        }


    }//GEN-LAST:event_b3ActionPerformed
    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
    }//GEN-LAST:event_b1ActionPerformed
    //executeQuery returnable
    //excuteupdate DB ME JAKE run hoga but kuchh return nahi hoga

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
            java.util.logging.Logger.getLogger(Mayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mayer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ParabitLogin dialog = new ParabitLogin(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tf1;
    private javax.swing.JTextField tf2;
    private javax.swing.JTextField tf3;
    // End of variables declaration//GEN-END:variables
}
