package ceamsexperts;

import com.mycompany.ceamsparabit.NewLandRegistration;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ApprLand extends javax.swing.JFrame implements Runnable {

    ParabitExpertDb obdb1;
    DefaultTableModel tb1model;
    public volatile boolean isRunning = true;
    Thread th;

    public ApprLand() {

        initComponents();
        addTableClickListener(); // Add table click listener
        th = new Thread(this);
        th.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                notificationData(); // Your custom method to fetch data
                Thread.sleep(1000); // Sleep for 1 second
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("Thread stopped.");
    }

    private void addTableClickListener() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow != -1) { // Check if a row is selected
                    isRunning = false; // Stop the thread
                }
            }
        });
    }

    public void notificationData() {
        tb1model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //All cells are non-editable
            }
        };
        jTable1.setModel(tb1model);
        obdb1 = new ParabitExpertDb();
        try {
            // Initialize the table model
            tb1model = (DefaultTableModel) jTable1.getModel();

            // Clear the table
            if (tb1model != null) {
                tb1model.setRowCount(0);
                tb1model.setColumnCount(0);
            }
            String sql = "SELECT LRID,WardName,ColonySocietyName,Address,Landmark,AreaSqft,LmarkDate,LMarkTime,SoilType,PolutionIndex,PolutionType,Status FROM landprofile WHERE Status = 'Approved Land'";
            System.out.println(sql);
            obdb1.rs = obdb1.stm.executeQuery(sql);
            tb1model.addColumn("Land Registration ID");
            tb1model.addColumn("Ward Name");
            tb1model.addColumn("Colony Society name");
            tb1model.addColumn("Address");
            tb1model.addColumn("Land Mark");
            tb1model.addColumn("Area Square Fit");
            tb1model.addColumn("Land Mark Date");
            tb1model.addColumn("Land Mark Time");
            tb1model.addColumn("Soil Type");
            tb1model.addColumn("Pollution Index");
            tb1model.addColumn("Type of Pollution");
            tb1model.addColumn("Status");
            // Populate the table
            while (obdb1.rs.next()) {
                String tbData[] = {
                    obdb1.rs.getString(1), obdb1.rs.getString(2), obdb1.rs.getString(3),
                    obdb1.rs.getString(4), obdb1.rs.getString(5), obdb1.rs.getString(6),
                    obdb1.rs.getString(7), obdb1.rs.getString(8), obdb1.rs.getString(9),
                    obdb1.rs.getString(10), obdb1.rs.getString(11), obdb1.rs.getString(12)
                };
                tb1model.addRow(tbData);
            }
        } catch (SQLException e) {
            System.out.println("Some Problem ------>>>>>> " + e);
        } catch (NullPointerException e) {
            System.out.println("Table Model is null ------>>>>>> " + e);
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("complete  details");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jCheckBoxMenuItem1);

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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        Object lrid = null;
        if (jTable1.getSelectedRow() != -1) {
            int selectedRow = jTable1.getSelectedRow();
            lrid = jTable1.getValueAt(selectedRow, 0);
            this.setVisible(false);
            NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1);
            nlr.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ApprLand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApprLand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApprLand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApprLand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApprLand().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
