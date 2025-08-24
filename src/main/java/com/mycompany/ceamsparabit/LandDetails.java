package com.mycompany.ceamsparabit;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class LandDetails extends javax.swing.JDialog {

    ParabitCeamsDb obdb, obdb2, obdb3, obdb4, obdb1;
    String s, LisLand, rl, pl, al, pcl, ll, rrl;
    byte[] imageData;
    String v[] = new String[22];
    DefaultTableModel tb1model;
    String sql1, sql2, workpost;
    public JButton btnn;

    public LandDetails(java.awt.Frame parent, boolean modal, String s, JButton btnn) {
        super(parent, modal);
        this.btnn = btnn;

        initComponents();
        getContentPane().setBackground(Color.green);
        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        tablEditFalse();
        try {
            workpost = ParabitLogin.srs.getString(13);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (workpost.equals("Land Inspector") && btnn.getText().equals("Land Approval")) {
            jTabbedPane1.setSelectedIndex(3);
            jMenuItem1.setVisible(false);
            jMenuItem2.setVisible(false);
            jCheckBoxMenuItem3.setVisible(false);
        }
        if (workpost.equals("Municipal Commissioner")) {
            this.jTabbedPane1.setSelectedIndex(2);
            jMenuItem1.setVisible(true);
            jMenuItem2.setVisible(false);
            jCheckBoxMenuItem2.setVisible(false);
            jCheckBoxMenuItem3.setVisible(false);
        }
        if (workpost.equals("Zone Municipal Officer")) {
            this.jTabbedPane1.setSelectedIndex(1);
            jMenuItem1.setVisible(true);
            jMenuItem2.setVisible(false);
            jCheckBoxMenuItem3.setVisible(false);
        }
        if (workpost.equals("Land Inspector")) {

            jCheckBoxMenuItem2.setVisible(false);
        }
        jTabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedIndex = jTabbedPane1.getSelectedIndex();
                try {
                    if (selectedIndex == 1 && workpost.equals("Zone Municipal Officer")) {
                        jCheckBoxMenuItem2.setVisible(true);
                        jMenuItem1.setVisible(true);
                    } else {
                        jCheckBoxMenuItem2.setVisible(false);
                        jMenuItem1.setVisible(false);
                    }
                    if ((selectedIndex > 0)) {
                        jMenuItem1.setVisible(false);
                        jMenuItem2.setVisible(false);
                        jCheckBoxMenuItem3.setVisible(false);
                    } else {
                        jMenuItem1.setVisible(true);
                        jMenuItem2.setVisible(true);
                        jCheckBoxMenuItem3.setVisible(true);

                        if (workpost.equals("Municipal Commissioner") || workpost.equals("Zone Municipal Officer")) {
                            jMenuItem1.setVisible(false);
                            jMenuItem2.setVisible(false);
                            jCheckBoxMenuItem3.setVisible(false);
                        }

                    }
                    if (workpost.equals("Zone Municipal Officer") && selectedIndex == 1) {
                        jMenuItem1.setVisible(true);
                    }
                    if (workpost.equals("Municipal Commissioner") && selectedIndex == 2) {
                        jMenuItem1.setVisible(true);
                    }
                    if (workpost.equals("Land Inspector") && btnn.getText().equals("Land Approval") && selectedIndex == 0) {
                        jMenuItem1.setVisible(false);
                        jMenuItem2.setVisible(false);
                        jCheckBoxMenuItem3.setVisible(false);
                    }

                } catch (Exception ee) {

                }
                if (selectedIndex == 0) {
                    cb1.setSelectedIndex(0);
                }
                if (selectedIndex == 1) {
                    cb2.setSelectedIndex(0);
                }
                if (selectedIndex == 2) {
                    cb3.setSelectedIndex(0);
                }
                if (selectedIndex == 3) {
                    cb4.setSelectedIndex(0);
                }
                if (selectedIndex == 4) {
                    cb5.setSelectedIndex(0);
                }
                if (selectedIndex == 5) {
                    cb6.setSelectedIndex(0);
                }
                if (selectedIndex == 6) {
                    cb7.setSelectedIndex(0);
                }
            }
        });
        if (workpost.equals("Land Inspector") && workpost.equals("Municipal Commissioner")) {
            this.jCheckBoxMenuItem1.setVisible(false);
            this.jCheckBoxMenuItem2.setVisible(false);
        }

        this.s = s;
        obdb = new ParabitCeamsDb();
        obdb2 = new ParabitCeamsDb();
        obdb3 = new ParabitCeamsDb();
        obdb4 = new ParabitCeamsDb();
        obdb1 = new ParabitCeamsDb();
        funct();

