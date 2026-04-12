package atm.database;

import java.io.FileInputStream;
import java.util.Properties;
import java.sql.*;

public class Conn {

    public Connection c;
    public Statement s;

    public Conn() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            c = DriverManager.getConnection(url, username, password);
            s = c.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("access  denied");
        }
    }
}
