package com.example.seniorproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class addUserRatingController {
    @FXML
    private ComboBox<String> usersComboBox;
    @FXML
    private ComboBox<String> leadershipComboBox;
    @FXML
    private ComboBox<String> communicationComboBox;
    @FXML
    private ComboBox<String> strategizingComboBox;
    @FXML
    private ComboBox<String> concentrationComboBox;
    @FXML
    private ComboBox<String> multiTaskingComboBox;
    @FXML
    private ComboBox<String> gameComboBox;
    @FXML
    private Label confirmMessageLabel;

    DatabaseConnection dc = new DatabaseConnection();
    //ObservableList<String> obsList = FXCollections.observableArrayList();



    @FXML
    public void initialize(){
        dc.getConnection();
        setUpUserCombo();
        setUpGameCombo();
        setUpLeaderCombo();
        setUpCommunicationCombo();
        setUpStratCombo();
        setUpConcentrationCombo();
        setUpMultiTaskingCombo();
    }

    @FXML
    public void addRatingOnAction(ActionEvent e) {
    String gameObj = getGameCombo();
    String userObj = getUserCombo();
    String leaderObj = getLeaderCombo();
    String comObj = getCommunicationCombo();
    String stratObj = getStratCombo();
    String conObj = getConcentrationCombo();
    String multiObj = getMultiTaskingCombo();
    int profileID = dc.getProfIDFromName(userObj);
    int gameID = dc.getGameIDFromName(gameObj);
    if(leaderObj != null && comObj != null && stratObj != null && conObj != null && multiObj != null && gameObj != null && userObj != null) {
        try{
            dc.insertUserRating(profileID, gameID, leaderObj, comObj, stratObj, conObj, multiObj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        confirmMessageLabel.setText("User: " + userObj + " has been rated");
    } else {
        confirmMessageLabel.setText("Make sure to enter all fields!");
    }

    }


    @FXML
    public void setUpUserCombo(){
            usersComboBox.getItems().addAll(dc.getAllUsers());
    }

    @FXML
    public void setUpGameCombo(){
            gameComboBox.getItems().addAll(dc.getAllGames());
    }

    @FXML
    public void setUpLeaderCombo(){
        leadershipComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }
    @FXML
    public void setUpCommunicationCombo(){
        communicationComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }
    @FXML
    public void setUpStratCombo(){
        strategizingComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }
    @FXML
    public void setUpConcentrationCombo(){
        concentrationComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }
    @FXML
    public void setUpMultiTaskingCombo(){
        multiTaskingComboBox.getItems().addAll("1", "2", "3", "4", "5");
    }
    @FXML
    private String getUserCombo() {
        return usersComboBox.getValue();
    }
    @FXML
    private String getGameCombo() {
        return gameComboBox.getValue();
    }
    @FXML
    private String getLeaderCombo() {
        return leadershipComboBox.getValue();
    }
    @FXML
    private String getCommunicationCombo() {
        return communicationComboBox.getValue();
    }
    @FXML
    private String getStratCombo() {
        return strategizingComboBox.getValue();
    }
    @FXML
    private String getConcentrationCombo() {
       return concentrationComboBox.getValue();
    }
    @FXML
    private String getMultiTaskingCombo() {
        return multiTaskingComboBox.getValue();
    }

    @FXML
    public void switchToLoggedInOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Scene window = new Scene(fxmlLoader.load(),851,638);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

}
