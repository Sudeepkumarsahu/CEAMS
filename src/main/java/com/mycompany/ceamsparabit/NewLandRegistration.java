package com.mycompany.ceamsparabit;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import ceamsexperts.Activities;

public class NewLandRegistration extends javax.swing.JDialog {

    JCheckBoxMenuItem jCheckBoxMenuItem1;
    JCheckBoxMenuItem jCheckBoxMenuItem2;
    JTable table;
    PvtImageLoader il;
    ParabitCeamsDb obdb;
    ParabitCeamsDb obdb1;
    String PollutionIndex, ColonySociety, Address, LandMark, AreaSqft, CenterCoOrdinate, Radius;
    int WC, ZC, VV, SDC, DC, SC, VC;
    String WN, SDN, DN, SN, cityName, imgquery, status;
    int ws, lt, st, pt, at, shapeT;
    ZoneId z = ZoneId.of("Asia/Kolkata");
    LocalDate today = LocalDate.now(z);
    LocalTime currentTime = LocalTime.now(z);
    Random r = new Random();
    int cmt = r.nextInt();
    Object lrid2, lrID;
    Object lrid;
    StringBuilder textt;

    public NewLandRegistration(java.awt.Frame parent, boolean modal, Object lrID, JCheckBoxMenuItem jCheckBoxMenuItem1, JTable table, JCheckBoxMenuItem jCheckBoxMenuItem2) {
        super(parent, modal);
        initComponents();
        this.lrID = lrID;
        this.jCheckBoxMenuItem1 = jCheckBoxMenuItem1;
        this.jCheckBoxMenuItem2 = jCheckBoxMenuItem2;
        this.table = table;
        il = new PvtImageLoader();
        obdb = new ParabitCeamsDb();
        obdb1 = new ParabitCeamsDb();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        this.setSize(screenSize.width, screenSize.height);
        this.setVisible(true);
        System.out.println(jCheckBoxMenuItem1.isSelected());
        System.out.println(jCheckBoxMenuItem1.getText());
        System.out.println(jCheckBoxMenuItem2.isSelected());
        System.out.println(jCheckBoxMenuItem2.getText());
        try {

            if (!(jCheckBoxMenuItem1.isSelected()) && jCheckBoxMenuItem1.getText().equals(" update Details")) {
                jButton1.setVisible(false);
                jButton4.setVisible(false);
                jButton5.setVisible(false);
                jButton2.setVisible(false);
                jButton3.setVisible(false);
                System.out.println("dgska");
                completeDetail();
            }

            System.out.println(ParabitLogin.srs.getInt(1));

            if (ParabitLogin.srs.getInt(1) == 4) {
                this.jButton1.setVisible(false);
                this.jButton4.setVisible(false);
                this.jButton3.setVisible(false);
                this.jButton2.setVisible(false);
                img1.setEnabled(false);
                img2.setEnabled(false);
                img3.setEnabled(false);
                img4.setEnabled(false);
                img5.setEnabled(false);
            }

//             System.out.println(jCheckBoxMenuItem2.isSelected()+"ssssssssssssssss");
            if (!(jCheckBoxMenuItem2.isSelected())) {//remark
                System.out.println("bsadkbkeb");
                completeDetail();
                this.jButton1.setVisible(false);
                jButton4.setVisible(false);
                jButton3.setVisible(false);
                jButton2.setVisible(false);
                img1.setEnabled(false);
                img2.setEnabled(false);
                img3.setEnabled(false);
                img4.setEnabled(false);
                img5.setEnabled(false);
            }
        } catch (Exception e) {

        }
        System.out.println(jCheckBoxMenuItem1.isSelected());
        try {
            //for checkboxes invisible
            if (ParabitLogin.srs.getInt(1) == 4 || !(jCheckBoxMenuItem1.isSelected())) {
                System.out.println("Menu");
                JCheckBox[] checkboxes = {
                    jCheckBox5, jCheckBox6,
                    jCheckBox9, jCheckBox11, jCheckBox12, jCheckBox13,
                    jCheckBox16, jCheckBox17, jCheckBox18, jCheckBox20, jCheckBox21, jCheckBox22,
                    jCheckBox24, jCheckBox25, jCheckBox27, jCheckBox28,
                    jCheckBox30, jCheckBox31, jCheckBox32, jCheckBox33, jCheckBox34, jCheckBox35, jCheckBox36,
                    jCheckBox37, jCheckBox38, jCheckBox39, jCheckBox40, jCheckBox41, jCheckBox42, jCheckBox43,
                    jCheckBox44, jCheckBox45, jCheckBox46
                };
                System.out.println(jCheckBoxMenuItem1.isSelected());
                System.out.println(jCheckBoxMenuItem1.getText());
                System.out.println(jCheckBoxMenuItem2.isSelected());
                System.out.println(jCheckBoxMenuItem2.getText());

                if (!(jCheckBoxMenuItem1.isSelected())) {//complete details
                    for (JCheckBox checkbox : checkboxes) {
                        checkbox.setVisible(false);
                    }
                    jButton5.setVisible(false);
                }
            }
            //forcomplete detail
            if ((ParabitLogin.srs.getString(13).equals("Municipal Commissioner") && !(jCheckBoxMenuItem1.isSelected())) || (ParabitLogin.srs.getString(13).equals("Zone Municipal Officer") && !(jCheckBoxMenuItem1.isSelected())) || (ParabitLogin.srs.getString(13).equals("Land Inspector") && !(jCheckBoxMenuItem1.isSelected()) && jCheckBoxMenuItem1.getText().equals("Complete Details"))) {
                this.jButton1.setVisible(false);
                this.jButton4.setVisible(false);
                this.jButton3.setVisible(false);
                this.jButton2.setVisible(false);
                this.jButton5.setVisible(false);
                img1.setEnabled(false);
                img2.setEnabled(false);
                img3.setEnabled(false);
                img4.setEnabled(false);
                img5.setEnabled(false);
//              
                completeDetail();
            }
        } catch (Exception e) {

        }
        try {
        } catch (Exception e) {

        }
    }

