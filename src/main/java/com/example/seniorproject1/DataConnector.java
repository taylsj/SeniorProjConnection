package com.example.seniorproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataConnector {
    private Connection conn;
    private PreparedStatement statement;
    private final String connectionStr = "jdbc:sqlserver://bcs430group1.database.windows.net:1433;database=SeniorProject;user=CloudSA1799e7e9@bcs430group1;password=Password!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public Connection databaselink1;
    public DataConnector() {
        connectToDB();
    }

    private void connectToDB() {
        try {
            conn = DriverManager.getConnection(connectionStr);
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
/*
    public Connection getConnection() {
        String url = "jdbc:sqlserver://bcs430group1.database.windows.net:1433;database=SeniorProject;user=CloudSA1799e7e9@bcs430group1;password=Password!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try{
            Class.forName("com.microsoft.sqlserver");
            databaseLink1 = DriverManager.getConnection(url);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return databaseLink1;
    }
    */

    }


