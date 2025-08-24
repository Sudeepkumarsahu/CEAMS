package ceamsexperts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class ParabitExpertDb {
    Connection con;
    Statement stm;
    ResultSet rs;
    public ParabitExpertDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/parabitceams", "root", "");
            stm = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
