package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private Connection conn;
    private PreparedStatement preparedStatement;

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

    public String getAboutMe(String profileName) {
        String tableName = "ProfileInfo";
        ResultSet result = null;
        String returnStr = "";
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT aboutMe FROM " + tableName + " WHERE profileName = \'" + profileName + "\'");
            while (result.next()) {
                returnStr = result.getString("aboutMe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnStr;

    }

    //method only works for updating ProfileInfo table
    public boolean updateColumn(String t, String userName, String newStr, String col) {
        //UPDATE ProfileInfo  SET aboutMe = 'test123' WHERE profileName = 'AB' ;
        try {
            // String sql = "UPDATE" + t + " SET " + col + " =\'" + newStr + "\' WHERE profileName =\'" + userName + "\'";
            String sql = "UPDATE ProfileInfo  SET aboutMe = 'test123' WHERE profileName = 'AB' ";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Successfully Updated");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean updateAboutMe(String userName, String newStr) {
        try {
            // String sql = "UPDATE" + t + " SET " + col + " =\'" + newStr + "\' WHERE profileName =\'" + userName + "\'";
            String sql = "UPDATE ProfileInfo SET aboutMe = \'" + newStr + "\' WHERE profileName = \'" + userName + "\'";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Successfully Updated");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }




    public int getUserID(String un) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT userID FROM  UserInfo WHERE userName = \'" + un + "\'");
            while (result.next()) {
                ans = result.getInt("userID");
                System.out.println("UserID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public void insertProfile(int userID, int gameID, int platformID, String profilePic, String aboutMe, int Rating, String profileName) {
        //call the getConnectionDB method
        //getConnectionDB();
        System.out.println("insertProfile method running...");
        String tableName = "ProfileInfo";
        try {
            String sql = "INSERT INTO " + tableName + " (userID, gameID, platformID, profilePic, aboutMe, Rating, profileName)  VALUES"
                    + "(?, ?, ?, NULL, ?, ?, ?)";
            System.out.println("Prepared Statement = " + sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, 5);
            preparedStatement.setInt(3, 5);
            //preparedStatement.setString(4, );
            preparedStatement.setString(4, aboutMe);
            preparedStatement.setInt(5, Rating);
            preparedStatement.setString(6, profileName);
            int row = preparedStatement.executeUpdate();
            System.out.println("Prepared Statement = " + preparedStatement.toString());
            if (row > 0) {
                System.out.println("**NEW Profile inserted into table");
            }
        } catch (SQLException e) {
        }

    }
        public static void main(String [] args) {
    DatabaseConnection dc = new DatabaseConnection();
    dc.getConnection();
    int x = dc.getUserID("Mike1");
    //dc.getAboutMe(x);
    //System.out.println(dc.getAboutMe(8));
    dc.insertProfile(1, 1, 1," ", "abc", 1, "hey");

    }


}





