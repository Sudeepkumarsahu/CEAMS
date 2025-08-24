/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ceamsparabit;

/**
 *
 * @author sahus
 */
import java.sql.*;
public class ParabitCeamsDb {
Connection con;
Statement stm;
ResultSet rs;
public static void main(String ar[]){
    ParabitCeamsDb ob = new ParabitCeamsDb();
    
}
    public ParabitCeamsDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //checked exception
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/parabitceams", "root", "");
            stm = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
