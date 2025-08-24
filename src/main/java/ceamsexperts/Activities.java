package ceamsexperts;

import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

public class Activities extends javax.swing.JDialog {

    ParabitExpertDb obdb1;
    private Object lrid;
    String activities, institute, selectedItem = "", query = "";
    int selectedIndex, sno1;
    String acts = "", index = "";

    public Activities(java.awt.Frame parent, boolean modal, Object lrid) {
        super(parent, modal);
        initComponents();
        this.lrid = lrid;
//        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        obdb1 = new ParabitExpertDb();
        String ss = "select Activities,Institute from activities";
        System.out.println(ss);
        try {
            obdb1.rs = obdb1.stm.executeQuery(ss);
            obdb1.rs.next();
            activities = obdb1.rs.getString(1);
            institute = obdb1.rs.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(Activities.class.getName()).log(Level.SEVERE, null, ex);
        }

        dataInList();

//        jList1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
////                if (e.getClickCount() == 2) {
////                    int index = jList1.locationToIndex(e.getPoint());
////                    if (index != -1) {
////                        jList1.setSelectedIndex(index);
////                        jPopupMenu1.show(jList1, e.getX(), e.getY());
////                    }
////                }
//                if (e.getClickCount() == 1) {
//                    fillData();
//                }
//            }
//        });
    }

//    public void fillData() {
//        selectedIndex = jList1.getSelectedIndex();
//        if (selectedIndex != -1) {  // Ensure that an item is selected
//            selectedItem = jList1.getModel().getElementAt(selectedIndex);
//            System.out.println("Selected item: " + selectedItem);
//
//            // Get the existing text and concatenate the new selection
//            String existingText = jTextField1.getText();
//            jTextField1.setText(existingText + " " + selectedItem);
//        }
//    }
//    public void fillData() {
//        selectedIndex = jList1.getSelectedIndex();
//        if (selectedIndex != -1) { // Ensure an item is selected
//            selectedItem = jList1.getModel().getElementAt(selectedIndex);
//            System.out.println("Selected item: " + selectedItem);
//            // Get existing text and split selections based on a delimiter (space)
//            String existingText = jTextField1.getText().trim();
//            String[] selections = existingText.isEmpty() ? new String[0] : existingText.split(" \\ ");
//            if (selections.length < 3) { // Ensure max 3 selections
//                jTextField1.setText(existingText.isEmpty() ? selectedItem : existingText + "  " + selectedItem);
//            } else {
//                System.out.println("You can only add up to three selections.");
//            }
//        }
//    }
    private void dataInList() {
        try {
            DefaultListModel model = new DefaultListModel();
            String s = "select Activities from activities";
            System.out.println(s);
            obdb1.rs = obdb1.stm.executeQuery(s);
            while (obdb1.rs.next()) {
                String ac = obdb1.rs.getString(1);
                model.addElement(ac);
            }
            jList1.setModel(model);

        } catch (SQLException ex) {
            Logger.getLogger(Activities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        jMenuItem1.setText("Done");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Select Activities");

        jButton1.setText("Submit Suggestion");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setText("Selected Activities");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(148, 148, 148))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(142, 142, 142))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        selectedIndex = jList1.getSelectedIndex();
//        if (selectedIndex != -1) {  // Ensure that an item is selected
//            selectedItem = jList1.getModel().getElementAt(selectedIndex);
//            System.out.println("Selected item: " + selectedItem);
//        }
//        if (jMenuItem1.getText().equals("Done")) {
//            query = "update landprofile set Activities = '" + selectedItem + "' where LRID = " + lrid;
//            System.out.println(query);
//        }
//        try {
//            obdb1.stm.executeUpdate(query);
//            JOptionPane.showMessageDialog(null, "Done");
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
             String s = "update landprofile set Status = 'Suggested Land' where LRID = " + lrid;
                System.out.println(s);
                obdb1.stm.executeUpdate(s);
            String getData = "SELECT statecode,districtcode FROM landprofile WHERE LRID = " + lrid;
            obdb1.rs = obdb1.stm.executeQuery(getData);
            obdb1.rs.next();
            int sc = obdb1.rs.getInt(1);
            int dc = obdb1.rs.getInt(2);

            String ELog = "INSERT INTO expertlandlog (LRID, date, time,ExpertID,ExpertType,StateCode,DistrictCode) VALUES ("
                    + lrid + ", CURDATE(), CURTIME()," + ExpertLogin.srs.getInt(1) + "," + ExpertLogin.srs.getInt(2) + "," + sc + "," + dc + ")";
            System.out.println(ELog);
            obdb1.stm.executeUpdate(ELog);
            System.out.println(index + "ddddddd");
//            index = index + String.valueOf(jList1.getSelectedIndex() + 1) + " ";
            System.out.println(index + "fffffffff");
            query = "update expertlandlog set AID = '" + index + "' where LRID = " + lrid;
            System.out.println(query);
            obdb1.stm.executeUpdate(query);

            index = index.trim().replace(" ", ", "); // Replace spaces with commas
            System.out.println("Formatted index: " + index);

            String getAID = "SELECT MIN(AID) AS AID "
                    + "FROM activities "
                    + "WHERE Department IN (SELECT DISTINCT Department FROM activities WHERE AID IN (" + index + "))"
                    + "AND AID IN (" + index + ")"
                    + "GROUP BY Department";

            System.out.println(getAID); // Debugging: Print the query
            obdb1.rs = obdb1.stm.executeQuery(getAID);
            StringBuilder Atype = new StringBuilder();
            while (obdb1.rs.next()) {
                if (Atype.length() > 0) {
                    Atype.append(" "); // Add comma instead of space
                }
                Atype.append(obdb1.rs.getString(1));
            }
            String atype = Atype.toString();
            System.out.println(atype);
            String query2 = "UPDATE expertlandlog "
                    + "SET AType =  '" + atype
                    + "' WHERE LRID = " + lrid;
            System.out.println(query2);
            obdb1.stm.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Done");

            String[] numberArray = index.split(" ");
            StringBuilder placeholders = new StringBuilder();

            // Prepare placeholders for SQL IN clause
            for (int i = 0; i < numberArray.length; i++) {
                placeholders.append("?");
                if (i < numberArray.length - 1) {
                    placeholders.append(", ");
                }
            }

            // Query the second table using these numbers
            String query = "SELECT * FROM activities WHERE AID IN (" + placeholders + ")";
            try (PreparedStatement pstmt = obdb1.con.prepareStatement(query)) {
                for (int i = 0; i < numberArray.length; i++) {
                    pstmt.setInt(i + 1, Integer.parseInt(numberArray[i]));
                }

                obdb1.rs = pstmt.executeQuery();
                System.out.println("Data from activities:");

                while (obdb1.rs.next()) {
                    int id = obdb1.rs.getInt("AID");
                    String data = obdb1.rs.getString("Department");
                    System.out.println("AID: " + id + ", Department: " + data);
                }
            }
           
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        jList1.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
        String act = jList1.getSelectedValue();
        index = index + String.valueOf(jList1.getSelectedIndex() + 1 + " ");
        acts = acts + act + " \n";

//        jTextField1.setText(acts);
        jTextArea1.setText(acts);
        System.out.println(index + "dfghjkll");
    }//GEN-LAST:event_jList1MouseClicked
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
            java.util.logging.Logger.getLogger(Activities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Activities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Activities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Activities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Activities dialog = new Activities(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