//        cb1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JComponent[] components = {cb1, cb2, cb3, cb4, cb5, cb6, cb7,
            jTable1, jTable2, jTable3, jTable4, jTable5, jTable6, jTable7};
        for (JComponent comp : components) {
            comp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

//    public void funct() {
//
//        try {
//            System.out.println("runnnnnnnnnn");
//            table(jTable4, s + " AND lp.Status = 'Approved Land'", image3);
//            table(jTable5, s + " AND lp.Status = 'Project Completed Land'", image4);
//            table(jTable6, s + " AND lp.Status = 'Locked Land'", image5);
//            table(jTable7, s + " AND lp.Status = 'Rejected Land'", image6);
//
//            System.out.println();
//            if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                table(jTable1, s + " AND lp.Status = 'Listed Land'", image);
//            }
//            if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                table(jTable2, s + " AND lp.Status = 'Requested Land'", image1);
//            }
//            if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                table(jTable3, s + " AND lp.Status = 'Pending Land'", image2);
//            }
//            try {
//
//                ParabitLogin.srs.getInt(2);//state code
//                ParabitLogin.srs.getInt(3);//district code
//                ParabitLogin.srs.getInt(4);//sub district code
//                ParabitLogin.srs.getInt(5);//zone code
//                ParabitLogin.srs.getInt(6);//ward code
//                System.out.println(ParabitLogin.srs.getInt(2) + " " + ParabitLogin.srs.getInt(3) + " " + ParabitLogin.srs.getInt(4) + " " + ParabitLogin.srs.getInt(5) + " " + ParabitLogin.srs.getInt(6) + " " + ParabitLogin.srs.getString(13));
//
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Listed Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Listed Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Listed Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//
//                obdb.rs = obdb.stm.executeQuery(ss);
//
//                while (obdb.rs.next()) {
//                    LisLand = obdb.rs.getString(1);
//                    jLabel1.setText(LisLand);
//                    jLabel15.setText(LisLand);
//                    jLabel22.setText(LisLand);
//                    jLabel29.setText(LisLand);
//                    jLabel36.setText(LisLand);
//                    jLabel43.setText(LisLand);
//                    jLabel50.setText(LisLand);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            try {
////                String ss = "select count(Status) from landprofile where Status = 'Requested Land'";
////                System.out.println(ss);
//
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Requested Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Requested Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Requested Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//                obdb.rs = obdb.stm.executeQuery(ss);
//                while (obdb.rs.next()) {
//                    rl = obdb.rs.getString(1);
//                    jLabel2.setText(rl);
//                    jLabel16.setText(rl);
//                    jLabel23.setText(rl);
//                    jLabel30.setText(rl);
//                    jLabel37.setText(rl);
//                    jLabel44.setText(rl);
//                    jLabel51.setText(rl);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            try {
////                String ss = "select count(Status) from landprofile where Status = 'Pending Land'";
////                System.out.println(ss);
//
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Pending Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Pending Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Pending Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//                obdb.rs = obdb.stm.executeQuery(ss);
//                while (obdb.rs.next()) {
//                    pl = obdb.rs.getString(1);
//                    jLabel3.setText(pl);
//                    jLabel17.setText(pl);
//                    jLabel24.setText(pl);
//                    jLabel31.setText(pl);
//                    jLabel38.setText(pl);
//                    jLabel45.setText(pl);
//                    jLabel52.setText(pl);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            try {
////                String ss = "select count(Status) from landprofile where Status = 'Approved Land'";
////                System.out.println(ss);
//
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Approved Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Approved Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Approved Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//                obdb.rs = obdb.stm.executeQuery(ss);
//                while (obdb.rs.next()) {
//                    al = obdb.rs.getString(1);
//                    jLabel4.setText(al);
//                    jLabel18.setText(al);
//                    jLabel25.setText(al);
//                    jLabel32.setText(al);
//                    jLabel39.setText(al);
//                    jLabel46.setText(al);
//                    jLabel53.setText(al);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            try {
////                String ss = "select count(Status) from landprofile where Status = 'Project Completed Land'";
////                System.out.println(ss);
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Project Completed Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Project Completed Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Project Completed Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//                obdb.rs = obdb.stm.executeQuery(ss);
//                while (obdb.rs.next()) {
//                    pcl = obdb.rs.getString(1);
//                    jLabel5.setText(pcl);
//                    jLabel19.setText(pcl);
//                    jLabel26.setText(pcl);
//                    jLabel33.setText(pcl);
//                    jLabel40.setText(pcl);
//                    jLabel47.setText(pcl);
//                    jLabel54.setText(pcl);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            try {
////                String ss = "select count(Status) from landprofile where Status = 'Locked Land'";
////                System.out.println(ss);
//
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Locked Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Locked Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Locked Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//                obdb.rs = obdb.stm.executeQuery(ss);
//                while (obdb.rs.next()) {
//                    ll = obdb.rs.getString(1);
//                    jLabel6.setText(ll);
//                    jLabel20.setText(ll);
//                    jLabel27.setText(ll);
//                    jLabel34.setText(ll);
//                    jLabel41.setText(ll);
//                    jLabel48.setText(ll);
//                    jLabel55.setText(ll);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            try {
////                String ss = "select count(Status) from landprofile where Status = 'Rejected Land'";
////                System.out.println(ss);
//                String ss = "";
//                if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
//                    ss = "select count(Status) from landprofile where Status = 'Rejected Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5) + " AND WardCode =" + ParabitLogin.srs.getInt(6);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
//                    ss = "select count(Status) from landprofile where Status = 'Rejected Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4)
//                            + " AND ZoneCode =" + ParabitLogin.srs.getInt(5);
//                    System.out.println(ss);
//                }
//                if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
//                    ss = "select count(Status) from landprofile where Status = 'Rejected Land' AND statecode =" + ParabitLogin.srs.getInt(2)
//                            + " AND districtcode = " + ParabitLogin.srs.getInt(3) + " AND SubDistrictcode =" + ParabitLogin.srs.getInt(4);
//                    System.out.println(ss);
//                }
//                obdb.rs = obdb.stm.executeQuery(ss);
//                while (obdb.rs.next()) {
//                    rrl = obdb.rs.getString(1);
//                    jLabel7.setText(rrl);
//                    jLabel21.setText(rrl);
//                    jLabel28.setText(rrl);
//                    jLabel35.setText(rrl);
//                    jLabel42.setText(rrl);
//                    jLabel49.setText(rrl);
//                    jLabel56.setText(rrl);
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            combo();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
    public void funct() {
        try {
            System.out.println("runnnnnnnnnn");
            setupTables();

            displayRoleSpecificTable();

            fetchLocationDetails();

            updateStatusCounts("Listed Land", jLabel1, jLabel15, jLabel22, jLabel29, jLabel36, jLabel43, jLabel50);
            updateStatusCounts("Requested Land", jLabel2, jLabel16, jLabel23, jLabel30, jLabel37, jLabel44, jLabel51);
            updateStatusCounts("Pending Land", jLabel3, jLabel17, jLabel24, jLabel31, jLabel38, jLabel45, jLabel52);
            updateStatusCounts("Approved Land", jLabel4, jLabel18, jLabel25, jLabel32, jLabel39, jLabel46, jLabel53);
            updateStatusCounts("Project Completed Land", jLabel5, jLabel19, jLabel26, jLabel33, jLabel40, jLabel47, jLabel54);
            updateStatusCounts("Locked Land", jLabel6, jLabel20, jLabel27, jLabel34, jLabel41, jLabel48, jLabel55);
            updateStatusCounts("Rejected Land", jLabel7, jLabel21, jLabel28, jLabel35, jLabel42, jLabel49, jLabel56);

            combo();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setupTables() {
        table(jTable4, s + " AND lp.Status = 'Approved Land'", image3);
        table(jTable5, s + " AND lp.Status = 'Project Completed Land'", image4);
        table(jTable6, s + " AND lp.Status = 'Locked Land'", image5);
        table(jTable7, s + " AND lp.Status = 'Rejected Land'", image6);
    }

    private void displayRoleSpecificTable() throws Exception {
        String role = ParabitLogin.srs.getString(13);
        if ("Land Inspector".equals(role)) {
            table(jTable1, s + " AND lp.Status = 'Listed Land'", image);
        } else if ("Zone Municipal Officer".equals(role)) {
            table(jTable2, s + " AND lp.Status = 'Requested Land'", image1);
        } else if ("Municipal Commissioner".equals(role)) {
            table(jTable3, s + " AND lp.Status = 'Pending Land'", image2);
        }
    }

    private void fetchLocationDetails() {
        try {
            int stateCode = ParabitLogin.srs.getInt(2);
            int districtCode = ParabitLogin.srs.getInt(3);
            int subDistrictCode = ParabitLogin.srs.getInt(4);
            int zoneCode = ParabitLogin.srs.getInt(5);
            int wardCode = ParabitLogin.srs.getInt(6);

            System.out.println(stateCode + " " + districtCode + " " + subDistrictCode + " " + zoneCode + " " + wardCode + " " + ParabitLogin.srs.getString(13));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void updateStatusCounts(String status, JLabel... labels) {
        try {
            String query = buildQueryForStatus(status);
            System.out.println(query);

            obdb.rs = obdb.stm.executeQuery(query);
            if (obdb.rs.next()) {
                String count = obdb.rs.getString(1);
                for (JLabel label : labels) {
                    label.setText(count);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String buildQueryForStatus(String status) throws Exception {
        String role = ParabitLogin.srs.getString(13);
        int stateCode = ParabitLogin.srs.getInt(2);
        int districtCode = ParabitLogin.srs.getInt(3);
        int subDistrictCode = ParabitLogin.srs.getInt(4);
        int zoneCode = ParabitLogin.srs.getInt(5);
        int wardCode = ParabitLogin.srs.getInt(6);

        StringBuilder query = new StringBuilder("SELECT COUNT(Status) FROM landprofile WHERE Status = '")
                .append(status).append("' AND statecode = ").append(stateCode)
                .append(" AND districtcode = ").append(districtCode)
                .append(" AND SubDistrictcode = ").append(subDistrictCode);

        if ("Land Inspector".equals(role)) {
            query.append(" AND ZoneCode = ").append(zoneCode).append(" AND WardCode = ").append(wardCode);
        } else if ("Zone Municipal Officer".equals(role)) {
            query.append(" AND ZoneCode = ").append(zoneCode);
        }

        return query.toString();
    }

    public void tablEditFalse() {
        tb1model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are non-editable
            }
        };
        jTable1.setModel(tb1model);
        jTable2.setModel(tb1model);
        jTable3.setModel(tb1model);
        jTable4.setModel(tb1model);
        jTable5.setModel(tb1model);
        jTable6.setModel(tb1model);
        jTable7.setModel(tb1model);
    }

    public void combo() {
        try {
            String sss = "";
            if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
                sss = "select wardname from zoneward where  DistrictCode = " + ParabitLogin.srs.getInt("DistrictCode") + " and StateCode = " + ParabitLogin.srs.getInt("StateCode") + " order by zoneward.wardname asc";
                System.out.println(sss);
            } else if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
                sss = "select wardname from zoneward where zone = " + ParabitLogin.srs.getInt("ZoneCode") + " and  DistrictCode = " + ParabitLogin.srs.getInt("DistrictCode") + " and StateCode = " + ParabitLogin.srs.getInt("StateCode") + " order by zoneward.wardname asc";
            } else if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
                sss = "select wardname from zoneward where WardCode = " + ParabitLogin.srs.getInt(6) + " and zone = " + ParabitLogin.srs.getInt("ZoneCode") + " and  DistrictCode = " + ParabitLogin.srs.getInt("DistrictCode") + " and StateCode = " + ParabitLogin.srs.getInt("StateCode") + " order by zoneward.wardname asc";
            }
            System.out.println(sss);
            obdb2.rs = obdb2.stm.executeQuery(sss);
            while (obdb2.rs.next()) {
                cb1.addItem(obdb2.rs.getString(1));
                cb2.addItem(obdb2.rs.getString(1));
                cb3.addItem(obdb2.rs.getString(1));
                cb4.addItem(obdb2.rs.getString(1));
                cb5.addItem(obdb2.rs.getString(1));
                cb6.addItem(obdb2.rs.getString(1));
                cb7.addItem(obdb2.rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void comboBoxWardName(JComboBox combo, JTable table, String sql) {
        try {
            // Initialize the table model
            tb1model = (DefaultTableModel) table.getModel();

            // Clear the table
            if (tb1model != null) {
                tb1model.setRowCount(0);
                tb1model.setColumnCount(0);
            }
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
                    obdb1.rs.getString(1), obdb1.rs.getString(5), obdb1.rs.getString(6),
                    obdb1.rs.getString(7), obdb1.rs.getString(8), obdb1.rs.getString(9),
                    obdb1.rs.getString(15), obdb1.rs.getString(16), obdb1.rs.getString(18),
                    obdb1.rs.getString(20), obdb1.rs.getString(21), obdb1.rs.getString(22)
                };
                tb1model.addRow(tbData);
            }
        } catch (SQLException e) {
            System.out.println("Some Problem ------>>>>>> " + e);
        } catch (NullPointerException e) {
            System.out.println("Table Model is null ------>>>>>> " + e);
        }
    }

    public void AllcomboBoxWardName(JComboBox combo, JTable table, String sql) {
        try {
            // SQL query
            System.out.println(sql);

            obdb1.rs = obdb1.stm.executeQuery(sql);
            // Initialize the table model
            tb1model = (DefaultTableModel) table.getModel();
            // Clear the table

            tb1model.setRowCount(0);
            tb1model.setColumnCount(0);

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
                    obdb1.rs.getString(1), obdb1.rs.getString(5), obdb1.rs.getString(6),
                    obdb1.rs.getString(7), obdb1.rs.getString(8), obdb1.rs.getString(9),
                    obdb1.rs.getString(15), obdb1.rs.getString(16), obdb1.rs.getString(18),
                    obdb1.rs.getString(20), obdb1.rs.getString(21), obdb1.rs.getString(22)
                };
                tb1model.addRow(tbData);
            }
        } catch (SQLException e) {
            System.out.println("Some Problem ------>>>>>> " + e);
        } catch (NullPointerException e) {
            System.out.println("Table Model is null ------>>>>>> " + e);
        }
    }

    public void table(JTable table, String sql, JLabel pic) {
        try {
            System.out.println(sql);
            obdb1.rs = obdb1.stm.executeQuery(sql);
            // Initialize the table model
            tb1model = (DefaultTableModel) table.getModel();
            tb1model.setRowCount(0);
            tb1model.setColumnCount(0);
            tb1model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // All cells are non-editable
                }
            };
            table.setModel(tb1model);
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
                    obdb1.rs.getString(1), obdb1.rs.getString(5), obdb1.rs.getString(6),
                    obdb1.rs.getString(7), obdb1.rs.getString(8), obdb1.rs.getString(9),
                    obdb1.rs.getString(15), obdb1.rs.getString(16), obdb1.rs.getString(18),
                    obdb1.rs.getString(20), obdb1.rs.getString(21), obdb1.rs.getString(22)
                };
                tb1model.addRow(tbData);

            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Check for single-click
                    int selectedRow = table.getSelectedRow();
                    int selcetedcolumn = table.getSelectedColumn();
                    if (selectedRow != -1 && selcetedcolumn != -1) {
                        String lrid = table.getValueAt(selectedRow, 0).toString();
                        System.out.println(lrid);
                        //this is for getting image from database and set into jlabel
                        // here we get image from database one by one because all images get fromo database at a same time is make very slowour program execution 
                        try {
                            String imageQuery = "SELECT SImg FROM landprofile WHERE LRID = ?";
                            PreparedStatement pstmt = obdb.con.prepareStatement(imageQuery);
                            pstmt.setString(1, lrid);
                            obdb.rs = pstmt.executeQuery();

                            if (obdb.rs.next()) {
                                Blob blob = obdb.rs.getBlob("SImg");
                                imageData = blob.getBytes(1, (int) blob.length());

                                ImageIcon imageIcon = new ImageIcon(imageData);
                                Image scaledImage = imageIcon.getImage().getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
                                pic.setIcon(new ImageIcon(scaledImage));
                            }
//                            obdb.rs.close();
//                            pstmt.close();
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        System.out.println("settf");
                        settf(lrid);
                    }
                }
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint()); // Get row at mouse click point
                    int column = table.columnAtPoint(e.getPoint()); // Get column at mouse click point

                    if (row != -1 && column != -1) { // Check if the click is within a valid cell
                        table.setRowSelectionInterval(row, row); // Select the row at click point
                        table.setColumnSelectionInterval(column, column); // Optional: select column
                        jPopupMenu1.show(table, e.getX(), e.getY()); // Show popup menu at clicked location
                    }
                }

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        image = new javax.swing.JLabel();
        cb1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        tfs1 = new javax.swing.JTextField();
        tftpt1 = new javax.swing.JTextField();
        tfr1 = new javax.swing.JTextField();
        tfwr1 = new javax.swing.JTextField();
        tfat1 = new javax.swing.JTextField();
        tfll1 = new javax.swing.JTextField();
        tfco1 = new javax.swing.JTextField();
        tfst1 = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        image1 = new javax.swing.JLabel();
        cb2 = new javax.swing.JComboBox<>();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        tftpt2 = new javax.swing.JTextField();
        tfwr2 = new javax.swing.JTextField();
        tfr2 = new javax.swing.JTextField();
        tfs2 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        tfll2 = new javax.swing.JTextField();
        tfst2 = new javax.swing.JTextField();
        tfat2 = new javax.swing.JTextField();
        tfco2 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        image2 = new javax.swing.JLabel();
        cb3 = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        tfs3 = new javax.swing.JTextField();
        tftpt3 = new javax.swing.JTextField();
        tfat3 = new javax.swing.JTextField();
        tfst3 = new javax.swing.JTextField();
        tfll3 = new javax.swing.JTextField();
        tfco3 = new javax.swing.JTextField();
        tfr3 = new javax.swing.JTextField();
        tfwr3 = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        image3 = new javax.swing.JLabel();
        cb4 = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        tftpt4ll = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        tftpt4 = new javax.swing.JTextField();
        tfwr4 = new javax.swing.JTextField();
        tfr4 = new javax.swing.JTextField();
        tfst4 = new javax.swing.JTextField();
        tfat4 = new javax.swing.JTextField();
        tfs4 = new javax.swing.JTextField();
        tfco4 = new javax.swing.JTextField();
        tfll4 = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        image4 = new javax.swing.JLabel();
        cb5 = new javax.swing.JComboBox<>();
        jLabel75 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        tftpt5 = new javax.swing.JTextField();
        tfat5 = new javax.swing.JTextField();
        tfco5 = new javax.swing.JTextField();
        tfr5 = new javax.swing.JTextField();
        tfs5 = new javax.swing.JTextField();
        tfwr5 = new javax.swing.JTextField();
        tfst5 = new javax.swing.JTextField();
        tfll5 = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        image5 = new javax.swing.JLabel();
        cb6 = new javax.swing.JComboBox<>();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        tftpt6 = new javax.swing.JTextField();
        tfco6 = new javax.swing.JTextField();
        tfst6 = new javax.swing.JTextField();
        tfat6 = new javax.swing.JTextField();
        tfs6 = new javax.swing.JTextField();
        tfll6 = new javax.swing.JTextField();
        tfr6 = new javax.swing.JTextField();
        tfwr6 = new javax.swing.JTextField();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        image6 = new javax.swing.JLabel();
        cb7 = new javax.swing.JComboBox<>();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        tftpt7 = new javax.swing.JTextField();
        tfs7 = new javax.swing.JTextField();
        tfco7 = new javax.swing.JTextField();
        tfwr7 = new javax.swing.JTextField();
        tfr7 = new javax.swing.JTextField();
        tfst7 = new javax.swing.JTextField();
        tfat7 = new javax.swing.JTextField();
        tfll7 = new javax.swing.JTextField();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenuItem1.setText("send to request");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("delete land");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Complete Details");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Remark");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("update details");
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jCheckBoxMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(1900, 1900));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });

        jLabel1.setText("Listed Land : ");

        jLabel2.setText("Requested Land");

        jLabel3.setText("Pending Land");

        jLabel4.setText("Approved Land");

        jLabel5.setText("Project Completed Land");

        jLabel6.setText("Locked Land");

        jLabel7.setText("Rejected Land");

        jTable1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTable1.setAutoResizeMode(0);
        jScrollPane1.setViewportView(jTable1);

        image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb1ItemStateChanged(evt);
            }
        });
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Pollution Type");

        jLabel9.setText("Shape");

        jLabel10.setText("Water Resources");

        jLabel11.setText("Radius");

        jLabel12.setText("Soil Type");

        jLabel13.setText("Area Type");

        jLabel14.setText("LatitudeLongitude");

        jLabel57.setText("Center Co-ordinate");

        tfwr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfwr1ActionPerformed(evt);
            }
        });

        tfll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfll1ActionPerformed(evt);
            }
        });

        jLabel105.setText("Listed Land : ");

        jLabel106.setText("Project Completed Land");

        jLabel107.setText("Requested Land");

        jLabel108.setText("Pending Land");

        jLabel109.setText("Approved Land");

        jLabel110.setText("Rejected Land");

        jLabel111.setText("Locked Land");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfr1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfwr1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfs1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(227, 227, 227)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfat1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfst1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfll1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfco1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel105)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel107)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel108)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel106)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel111)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel110)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel107)
                            .addComponent(jLabel2)
                            .addComponent(jLabel108)
                            .addComponent(jLabel3))
                        .addGap(468, 468, 468))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel109)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfst1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tftpt1)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfat1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfs1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfll1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfwr1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfr1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfco1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                        .addGap(31, 31, 31))))
        );

        jTabbedPane1.addTab("Listed land", jPanel1);

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        jLabel15.setText("Listed Land : ");

        jLabel16.setText("Requested Land");

        jLabel17.setText("Pending Land");

        jLabel18.setText("Approved Land");

        jLabel19.setText("Project Completed Land");

        jLabel20.setText("Locked Land");

        jLabel21.setText("Rejected Land");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(jTable2);

        image1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb2ItemStateChanged(evt);
            }
        });
        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });

        jLabel58.setText("Pollution Type");

        jLabel59.setText("Shape");

        jLabel60.setText("Water Resources");

        jLabel61.setText("Radius");

        tfwr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfwr2ActionPerformed(evt);
            }
        });

        jLabel62.setText("Soil Type");

        jLabel63.setText("Latitute Longitude");

        jLabel64.setText("Area Type");

        jLabel65.setText("Center Co-Ordinate");

        jLabel112.setText("Listed Land : ");

        jLabel113.setText("Requested Land");

        jLabel114.setText("Pending Land");

        jLabel115.setText("Approved Land");

        jLabel116.setText("Project Completed Land");

        jLabel117.setText("Locked Land");

        jLabel118.setText("Rejected Land");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfr2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfwr2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfs2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tftpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(162, 162, 162)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfst2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfco2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfll2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfat2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel112)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel113)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(jLabel114)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jLabel115)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel116)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(196, 196, 196)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(image1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(jLabel117)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)
                                .addGap(61, 61, 61)
                                .addComponent(jLabel118)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel112)
                    .addComponent(jLabel113)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel114)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel115)
                    .addComponent(jLabel18)
                    .addComponent(jLabel116)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel117)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel118)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(image1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tftpt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(tfst2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfat2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfs2)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfll2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfwr2)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfco2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfr2)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(54, 54, 54))
        );

        jTabbedPane1.addTab("Requested Land", jPanel2);

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
        });

        jLabel22.setText("Listed Land : ");

        jLabel23.setText("Requested Land");

        jLabel24.setText(" Pending Land");

        jLabel25.setText("Approved Land");

        jLabel26.setText("Project Completed Land");

        jLabel27.setText("Locked Land");

        jLabel28.setText("Rejected Land");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        image2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
            }
        });

        jLabel66.setText(" Shape");

        jLabel67.setText("Water Resources");

        jLabel68.setText("Pollution Type");

        jLabel69.setText("Center Co-Ordinate");

        jLabel70.setText("Radius");

        jLabel71.setText("Latitute Longitude");

        jLabel72.setText("Soil Type");

        jLabel73.setText("Area Type");

        tfwr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfwr3ActionPerformed(evt);
            }
        });

        jLabel119.setText("Listed Land : ");

        jLabel120.setText("Requested Land");

        jLabel121.setText(" Pending Land");

        jLabel122.setText("Approved Land");

        jLabel123.setText("Project Completed Land");

        jLabel124.setText("Locked Land");

        jLabel125.setText("Rejected Land");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel119)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addComponent(jLabel120)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel121)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel122)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel123)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(image2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel124)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel125)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel66, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfwr3)
                                    .addComponent(tfs3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(tftpt3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(tfr3))
                                .addGap(194, 194, 194)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfst3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfat3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfll3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfco3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel125)
                            .addComponent(jLabel28))
                        .addGap(2, 2, 2))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel119)
                        .addComponent(jLabel120)
                        .addComponent(jLabel121)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel122)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel123)
                        .addComponent(jLabel26)
                        .addComponent(jLabel124)
                        .addComponent(jLabel27)))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(image2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfst3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfat3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfs3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfll3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwr3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfco3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfr3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Pending Land", jPanel3);

        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
        });

        jLabel29.setText("Listed Land : ");

        jLabel30.setText("Requested Land");

        jLabel31.setText("Pending Land");

        jLabel32.setText("Approved Land");

        jLabel33.setText("Project Completed Land");

        jLabel34.setText("Locked Land");

        jLabel35.setText("Rejected Land");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable4);

        image3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb4ActionPerformed(evt);
            }
        });

        jLabel74.setText("Shape");

        tftpt4ll.setText("Pollution Index");

        jLabel76.setText("Radius");

        jLabel77.setText("Water Resources");

        jLabel78.setText("Latitude Longitude");

        jLabel79.setText("Center Co-Ordinate");

        jLabel80.setText("Soil Type");

        jLabel81.setText("Area Type");

        tfr4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfr4ActionPerformed(evt);
            }
        });

        jLabel126.setText("Listed Land : ");

        jLabel127.setText("Requested Land");

        jLabel128.setText("Pending Land");

        jLabel129.setText("Approved Land");

        jLabel130.setText("Project Completed Land");

        jLabel131.setText("Locked Land");

        jLabel132.setText("Rejected Land");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfs4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tftpt4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfr4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfwr4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(142, 142, 142)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfst4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfat4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfll4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfco4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(image3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel126)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel127)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel128)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel129)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel130)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel131)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel132)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(tftpt4ll, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1321, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel128)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel126)
                        .addComponent(jLabel127))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel129)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel130)
                            .addComponent(jLabel33)
                            .addComponent(jLabel131)
                            .addComponent(jLabel34)
                            .addComponent(jLabel132)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(image3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftpt4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfst4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfs4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfat4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfll4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfwr4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfco4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfr4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(520, Short.MAX_VALUE)
                    .addComponent(tftpt4ll, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(314, 314, 314)))
        );

        jTabbedPane1.addTab("Approved Land", jPanel4);

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
        });

        jLabel36.setText("Listed Land : ");

        jLabel37.setText("Requested Land");

        jLabel38.setText("Pending Land");

        jLabel39.setText("Approved Land");

        jLabel40.setText("Project Completed Land");

        jLabel41.setText("Locked Land");

        jLabel42.setText("Rejected Land");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable5);

        image4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb5ActionPerformed(evt);
            }
        });

        jLabel75.setText("Pollution Type");

        jLabel82.setText("Water Resources");

        jLabel83.setText("Radius");

        jLabel84.setText("Soil Type");

        jLabel85.setText("Area Type");

        jLabel86.setText("Latitude Longitude");

        jLabel87.setText("Center Co-Ordinate");

        jLabel88.setText("Shape");

        jLabel133.setText("Listed Land : ");

        jLabel134.setText("Requested Land");

        jLabel135.setText("Pending Land");

        jLabel136.setText("Approved Land");

        jLabel137.setText("Project Completed Land");

        jLabel138.setText("Locked Land");

        jLabel139.setText("Rejected Land");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfwr5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfr5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(163, 163, 163)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfco5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(tfll5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tfs5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tftpt5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(163, 163, 163)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(tfat5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(39, 39, 39)
                                                    .addComponent(tfst5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(91, 91, 91)
                                .addComponent(image4, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel133)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel134)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel135)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel136)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel137)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel138)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel139)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1446, 1446, 1446))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel133)
                    .addComponent(jLabel134))
                .addGap(18, 18, 18)
                .addComponent(cb5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftpt5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfst5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfs5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfat5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwr5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfll5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfr5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfco5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel135)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel136)
                    .addComponent(jLabel39)
                    .addComponent(jLabel137)
                    .addComponent(jLabel40)
                    .addComponent(jLabel138)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel139)
                    .addComponent(jLabel42))
                .addGap(73, 73, 73)
                .addComponent(image4, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(319, 319, 319))
        );

        jTabbedPane1.addTab("projected completed land", jPanel5);

        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
        });

        jLabel43.setText("Listed Land : ");

        jLabel44.setText("Requested Land");

        jLabel45.setText("Pending Land");

        jLabel46.setText("Approved Land");

        jLabel47.setText("Project Completed Land");

        jLabel48.setText("locked land");

        jLabel49.setText("Rejected Land");

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(jTable6);

        image5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb6ActionPerformed(evt);
            }
        });

        jLabel89.setText("Shape");

        jLabel90.setText("Pollution Type");

        jLabel91.setText("Radius");

        jLabel92.setText("Water Resources");

        jLabel93.setText("Center Co-Ordinate");

        jLabel94.setText("Area Type");

        jLabel95.setText("Latitude Longitude");

        jLabel96.setText("Sooil Type");

        jLabel140.setText("Listed Land : ");

        jLabel141.setText("Requested Land");

        jLabel142.setText("Pending Land");

        jLabel143.setText("Approved Land");

        jLabel144.setText("Project Completed Land");

        jLabel145.setText("locked land");

        jLabel146.setText("Rejected Land");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tftpt6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfr6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfwr6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfs6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfco6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfll6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfat6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfst6, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(image5, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel140)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel141)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel142)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel143)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel144)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel145)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel146)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 201, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel140)
                    .addComponent(jLabel141)
                    .addComponent(jLabel143)
                    .addComponent(jLabel144)
                    .addComponent(jLabel145)
                    .addComponent(jLabel146)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel142)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48))
                .addGap(18, 18, 18)
                .addComponent(cb6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(image5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftpt6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfst6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfs6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfat6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwr6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfll6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfr6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfco6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("locked land", jPanel6);

        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
        });

        jLabel50.setText("Listed Land : ");

        jLabel51.setText("Requested Land");

        jLabel52.setText("Pending Land");

        jLabel53.setText("Approved Land");

        jLabel54.setText("Project Completed Land");

        jLabel55.setText("locked land");

        jLabel56.setText("Rejected Land");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTable7);

        image6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cb7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Ward" }));
        cb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb7ActionPerformed(evt);
            }
        });

        jLabel97.setText("Shape");

        jLabel98.setText("Pollution Type");

        jLabel99.setText("Radius");

        jLabel100.setText("Water Resource");

        jLabel101.setText("Latitude Longitude");

        jLabel102.setText("Center Co-Ordinate");

        jLabel103.setText("Soil Type");

        jLabel104.setText("Area Type");

        jLabel147.setText("Listed Land : ");

        jLabel148.setText("Requested Land");

        jLabel149.setText("Pending Land");

        jLabel150.setText("Approved Land");

        jLabel151.setText("Project Completed Land");

        jLabel152.setText("locked land");

        jLabel153.setText("Rejected Land");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfr7, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(tfwr7)
                            .addComponent(tfs7)
                            .addComponent(tftpt7))
                        .addGap(177, 177, 177)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfco7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfll7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfat7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfst7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel147)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cb7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel148)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel149)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel150)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel151)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel152)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel153)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(image6, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel147)
                    .addComponent(jLabel50)
                    .addComponent(jLabel148)
                    .addComponent(jLabel51)
                    .addComponent(jLabel149)
                    .addComponent(jLabel52)
                    .addComponent(jLabel150)
                    .addComponent(jLabel53)
                    .addComponent(jLabel151)
                    .addComponent(jLabel54)
                    .addComponent(jLabel152)
                    .addComponent(jLabel55)
                    .addComponent(jLabel153)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addComponent(cb7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(image6, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftpt7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfst7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfat7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfs7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfll7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfwr7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfco7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfr7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rejected Land", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1498, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            Object lrid;
            System.out.println("hellosudeep");
            System.out.println(ParabitLogin.srs.getString(13));
            if (ParabitLogin.srs.getString(13).equals("Land Inspector")) {
                int selectedRow = jTable1.getSelectedRow();
                lrid = jTable1.getValueAt(selectedRow, 0); // Replace 0 with the column index of LRID
                String s = "update landprofile set Status = 'Requested Land', NotificationText = NULL where landprofile.LRID = " + lrid;
                System.out.println(s);
                obdb.stm.executeUpdate(s);
                JOptionPane.showMessageDialog(null, "Done");
                funct();
            }
            if (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer")) {
                int selectedRow = jTable2.getSelectedRow();
                lrid = jTable2.getValueAt(selectedRow, 0);
                String s = "update landprofile set Status = 'Pending Land' where landprofile.LRID = " + lrid;
                System.out.println(s);
                obdb.stm.executeUpdate(s);
                JOptionPane.showMessageDialog(null, "Done");
                funct();
//                tb1model.removeRow(selectedRow);
            }
            if (ParabitLogin.srs.getString(13).equals("Municipal Commissioner")) {
                int selectedRow = jTable3.getSelectedRow();
                lrid = jTable3.getValueAt(selectedRow, 0);
                String s = "update landprofile set Status = 'Approved Land' where landprofile.LRID = " + lrid;
                System.out.println(s);
                obdb.stm.executeUpdate(s);
                JOptionPane.showMessageDialog(null, "Done");
                funct();
//                tb1model.removeRow(selectedRow);
            }
//            stopProcess();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "1,Some Problem " + e);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            int c = jTable1.getSelectedRow();
            int selectedcolumn = 0;
            Object cellData = jTable1.getValueAt(c, selectedcolumn);

            String s = "update landprofile set Status = 'Rejected Land', NotificationText = NUll where LRID = " + cellData;
            System.out.println(s);
            int selection = JOptionPane.showConfirmDialog(null, "Do yoy want to delete this row", "confirm", JOptionPane.YES_NO_OPTION);
            if (selection == JOptionPane.YES_OPTION) {
                obdb.stm.executeUpdate(s);
                JOptionPane.showMessageDialog(null, "Land is Deleted");
                funct();
//                tb1model.removeRow(c);
            }
//            stopProcess();
        } catch (Exception e) {
            System.out.println(e);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    public void settf(String lrid) {
        try {
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
                    + "zw.DistrictCode AND lp.ZoneCode = zw.Zone "
                    + " and lp.LRID = " + lrid + " GROUP BY LRID";
            System.out.println(sss);
            obdb4.rs = obdb4.stm.executeQuery(sss);
            System.out.println(s);
            while (obdb4.rs.next()) {
                String s = obdb4.rs.getString(22);//status

                if (s.equals("Listed Land")) {
                    tftpt1.setText(obdb4.rs.getString(21));
                    tfs1.setText(obdb4.rs.getString(10));
                    tfwr1.setText(obdb4.rs.getString(19));
                    tfr1.setText(obdb4.rs.getString(13));
                    tfst1.setText(obdb4.rs.getString(18));
                    tfat1.setText(obdb4.rs.getString(17));
                    tfll1.setText(obdb4.rs.getString(14));
                    tfco1.setText(obdb4.rs.getString(12));
                } else if (s.equals("Requested Land")) {
                    tftpt2.setText(obdb4.rs.getString(21));
                    tfs2.setText(obdb4.rs.getString(10));
                    tfwr2.setText(obdb4.rs.getString(19));
                    tfr2.setText(obdb4.rs.getString(13));
                    tfst2.setText(obdb4.rs.getString(18));
                    tfat2.setText(obdb4.rs.getString(17));
                    tfll2.setText(obdb4.rs.getString(14));
                    tfco2.setText(obdb4.rs.getString(12));
                } else if (s.equals("Pending Land")) {
                    tftpt3.setText(obdb4.rs.getString(21));
                    tfs3.setText(obdb4.rs.getString(10));
                    tfwr3.setText(obdb4.rs.getString(19));
                    tfr3.setText(obdb4.rs.getString(13));
                    tfst3.setText(obdb4.rs.getString(18));
                    tfat3.setText(obdb4.rs.getString(17));
                    tfll3.setText(obdb4.rs.getString(14));
                    tfco3.setText(obdb4.rs.getString(12));
                } else if (s.equals("Approved Land")) {
                    tftpt4.setText(obdb4.rs.getString(21));
                    tfs4.setText(obdb4.rs.getString(10));
                    tfwr4.setText(obdb4.rs.getString(19));
                    tfr4.setText(obdb4.rs.getString(13));
                    tfst4.setText(obdb4.rs.getString(18));
                    tfat4.setText(obdb4.rs.getString(17));
                    tfll4.setText(obdb4.rs.getString(14));
                    tfco4.setText(obdb4.rs.getString(12));
                } else if (s.equals("Project Completed Land")) {
                    tftpt5.setText(obdb4.rs.getString(21));
                    tfs5.setText(obdb4.rs.getString(10));
                    tfwr5.setText(obdb4.rs.getString(19));
                    tfr5.setText(obdb4.rs.getString(13));
                    tfst5.setText(obdb4.rs.getString(18));
                    tfat5.setText(obdb4.rs.getString(17));
                    tfll5.setText(obdb4.rs.getString(14));
                    tfco5.setText(obdb4.rs.getString(12));
                } else if (s.equals("Locked Land")) {
                    tftpt6.setText(obdb4.rs.getString(21));
                    tfs6.setText(obdb4.rs.getString(10));
                    tfwr6.setText(obdb4.rs.getString(19));
                    tfr6.setText(obdb4.rs.getString(13));
                    tfst6.setText(obdb4.rs.getString(18));
                    tfat6.setText(obdb4.rs.getString(17));
                    tfll6.setText(obdb4.rs.getString(14));
                    tfco6.setText(obdb4.rs.getString(12));
                } else if (s.equals("Rejected Land")) {
                    tftpt7.setText(obdb4.rs.getString(21));
                    tfs7.setText(obdb4.rs.getString(10));
                    tfwr7.setText(obdb4.rs.getString(19));
                    tfr7.setText(obdb4.rs.getString(13));
                    tfst7.setText(obdb4.rs.getString(18));
                    tfat7.setText(obdb4.rs.getString(17));
                    tfll7.setText(obdb4.rs.getString(14));
                    tfco7.setText(obdb4.rs.getString(12));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void tfwr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfwr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfwr1ActionPerformed

    private void tfll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfll1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfll1ActionPerformed

    private void tfwr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfwr2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfwr2ActionPerformed

    private void tfwr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfwr3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfwr3ActionPerformed

    private void tfr4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfr4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfr4ActionPerformed
    public void comboData(int n, JComboBox<String> cb, JTable table, String status) {
        if (tb1model == null) {
            tb1model = (DefaultTableModel) table.getModel();
        }
        if (n == 0) {
            tb1model.setRowCount(0);

            try {
                System.out.println(ParabitLogin.srs.getInt(1));
                if (ParabitLogin.srs.getInt(1) > 4) {
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
                            + " and lp.StateCode = " + ParabitLogin.srs.getInt(2)
                            + " and lp.DistrictCode = " + ParabitLogin.srs.getInt(3)
                            + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4)
                            + " and lp.ZoneCode = " + ParabitLogin.srs.getInt(5) + " and lp.Status = '" + status + "' Group By LRID";
                } else if (ParabitLogin.srs.getInt(1) == 4) {
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
                            + " and lp.StateCode = " + ParabitLogin.srs.getInt(2)
                            + " and lp.DistrictCode = " + ParabitLogin.srs.getInt(3)
                            + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4)
                            + " and lp.Status = '" + status + "' Group By LRID";

                }
            } catch (Exception e) {
                System.out.println(e);
            }
            AllcomboBoxWardName(cb, table, sql1);

        } else {
            String wn = (String) cb.getItemAt(n);
            System.out.println(wn);
            tb1model.setRowCount(0);
            try {
                if (ParabitLogin.srs.getInt(1) > 4) {
                    sql2 = "select lp.LRID,lp.LandInspectorVerified,lp.City,lp.VillageCode,zw.WardName,"
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
                            + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4)
                            + " and lp.ZoneCode = " + ParabitLogin.srs.getInt(5) + " and lp.Status = '" + status + "' and zw.WardName = '" + wn + "' Group By LRID";
                } else if (ParabitLogin.srs.getInt(1) == 4) {
                    sql2 = "select lp.LRID,lp.LandInspectorVerified,lp.City,lp.VillageCode,zw.WardName,"
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
                            + " and lp.SubDistrictCode = " + ParabitLogin.srs.getInt(4)
                            + " and lp.Status = '" + status + "' and zw.WardName = '" + wn + "' Group By LRID";

                }
            } catch (Exception e) {
                System.out.println(e);
            }
            comboBoxWardName(cb, table, sql2);
        }
    }
    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
        int n = cb1.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb1, jTable1, "Listed Land");
    }//GEN-LAST:event_cb1ActionPerformed

    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
        int n = cb2.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb2, jTable2, "Requested Land");
    }//GEN-LAST:event_cb2ActionPerformed

    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
        int n = cb3.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb3, jTable3, "Pending Land");
    }//GEN-LAST:event_cb3ActionPerformed

    private void cb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb4ActionPerformed
        int n = cb4.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb4, jTable4, "Approved Land");
    }//GEN-LAST:event_cb4ActionPerformed

    private void cb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb5ActionPerformed
        int n = cb5.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb5, jTable5, "Project Completed Land");
    }//GEN-LAST:event_cb5ActionPerformed

    private void cb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb6ActionPerformed
        int n = cb6.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb6, jTable6, "Locked Land");
    }//GEN-LAST:event_cb6ActionPerformed

    private void cb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb7ActionPerformed
        int n = cb7.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb7, jTable7, "Rejected Land");
    }//GEN-LAST:event_cb7ActionPerformed

    private void cb2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb2ItemStateChanged

        int n = cb2.getSelectedIndex();
        System.out.println(n);
        comboData(n, cb2, jTable2, "Requested Land");

        // TODO add your handling code here:
    }//GEN-LAST:event_cb2ItemStateChanged

    private void cb1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cb1ItemStateChanged

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
    }//GEN-LAST:event_jTabbedPane1MouseClicked
    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
    }//GEN-LAST:event_jPanel2MouseEntered
    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
    }//GEN-LAST:event_jPanel3MouseEntered
    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
    }//GEN-LAST:event_jPanel4MouseEntered
    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
    }//GEN-LAST:event_jPanel5MouseEntered
    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
    }//GEN-LAST:event_jPanel6MouseEntered
    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
    }//GEN-LAST:event_jPanel8MouseEntered
    public Object LRID(JTable table) {
        Object lrid = null;
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            lrid = table.getValueAt(selectedRow, 0).toString();
            System.out.println(lrid + "hello");

        }
        return lrid;
    }
    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        Object lrid;
        JTable[] tables = {jTable1, jTable2, jTable3, jTable4, jTable5, jTable6, jTable7};
        for (JTable table : tables) {
            if (table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                lrid = table.getValueAt(selectedRow, 0);
                this.setVisible(false);
//                NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1, table, jCheckBoxMenuItem2);
                NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1);
                nlr.setVisible(true);
            }
        }
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jCheckBoxMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem2ActionPerformed
        Object lrid;
        JTable[] tables = {jTable1, jTable2, jTable3, jTable4, jTable5, jTable6, jTable7};
        for (JTable table : tables) {
            if (table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                lrid = table.getValueAt(selectedRow, 0);
                this.setVisible(false);
                NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem1, table, jCheckBoxMenuItem2);
                nlr.setVisible(true);
            }
        }
    }//GEN-LAST:event_jCheckBoxMenuItem2ActionPerformed

    private void jCheckBoxMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem3ActionPerformed
