package com.mycompany.ceamsparabit;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;

public class ZoneInspector extends javax.swing.JFrame {

    String s;

    public ZoneInspector() {
        initComponents();
//         Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = toolkit.getScreenSize();
//        this.setSize(screenSize.width, screenSize.height);
//        this.setVisible(true);
JComponent[] components = {jButton1,jButton2,jButton3,jButton4,jButton5,jButton6,jButton7};
        for (JComponent comp : components) {
            comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Ward details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Land details");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Device details");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Academic details");

        jButton5.setText("Request");

        jButton6.setText("Profile");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Official Notification");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Zone Inspector");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        DeviceDetail dd = new DeviceDetail(this, false);
        dd.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        try {
//            String s = "SELECT NNEID,WardCode,WardNumber,WardName"
//                    + ",EmpName,WorkPost,OffMobNo,PerMobNo,Status"
//                    + ",DeviceNumber,DeviceStatus FROM nnemprecords "
//                    + "WHERE ZoneCode = " + ParabitLogin.srs.getInt(5)
//                    + " ORDER BY 'nnemprecords,NNEID' ASC";
//            this.setVisible(false);
//            ApprovalLand al = new ApprovalLand(this, false, s);
//            al.setVisible(true);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
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
        Ward wd = new Ward(this, false, s);
        wd.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        try {
            String s = "SELECT LRID ,statecode,districtcode,Villagecode,City,ZoneCode,  WardCode  ,AreaSqft, LmarkDate,LMarkTime,PolutionIndex,Status FROM landprofile where "
                    + "ZoneCode = " + ParabitLogin.srs.getInt(5);
            String sss = "SELECT lp.LRID,lp.LandInspectorVerified,lp.City,lp.Villagecode,zw.WardName,"
                    + "lp.ColonySocietyName,lp.Address,lp.Landmark,lp.AreaSqft,"
                    + "shy.shapename,ay.markareatype,lp.CenterCoOrdinate,lp.Radius"
                    + ",lp.LL1,lp.LmarkDate,lp.LMarkTime,ly.typeofland,sy.soiltype,"
                    + "wa.resource,lp.PolutionIndex,p.TypeOfPollution,lp.Status FROM"
                    + " landprofile lp ,zoneward zw, shapetype shy,areatype ay,landtype"
                    + " ly,typesofsoil sy,waterresources wa, pollution p WHERE lp.Shape"
                    + " = shy.SNo AND lp.AreaType = ay.SNo AND lp.LandType = ly.SNo AND"
                    + " lp.SoilType = sy.SNo AND lp.Wavialable = wa.SNo AND lp.PolutionType"
                    + " = p.SNo AND lp.statecode = zw.StateCode AND lp.districtcode = "
                    + "zw.DistrictCode AND lp.ZoneCode = zw.Zone AND lp.ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " GROUP BY LRID";
            String sql1 = "select lp.LRID,lp.LandInspectorVerified,lp.City,lp.VillageCode,zw.WardName,"
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
                    + " and lp.ZoneCode = " + ParabitLogin.srs.getInt(5)
                    + " and lp.StateCode = " + ParabitLogin.srs.getInt(2)
                    + " and lp.DistrictCode = " + ParabitLogin.srs.getInt(3)
                    + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4);

            System.out.println(sss);
            LandDetails al = new LandDetails(this, false, sql1,null);
            al.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.setVisible(false);
        Profile p = new Profile();
        p.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ZoneInspector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
