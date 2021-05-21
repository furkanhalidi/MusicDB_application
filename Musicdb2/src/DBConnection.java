import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection c = null;

    public DBConnection(){

    }

    public Connection connDb(){

        try {
            this.c =  DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb?useSSL=false", "root", "41f6513fb");
       return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
}
