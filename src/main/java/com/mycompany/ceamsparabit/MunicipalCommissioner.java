package com.mycompany.ceamsparabit;

public class MunicipalCommissioner extends javax.swing.JFrame {

    ParabitCeamsDb obdb;

    public MunicipalCommissioner() {
        initComponents();
        obdb = new ParabitCeamsDb();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Municipal Commissioner ");

        jButton1.setText("Land Details");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Profile");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        try {
//            String s = "SELECT LRID ,statecode,districtcode,Villagecode,City,ZoneCode,  WardCode  ,AreaSqft, LmarkDate,LMarkTime,PolutionIndex,Status FROM landprofile where "
//                    + "ZoneCode = " + ParabitLogin.srs.getInt(5);
            String ns = "SELECT LRID ,statecode,districtcode, Villagecode,City,ZoneCode,  WardCode  ,AreaSqft, LmarkDate,LMarkTime,PolutionIndex,Status FROM landprofile WHERE districtcode = " + ParabitLogin.srs.getInt(3) + " and SubDistrictcode =" + ParabitLogin.srs.getInt(4);
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
                    + " and lp.StateCode = " + ParabitLogin.srs.getInt(2)
                    + " and lp.DistrictCode = " + ParabitLogin.srs.getInt(3)
                    + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4);
            System.out.println("hello       "+sql1);
            LandDetails ld = new LandDetails(this, false, sql1,null);
            ld.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        Profile p = new Profile();
        p.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MunicipalCommissioner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
