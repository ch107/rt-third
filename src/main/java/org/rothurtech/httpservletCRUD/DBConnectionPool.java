package org.rothurtech.httpservletCRUD;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnectionPool {
    private static BasicDataSource ds = new BasicDataSource();

    static String url = "jdbc:postgresql://localhost:5432/mydb";
    static String user = "myuser";
    static String password = "mysecretpassword";

    static {
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
