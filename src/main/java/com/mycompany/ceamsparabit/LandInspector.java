package com.mycompany.ceamsparabit;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import static java.lang.String.valueOf;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
public class LandInspector extends javax.swing.JFrame implements Runnable{

    ParabitCeamsDb obdb, obdb1;
    String s1, s2, s3, s;
    int wc;
    Thread th;
    public volatile boolean isRunning = true;

    public LandInspector() {
        initComponents();
        th = new Thread(this);
        obdb = new ParabitCeamsDb();
        obdb1 = new ParabitCeamsDb();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
        th.start();
 JComponent[] components = {b2,b3,b4,b5,b6,b7,b8,jButton1,jButton2,jButton3,jButton4};
        for (JComponent comp : components) {
            comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

    
    }
    @Override
     public void run() {
        while (true) {
            try {
                if (isRunning) {
                   countTextSNo();
                    Thread.sleep(200);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

public void wardcode(){
    th.start();
        try {
            String ss = "select WardCode from nnemprecords where NNEID = " + ParabitLogin.srs.getInt(1);
            System.out.println(ss);
            obdb.rs = obdb.stm.executeQuery(ss);
            if (obdb.rs.next()) {
                wc = obdb.rs.getInt(1);
                System.out.println(wc);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        countTextSNo();
        isRunning = false;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        b8.setText("search");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        b2.setText("New Land Registration");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setText("Inspection");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setText(" Profile");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setText("Activity");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setText("Official Notification");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setText("Registered Land");
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        jLabel1.setText("Land inspector");

        jLabel2.setText("jLabel2");

        jButton2.setText("land details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Notification:");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Land Approval");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(b5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(b6))
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b7)
                    .addComponent(jButton4))
                .addContainerGap(156, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void countTextSNo() {
        try {
            String s = "SELECT COUNT(NotificationText) FROM landprofile where NotificationText is not NULL and ZoneCode ="+ParabitLogin.srs.getInt(5);
            System.out.println("Executing query: " + s);
            obdb1.rs = obdb1.stm.executeQuery(s);
            if (obdb1.rs.next()) {
                long count = obdb1.rs.getLong(1);
                System.out.println("Count is " + count);
                jLabel3.setText(valueOf(count));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LandInspector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
//        this.setVisible(false);
        Object lrid = 0;
//        NewLandRegistration nlr = new NewLandRegistration(this, false, lrid, null, null, null);
        NewLandRegistration nlr = new NewLandRegistration(this, false, lrid);
        nlr.setVisible(true);
    }//GEN-LAST:event_b2ActionPerformed
    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
    }//GEN-LAST:event_b5ActionPerformed
    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
    }//GEN-LAST:event_b6ActionPerformed
    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
    }//GEN-LAST:event_b7ActionPerformed
    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
    }//GEN-LAST:event_b3ActionPerformed
    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        this.setVisible(false);
        Profile p = new Profile();
        p.setVisible(true);
    }//GEN-LAST:event_b4ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String sql1 = "";
//        this.setVisible(false);
        try {
            sql1 = "select lp.LRID,lp.LandInspectorVerified,lp.City,lp.VillageCode,zw.WardName,"
                    + "lp.ColonySocietyName,lp.Address,lp.LandMark,lp.AreaSqft,"
                    + "shy.ShapeName,ay.MarkAreaType,lp.CenterCoOrdinate,"
                    + "lp.Radius,lp.LL1,lp.LMarkDate,lp.LMarkTime,ly.TypeOfLand,"
                    + "sy.SoilType,wa.Resource,lp.PolutionIndex,p.TypeOfPollution,"
                    + "lp.Status,lp.SImg from landprofile lp, zoneward zw, shapetype shy"
                    + ",areatype ay, landtype ly, typesofsoil sy, waterresources wa, pollution p"
                    + " where lp.Shape = shy.SNo and lp.Areatype = ay.SNo and lp.LandType = ly.Sno"
                    + " and lp.SoilType = sy.SNo and lp.Wavialable = wa.SNo and lp.PolutionType = p.SNo"
                    + " and lp.StateCode = zw.StateCode and lp.DistrictCode = zw.DistrictCode and "
                    + "lp.ZoneCode = zw.zone and lp.WardCode = zw.WardCode "
                    + " and lp.WardCode = " + ParabitLogin.srs.getInt(6)
                    + " and lp.ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and lp.StateCode = " + ParabitLogin.srs.getInt(2)
                    + " and lp.DistrictCode = " + ParabitLogin.srs.getInt(3)
                    + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4);

        } catch (Exception t) {
            System.out.println(t);
        }
        LandDetails al = new LandDetails(this, false, sql1,jButton2);
        al.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        
        this.setVisible(false);
        Notification n = new Notification();
        n.setVisible(true);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

 String sql1 = "";
//        this.setVisible(false);
        try {
            sql1 = "select lp.LRID,lp.LandInspectorVerified,lp.City,lp.VillageCode,zw.WardName,"
                    + "lp.ColonySocietyName,lp.Address,lp.LandMark,lp.AreaSqft,"
                    + "shy.ShapeName,ay.MarkAreaType,lp.CenterCoOrdinate,"
                    + "lp.Radius,lp.LL1,lp.LMarkDate,lp.LMarkTime,ly.TypeOfLand,"
                    + "sy.SoilType,wa.Resource,lp.PolutionIndex,p.TypeOfPollution,"
                    + "lp.Status,lp.SImg from landprofile lp, zoneward zw, shapetype shy"
                    + ",areatype ay, landtype ly, typesofsoil sy, waterresources wa, pollution p"
                    + " where lp.Shape = shy.SNo and lp.Areatype = ay.SNo and lp.LandType = ly.Sno"
                    + " and lp.SoilType = sy.SNo and lp.Wavialable = wa.SNo and lp.PolutionType = p.SNo"
                    + " and lp.StateCode = zw.StateCode and lp.DistrictCode = zw.DistrictCode and "
                    + "lp.ZoneCode = zw.zone and lp.WardCode = zw.WardCode "
                    + " and lp.WardCode = " + ParabitLogin.srs.getInt(6)
                    + " and lp.ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and lp.StateCode = " + ParabitLogin.srs.getInt(2)
                    + " and lp.DistrictCode = " + ParabitLogin.srs.getInt(3)
                    + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4);

        } catch (Exception t) {
            System.out.println(t);
        }
        LandDetails al = new LandDetails(this, false, sql1,jButton4);
        al.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LandInspector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel l1;
    // End of variables declaration//GEN-END:variables
}
