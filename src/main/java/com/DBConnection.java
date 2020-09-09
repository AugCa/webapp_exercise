package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final static String username = "root";
    private final static String password = "password";
    public static Connection createConnection()
    {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/users";

        try
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }

    public static Connection productConnection()
    {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/products";

        try
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}