    public void completeDetail() {
        try {
            String ss = "select DISTINCT(lp.StateCode),lp.StateName,lp.DistrictCode,"
                    + "lp.DistrictName,lp.SubDistrictCode,lp.SubDistrictName,"
                    + "lp.ZoneCode,lp.WardCode,lp.WardName,lp.VillageCode,lp.City,"
                    + "lp.LMarkDate,lp.LMarkTime,lty.TypeOfLand,sy.SoilType,wa.Resource,"
                    + "ay.MarkAreaType,p.TypeOfPollution,lp.PolutionIndex,lp.Status,"
                    + "lp.ColonySocietyName,lp.Address,lp.LandMark,lp.AreaSqft,shy.ShapeName,"
                    + "lp.CenterCoOrdinate,lp.Radius,lp.LL1,lp.LL2,lp.LL3,lp.LL4,lp.LL5,"
                    + "lp.LL6,lp.LL7,lp.LL8,lp.LL9,lp.LL10,lp.LL11,lp.LL12 from landprofile "
                    + "lp, zoneward zw, shapetype shy, areatype ay, typesofsoil sy, "
                    + "waterresources wa, pollution p, landtype lty WHERE lp.Shape = shy.SNo "
                    + "AND lp.AreaType = ay.SNo AND lp.SoilType = sy.SNo AND lp.Wavialable "
                    + "= wa.SNo AND lp.LandType = lty.SNo AND lp.PolutionType = p.SNo AND "
                    + "lp.statecode = zw.StateCode AND lp.districtcode = zw.DistrictCode AND "
                    + "lp.ZoneCode = zw.Zone and lp.LRID = " + lrID;
            System.out.println(ss);
            obdb1.rs = obdb1.stm.executeQuery(ss);
            obdb1.rs.next();
            String wardname = obdb1.rs.getString(9);
            String ltype = obdb1.rs.getString(14);
            String stype = obdb1.rs.getString(15);
            String wresource = obdb1.rs.getString(16);
            String atype = obdb1.rs.getString(17);
            String polltype = obdb1.rs.getString(18);
            String shatype = obdb1.rs.getString(25);
            String statename = obdb1.rs.getString(2);
            String subdisttname = obdb1.rs.getString(6);
            String disttname = obdb1.rs.getString(4);
            String statuses = obdb1.rs.getString(20);
            int villcode = obdb1.rs.getInt(10);
            int wardcode = obdb1.rs.getInt(8);
            int zonecode = obdb1.rs.getInt(7);
            int subdisttcode = obdb1.rs.getInt(5);
            int disttcode = obdb1.rs.getInt(3);
            int statecode = obdb1.rs.getInt(1);
            cb1.addItem(String.valueOf(ltype));
            cb2.addItem(String.valueOf(stype));
            cb3.addItem(String.valueOf(polltype));
            cb5.addItem(String.valueOf(atype));
            cb4.addItem(String.valueOf(wresource));
            cb6.addItem(String.valueOf(shatype));
            cb7.addItem(String.valueOf(statuses));
            VCode.setText(VCode.getText() + " " + villcode);
            city.setText(obdb1.rs.getString(11));
            SDCode.setText(SDCode.getText() + " " + subdisttcode);
            zcode.setText(zcode.getText() + " " + zonecode);
            wcode.setText(wcode.getText() + " " + wardcode);
            WName.setText(WName.getText() + " " + wardname);
            DCode.setText(DCode.getText() + " " + disttcode);
            SCode.setText(SCode.getText() + " " + statecode);
            SName.setText(SName.getText() + " " + statename);
            SDName.setText(SDName.getText() + " " + subdisttname);
            DName.setText(DName.getText() + " " + disttname);
            PIL.setText(obdb1.rs.getString(19));
            CSL.setText(obdb1.rs.getString(21));
            AL.setText(obdb1.rs.getString(22));
            LML.setText(obdb1.rs.getString(23));
            ASFL.setText(obdb1.rs.getString(24));
            CCOL.setText(obdb1.rs.getString(26));
            RL.setText(obdb1.rs.getString(27));
            lltf1.setText(obdb1.rs.getString(28));
            lltf2.setText(obdb1.rs.getString(29));
            lltf3.setText(obdb1.rs.getString(30));
            lltf4.setText(obdb1.rs.getString(31));
            lltf5.setText(obdb1.rs.getString(32));
            lltf6.setText(obdb1.rs.getString(33));
            lltf7.setText(obdb1.rs.getString(34));
            lltf8.setText(obdb1.rs.getString(35));
            lltf9.setText(obdb1.rs.getString(36));
            lltf10.setText(obdb1.rs.getString(37));
            lltf11.setText(obdb1.rs.getString(38));
            lltf12.setText(obdb1.rs.getString(39));
            MDL.setText(MDL.getText() + " " + obdb1.rs.getString(12));
            MTL.setText(MTL.getText() + " " + obdb1.rs.getString(13));
            databaseImage1new("Img1", l1, l6);
            databaseImage1new("Img2", l2, l6);
            databaseImage1new("Img3", l3, l6);
            databaseImage1new("Img4", l4, l6);
            databaseImage1new("SImg", l5, l6);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//new land registration
    public NewLandRegistration(java.awt.Frame parent, boolean modal, Object lrID) {
        super(parent, modal);
        initComponents();
        System.out.println("hello sudeep");
        obdb = new ParabitCeamsDb();
        obdb1 = new ParabitCeamsDb();
        il = new PvtImageLoader();
        try {
//            String s = "SELECT DISTINCT nn.EmpName,nn.DistrictCode ,"
//                    + "nn.SubDistrictCode,vv.DistrictName,vv.SubDistrictName,"
//                    + "vv.StateName,vv.VillageVersion , vv.VillageCode  "
//                    + "FROM nnemprecords nn,villages vv WHERE nn.SubdistrictCode"
//                    + " = vv.SubDistrictCode and nn.districtcode = vv.DistrictCode"
//                    + " and NNEID =" + ParabitLogin.srs.getInt(1);
            String s = "SELECT nn.EmpName, nn.DistrictCode, nn.SubDistrictCode, vv.DistrictName, vv.SubDistrictName, vv.StateName, vv.VillageVersion, vv.VillageCode FROM ( SELECT DISTINCT EmpName, DistrictCode, SubDistrictCode, NNEID FROM nnemprecords WHERE NNEID = " + ParabitLogin.srs.getInt(1) + " ) nn JOIN villages vv ON nn.SubDistrictCode = vv.SubDistrictCode AND nn.DistrictCode = vv.DistrictCode LIMIT 1";
            System.out.println(s);
            obdb.rs = obdb.stm.executeQuery(s);
            obdb.rs.next();
            WN = ParabitLogin.srs.getString(8);
            SDN = obdb.rs.getString(5);
            DN = obdb.rs.getString(4);
            SN = obdb.rs.getString(6);
            VV = obdb.rs.getInt(7);
            VC = obdb.rs.getInt(8);
            WC = ParabitLogin.srs.getInt(6);
            ZC = ParabitLogin.srs.getInt(5);
            SDC = ParabitLogin.srs.getInt(4);
            DC = ParabitLogin.srs.getInt(3);
            SC = ParabitLogin.srs.getInt(2);
            wcode.setText(wcode.getText() + " " + WC);
            WName.setText(WName.getText() + " " + WN);
            zcode.setText(zcode.getText() + " " + ZC);
            VVersion.setText(VVersion.getText() + " " + VV);
            SDCode.setText(SDCode.getText() + " " + SDC);
            DCode.setText(DCode.getText() + " " + DC);
            SCode.setText(SCode.getText() + " " + SC);
            SName.setText(SName.getText() + " " + SN);
            SDName.setText(SDName.getText() + " " + SDN);
            DName.setText(DName.getText() + " " + DN);
            MDL.setText(MDL.getText() + " " + today);
            MTL.setText(MTL.getText() + " " + currentTime);
            VCode.setText(VCode.getText() + " " + VC);
        } catch (Exception e) {
            System.out.println(e);
        }
        addwaterType();
        LandType();
        SoilType();
        PollutionType();
        AreaType();
        ShapeType();
        cb7.setEnabled(false);
        JCheckBox[] checkboxes = {
            jCheckBox5, jCheckBox6,
            jCheckBox9, jCheckBox11, jCheckBox12, jCheckBox13,
            jCheckBox16, jCheckBox17, jCheckBox18, jCheckBox20, jCheckBox21, jCheckBox22,
            jCheckBox24, jCheckBox25, jCheckBox27, jCheckBox28,
            jCheckBox30, jCheckBox31, jCheckBox32, jCheckBox33, jCheckBox34, jCheckBox35, jCheckBox36,
            jCheckBox37, jCheckBox38, jCheckBox39, jCheckBox40, jCheckBox41, jCheckBox42, jCheckBox43,
            jCheckBox44, jCheckBox45, jCheckBox46
        };
        for (JCheckBox checkbox : checkboxes) {
            checkbox.setVisible(false);
        }
        jButton5.setVisible(false);
        jButton6.setVisible(false);

    }
//for complete detail 

    public NewLandRegistration(java.awt.Frame parent, boolean modal, Object lrID, JCheckBoxMenuItem jCheckBoxMenuItem3) {
        super(parent, modal);
        initComponents();
        this.lrID = lrID;
        obdb = new ParabitCeamsDb();
        obdb1 = new ParabitCeamsDb();
        il = new PvtImageLoader();
        this.jCheckBoxMenuItem1 = jCheckBoxMenuItem3;
        completeDetail();
//        cb7.setEnabled(false);
        JCheckBox[] checkboxes = {
            jCheckBox5, jCheckBox6,
            jCheckBox9, jCheckBox11, jCheckBox12, jCheckBox13,
            jCheckBox16, jCheckBox17, jCheckBox18, jCheckBox20, jCheckBox21, jCheckBox22,
            jCheckBox24, jCheckBox25, jCheckBox27, jCheckBox28,
            jCheckBox30, jCheckBox31, jCheckBox32, jCheckBox33, jCheckBox34, jCheckBox35, jCheckBox36,
            jCheckBox37, jCheckBox38, jCheckBox39, jCheckBox40, jCheckBox41, jCheckBox42, jCheckBox43,
            jCheckBox44, jCheckBox45, jCheckBox46
        };
        for (JCheckBox checkbox : checkboxes) {
            checkbox.setVisible(false);
        }
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        jButton6.setVisible(false);
        System.out.println("            ff    " + jCheckBoxMenuItem1.getText());
        if (jCheckBoxMenuItem1.getText().equals("complete  details")) {
            updatedetailsfun();
        }
    }

    //update detals
    public NewLandRegistration(java.awt.Frame parent, boolean modal, Object lrID, JCheckBoxMenuItem jCheckBoxMenuItem3, JTable table) {
        super(parent, modal);
        initComponents();
        this.lrID = lrID;
        obdb = new ParabitCeamsDb();
        obdb1 = new ParabitCeamsDb();
        il = new PvtImageLoader();
        this.jCheckBoxMenuItem1 = jCheckBoxMenuItem3;
        System.out.println(jCheckBoxMenuItem1.getText());
        if (jCheckBoxMenuItem1 != null && jCheckBoxMenuItem1.getText().equals("update details")) {
            JCheckBox[] checkboxes = {
                jCheckBox5, jCheckBox6,
                jCheckBox9, jCheckBox11, jCheckBox12, jCheckBox13,
                jCheckBox16, jCheckBox17, jCheckBox18, jCheckBox20, jCheckBox21, jCheckBox22,
                jCheckBox24, jCheckBox25, jCheckBox27, jCheckBox28,
                jCheckBox30, jCheckBox31, jCheckBox32, jCheckBox33, jCheckBox34, jCheckBox35, jCheckBox36,
                jCheckBox37, jCheckBox38, jCheckBox39, jCheckBox40, jCheckBox41, jCheckBox42, jCheckBox43,
                jCheckBox44, jCheckBox45, jCheckBox46
            };
            for (JCheckBox checkbox : checkboxes) {
                checkbox.setVisible(false);
            }
            updatedetailsfun();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox6 = new javax.swing.JCheckBox();
        tp1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        wcode = new javax.swing.JLabel();
        WName = new javax.swing.JLabel();
        zcode = new javax.swing.JLabel();
        VVersion = new javax.swing.JLabel();
        SDCode = new javax.swing.JLabel();
        SDName = new javax.swing.JLabel();
        DCode = new javax.swing.JLabel();
        DName = new javax.swing.JLabel();
        SCode = new javax.swing.JLabel();
        SName = new javax.swing.JLabel();
        MDL = new javax.swing.JLabel();
        MTL = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        CSL = new javax.swing.JTextField();
        AL = new javax.swing.JTextField();
        LML = new javax.swing.JTextField();
        ASFL = new javax.swing.JTextField();
        CCOL = new javax.swing.JTextField();
        RL = new javax.swing.JTextField();
        PIL = new javax.swing.JTextField();
        cb1 = new javax.swing.JComboBox<>();
        cb2 = new javax.swing.JComboBox<>();
        cb3 = new javax.swing.JComboBox<>();
        cb5 = new javax.swing.JComboBox<>();
        cb4 = new javax.swing.JComboBox<>();
        cb6 = new javax.swing.JComboBox<>();
        city = new javax.swing.JTextField();
        VCode = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb7 = new javax.swing.JComboBox<>();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox45 = new javax.swing.JCheckBox();
        jCheckBox46 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ll1 = new javax.swing.JLabel();
        ll2 = new javax.swing.JLabel();
        ll3 = new javax.swing.JLabel();
        ll4 = new javax.swing.JLabel();
        ll5 = new javax.swing.JLabel();
        ll6 = new javax.swing.JLabel();
        ll7 = new javax.swing.JLabel();
        ll8 = new javax.swing.JLabel();
        ll9 = new javax.swing.JLabel();
        ll10 = new javax.swing.JLabel();
        ll11 = new javax.swing.JLabel();
        ll12 = new javax.swing.JLabel();
        lltf2 = new javax.swing.JTextField();
        lltf1 = new javax.swing.JTextField();
        lltf3 = new javax.swing.JTextField();
        lltf4 = new javax.swing.JTextField();
        lltf5 = new javax.swing.JTextField();
        lltf6 = new javax.swing.JTextField();
        lltf8 = new javax.swing.JTextField();
        lltf7 = new javax.swing.JTextField();
        lltf9 = new javax.swing.JTextField();
        lltf10 = new javax.swing.JTextField();
        lltf11 = new javax.swing.JTextField();
        lltf12 = new javax.swing.JTextField();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox35 = new javax.swing.JCheckBox();
        jCheckBox36 = new javax.swing.JCheckBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jCheckBox38 = new javax.swing.JCheckBox();
        jCheckBox39 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        img1 = new javax.swing.JButton();
        img2 = new javax.swing.JButton();
        img3 = new javax.swing.JButton();
        img4 = new javax.swing.JButton();
        img5 = new javax.swing.JButton();
        l1 = new javax.swing.JLabel();
        l2 = new javax.swing.JLabel();
        l3 = new javax.swing.JLabel();
        l4 = new javax.swing.JLabel();
        l5 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        jCheckBox40 = new javax.swing.JCheckBox();
        jCheckBox41 = new javax.swing.JCheckBox();
        jCheckBox42 = new javax.swing.JCheckBox();
        jCheckBox43 = new javax.swing.JCheckBox();
        jCheckBox44 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jCheckBox6.setSelected(true);
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton1.setBackground(new java.awt.Color(20, 232, 208));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setText("next");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Land Registration");

        wcode.setText("Ward Code:");

        WName.setText("WardName : ");

        zcode.setText("Zone Code :");

        VVersion.setText("City");

        SDCode.setText("Sub District Code : ");

        SDName.setText("Sub District Name :");

        DCode.setText("District Code : ");

        DName.setText("District Name :");

        SCode.setText("State Code : ");

        SName.setText("State Name :");

        MDL.setText("Marking Date");

        MTL.setText("Marking Time");

        jLabel15.setText("Land Type");

        jLabel16.setText("Soil Type");

        jLabel17.setText("Pollution Index");

        jLabel18.setText("Pollution Type");

        jLabel19.setText("Area Type");

        jLabel20.setText("Water Availability");

        jLabel21.setText("Colony/Society");

        jLabel22.setText("Address");

        jLabel23.setText("Land Mark");

        jLabel24.setText("Area SquareFit");

        jLabel25.setText("Shape");

        jLabel26.setText("Center CoOrdinate");

        jLabel27.setText("Radius");

        CSL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        CSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CSLActionPerformed(evt);
            }
        });

        AL.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        LML.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        LML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LMLActionPerformed(evt);
            }
        });

        ASFL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ASFL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASFLActionPerformed(evt);
            }
        });

        CCOL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        PIL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PILActionPerformed(evt);
            }
        });

        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        cb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb2ActionPerformed(evt);
            }
        });

        cb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb3ActionPerformed(evt);
            }
        });

        cb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb5ActionPerformed(evt);
            }
        });

        cb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb4ActionPerformed(evt);
            }
        });

        cb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb6ActionPerformed(evt);
            }
        });

        city.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityActionPerformed(evt);
            }
        });

        VCode.setText("Village Code :  ");

        jLabel1.setText("Status");

        cb7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Listed Land", "Pending Land", "Activated Land", "Rejected Land", "Projected Completed Land", "Locked Land" }));
        cb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb7ActionPerformed(evt);
            }
        });

        jCheckBox5.setSelected(true);
        jCheckBox5.setText("City is wrong");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jCheckBox9.setSelected(true);
        jCheckBox9.setText("Area Type is wrong");

        jCheckBox11.setSelected(true);
        jCheckBox11.setText("Land Type is wrong");

        jCheckBox12.setSelected(true);
        jCheckBox12.setText("Shape is wrong");

        jCheckBox13.setSelected(true);
        jCheckBox13.setText("Pollution Index is wrong");

        jCheckBox16.setSelected(true);
        jCheckBox16.setText("Address is wrong");

        jCheckBox17.setSelected(true);
        jCheckBox17.setText("Pollution Type is wrong");

        jCheckBox18.setSelected(true);
        jCheckBox18.setText("Water Availability is wrong");

        jCheckBox20.setSelected(true);
        jCheckBox20.setText("Status is wrong");

        jCheckBox24.setSelected(true);
        jCheckBox24.setText("Soil Type is wrong");

        jCheckBox25.setSelected(true);
        jCheckBox25.setText("Area SquareFit is wrong");

        jCheckBox27.setSelected(true);
        jCheckBox27.setText("Land Mark is wrong");

        jCheckBox28.setSelected(true);
        jCheckBox28.setText("Colony/Society name is wrong");

        jCheckBox45.setSelected(true);
        jCheckBox45.setText("Radius is wrong");

        jCheckBox46.setSelected(true);
        jCheckBox46.setText("Center CoOrdinate is wrong");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBox25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, Short.MAX_VALUE)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ASFL, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(CSL, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(LML, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(CCOL, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(RL, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(AL, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(cb6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(2192, 2192, 2192))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(WName, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(wcode, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zcode, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(VVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 42, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(MDL, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(MTL, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(SDCode, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(SDName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(68, 68, 68)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DName, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(DCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(PIL, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SCode, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SName, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(113, 113, 113)
                                .addComponent(VCode, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1030, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cb5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(75, 75, 75)
                                        .addComponent(jCheckBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cb7, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(976, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(wcode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(zcode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SCode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(VCode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DCode, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SDCode, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(DName, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(SDName, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addComponent(SName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(VVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(city)
                        .addComponent(WName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jCheckBox20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(PIL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cb5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cb7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(MDL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jCheckBox17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(MTL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CSL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCheckBox28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(AL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LML, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBox27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ASFL, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CCOL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(305, 305, 305))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(285, 285, 285))))
        );

        tp1.addTab(" Land Marking Address", jPanel1);

        jButton3.setText("Next");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Previous");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ll1.setBackground(new java.awt.Color(224, 198, 198));
        ll1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll1.setText("Latitude Longitude1");

        ll2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll2.setText("Latitude Longitude2");

        ll3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll3.setText("Latitude Longitude3");

        ll4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll4.setText("Latitude Longitude4");

        ll5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll5.setText("Latitude Longitude5");

        ll6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll6.setText("Latitude Longitude6");

        ll7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll7.setText("Latitude Longitude7");

        ll8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll8.setText("Latitude Longitude8");

        ll9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll9.setText("Latitude Longitude9");

        ll10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll10.setText("Latitude Longitude10");

        ll11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll11.setText("Latitude Longitude11");

        ll12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ll12.setText("Latitude Longitude12");

        lltf2.setText("890823");

        lltf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf1ActionPerformed(evt);
            }
        });

        lltf3.setText("27197");

        lltf4.setText("90-0124");
        lltf4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf4ActionPerformed(evt);
            }
        });

        lltf5.setText("0-9283");
        lltf5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf5ActionPerformed(evt);
            }
        });

        lltf6.setText("87632");
        lltf6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf6ActionPerformed(evt);
            }
        });

        lltf8.setText("78621");

        lltf7.setText("73268");
        lltf7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf7ActionPerformed(evt);
            }
        });

        lltf9.setText("765312");
        lltf9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf9ActionPerformed(evt);
            }
        });

        lltf10.setText("76821");

        lltf11.setText("7623");

        lltf12.setText("8239");
        lltf12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lltf12ActionPerformed(evt);
            }
        });

        jCheckBox21.setSelected(true);
        jCheckBox21.setText("Latitude Longitude 7 is wrong");

        jCheckBox22.setSelected(true);
        jCheckBox22.setText("Latitude Longitude 8 is wrong");

        jCheckBox30.setSelected(true);
        jCheckBox30.setText("Latitude Longitude 9 is wrong");

        jCheckBox31.setSelected(true);
        jCheckBox31.setText("Latitude Longitude 10 is wrong");

        jCheckBox32.setSelected(true);
        jCheckBox32.setText("Latitude Longitude 11 is wrong");

        jCheckBox33.setSelected(true);
        jCheckBox33.setText("Latitude Longitude 12 is wrong");

        jCheckBox34.setSelected(true);
        jCheckBox34.setText("Latitude Longitude 1 is wrong");

        jCheckBox35.setSelected(true);
        jCheckBox35.setText("Latitude Longitude 3 is wrong");

        jCheckBox36.setSelected(true);
        jCheckBox36.setText("Latitude Longitude 2 is wrong");

        jCheckBox37.setSelected(true);
        jCheckBox37.setText("Latitude Longitude 4 is wrong");

        jCheckBox38.setSelected(true);
        jCheckBox38.setText("Latitude Longitude 5 is wrong");

        jCheckBox39.setSelected(true);
        jCheckBox39.setText("Latitude Longitude 6 is wrong");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jCheckBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox37, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox38, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox39, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ll4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ll5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ll6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(ll3)
                            .addComponent(ll2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lltf5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lltf4, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(lltf3)
                                    .addComponent(lltf6)))
                            .addComponent(lltf2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ll1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lltf1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ll9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ll10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ll11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ll12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ll7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ll8)))
                        .addGap(253, 253, 253)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lltf8, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lltf12, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lltf11, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lltf10, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lltf9, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lltf7, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1254, 1254, 1254))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1022, 1022, 1022))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(lltf7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(ll7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jCheckBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lltf1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ll1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(14, 14, 14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jCheckBox21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lltf2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lltf8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ll2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCheckBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ll8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jCheckBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ll3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lltf3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ll9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lltf9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ll4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lltf4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ll10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lltf10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ll5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lltf5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ll11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lltf11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ll12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lltf12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lltf6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ll6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(301, 301, 301))
        );

        tp1.addTab(" ressgeographical add", jPanel2);

        jButton2.setText("Previous");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        img1.setText("image 1");
        img1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img1ActionPerformed(evt);
            }
        });

        img2.setText("image 2");
        img2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img2ActionPerformed(evt);
            }
        });

        img3.setText("image 3");
        img3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img3ActionPerformed(evt);
            }
        });

        img4.setText("image 4");
        img4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img4ActionPerformed(evt);
            }
        });

        img5.setText("image 5");
        img5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                img5ActionPerformed(evt);
            }
        });

        l1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        l1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                l1MouseEntered(evt);
            }
        });

        l2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        l2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                l2MouseEntered(evt);
            }
        });

        l3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        l3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                l3MouseEntered(evt);
            }
        });

        l4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        l4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                l4MouseEntered(evt);
            }
        });

        l5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        l5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                l5MouseEntered(evt);
            }
        });

        l6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBox40.setSelected(true);
        jCheckBox40.setText("image 1 is wrong");

        jCheckBox41.setSelected(true);
        jCheckBox41.setText("image 4 is wrong");

        jCheckBox42.setSelected(true);
        jCheckBox42.setText("image 5 is wrong");

        jCheckBox43.setSelected(true);
        jCheckBox43.setText("image 2 is wrong");

        jCheckBox44.setSelected(true);
        jCheckBox44.setText("image 3 is wrong");

        jButton5.setText("Send to remark");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Update Detail");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jCheckBox41, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(img4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox40, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(img1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBox43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(img2))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox44, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(img3))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jCheckBox42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(img5)))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(132, 132, 132)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(l5, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(l3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(104, 104, 104)
                        .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 870, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(img1)
                                    .addComponent(jCheckBox40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(112, 112, 112)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(img2)
                                            .addComponent(jCheckBox43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(115, 115, 115)
                                        .addComponent(img3))
                                    .addComponent(jCheckBox44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(132, 132, 132)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(img4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(136, 136, 136)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(img5)
                                    .addComponent(jCheckBox42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addGap(113, 113, 113))
        );

        tp1.addTab("Site images", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tp1, javax.swing.GroupLayout.PREFERRED_SIZE, 1063, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void databaseImage1new(String image, JLabel label, JLabel boxlabel) {
        try {
            String query = "";
            // SQL query to get the image from the database
            try {
                query = "SELECT lp." + image + " FROM landprofile lp, nnemprecords nn WHERE nn.LoginID = "
                        + ParabitLogin.srs.getInt(25) + " and nn.LoginPSW = "
                        + ParabitLogin.srs.getInt(26) + " and nn.Status = 1 and lp.LRID = " + lrID;

            } catch (Exception e) {
                query = "SELECT " + image + " FROM landprofile WHERE LRID = " + lrID;
            }
            System.out.println(query);
            obdb.stm = obdb.con.prepareStatement(query);

            // Execute the query
            obdb.rs = obdb.stm.executeQuery(query);

            if (obdb.rs.next()) {
                byte[] imageData = obdb.rs.getBytes(image); // Replace "EmpPhoto" with your actual image column name

                // Check if imageData is not null and has data
                if (imageData != null && imageData.length > 0) {
                    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                    Image img = ImageIO.read(bais);

                    // Check if img was successfully created
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

//insert data into landprofile
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!(city.getText().isEmpty()) && !(PIL.getText().isEmpty()) && !(CSL.getText().isEmpty()) && !(AL.getText().isEmpty()) && !(LML.getText().isEmpty()) && !(ASFL.getText().isEmpty()) && !(CCOL.getText().isEmpty()) && !(RL.getText().isEmpty())) {
            PollutionIndex = PIL.getText();
            ColonySociety = CSL.getText();
            Address = AL.getText();
            LandMark = LML.getText();
            AreaSqft = ASFL.getText();
            CenterCoOrdinate = CCOL.getText();
            Radius = RL.getText();
            cityName = city.getText();
            ws = cb4.getSelectedIndex() + 1;
            lt = cb1.getSelectedIndex() + 1;
            st = cb2.getSelectedIndex() + 1;
            pt = cb3.getSelectedIndex() + 1;
            at = cb5.getSelectedIndex() + 1;
            shapeT = cb6.getSelectedIndex() + 1;
            status = cb7.getSelectedItem().toString();

            String insertQuery = "INSERT INTO landprofile(statecode,districtcode"
                    + ",SubDistrictcode,ZoneCode,WardCode,City,PolutionIndex,"
                    + "ColonySocietyName,Address,Landmark,AreaSqft,"
                    + "CenterCoOrdinate,Radius,Wavialable,LandType,"
                    + "SoilType,PolutionType,AreaType,Shape,LmarkDate,"
                    + "Comment,LMarkTime,VillageCode,Status,WardName,SubDistrictName,DistrictName,StateName)values("
                    + SC + "," + DC + "," + SDC + "," + ZC + "," + WC
                    + ",'" + cityName + "'," + PollutionIndex + ",'"
                    + ColonySociety + "','" + Address + "','" + LandMark
                    + "'," + AreaSqft + ",'" + CenterCoOrdinate + "'," + Radius
                    + "," + ws + "," + lt + "," + st + "," + pt + "," + at
                    + "," + shapeT + ",'" + today + "','" + cmt + "','" + currentTime
                    + "'," + VC + ",'" + status + "','" + WN + "','" + SDN + "','" + DN + "','" + SN + "')";
            System.out.println(insertQuery);
            try {
                obdb.stm.executeUpdate(insertQuery);
            } catch (Exception e) {
                System.out.println(e);
            }
            String s = "select lrid from landprofile where WardCode = "
                    + WC + " and Comment ='" + cmt + "'";
            System.out.println(s);
            try {
                obdb1.rs = obdb1.stm.executeQuery(s);
                obdb1.rs.next();
//                lrid = obdb1.rs.getInt(1);
                lrid = obdb1.rs.getInt(1);
            } catch (Exception e) {
                System.out.print(e);
            }
            tp1.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(null, "please fill the important field");
        }

    }//GEN-LAST:event_jButton1ActionPerformed
//insert latitude and longitude
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tp1.setSelectedIndex(2);
        String setLL = "UPDATE landprofile SET LL1 ='" + lltf1.getText() + "',"
                + "LL2 = '" + lltf2.getText() + "'," + "LL3 = '" + lltf3.getText()
                + "'," + "LL4 = '" + lltf4.getText() + "'," + "LL5 = '"
                + lltf5.getText() + "'," + "LL6 = '" + lltf6.getText() + "',"
                + "LL7 = '" + lltf7.getText() + "'," + "LL8 = '" + lltf8.getText()
                + "'," + "LL9 = '" + lltf9.getText() + "'," + "LL10 = '" + lltf10.getText()
                //                + "'," + "LL11 = '" + lltf11.getText() + "'," + "LL12 = '" + lltf12.getText() + "' where LRID =" + lrid;
                + "'," + "LL11 = '" + lltf11.getText() + "'," + "LL12 = '" + lltf12.getText() + "' where LRID =" + lrid;
        try {
            System.out.println(setLL);
            obdb.stm.executeUpdate(setLL);
        } catch (Exception e) {
            System.out.println(e + "hello");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tp1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void lltf6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf6ActionPerformed
    }//GEN-LAST:event_lltf6ActionPerformed

    private void lltf5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf5ActionPerformed
    }//GEN-LAST:event_lltf5ActionPerformed

    private void lltf4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf4ActionPerformed
    }//GEN-LAST:event_lltf4ActionPerformed

    private void img5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img5ActionPerformed
        il.loadImage(l5, 4);
//        imgquery = "UPDATE landprofile SET SImg = ? where LRID =" + lrid;
//        if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
//            imgquery = "UPDATE landprofile SET SImg = ? where LRID =" + lrID;
//        } else {
//            imgquery = "UPDATE landprofile SET SImg = ? where LRID =" + lrid;
//
//        }
        if (jCheckBoxMenuItem1 == null) {
            imgquery = "UPDATE landprofile SET SImg = ? where LRID =" + lrid;
        } else {
            if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
                imgquery = "UPDATE landprofile SET SImg = ? where LRID =" + lrID;
            }
        }
        System.out.println(imgquery);
        imageUpload(imgquery);
    }//GEN-LAST:event_img5ActionPerformed

    private void img4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img4ActionPerformed
        il.loadImage(l4, 3);
//        if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
//            imgquery = "UPDATE landprofile SET Img4 = ? where LRID =" + lrID;
//        } else {
//            imgquery = "UPDATE landprofile SET Img4 = ? where LRID =" + lrid;
//
//        }
        if (jCheckBoxMenuItem1 == null) {
            imgquery = "UPDATE landprofile SET Img4 = ? where LRID =" + lrid;
        } else {
            if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
                imgquery = "UPDATE landprofile SET Img4 = ? where LRID =" + lrID;
            }
        }
        System.out.println(imgquery);
        imageUpload(imgquery);

    }//GEN-LAST:event_img4ActionPerformed

    private void img3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img3ActionPerformed
        il.loadImage(l3, 2);
