package com.jspiders.util;

import com.jspiders.exceptions.DatabaseConnectionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    private final static String dburl = "jdbc:mysql://localhost:3306/cms";
    private final static String user = "root";
    private final static String pass = "root";
    private final static HikariDataSource ds;

    static {
        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(dburl);
            config.setUsername(user);
            config.setPassword(pass);

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(3);
            config.setConnectionTimeout(5000);
            config.setIdleTimeout(5000);

            ds = new HikariDataSource(config);
            System.out.println("Connected to database successfully..");
        }
        catch (Exception e) {
            System.err.println("Failed to initialize the database connection");
            throw new DatabaseConnectionException("Couldn't connect to database");
        }
    }

    public static HikariDataSource getDataSource() {
        return ds;
    }

    public static Connection buildConnection(){

        HikariDataSource ds = DBConnection.getDataSource();
        Connection con = null;
        try {
            con = ds.getConnection();
        }
        catch (SQLException e) {
            throw new DatabaseConnectionException(e.getMessage());
        }
        return con;
    }

}
