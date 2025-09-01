package backendjdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/BDProductos";
    private static String user = "Walter";
    private static String password = "12345";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
       if(pool == null){
        pool = new BasicDataSource();
        pool.setUrl(url);
        pool.setUsername(user);
        pool.setPassword(password);
        pool.setInitialSize(3);
        pool.setMinIdle(3);
        pool.setMaxIdle(8);
        pool.setMaxTotal(8);

       }

        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }


}