//        if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
//            imgquery = "UPDATE landprofile SET Img3 = ? where LRID =" + lrID;
//        } else {
//            imgquery = "UPDATE landprofile SET Img3 = ? where LRID =" + lrid;
//
//        }
        if (jCheckBoxMenuItem1 == null) {
            imgquery = "UPDATE landprofile SET Img3 = ? where LRID =" + lrid;
        } else {
            if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
                imgquery = "UPDATE landprofile SET Img3 = ? where LRID =" + lrID;
            }
        }
        System.out.println(imgquery);
        imageUpload(imgquery);

    }//GEN-LAST:event_img3ActionPerformed

    private void img2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img2ActionPerformed
        il.loadImage(l2, 1);
//        if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
//            imgquery = "UPDATE landprofile SET Img2 = ? where LRID =" + lrID;
//        } else {
//            imgquery = "UPDATE landprofile SET Img2 = ? where LRID =" + lrid;
//
//        }
        if (jCheckBoxMenuItem1 == null) {
            imgquery = "UPDATE landprofile SET Img2 = ? where LRID =" + lrid;
        } else {
            if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
                imgquery = "UPDATE landprofile SET Img2 = ? where LRID =" + lrID;
            }
        }
        System.out.println(imgquery);
        imageUpload(imgquery);

    }//GEN-LAST:event_img2ActionPerformed

    private void img1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_img1ActionPerformed
        il.loadImage(l1, 0);
        if (jCheckBoxMenuItem1 == null) {
            imgquery = "UPDATE landprofile SET Img1 = ? where LRID =" + lrid;
        } else {
            if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
                imgquery = "UPDATE landprofile SET Img1 = ? where LRID =" + lrID;
            }
        }
