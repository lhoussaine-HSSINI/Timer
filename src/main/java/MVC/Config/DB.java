
package MVC.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/registro","root","");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
