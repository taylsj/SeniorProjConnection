package com.example.seniorproject1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ratingsController {
    @FXML
    private Label leadershipLabel;
    @FXML
    private Label communicationLabel;
    @FXML
    private Label strategizingLabel;
    @FXML
    private Label concentrationLabel;
    @FXML
    private Label multitaskingLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private ListView listViewRating;
    private UserProfileModel user = UserProfileModel.getInstance();
    private DatabaseConnection dc = new DatabaseConnection();
    ObservableList<String> obsList = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws SQLException {
        userNameLabel.setText(user.getUserName());
        dc.getConnection();
        int pID = dc.getProfID(user.getUserName());
        user.setProfID(pID);
        /*
        int temp = dc.getRatingLeader(user.getProfID());
        leadershipLabel.setText(String.valueOf(temp));

        int temp2 = dc.getRatingConcentration(user.getProfID());
        concentrationLabel.setText(String.valueOf(temp2));

        int temp3 = dc.getRatingCommunication(user.getProfID());
        communicationLabel.setText(String.valueOf(temp3));

        int temp4 = dc.getRatingMultiTasking(user.getProfID());
        multitaskingLabel.setText(String.valueOf(temp4));

        int temp5 = dc.getRatingStrategizing(user.getProfID());
        strategizingLabel.setText(String.valueOf(temp5));
*/
        obsList.add("Leadership\t\t Concentration\t\t Communication\t\t MultiTasking\t\t Strategizing\t\tGame");
        listViewRating.setItems(obsList);
        ResultSet result1 = dc.getRatingLeaderList(pID);
        if(result1 == null){
            return;
        }
        ResultSet result2 = dc.getRatingConcentrationList(pID);
        ResultSet result3 = dc.getRatingCommunicationList(pID);
        ResultSet result4 = dc.getRatingMultiTaskingList(pID);
        ResultSet result5 = dc.getRatingStrategizingList(pID);
        ResultSet result6 = dc.getGameID(pID);



        do {
            int LeaderRate = result1.getInt("Leadership");
            int ConcentrationRate = result2.getInt("Concentration");
            int CommunicationRate = result3.getInt("Communication");
            int MultiTaskingRate = result4.getInt("MultiTasking");
            int StrategizingRate = result5.getInt("Strategizing");
            int gameID = result6.getInt("gameID");
            obsList.add(LeaderRate + "\t\t\t\t " +  ConcentrationRate + "\t\t\t\t " +  CommunicationRate + "\t\t\t\t\t " + MultiTaskingRate +
                    "\t\t\t\t " + StrategizingRate + "\t\t\t\tFor Post Playing:  " + dc.getGameTitle(gameID));
            result2.next();
            result3.next();
            result4.next();
            result5.next();
            result6.next();
        }
        while(result1.next());
        listViewRating.setItems(obsList);

    }

    public void switchToProfileOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }


    public static void main(String [] args) {
        DatabaseConnection dc = new DatabaseConnection();
        dc.getConnection();
        UserProfileModel user = UserProfileModel.getInstance();
        user.setUserName("AB");
        int temp1 = dc.getProfID(user.getUserName());
        user.setProfID(temp1);
        dc.getRatingLeader(user.getProfID());
        //System.out.println("Leadership is = " + user.getLeadership());
        //int temp = dc.getRatingLeader(user.getProfID());
        System.out.println("ProfileID is = " + temp1);
    }



}

