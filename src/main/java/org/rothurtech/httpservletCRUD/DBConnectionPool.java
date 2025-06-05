package org.rothurtech.httpservletCRUD;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

//A connection pool is a cache of database connections maintained in memory so they can be reused rather than created and destroyed repeatedly.
public class DBConnectionPool {
    private static BasicDataSource ds = new BasicDataSource();

    static String url = "jdbc:postgresql://localhost:5432/mydb";
    static String user = "myuser";
    static String password = "mysecretpassword";

    // all connection use the same configuration
    static {
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl(url);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setMinIdle(5); // minimum number of idle connections the pool tries to maintain
        ds.setMaxIdle(10); // number of idle connection allowed
        ds.setMaxOpenPreparedStatements(100); //maximum number of prepared statements cached per connection.
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
