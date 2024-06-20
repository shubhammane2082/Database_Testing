package org.example;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Myconnection
{
    Connection con;

    @BeforeClass
    public Connection createConnection() {
        try {
            java.lang.String url = "jdbc:mysql://localhost:3306/db_testing";
            java.lang.String username = "root";
            java.lang.String password = "Root";
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful...");
        }
        catch (Exception e)
        {
            System.out.println("connection not successful...");
            System.out.println(e.getMessage());
        }
        return con;
    }

    @AfterClass
    public void teardown() throws SQLException
    {
        con.close();
    }
}