//        // update the data
        Object lrid;

        int selectedRow = jTable1.getSelectedRow();
        lrid = jTable1.getValueAt(selectedRow, 0);
        this.setVisible(false);
        NewLandRegistration nlr = new NewLandRegistration(null, false, lrid, jCheckBoxMenuItem3, jTable1);
        nlr.setVisible(true);


    }//GEN-LAST:event_jCheckBoxMenuItem3ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LandDetails dialog = new LandDetails(new javax.swing.JFrame(), true, "", null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JComboBox<String> cb4;
    private javax.swing.JComboBox<String> cb5;
    private javax.swing.JComboBox<String> cb6;
    private javax.swing.JComboBox<String> cb7;
    private javax.swing.JLabel image;
    private javax.swing.JLabel image1;
    private javax.swing.JLabel image2;
    private javax.swing.JLabel image3;
    private javax.swing.JLabel image4;
    private javax.swing.JLabel image5;
    private javax.swing.JLabel image6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField tfat1;
    private javax.swing.JTextField tfat2;
    private javax.swing.JTextField tfat3;
    private javax.swing.JTextField tfat4;
    private javax.swing.JTextField tfat5;
    private javax.swing.JTextField tfat6;
    private javax.swing.JTextField tfat7;
    private javax.swing.JTextField tfco1;
    private javax.swing.JTextField tfco2;
    private javax.swing.JTextField tfco3;
    private javax.swing.JTextField tfco4;
    private javax.swing.JTextField tfco5;
    private javax.swing.JTextField tfco6;
    private javax.swing.JTextField tfco7;
    private javax.swing.JTextField tfll1;
    private javax.swing.JTextField tfll2;
    private javax.swing.JTextField tfll3;
    private javax.swing.JTextField tfll4;
    private javax.swing.JTextField tfll5;
    private javax.swing.JTextField tfll6;
    private javax.swing.JTextField tfll7;
    private javax.swing.JTextField tfr1;
    private javax.swing.JTextField tfr2;
    private javax.swing.JTextField tfr3;
    private javax.swing.JTextField tfr4;
    private javax.swing.JTextField tfr5;
    private javax.swing.JTextField tfr6;
    private javax.swing.JTextField tfr7;
    private javax.swing.JTextField tfs1;
    private javax.swing.JTextField tfs2;
    private javax.swing.JTextField tfs3;
    private javax.swing.JTextField tfs4;
    private javax.swing.JTextField tfs5;
    private javax.swing.JTextField tfs6;
    private javax.swing.JTextField tfs7;
    private javax.swing.JTextField tfst1;
    private javax.swing.JTextField tfst2;
    private javax.swing.JTextField tfst3;
    private javax.swing.JTextField tfst4;
    private javax.swing.JTextField tfst5;
    private javax.swing.JTextField tfst6;
    private javax.swing.JTextField tfst7;
    private javax.swing.JTextField tftpt1;
    private javax.swing.JTextField tftpt2;
    private javax.swing.JTextField tftpt3;
    private javax.swing.JTextField tftpt4;
    private javax.swing.JLabel tftpt4ll;
    private javax.swing.JTextField tftpt5;
    private javax.swing.JTextField tftpt6;
    private javax.swing.JTextField tftpt7;
    private javax.swing.JTextField tfwr1;
    private javax.swing.JTextField tfwr2;
    private javax.swing.JTextField tfwr3;
    private javax.swing.JTextField tfwr4;
    private javax.swing.JTextField tfwr5;
    private javax.swing.JTextField tfwr6;
    private javax.swing.JTextField tfwr7;
    // End of variables declaration//GEN-END:variables
}