//        if (jCheckBoxMenuItem1.getText().equals(" update Details")) {
//            imgquery = "UPDATE landprofile SET Img1 = ? where LRID =" + lrID;
//        } else {
//            imgquery = "UPDATE landprofile SET Img1 = ? where LRID =" + lrid;
//
//        }
        System.out.println(imgquery);
        imageUpload(imgquery);


    }//GEN-LAST:event_img1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tp1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lltf7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf7ActionPerformed
    }//GEN-LAST:event_lltf7ActionPerformed

    private void LMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LMLActionPerformed
    }//GEN-LAST:event_LMLActionPerformed

    private void ASFLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASFLActionPerformed
    }//GEN-LAST:event_ASFLActionPerformed

    private void lltf9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf9ActionPerformed
    }//GEN-LAST:event_lltf9ActionPerformed

    private void cb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb2ActionPerformed
    }//GEN-LAST:event_cb2ActionPerformed

    private void lltf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf1ActionPerformed
    }//GEN-LAST:event_lltf1ActionPerformed

    private void cb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb6ActionPerformed
    }//GEN-LAST:event_cb6ActionPerformed

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed
    }//GEN-LAST:event_cb1ActionPerformed

    private void cb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb5ActionPerformed
    }//GEN-LAST:event_cb5ActionPerformed

    private void lltf12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lltf12ActionPerformed
    }//GEN-LAST:event_lltf12ActionPerformed

    private void cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityActionPerformed
    }//GEN-LAST:event_cityActionPerformed

    private void PILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PILActionPerformed
    }//GEN-LAST:event_PILActionPerformed

    private void cb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb3ActionPerformed
    }//GEN-LAST:event_cb3ActionPerformed

    private void cb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb4ActionPerformed
    }//GEN-LAST:event_cb4ActionPerformed

    private void CSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CSLActionPerformed
    }//GEN-LAST:event_CSLActionPerformed

    private void l1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MouseClicked
    }//GEN-LAST:event_l1MouseClicked

    private void l2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MouseClicked
    }//GEN-LAST:event_l2MouseClicked
    private void l1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MouseEntered
        try {
            il.showImage(l6, 0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_l1MouseEntered

    private void l2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MouseEntered
        try {
            il.showImage(l6, 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_l2MouseEntered

    private void l3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MouseEntered
        try {
            il.showImage(l6, 2);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_l3MouseEntered

    private void l4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MouseEntered
        try {
            il.showImage(l6, 3);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_l4MouseEntered

    private void l5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l5MouseEntered
        try {
            il.showImage(l6, 4);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_l5MouseEntered

    private void cb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb7ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed
    private void checkUncheckedCheckboxes() {
        JCheckBox[] checkboxes = {
            jCheckBox5, jCheckBox6,
            jCheckBox9, jCheckBox11, jCheckBox12,
            jCheckBox13, jCheckBox16, jCheckBox17, jCheckBox18,
            jCheckBox20, jCheckBox21, jCheckBox22, jCheckBox24,
            jCheckBox25, jCheckBox27, jCheckBox28, jCheckBox30,
            jCheckBox31, jCheckBox32, jCheckBox33, jCheckBox34, jCheckBox35, jCheckBox36,
            jCheckBox37, jCheckBox38, jCheckBox39, jCheckBox40, jCheckBox41, jCheckBox42,
            jCheckBox43, jCheckBox44
        };

        StringBuilder uncheckedMessage = new StringBuilder(); // StringBuilder to hold all messages

        // Iterate over each checkbox and check its state
        for (JCheckBox checkbox : checkboxes) {
            if (!checkbox.isSelected()) { // If the checkbox is unchecked
                textt = uncheckedMessage.append(checkbox.getText()).append("\n"); // Add message to string
            }
        }

        // Print the combined message to the console or use it elsewhere
        if (uncheckedMessage.length() > 0) {
            System.out.println(uncheckedMessage.toString()); // Output unchecked message
            JOptionPane.showMessageDialog(this, textt.toString(), "Unchecked Checkboxes", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("All checkboxes are selected.");
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        checkUncheckedCheckboxes();
        try {
            if (ParabitLogin.srs.getInt(1) == 7) {
                if (jCheckBoxMenuItem2.isSelected() == false) {
                    try {
                        int selectedRow = table.getSelectedRow();
                        lrID = table.getValueAt(selectedRow, 0); // Replace 0 with the column index of LRID
                        String s = "update landprofile set Status = 'Listed Land', NotificationText = '" + textt + "' where landprofile.LRID = " + lrID;
                        System.out.println(s);
                        obdb.stm.executeUpdate(s);
                        JOptionPane.showMessageDialog(null, "Done");
                    } catch (HeadlessException | SQLException e) {
                        JOptionPane.showMessageDialog(null, "1,Some Problem " + e);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(NewLandRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    public void updatedetailsfun() {
        if (jCheckBoxMenuItem1.getText().equals("complete  details")) { //for expert
            city.setEditable(false);
            AL.setEditable(false);
            LML.setEditable(false);
            ASFL.setEditable(false);
            CCOL.setEditable(false);
            CSL.setEditable(false);
            RL.setEditable(false);
            cb6.setEnabled(false);
            cb4.setEnabled(false);
            lltf1.setEditable(false);
            lltf2.setEditable(false);
            lltf3.setEditable(false);
            lltf4.setEditable(false);
            lltf5.setEditable(false);
            lltf6.setEditable(false);
            lltf7.setEditable(false);
            lltf8.setEditable(false);
            lltf9.setEditable(false);
            lltf10.setEditable(false);
            lltf11.setEditable(false);
            lltf12.setEditable(false);
            img1.setEnabled(false);
            img2.setEnabled(false);
            img3.setEnabled(false);
            img4.setEnabled(false);
            img5.setEnabled(false);
        }
        img1.setVisible(true);
        img2.setVisible(true);
        img3.setVisible(true);
        img4.setVisible(true);
        img5.setVisible(true);
        jButton6.setVisible(true);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
        jButton3.setVisible(false);
        jButton4.setVisible(false);
        jButton5.setVisible(false);
        cb7.setEnabled(false);
        try {
            if (jCheckBoxMenuItem1.getText().equals("Complete Details")) {

            } else {
                String ss = "select DISTINCT(lp.StateCode),lp.StateName,lp.DistrictCode,"
                        + "lp.DistrictName,lp.SubDistrictCode,lp.SubDistrictName,"
                        + "lp.ZoneCode,lp.WardCode,lp.WardName,lp.VillageCode,lp.City,"
                        + "lp.LMarkDate,lp.LMarkTime,lty.TypeOfLand,sy.SoilType,wa.Resource,"
                        + "ay.MarkAreaType,p.TypeOfPollution,lp.PolutionIndex,lp.Status,"
                        + "lp.ColonySocietyName,lp.Address,lp.LandMark,lp.AreaSqft,shy.ShapeName,"
                        + "lp.CenterCoOrdinate,lp.Radius,lp.LL1,lp.LL2,lp.LL3,lp.LL4,lp.LL5,"
                        + "lp.LL6,lp.LL7,lp.LL8,lp.LL9,lp.LL10,lp.LL11,lp.LL12 from landprofile "
                        + "lp, zoneward zw, shapetype shy, areatype ay, typesofsoil sy, "
                        + "waterresources wa, pollution p, landtype lty WHERE lp.Shape = shy.SNo "
                        + "AND lp.AreaType = ay.SNo AND lp.SoilType = sy.SNo AND lp.Wavialable "
                        + "= wa.SNo AND lp.LandType = lty.SNo AND lp.PolutionType = p.SNo AND "
                        + "lp.statecode = zw.StateCode AND lp.districtcode = zw.DistrictCode AND "
                        + "lp.ZoneCode = zw.Zone and lp.LRID = " + lrID;
                System.out.println(ss);
                obdb1.rs = obdb1.stm.executeQuery(ss);
                obdb1.rs.next();
                String wardname = obdb1.rs.getString(9);
                String ltype = obdb1.rs.getString(14);
                String stype = obdb1.rs.getString(15);
                String wresource = obdb1.rs.getString(16);
                String atype = obdb1.rs.getString(17);
                String polltype = obdb1.rs.getString(18);
                String shatype = obdb1.rs.getString(25);
                String statename = obdb1.rs.getString(2);
                String subdisttname = obdb1.rs.getString(6);
                String disttname = obdb1.rs.getString(4);
                String statuses = obdb1.rs.getString(20);
                int villcode = obdb1.rs.getInt(10);
                int wardcode = obdb1.rs.getInt(8);
                int zonecode = obdb1.rs.getInt(7);
                int subdisttcode = obdb1.rs.getInt(5);
                int disttcode = obdb1.rs.getInt(3);
                int statecode = obdb1.rs.getInt(1);
//                VCode.setText(VCode.getText() + " " + villcode);
                city.setText(obdb1.rs.getString(11));
//                SDCode.setText(SDCode.getText() + " " + subdisttcode);
//                zcode.setText(zcode.getText() + " " + zonecode);
//                wcode.setText(wcode.getText() + " " + wardcode);
//                WName.setText(WName.getText() + " " + wardname);
//                DCode.setText(DCode.getText() + " " + disttcode);
//                SCode.setText(SCode.getText() + " " + statecode);
//                SName.setText(SName.getText() + " " + statename);
//                SDName.setText(SDName.getText() + " " + subdisttname);
//                DName.setText(DName.getText() + " " + disttname);
                PIL.setText(obdb1.rs.getString(19));
                CSL.setText(obdb1.rs.getString(21));
                AL.setText(obdb1.rs.getString(22));
                LML.setText(obdb1.rs.getString(23));
                ASFL.setText(obdb1.rs.getString(24));
                CCOL.setText(obdb1.rs.getString(26));
                RL.setText(obdb1.rs.getString(27));
                lltf1.setText(obdb1.rs.getString(28));
                lltf2.setText(obdb1.rs.getString(29));
                lltf3.setText(obdb1.rs.getString(30));
                lltf4.setText(obdb1.rs.getString(31));
                lltf5.setText(obdb1.rs.getString(32));
                lltf6.setText(obdb1.rs.getString(33));
                lltf7.setText(obdb1.rs.getString(34));
                lltf8.setText(obdb1.rs.getString(35));
                lltf9.setText(obdb1.rs.getString(36));
                lltf10.setText(obdb1.rs.getString(37));
                lltf11.setText(obdb1.rs.getString(38));
                lltf12.setText(obdb1.rs.getString(39));
                MDL.setText(MDL.getText() + " " + obdb1.rs.getString(12));
                MTL.setText(MTL.getText() + " " + obdb1.rs.getString(13));
                databaseImage1new("Img1", l1, l6);
                databaseImage1new("Img2", l2, l6);
                databaseImage1new("Img3", l3, l6);
                databaseImage1new("Img4", l4, l6);
                databaseImage1new("SImg", l5, l6);
            }
            addwaterType();
            LandType();
            SoilType();
            PollutionType();
            AreaType();
            ShapeType();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String upcity = city.getText();
        String upPI = PIL.getText();
        int upLT = cb1.getSelectedIndex() + 1;
        int upAT = cb5.getSelectedIndex() + 1;
        int upST = cb2.getSelectedIndex() + 1;
        int upPT = cb3.getSelectedIndex() + 1;
        int upWA = cb4.getSelectedIndex() + 1;
        String upCS = CSL.getText();
        String upA = AL.getText();
        String upLM = LML.getText();
        String upASF = ASFL.getText();
        int upS = cb6.getSelectedIndex() + 1;
        String upCCO = CCOL.getText();
        String upR = RL.getText();
        String setLL = "UPDATE landprofile SET City ='" + upcity + "',"
                + "PolutionIndex = '" + upPI + "'," + "LandType = " + upLT
                + "," + "AreaType = " + upAT + "," + "SoilType = "
                + upST + "," + "PolutionType = " + upPT + ","
                + "Wavialable = " + upWA + "," + "ColonySocietyName = '" + upCS
                + "'," + "Address = '" + upA + "'," + "Landmark = '" + upLM
                + "'," + "AreaSqft = '" + upASF + "'," + "Shape = " + upS + ","
                + "CenterCoOrdinate = '" + upCCO + "'," + "Radius = '" + upR + "'," + "LL1 ='" + lltf1.getText() + "',"
                + "LL2 = '" + lltf2.getText() + "'," + "LL3 = '" + lltf3.getText()
                + "'," + "LL4 = '" + lltf4.getText() + "'," + "LL5 = '"
                + lltf5.getText() + "'," + "LL6 = '" + lltf6.getText() + "',"
                + "LL7 = '" + lltf7.getText() + "'," + "LL8 = '" + lltf8.getText()
                + "'," + "LL9 = '" + lltf9.getText() + "'," + "LL10 = '" + lltf10.getText()
                + "'," + "LL11 = '" + lltf11.getText() + "'," + "LL12 = '" + lltf12.getText() + "'" + " where LRID = " + lrID;
        System.out.println(setLL);
        try {
            obdb.stm.executeUpdate(setLL);
            JOptionPane.showMessageDialog(null, "Land Update Successfully");
        } catch (Exception e) {
            System.out.println(e + "hello");
        }

        if (jCheckBoxMenuItem1.getText().equals("complete  details")) {
            this.setVisible(false);
            Activities a = new Activities(null, false, lrID);
            a.setVisible(true);
        }
    }//GEN-LAST:event_jButton6ActionPerformed
// for insert image into database

    public void imageUpload(String imgquery) {
        try {
            FileInputStream fileInputStream = new FileInputStream(il.filePath);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);
            PreparedStatement ps = obdb.con.prepareStatement(imgquery);
            ps.setBytes(1, imageData);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Image inserted successfully");
            } else {
                System.out.println("Image not inserted ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addwaterType() {
        String a = "select * from waterresources";
        System.out.println(a);
        try {
            obdb.rs = obdb.stm.executeQuery(a);
            while (obdb.rs.next()) {
                String type = obdb.rs.getString(2);
                cb4.addItem(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void LandType() {
        String a = "select * from landtype";
        System.out.println(a);
        try {
            obdb.rs = obdb.stm.executeQuery(a);
            while (obdb.rs.next()) {
                String type = obdb.rs.getString(2);
                cb1.addItem(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void SoilType() {
        String a = "select * from typesofsoil";
        System.out.println(a);
        try {
            obdb.rs = obdb.stm.executeQuery(a);
            while (obdb.rs.next()) {
                String type = obdb.rs.getString(2);
                cb2.addItem(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void PollutionType() {
        String a = "select * from pollution";
        System.out.println(a);
        try {
            obdb.rs = obdb.stm.executeQuery(a);
            while (obdb.rs.next()) {
                String type = obdb.rs.getString(2);
                cb3.addItem(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void AreaType() {
        String a = "select * from areatype";
        System.out.println(a);
        try {
            obdb.rs = obdb.stm.executeQuery(a);
            while (obdb.rs.next()) {
                String type = obdb.rs.getString(2);
                cb5.addItem(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void ShapeType() {
        String a = "select * from shapetype";
        System.out.println(a);
        try {
            obdb.rs = obdb.stm.executeQuery(a);
            while (obdb.rs.next()) {
                String type = obdb.rs.getString(2);
                cb6.addItem(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewLandRegistration dialog = new NewLandRegistration(new javax.swing.JFrame(), true, null, null, null, null);
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
    private javax.swing.JTextField AL;
    private javax.swing.JTextField ASFL;
    private javax.swing.JTextField CCOL;
    private javax.swing.JTextField CSL;
    private javax.swing.JLabel DCode;
    private javax.swing.JLabel DName;
    private javax.swing.JTextField LML;
    private javax.swing.JLabel MDL;
    private javax.swing.JLabel MTL;
    private javax.swing.JTextField PIL;
    private javax.swing.JTextField RL;
    private javax.swing.JLabel SCode;
    private javax.swing.JLabel SDCode;
    private javax.swing.JLabel SDName;
    private javax.swing.JLabel SName;
    private javax.swing.JLabel VCode;
    private javax.swing.JLabel VVersion;
    private javax.swing.JLabel WName;
    private javax.swing.JComboBox<String> cb1;
    private javax.swing.JComboBox<String> cb2;
    private javax.swing.JComboBox<String> cb3;
    private javax.swing.JComboBox<String> cb4;
    private javax.swing.JComboBox<String> cb5;
    private javax.swing.JComboBox<String> cb6;
    private javax.swing.JComboBox<String> cb7;
    private javax.swing.JTextField city;
    private javax.swing.JButton img1;
    private javax.swing.JButton img2;
    private javax.swing.JButton img3;
    private javax.swing.JButton img4;
    private javax.swing.JButton img5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox38;
    private javax.swing.JCheckBox jCheckBox39;
    private javax.swing.JCheckBox jCheckBox40;
    private javax.swing.JCheckBox jCheckBox41;
    private javax.swing.JCheckBox jCheckBox42;
    private javax.swing.JCheckBox jCheckBox43;
    private javax.swing.JCheckBox jCheckBox44;
    private javax.swing.JCheckBox jCheckBox45;
    private javax.swing.JCheckBox jCheckBox46;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel l1;
    private javax.swing.JLabel l2;
    private javax.swing.JLabel l3;
    private javax.swing.JLabel l4;
    private javax.swing.JLabel l5;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel ll1;
    private javax.swing.JLabel ll10;
    private javax.swing.JLabel ll11;
    private javax.swing.JLabel ll12;
    private javax.swing.JLabel ll2;
    private javax.swing.JLabel ll3;
    private javax.swing.JLabel ll4;
    private javax.swing.JLabel ll5;
    private javax.swing.JLabel ll6;
    private javax.swing.JLabel ll7;
    private javax.swing.JLabel ll8;
    private javax.swing.JLabel ll9;
    private javax.swing.JTextField lltf1;
    private javax.swing.JTextField lltf10;
    private javax.swing.JTextField lltf11;
    private javax.swing.JTextField lltf12;
    private javax.swing.JTextField lltf2;
    private javax.swing.JTextField lltf3;
    private javax.swing.JTextField lltf4;
    private javax.swing.JTextField lltf5;
    private javax.swing.JTextField lltf6;
    private javax.swing.JTextField lltf7;
    private javax.swing.JTextField lltf8;
    private javax.swing.JTextField lltf9;
    private javax.swing.JTabbedPane tp1;
    private javax.swing.JLabel wcode;
    private javax.swing.JLabel zcode;
    // End of variables declaration//GEN-END:variables
}
