package atm.database;

import java.io.InputStream;
import java.util.Properties;
import java.sql.*;

public class Conn {

    public Connection c;
    public Statement s;

    public Conn() {
        try {
            Properties props = new Properties();
            InputStream input = getClass().getResourceAsStream("/config.properties");
            props.load(input);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            c = DriverManager.getConnection(url, username, password);
            s = c.createStatement();
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}
