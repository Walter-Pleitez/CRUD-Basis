package backendjdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/dbackend";
    private static String user = "Admin";
    private static String password = "Admin";
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
