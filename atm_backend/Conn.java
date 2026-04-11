package atm_backend;

import java.sql.*;

public class Conn {

    public Connection c;
    public Statement s;

    public Conn() {
        try {

            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmsystem", "root", "Arnav_123");
            s = c.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
        
            System.out.println("access  denied");
        }
    }
}
