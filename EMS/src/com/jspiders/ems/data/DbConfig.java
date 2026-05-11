package com.jspiders.ems.data;

import com.jspiders.ems.exceptions.DatabaseConnectivityException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private final static String dbUrl= "jdbc:mysql://localhost:3306/ems";
    private final static String user = "root";
    private final static String password = "root";
    static Connection con = null;

    public static Connection getDbConnection() {
        try
        {
            System.out.println();
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("CONNECTED TO DATABASE SUCCESSFULLY..");
        }
        catch (SQLException e)
        {
            System.out.println();
            System.err.println("ERROR! COULDN'T CONNECT TO DATABASE!");
            throw new DatabaseConnectivityException(e.getMessage());
        }
        return con;
    }

}
