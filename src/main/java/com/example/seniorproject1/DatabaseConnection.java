package com.example.seniorproject1;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private Connection conn;
    private PreparedStatement preparedStatement;

    public Connection getConnection() {
        //String databaseName = "SeniorProject";
        //String databaseUser = "CloudSA1799e7e9";
        //String databasePassword = "Password!";
        //String connectionStr = "jdbc:sqlserver://bcs430group1.database.windows.net:1433;database=SeniorProject;user=CloudSA1799e7e9@bcs430group1;password=Password!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        String connectionStr = "jdbc:ucanaccess://.//SeniorProjectDB.accdb";
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

    public int getRatingLeader(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Leadership FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                ans = result.getInt("Leadership");
                System.out.println("Leadership = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getRatingLeaderList(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Leadership FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                return result;
                //ans = result.getInt("Leadership");
                //System.out.println("Leadership = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getRatingCommunication(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Communication FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                ans = result.getInt("Communication");
                System.out.println("Communication = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getRatingCommunicationList(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Communication FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                //ans = result.getInt("Communication");
                //System.out.println("Communication = " + ans);
             return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public int getRatingStrategizing(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Strategizing FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                ans = result.getInt("Strategizing");
                System.out.println("Strategizing = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getRatingStrategizingList(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Strategizing FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                return result;
               // ans = result.getInt("Strategizing");
               // System.out.println("Strategizing = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getRatingConcentration(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Concentration FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                ans = result.getInt("Concentration");
                System.out.println("Concentration = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getRatingConcentrationList(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Concentration FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                return result;
                //ans = result.getInt("Concentration");
                //System.out.println("Concentration = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getRatingMultiTasking(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Multitasking FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                ans = result.getInt("Multitasking");
                System.out.println("Multitasking = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getRatingMultiTaskingList(int profID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Multitasking FROM Post WHERE profileID = \'" + profID + "\'");
            while (result.next()) {
                return result;
                //ans = result.getInt("Multitasking");
                //System.out.println("Multitasking = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getProfID(String un) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT profileID FROM ProfileInfo WHERE profileName = \'" + un + "\'");
            while (result.next()) {
                ans = result.getInt("profileID");
                System.out.println("profileID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getAllFromPost() {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM Post");
           //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllFromPostByPlatform(int platformID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM Post WHERE PlatformID = " + platformID);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllFromPostByGame(int gameID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM Post WHERE GameID = " + gameID);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProfName(int id) {
        String ans = " ";
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT profileName FROM ProfileInfo  WHERE ProfileID = " + id);
            while (result.next()) {
                ans = result.getString("profileName");
                System.out.println("profileName = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public int getPostID(int un) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT postID FROM Post JOIN ProfileInfo ON ProfileInfo.profileID = Post.profileID WHERE ProfileInfo.profileID = \'" + un + "\'");
            while (result.next()) {
                ans = result.getInt("postID");
                System.out.println("postID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getGameTitle(int id) {
        String ans = " ";
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT gameTitle FROM Game WHERE gameID = \'" + id + "\'");
            while (result.next()) {
                ans = result.getString("gameTitle");
                System.out.println("gameTitle = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getGameID(int un) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT gameID FROM Post WHERE profileID = " + un);
            while (result.next()) {
                //ans = result.getInt("gameID");
                return result;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String userName, String newStr) {
        try {
            // String sql = "UPDATE" + t + " SET " + col + " =\'" + newStr + "\' WHERE profileName =\'" + userName + "\'";
            String sql = "UPDATE UserInfo SET userPass = \'" + newStr + "\' WHERE userName = \'" + userName + "\'";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Successfully Updated");
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public String getCurrentPass(String un) {
        String ans = " ";
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT userPass FROM UserInfo WHERE userName = \'" + un + "\'");
            while (result.next()) {
                ans = result.getString("userPass");
                System.out.println("userPass = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public void insertPost(int profileID, int gameID, int platformID, String description, String comment) {
        //call the getConnectionDB method
        //getConnectionDB();
        System.out.println("insertPost method running...");
        String tableName = "Post";
        try {
            String sql = "INSERT INTO " + tableName + " (ProfileID, GameID, PlatformID, Description, Comments )  VALUES"
                    + "(?, ?, ?, ?, ?)";
            //System.out.println("Prepared Statement = " + sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, profileID);
            preparedStatement.setInt(2, gameID);
            preparedStatement.setInt(3, platformID);
            //preparedStatement.setString(4, );
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, comment);
            // preparedStatement.setString(6, profileName);
            int row = preparedStatement.executeUpdate();
            System.out.println("Prepared Statement = " + preparedStatement.toString());
            if (row > 0) {
                System.out.println("**NEW Post inserted into table");
            }
        } catch (SQLException e) {
        }
    }
        public int getPlatformIDFromName(String name) {
            int ans = -1;
            ResultSet result = null;
            try {
                Statement stmt = conn.createStatement();
                result = stmt.executeQuery("SELECT platformID FROM Platform WHERE platformType = \'" + name + "\'");
                while (result.next()) {
                    ans = result.getInt("platformID");
                    System.out.println("platformID = " + ans);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ans;
        }

    public int getGameIDFromName(String name) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT gameID FROM Game WHERE gameTitle = \'" + name + "\'");
            while (result.next()) {
                ans = result.getInt("gameID");
                System.out.println("gameID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getAllRatingFromPostByGame(int gameID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT Leadership, Communication, Strategizing, Concentration, MultiTasking FROM Post WHERE  gameID = " + gameID);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllFromPostByUser(int profileID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM Post WHERE profileID = " + profileID);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getMostPostsPlatformID() {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT TOP 1 PlatformID FROM Post GROUP BY PlatformID ORDER BY Count(PlatformID) DESC");
            while (result.next()) {
                ans = result.getInt("platformID");
                System.out.println("platformID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }
    public int getMostPostsGameID() {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT TOP 1 GameID FROM Post GROUP BY GameID ORDER BY Count(GameID) DESC");
            while (result.next()) {
                ans = result.getInt("GameID");
                System.out.println("GameID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String getPlatformName(int id) {
        String ans = " ";
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT platformType FROM Platform WHERE platformID = " + id);
            while (result.next()) {
                ans = result.getString("platformType");
                System.out.println("platformType = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public int getGameIDCount(int id) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT COUNT(gameID) AS gameID FROM Post WHERE gameID = " + id);
            while (result.next()) {
                ans = result.getInt("gameID");
                System.out.println("gameID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public int getPlatformIDCount(int id) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT COUNT(platformID) AS platformID FROM Post WHERE platformID = " + id);
            while (result.next()) {
                ans = result.getInt("platformID");
                System.out.println("platformID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public ResultSet getAllFromFavPlatformByProfileID(int id) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM FavoritePlatforms WHERE profileID = " + id);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllFromFavGameByProfileID(int id) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM FavoriteGames WHERE profileID = " + id);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertFavoritesGame(int profileID, int gameID) {
        //call the getConnectionDB method
        //getConnectionDB();
        System.out.println("insertFavoritesGame method running...");
        String tableName = "FavoriteGames";
        try {
            String sql = "INSERT INTO " + tableName + " (ProfileID, GameID)  VALUES"
                    + "(?, ?)";
            //System.out.println("Prepared Statement = " + sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, profileID);
            preparedStatement.setInt(2, gameID);
            //preparedStatement.setInt(3, platformID);
            //preparedStatement.setString(4, );
           //preparedStatement.setString(4, description);
           //preparedStatement.setString(5, comment);
            //preparedStatement.setString(6, profileName);
            int row = preparedStatement.executeUpdate();
            System.out.println("Prepared Statement = " + preparedStatement.toString());
            if (row > 0) {
                System.out.println("**NEW Post inserted into table");
            }
        } catch (SQLException e) {
        }
    }

    public void insertFavoritesPlatform(int profileID, int platformID) {
        //call the getConnectionDB method
        //getConnectionDB();
        System.out.println("insertFavoritesPlatform method running...");
        String tableName = "FavoritePlatforms";
        try {
            String sql = "INSERT INTO " + tableName + " (profileID, platformID)  VALUES"
                    + "(?, ?)";
            //System.out.println("Prepared Statement = " + sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, profileID);
            preparedStatement.setInt(2, platformID);
            //preparedStatement.setInt(3, platformID);
            //preparedStatement.setString(4, );
            //preparedStatement.setString(4, description);
            //preparedStatement.setString(5, comment);
            //preparedStatement.setString(6, profileName);
            int row = preparedStatement.executeUpdate();
            System.out.println("Prepared Statement = " + preparedStatement.toString());
            if (row > 0) {
                System.out.println("**NEW Post inserted into table");
            }
        } catch (SQLException e) {
        }
    }

    public int existingGameInFav(int profID, int gameID) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String tableName = "FavoriteGames";
        //int a = 0;
        int pID = 0;
        int gID = 0;

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE profileID = " + profID + " AND gameID = " + gameID);

            while (result.next()) {
                //a = result.getInt("gameID");
                pID = result.getInt("profileID");
                gID = result.getInt("gameID");
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
        //System.out.println("Boolean = " + gameID.equals(gID));
        return gID;

    }

    public int existingPlatInFav(int profID, int platID) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String tableName = "FavoritePlatforms";
        //int a = 0;
        int pID = 0;
        int gID = 0;

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE profileID = " + profID + " AND platformID = " + platID);

            while (result.next()) {
                //a = result.getInt("gameID");
                pID = result.getInt("profileID");
                pID = result.getInt("platformID");
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
        //System.out.println("Boolean = " + gameID.equals(gID));
        return pID;

    }

    public ResultSet getAllFromFavPlatByPlatform(int platformID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM FavoritePlatforms WHERE platformID = " + platformID);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getAllFromFavGameByGame(int gameID) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM FavoriteGames WHERE gameID = " +gameID);
            //ResultSet x = result;
            return result;
            //return x;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFromFavGameByProfile(int profID, int gameID) {
        //int ans = -1;
        //ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM FavoriteGames WHERE profileID = " + profID + " AND gameID = " + gameID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromFavPlatByProfile(int profID, int platID) {
        //int ans = -1;
        //ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM FavoritePlatforms WHERE profileID = " + profID + " AND platformID = " + platID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getAllUsers() {
        int ans = -1;
        ArrayList<String> list = new ArrayList<>();
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM UserInfo;");
            while(result.next()) {
                list.add(result.getString("userName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   public void insertUserRating(int profID, int gameID, String leader, String com, String strat, String con, String multi ) {
        //call the getConnectionDB method
        //getConnectionDB();
        System.out.println("insertUserRating method running...");
        String tableName = "Post";
        try {
            String sql = "INSERT INTO " + tableName + " (profileID, gameID, Leadership, Communication, Strategizing, Concentration, Multitasking)  VALUES"
                    + "(?, ?, ?, ?, ?, ?, ?)";
            //System.out.println("Prepared Statement = " + sql);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, profID);
            preparedStatement.setInt(2, gameID);
            preparedStatement.setString(3, leader);
            preparedStatement.setString(4, com);
            //preparedStatement.setString(4, );
            preparedStatement.setString(5, strat);
            preparedStatement.setString(6, con);
            preparedStatement.setString(7, multi);

            int row = preparedStatement.executeUpdate();
            System.out.println("Prepared Statement = " + preparedStatement.toString());
            if (row > 0) {
                System.out.println("**NEW Rating inserted into Post table");
            }
        } catch (SQLException e) {
        }
    }


    public ArrayList getAllGames() {
        int ans = -1;
        ArrayList<String> list = new ArrayList<>();
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT * FROM Game;");
            while(result.next()) {
                list.add(result.getString("gameTitle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getProfIDFromName(String pn) {
        int ans = -1;
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT profileID FROM ProfileInfo WHERE profileName = \'" + pn + "\'");
            while (result.next()) {
                ans = result.getInt("profileID");
                System.out.println("profileID = " + ans);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static void main(String [] args) {
    DatabaseConnection dc = new DatabaseConnection();
    dc.getConnection();
    int x = dc.getUserID("Mike1");
    //dc.getAboutMe(x);
    //System.out.println(dc.getAboutMe(8));
    //dc.insertProfile(1, 1, 1," ", "abc", 1, "hey");
    int a = dc.getRatingLeader(12);
    int b = dc.getRatingCommunication(12);
    int c = dc.getRatingStrategizing(12);
    int d = dc.getRatingConcentration(12);
    int e = dc.getRatingMultiTasking(12);
    int test = dc.getProfID("AB");
    ResultSet test2 = dc.getAllFromPost();
    String prof = dc.getProfName(3);
    int f = dc.getPostID(15);
    String game = dc.getGameTitle(2);
    //int g = dc.getGameID(12);
    String pass = dc.getCurrentPass("PassTest");
    int g = dc.getPlatformIDFromName("PlayStation");
    int h = dc.getGameIDFromName("OverWatch");
    ResultSet test3 = dc.getAllRatingFromPostByGame(1);
    int i = dc.getGameIDCount(1);
    //ResultSet test4 = dc.getAllFromFavByProfileID(12);
    //System.out.println(test4);
        int z = dc.existingGameInFav(12, 1);
    int j = dc.getProfIDFromName("Mike1");


    }


}





