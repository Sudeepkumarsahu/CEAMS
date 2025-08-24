/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ceamsexperts;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class SchoolAdministrator extends javax.swing.JFrame implements Runnable {

    ParabitExpertDb obdb1;
    Thread t;
    private boolean isRunning = true;

    public SchoolAdministrator() {
        initComponents();
        t = new Thread(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
        obdb1 = new ParabitExpertDb();
        t.start();
    }

    public void run() {
        while (true) {
            try {
                if (isRunning) {
                    tableDataFunc();
                    Thread.sleep(800);
                }
            } catch (Exception e) {
            }
        }
    }

    public void stopProcess() {
        isRunning = false;
    }

    private void tableDataFunc() {
        try {
//            String s = "SELECT LRID, StateCode, DistrictCode, VillageCode, City, ZoneCode, WardCode, WardName, AreaSqft, LMarkDate, LMarkTime, PolutionIndex, Status, Activities, Institute FROM landprofile WHERE Status = 'Activated Land' AND Institute IN ('S', 'B')";
            String s = "SELECT lp.LRID , lp.StateCode, lp.DistrictCode, lp.VillageCode, lp.City,"
                    + "    lp.ZoneCode, lp.WardCode, lp.WardName, lp.AreaSqft, lp.LMarkDate,"
                    + "    lp.LMarkTime, lp.PolutionIndex, lp.Status, lp.Activities, a.Institute "
                    + "FROM landprofile lp,activities a where lp.Activities = a.Activities AND "
                    + "lp.Status = 'Activated Land' AND a.Institute IN ('S','B')";
            obdb1.rs = obdb1.stm.executeQuery(s);
            System.out.println(s);
            // Clear existing table columns and rows
            DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel();
            tb1model.setRowCount(0);
            tb1model.setColumnCount(0);
            tb1model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };
            jTable1.setModel(tb1model);
            // Manually add column names
            tb1model.addColumn("Land Registration ID");
            tb1model.addColumn("State Code");
            tb1model.addColumn("District Code");
            tb1model.addColumn("VillageCode");
            tb1model.addColumn("City");
            tb1model.addColumn("Zone Code");
            tb1model.addColumn("Ward Code");
            tb1model.addColumn("Ward Name");
            tb1model.addColumn("Area Sqft");
            tb1model.addColumn("Land Mark Date");
            tb1model.addColumn("Land Mark Time");
            tb1model.addColumn("Pollution Index");
            tb1model.addColumn("Status");
            tb1model.addColumn("Activities");
            tb1model.addColumn("Institute");

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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(0, 0, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("School Administrator");

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SchoolAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchoolAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchoolAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchoolAdministrator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchoolAdministrator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
