/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ceamsexperts;

import com.mycompany.ceamsparabit.NewLandRegistration;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.ListSelectionModel;

public class PlantENotification extends javax.swing.JFrame implements Runnable {

    ParabitExpertDb obdb, obdb1, obdb2;
    Thread t;
    DefaultTableModel tbmodel1;
    private boolean isRunning = true;
    String selectedItem = "", query = "", plant;
    Object lrid, aid, soilType, areaType;
    StringBuilder concatenateactivities;
    private LinkedHashMap<String, List<String>> plantMap;
    // Global list model
    private DefaultListModel<String> listModel;

    public PlantENotification() {
        this.listModel = new DefaultListModel<>();
        initComponents();
        jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        t = new Thread(this);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
        obdb = new ParabitExpertDb();
        obdb1 = new ParabitExpertDb();
        obdb2 = new ParabitExpertDb();
        tableDataFunc();
        relatedToPlants();
        concatenateactivities = new StringBuilder();
    }

    private void relatedToPlants() {
        DefaultListModel<String> model = new DefaultListModel<>();
        jComboBox1.addActionListener(e -> {
            String type = (String) jComboBox1.getSelectedItem();
            System.out.println("Selected Type: " + type); // Debugging

            model.clear();
            if (type != null) {
                String q = "SELECT DISTINCT Plants FROM planttype WHERE PType = ?";
                try {
                    PreparedStatement pstmt = obdb1.con.prepareStatement(q);
                    pstmt.setString(1, type);
                    obdb1.rs = pstmt.executeQuery();

                    while (obdb1.rs.next()) {
                        String plant = obdb1.rs.getString(1);
                        System.out.println("Plant Added: " + plant); // Debugging
                        model.addElement(plant);
                    }
                    jList1.setModel(model);
                } catch (SQLException ex) {
                    ex.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        noOfplant = new javax.swing.JTextField();
        water = new javax.swing.JTextField();
        monitor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Complete Details");
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
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("City:");

        jLabel7.setText("Plant Type");

        jLabel8.setText("Land Mark:");

        jLabel9.setText("Colony:");

        jLabel10.setText("Address:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("Plantation Expert");

        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText(" Plant Done");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel13.setText("No.Of Plants");

        jLabel14.setText("Water(y/n)");

        jLabel15.setText("Monitoring(y/n)");

        monitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monitorActionPerformed(evt);
            }
        });

        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plant Type", "Plants"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setShowVerticalLines(true);
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1802, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(468, 468, 468)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(jTextField4)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(monitor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noOfplant, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(water, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2)))
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jLabel13)
                                                .addGap(28, 28, 28))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(noOfplant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(69, 69, 69))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(78, 78, 78)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(monitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(362, 362, 362)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(water, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            lrid = jTable1.getValueAt(selectedRow, 0);
            aid = jTable1.getValueAt(selectedRow, 1);
            soilType = jTable1.getValueAt(selectedRow, 4);
            System.out.println(soilType);
            areaType = jTable1.getValueAt(selectedRow, 5);
            databaseImage("SImg", jLabel1, lrid, jLabel16);
            databaseImage("Img1", jLabel2, lrid, jLabel16);
            databaseImage("Img2", jLabel3, lrid, jLabel16);
            databaseImage("Img3", jLabel4, lrid, jLabel16);
            databaseImage("Img4", jLabel5, lrid, jLabel16);
            textFieldsData();
            plantsComboBox();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String getData = "SELECT DISTINCT ExpertID,StateCode,DistrictCode FROM expertlandlog WHERE LRID = " + lrid;
            System.out.println(getData);
            obdb1.rs = obdb1.stm.executeQuery(getData);
            obdb1.rs.next();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            int rowCount = model.getRowCount();

            List<String> plantTypes = new ArrayList<>();
            List<String> plantNames = new ArrayList<>();

// Collect all plant types and plant names
            for (int i = 0; i < rowCount; i++) {
                String plantType = model.getValueAt(i, 0).toString();
                String plantName = model.getValueAt(i, 1).toString();

                if (!plantTypes.contains(plantType)) { // Avoid duplicate plant types
                    plantTypes.add(plantType);
                }
                plantNames.add(plantName);
            }

// Convert lists to comma-separated strings
            String plantTypeString = String.join(", ", plantTypes);
            String plantNameString = String.join(", ", plantNames);
            String insert = "INSERT INTO plantsuggestion (LRID, ExpID, StateCode, DistrictCode, Date, Time, PlantName, PlantType, TotalNoOfPlants, WaterRequirement, Monitoring) "
                    + "VALUES (?, ?, ?, ?, CURDATE(), CURTIME(), ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = obdb1.con.prepareStatement(insert);

            pstmt.setObject(1, lrid);
            pstmt.setInt(2, obdb1.rs.getInt(1));
            pstmt.setInt(3, obdb1.rs.getInt(2));
            pstmt.setInt(4, obdb1.rs.getInt(3));
            pstmt.setString(5, plantNameString); // Store all plant names in one column
            pstmt.setString(6, plantTypeString); // Store all plant types in one column
            pstmt.setString(7, noOfplant.getText());
            pstmt.setString(8, water.getText());
            pstmt.setString(9, monitor.getText());

            pstmt.executeUpdate(); // Execute single row insertion

            pstmt.close();

            System.out.println("Single row inserted successfully!");
                        

            String s = "update landprofile set Status = 'Plant Done' where LRID = " + lrid;
            System.out.println(s);
            obdb1.stm.executeUpdate(s);
        } catch (SQLException ex) {
            Logger.getLogger(PlantENotification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        Object lrid;
        if (jTable1.getSelectedRow() != -1) {
            int selectedRow = jTable1.getSelectedRow();
            lrid = jTable1.getValueAt(selectedRow, 0);
            this.setVisible(false);
            NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1);
            nlr.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void monitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monitorActionPerformed
    }//GEN-LAST:event_monitorActionPerformed
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {
            String plantType = (String) jComboBox1.getSelectedItem();
            String selectedPlant = jList1.getSelectedValue();

            if (plantType != null && selectedPlant != null) {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                boolean found = false;

                // Check if plant type already exists in the table
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(plantType)) {
                        // If found, update the second column (append plant name)
                        String existingPlants = (String) model.getValueAt(i, 1);
                        if (!existingPlants.contains(selectedPlant)) { // Avoid duplicates
                            model.setValueAt(existingPlants + ", " + selectedPlant, i, 1);
                            System.out.println("Updated row: " + plantType + " - " + selectedPlant);
                        }
                        found = true;
                        break;
                    }
                }
                // If plant type is not found, add a new row
                if (!found) {
                    model.addRow(new Object[]{plantType, selectedPlant});
                    System.out.println("Added new row: " + plantType + " - " + selectedPlant);
                }
            }
        }

    }//GEN-LAST:event_jList1ValueChanged

    private void tableDataFunc() {
        try {
            String s = "SELECT DISTINCT lp.LRID, lp.Address, lp.WardName, lp.Landmark, tp.Soiltype, at.MarkAreaType "
                    + "FROM expertlandlog ep "
                    + "JOIN landprofile lp ON lp.LRID = ep.LRID "
                    + "JOIN typesofsoil tp ON lp.soiltype = tp.sno "
                    + "JOIN areatype at ON lp.AreaType = at.sno "
                    + "WHERE lp.Status = 'Suggested Land' AND ( "
                    + "    ep.AType LIKE '%6%' OR "
                    + "    ep.AType LIKE '%8%' OR "
                    + "    ep.AType LIKE '%10%' OR "
                    + "    ep.AType LIKE '%17%')";
            obdb1.rs = obdb1.stm.executeQuery(s);
            System.out.println(s);
            // Clear existing table columns and rows
            DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel();
            tb1model.setRowCount(0);
            tb1model.setColumnCount(0);
            tb1model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };
            jTable1.setModel(tb1model);
            // Manually add column names
            tb1model.addColumn("Land Registration ID");
            tb1model.addColumn("Address");
            tb1model.addColumn("Ward Name");
            tb1model.addColumn("Land Mark");
            tb1model.addColumn("Soil Type");
            tb1model.addColumn("Area Type");
            int columnCount = tb1model.getColumnCount();

            int st = 0, at = 0;
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

    private void plantsComboBox() {
        jComboBox1.removeAllItems();

        String planttype = "";
        String query = "SELECT DISTINCT PType FROM planttype pt,plantdata pd WHERE pt.PType = pd.PlantType AND pd.SoilType LIKE '%" + soilType + "%'";
        System.out.println(query);

        try {
            obdb2.rs = obdb2.stm.executeQuery(query);
            while (obdb2.rs.next()) {
                planttype = obdb2.rs.getString("PType"); // Fetch by column name
                jComboBox1.addItem(planttype); // Add item to JComboBox

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void textFieldsData() {
        try {
            String ss = "select City, Address, ColonySocietyName, LandMark from landprofile where LRID = " + lrid;
            System.out.println(ss);
            obdb.rs = obdb.stm.executeQuery(ss);
            while (obdb.rs.next()) {
                String city = obdb.rs.getString(1);
                String Address = obdb.rs.getString(2);
                String colony = obdb.rs.getString(3);
                String landmark = obdb.rs.getString(4);

                jTextField1.setText(city);
                jTextField2.setText(Address);
                jTextField3.setText(colony);
                jTextField4.setText(landmark);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void databaseImage(String image, JLabel label, Object lrid, JLabel boxlabel) {
        try {
            String query = "SELECT " + image + " FROM landprofile WHERE LRID = " + lrid;
            System.out.println(query);
            obdb.stm = obdb.con.prepareStatement(query);
            obdb.rs = obdb.stm.executeQuery(query);
            if (obdb.rs.next()) {
                byte[] imageData = obdb.rs.getBytes(image);
                if (imageData != null && imageData.length > 0) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                    Image img = ImageIO.read(bais);
                    if (img != null) {
                        // Resize the image for the smaller label
                        Image scaledImg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                        // Set the scaled image on the smaller JLabel
                        label.setIcon(new ImageIcon(scaledImg));

                        // Add a mouse listener to display the larger image
                        label.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                            @Override
                            public void mouseMoved(java.awt.event.MouseEvent evt) {
                                // Resize the image for the larger label
                                Image largeImg = img.getScaledInstance(boxlabel.getWidth(), boxlabel.getHeight(), Image.SCALE_SMOOTH);
                                boxlabel.setIcon(new ImageIcon(largeImg));
                            }
                        });

                    } else {
                        System.out.println("Image data is invalid or not in a supported format.");
                    }
                } else {
                    System.out.println("No image found for this record.");
                }
            }
        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
    }

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
            java.util.logging.Logger.getLogger(PlantENotification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlantENotification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlantENotification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlantENotification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlantENotification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField monitor;
    private javax.swing.JTextField noOfplant;
    private javax.swing.JTextField water;
    // End of variables declaration//GEN-END:variables
}
