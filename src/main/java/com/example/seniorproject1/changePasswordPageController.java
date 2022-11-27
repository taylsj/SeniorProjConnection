package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class changePasswordPageController {


    @FXML
    private Label userNamePasswordLabel;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button favoriteGamesButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Label passwordUpdatedLabel;

    @FXML
    private Button platformButton;

    @FXML
    private Button ratingsButton;

    @FXML
    private Button savePasswordButton;

    @FXML
    private Label userNameLabel;


    private UserProfileModel user = UserProfileModel.getInstance();


    @FXML
    public void initialize() {
        userNamePasswordLabel.setText(user.getUserName());
        //dc.getConnection();
        //String temp = dc.getAboutMe(user.getUserName());
        //aboutMeTextArea.setText(temp);
    }

    @FXML
    void changePasswordButtonOnAction(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void ratingButtonOnAction(ActionEvent event) {

    }

    @FXML
    void savePasswordButtonOnAction(ActionEvent event) {

    }

    @FXML
    void switchToLoggedInOnAction(ActionEvent event) {

    }

}
