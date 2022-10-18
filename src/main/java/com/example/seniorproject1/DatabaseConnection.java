package com.example.seniorproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;
    private Connection conn;

    public Connection getConnection() {
        //String databaseName = "SeniorProject";
        //String databaseUser = "CloudSA1799e7e9";
        //String databasePassword = "Password!";
        String connectionStr = "jdbc:sqlserver://bcs430group1.database.windows.net:1433;database=SeniorProject;user=CloudSA1799e7e9@bcs430group1;password=Password!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection temp = null;
        try {
            conn = DriverManager.getConnection(connectionStr);
            temp = conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return temp;
        /*
        try{
            Class.forName("com.microsoft.sqlserver");
            databaseLink = DriverManager.getConnection(url);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
*/
    }
}





